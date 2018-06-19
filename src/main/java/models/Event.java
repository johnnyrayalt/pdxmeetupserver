package models;

import java.util.Objects;

public class Event {
    private int idOfEventFromMeetUpApi;
    private int id;

    public Event(int idOfEventFromMeetUpApi) {
        this.idOfEventFromMeetUpApi = idOfEventFromMeetUpApi;
    }

    // ID of Event from the Meet Ups API
    public int getIdOfEventFromMeetUpApi() { return idOfEventFromMeetUpApi; }
    public void setIdOfEventFromMeetUpApi(int idOfEventFromMeetUpApi) { this.idOfEventFromMeetUpApi = idOfEventFromMeetUpApi; }

    // ID in pdxmeetups DB
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return idOfEventFromMeetUpApi == event.idOfEventFromMeetUpApi &&
                id == event.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOfEventFromMeetUpApi, id);
    }
}
