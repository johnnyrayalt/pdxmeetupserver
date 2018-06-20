package dao;

import models.Keyword;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oKeywordDao implements KeywordDao {
    private final Sql2o sql2o;

    public Sql2oKeywordDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Keyword keyword) {
        String sql = "INSERT INTO keywords (keyword) VALUES (:keyword)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(keyword)
                    .executeUpdate()
                    .getKey();
            keyword.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Keyword> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM keywords")
                    .executeAndFetch(Keyword.class);
        }
    }

    @Override
    public Keyword findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM keywords WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Keyword.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM keywords WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllKeywords() {
        String sql = "DELETE FROM keywords";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
