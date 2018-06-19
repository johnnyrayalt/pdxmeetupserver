package models;

import java.util.List;
import java.util.Objects;

public class Event {
    private List<Event> eventList;

    public Event(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Event> getEventList() { return eventList; }
    public void setEventList(List<Event> eventList) { this.eventList = eventList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventList, event.eventList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(eventList);
    }
}
