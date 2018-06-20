package dao;

import models.Keyword;

import java.util.List;

public interface KeywordDao {
    //Create
    void add(Keyword keyword);

    //Read
    List<Keyword> getAll();
//    Keyword findbyId(int id);

    //Delete
//    void deleteById(int id);
    void clearAllKeywords();
}
