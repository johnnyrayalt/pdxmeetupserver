package dao;

import models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pdxmeetups_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        userDao.clearAllUsers();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_addsUserWithCorrectId() throws Exception {
        User user = setNewUser();
        userDao.add(user);
        assertNotEquals(0, user.getId());
    }

    @Test
    public void getAll_returnsAllUsers() throws Exception {
        User user = setNewUser();
        User user1 = setNewUser();
        assertEquals(2, userDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectUser() {
        User user = setNewUser();
        User user1 = setNewUser();
        User userToFindById = userDao.findById(user1.getId());
        assertEquals(user1, userToFindById);
    }

    //Helpers
    public User setNewUser() {
        User user = new User("John Doe", "N/A");
        userDao.add(user);
        return user;
    }

}