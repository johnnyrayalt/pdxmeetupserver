package dao;

import models.User;

import java.util.List;

public interface UserDao {

    //Create
    void add(User user);
    //

    //Read
    List<User> getAll();
    User findById(int id);

    //Update
    void update(int id, String newName, String newPhoto);

    //Delete
    void deleteById(int id);
    void clearAllUsers();
}
