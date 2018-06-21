import com.google.gson.Gson;
import dao.Sql2oEventDao;
import dao.Sql2oUserDao;
import dao.Sql2oKeywordDao;
import models.Event;
import models.User;
import models.Keyword;
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
        Sql2oEventDao eventDao;
        Sql2oKeywordDao keywordDao;
        Connection conn;
        Gson gson = new Gson();
        Sql2o sql2o;
        if(isProduction) {
            //Heroku Credentials
            String connectionString = "jdbc:postgresql://@ec2-54-163-246-193.compute-1.amazonaws.com:5432/d48d2flegfc09c";
            sql2o = new Sql2o(connectionString, "agobhmmiosahjr", "648fede5267ad1702584a4a5b31c70c6ff177cb84654ff8275296050d6d3b207");
        } else {
            //Local PostgresDB for Test Environment
            String connectionString = "postgresql://localhost:5432/pdxmeetupsdb";
            sql2o = new Sql2o(connectionString, null, null);
        }

        eventDao = new Sql2oEventDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        keywordDao = new Sql2oKeywordDao(sql2o);
        conn = sql2o.open();

//              \\
//              \\
//  ADD A USER  \\
//              \\
//              \\
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

//                \\
//                \\
//  ADD AN EVENT  \\
//                \\
//                \\

        // Return all events by user ID
        get("/api/users/:id/events", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            User userToFind = userDao.findById(userId);
            List<Event> allEvents;
            if (userToFind == null) {
                throw new ApiException(404, String.format("No Users with the id: \"%s\" exists", request.params("id")));
            }

            allEvents = eventDao.getAllEventsByUser(userId);
            return gson.toJson(allEvents);
        });

        post("/api/users/:id/events/new", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            Event event = gson.fromJson(request.body(), Event.class);
            event.setuserId(userId);
            eventDao.add(event);
            response.status(201);
            return gson.toJson(event);
        });

//                 \\
//                 \\
//  ADD A KEYWORD  \\
//                 \\
//                 \\

        // Add a new Keyword
        post("/api/keywords/new", "application/json", (request, response) -> {
            Keyword keyword = gson.fromJson(request.body(), Keyword.class);
            keywordDao.add(keyword);
            response.status(201);
            return gson.toJson(keyword);
        });

        // Returns all Keywords
        get("/api/keywords", "application/json", (request, response) -> {
            return gson.toJson(keywordDao.getAll());
        });

        // Returns Keyword by ID
        get("/api/keywords/:id", "application/json", (request, response) -> {
            int keywordId = Integer.parseInt(request.params("id"));
            Keyword keywordToFind = keywordDao.findById(keywordId);

            if (keywordToFind == null) {
                throw new ApiException(404, String.format("No keyword with the id: \"%s\" exists.", request.params("id")));
            }

            return gson.toJson(keywordToFind);
        });

        // Delete keyword
        post("/api/keywords/:id/delete", "application/json", (request, response) -> {
            int keywordId = Integer.parseInt(request.params("id"));
            keywordDao.deleteById(keywordId);
            return gson.toJson(keywordDao.getAll());
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
