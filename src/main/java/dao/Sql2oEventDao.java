package dao;

import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oEventDao implements EventDao{
    private final Sql2o sql2o;

    public Sql2oEventDao(Sql2o sql2o) { this.sql2o = sql2o; }


    @Override
    public void add(Event event) {
        String sql = "INSERT INTO events(meetupapiid) VALUES (:meetUpApiId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(event)
                    .executeUpdate()
                    .getKey();
            event.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void clearAllEvents() {
        String sql = "DELETE FROM events";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
