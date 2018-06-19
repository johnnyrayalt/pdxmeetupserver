import com.google.gson.Gson;
import dao.Sql2oUserDao;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import exceptions.ApiException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static boolean isProduction = false;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            isProduction = true;
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
        Sql2o sql2o;
        if(isProduction) {
            //Heroku Credentials
            String connectionString = "jdbc:postgresql://uvwijeyphjojeu:0c4765d4acb8fc63391654eee96e7470e53747450fae08f30e91412c012a577f@ec2-23-21-238-28.compute-1.amazonaws.com:5432/d32rom86rntfh9";
            sql2o = new Sql2o(connectionString, "uvwijeyphjojeu", "0c4765d4acb8fc63391654eee96e7470e53747450fae08f30e91412c012a577f");
        } else {
            //Local PostgresDB for Test Environment
            String connectionString = "postgresql://localhost:5432/pdxmeetups";
            sql2o = new Sql2o(connectionString, null, null);
        }

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
            User userToFind = userDao.findById(userId);

            if (userToFind == null) {
                throw new ApiException(404, String.format("No users with the id: \"%s\" exists.", request.params("id")));
            }

            return gson.toJson(userToFind);
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

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }
            return "OK";
        });

        //FILTERS
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
