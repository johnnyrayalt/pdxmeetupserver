package dao;

import models.User;

import java.util.List;

public interface UserDao {

    //Create
    void add(User user);

    //Read
    List<User> getAll();
    User findById(int id);

    //Update

    //Delete
    void clearAllUsers();
}
