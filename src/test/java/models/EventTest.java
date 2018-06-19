package models;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void eventObjectInstantiatesCorrectly() throws Exception {
        Event event = setNewEvent();
        assertTrue(event instanceof Event);
    }

    @Test
    public void getMeetUpApiId_returnsIDFromMeetUpApi() throws Exception {
        Event event = setNewEvent();
        assertEquals(1, event.getMeetUpApiId());
    }

    @Test
    public void getId_returnsPdxMeetUpDbId() throws Exception {
        Event event = setNewEvent();
        event.setId(1);
        assertEquals(1, event.getId());
    }

    //Helpers
    public Event setNewEvent() {
        return new Event(1);
    }
}