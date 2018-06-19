package dao;

import models.Event;
import models.User;

import java.util.List;

public interface UserDao {

    //Create
    void add(User user);

    //Read
    List<User> getAll();
    User findById(int id);
    List<Event> getAllEventsByUser(int userId);

    //Update
    void update(int id, String newName, String newPhoto);

    //Delete
    void deleteById(int id);
    void clearAllUsers();
}
