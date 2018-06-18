package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao;
    private static Connection conn;

    @BeforeClass
    public void setUp() throws Exception {
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



}