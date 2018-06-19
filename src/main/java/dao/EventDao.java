package dao;

import models.Event;

import java.util.List;

public interface EventDao {

    //Create
    void add(Event event);

    //Read
    List<Event> getAll();
    Event findById(int id);

    //Delete
    void deleteById(int id);
    void clearAllEvents();
}
