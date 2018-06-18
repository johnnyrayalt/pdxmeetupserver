import models.User;
import dao.Sql2oUserDao;
import com.google.gson.Gson;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        //Local PostgresDB for Test Environment
//        String connectionString = "postgresql://localhost:5432/pdxmeetups";

        //Heroku Credentials
        String connectionString = "postgresql://uvwijeyphjojeu:0c4765d4acb8fc63391654eee96e7470e53747450fae08f30e91412c012a577f@ec2-23-21-238-28.compute-1.amazonaws.com:5432/d32rom86rntfh9";

        Sql2o sql2o = new Sql2o(connectionString, "uvwijeyphjojeu", "0c4765d4acb8fc63391654eee96e7470e53747450fae08f30e91412c012a577f");
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
