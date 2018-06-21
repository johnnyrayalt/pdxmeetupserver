package models;

import java.util.Objects;

public class Event {
    private String meetUpApiId;
    private int userId;
    private int id;

    public Event(String meetUpApiId) {
        this.meetUpApiId = meetUpApiId;
    }

    public String getmeetUpApiId() { return meetUpApiId; }
    public void setmeetUpApiId(String meetUpApiId) { this.meetUpApiId = meetUpApiId; }

    public int getuserId() { return userId; }
    public void setuserId(int userId) { this.userId = userId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return userId == event.userId &&
                id == event.id &&
                Objects.equals(meetUpApiId, event.meetUpApiId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(meetUpApiId, userId, id);
    }
}
