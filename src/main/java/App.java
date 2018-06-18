import com.google.gson.Gson;
import dao.Sql2oUserDao;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

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


        post("/api/users/new", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });

        get("/api/users", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(userDao.getAll());
        });

        get("/api/users/:id", "application/json", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(userDao.findById(userId));
        });
    }
}
