import com.google.gson.Gson;
import dao.Sql2oUserDao;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        String connectionString = "postgresql://localhost:5432/pdxmeetups";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();


        // Add a new User
        post("/api/users/new", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        // Get all Users
        get("/api/users", "application/json", (request, response) -> {
            return gson.toJson(userDao.getAll());
        });

        // Get individual User
        get("/api/users/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            return gson.toJson(userDao.findById(userId));
        });


        // Post User update
        post("/api/users/:id/update", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            User updatedUser = gson.fromJson(request.body(), User.class);
            userDao.update(userId, updatedUser.getName(), updatedUser.getPhoto());
            return gson.toJson(userDao.findById(userId));
        });

        // Delete user
        post("/api/users/:id/delete", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            userDao.deleteById(userId);
            return gson.toJson(userDao.getAll());
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });
    }
}
