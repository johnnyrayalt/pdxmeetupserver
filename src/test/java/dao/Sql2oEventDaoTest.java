package dao;

import models.Event;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEventDaoTest {
    private static Sql2oEventDao eventDao;
    private static Sql2oUserDao userDao;
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
        eventDao.clearAllEvents();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_addsEvent() throws Exception{
        Event event = setNewEvent();
        assertNotEquals(0, event.getId());
    }

    @Test
    public void addingEventSetsId() throws Exception {
        Event testEvent = new Event("1");
        int originalEventId = testEvent.getId();
        eventDao.add(testEvent);
        assertNotEquals(originalEventId, testEvent.getId());
    }

    @Test
    public void getAll_returnsAllEvents() throws Exception {
        Event event = setNewEvent();
        Event event1 = setNewEvent();
        assertNotEquals(null, eventDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectEvent() throws Exception {
        Event event = setNewEvent();
        Event event1 = setNewEvent();
        Event eventToFindById = eventDao.findById(event1.getId());
        assertEquals(event1, eventToFindById);
    }

    @Test
    public void deleteById_deletesEventByTheirId() throws Exception {
        Event event = setNewEvent();
        Event event1 = new Event("2");
        eventDao.add(event1);
        eventDao.deleteById(event.getId());
        assertEquals(1, eventDao.getAll().size());
    }

    @Test
    public void userIdIsReturnedCorrectly() throws Exception {
        Event event =  new Event("1");
        int originalEventId = event.getuserId();
        eventDao.add(event);
        assertEquals(originalEventId, eventDao.findById(event.getId()).getuserId());
    }

    @Test
    public void getAllEventsByUser_ReturnsAllEventsByUserId() throws Exception {
        User user = new User("John Doe", "N/A");
        userDao.add(user);
        user.setId(2);
        int userId = user.getId();
        Event newEvent = new Event("1");
        newEvent.setuserId(2);
        Event newEvent1 = new Event("2");
        newEvent1.setuserId(2);
        Event newEvent2 = new Event("3");
        newEvent2.setuserId(2);
        eventDao.add(newEvent);
        eventDao.add(newEvent1);
        assertEquals(2, eventDao.getAllEventsByUser(userId).size());
        assertFalse(eventDao.getAllEventsByUser(userId).contains(newEvent2));
    }

    public Event setNewEvent() {
        Event event =  new Event("1");
        event.setId(2);
        eventDao.add(event);
        return event;
    }
}