package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
    @Test
    public void eventClassInstantiatesCorrectly() throws Exception{
        Event event = setNewEvent();
        assertTrue(event instanceof Event);
    }

//    @Test
//    public void getIdOfEventFromMeetUpApi() {
//
//    }
//
//    @Test
//    public void setIdOfEventFromMeetUpApi() {
//    }
//
//    @Test
//    public void getId() {
//    }
//
//    @Test
//    public void setId() {
//    }

    //Helpers
    public Event setNewEvent() {
        return new Event(1);
    }
}