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
        User user = setNewUser();
        user.setPhoto("http://knowyourmeme.com/memes/woll-smoth");
        assertEquals("http://knowyourmeme.com/memes/woll-smoth", user.getPhoto());
    }

    @Test
    public void getId() {
        User user = setNewUser();
        user.setId(1);
        assertEquals(1, user.getId());
    }


    //Helpers
    public User setNewUser() {
        return new User("John", "N/A");
    }
}