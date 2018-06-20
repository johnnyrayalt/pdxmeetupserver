package dao;

import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao;
    private static Sql2oEventDao eventDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pdxmeetupsdb_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        userDao = new Sql2oUserDao(sql2o);
        eventDao = new Sql2oEventDao(sql2o);
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
    public void findById_returnsCorrectUser() throws Exception {
        User user = setNewUser();
        User user1 = setNewUser();
        User userToFindById = userDao.findById(user1.getId());
        assertEquals(user1, userToFindById);
    }

    @Test
    public void update_updatesUsersNameAndOrPhotoCorrectly() throws Exception {
        User user = setNewUser();
        userDao.update(user.getId(), "Jim Doe", "Test");
        assertEquals("Jim Doe", userDao.findById(user.getId()).getName());
    }

    @Test
    public void deleteById_deletesUserByTheirId() throws Exception {
        User user = setNewUser();
        User user1 = setNewUser();
        userDao.deleteById(user.getId());
        assertEquals(1, userDao.getAll().size());
    }

    //Helpers
    public User setNewUser() {
        User user = new User("John Doe", "N/A");
        userDao.add(user);
        return user;
    }

}