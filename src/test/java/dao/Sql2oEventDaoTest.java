package dao;

import models.Event;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEventDaoTest {
    private static Sql2oEventDao eventDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pdxmeetupsdb_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        eventDao = new Sql2oEventDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        eventDao.clearAllEvents();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() throws Exception{
        Event event = setNewEvent();
        assertNotEquals(0, event.getId());
    }

    //Helpers
    public Event setNewEvent() {
        Event event =  new Event(1);
        eventDao.add(event);
        return event;
    }
}