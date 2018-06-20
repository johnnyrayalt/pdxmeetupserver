package dao;


import models.Keyword;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.security.Key;

import static org.junit.Assert.*;

public class Sql2oKeywordDaoTest {
    private static Sql2oKeywordDao keywordDao;
    private static Connection conn;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/pdxmeetupsdb_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        keywordDao = new Sql2oKeywordDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        keywordDao.clearAllKeywords();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_addsKeywordWithCorrectId() throws Exception {
        Keyword keyword = setNewKeyword();
        assertNotEquals(0, keyword.getId());
    }

    @Test
    public void addingKeywordSetsId() throws Exception {
        Keyword testKeyword = new Keyword("java");
        int originalKeywordId = testKeyword.getId();
        keywordDao.add(testKeyword);
        assertNotEquals(originalKeywordId, testKeyword.getId());
    }

    public Keyword setNewKeyword() {
        Keyword keyword = new Keyword("java");
        keywordDao.add(keyword);
        return keyword;
    }
}