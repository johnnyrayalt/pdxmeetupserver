package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getName() {
        User user = setNewUser();
        user.setName("Jim");
        assertEquals("Jim", user.getName());
    }

    @Test
    public void getPhoto() {
    }

    @Test
    public void getId() {
    }

    //Helpers
    public User setNewUser() {
        return new User("John", "N/A");
    }
}