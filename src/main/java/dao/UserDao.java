package dao;

import models.User;

import java.util.List;

public interface UserDao {

    //Create
    void add(User user);

    //Read
    List<User> getAll();

    //Update


    //Delete
    void clearAllUsers();
}
