package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
    @Test
    public void eventClassInstantiatesCorrectly() throws Exception{
        Event event = setNewEvent();
        assertTrue(event instanceof Event);
    }

    @Test
    public void getIdOfEventFromMeetUpApi() {
        Event event = setNewEvent();
        assertEquals(1, event.getIdOfEventFromMeetUpApi());
    }

    @Test
    public void getId() {
        Event event = setNewEvent();
        event.setId(1);
        assertEquals(1, event.getId());
    }

    //Helpers
    public Event setNewEvent() {
        return new Event(1);
    }
}