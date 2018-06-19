package models;

import java.util.List;
import java.util.Objects;

public class Event {
    private int meetUpApiId;
    private int id;

    public Event(int meetUpApiId) {
        this.meetUpApiId = meetUpApiId;
    }

    public int getMeetUpApiId() { return meetUpApiId; }
    public void setMeetUpApiId(int meetUpApiId) { this.meetUpApiId = meetUpApiId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return meetUpApiId == event.meetUpApiId &&
                id == event.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(meetUpApiId, id);
    }
}
