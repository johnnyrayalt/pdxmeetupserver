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

    @Test
    public void getAll_returnsAllKeywords() throws Exception {
        Keyword keyword = setNewKeyword();
        Keyword keyword1 = setNewKeyword();
        assertNotEquals(null, keywordDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectKeyword() throws Exception {
        Keyword keyword = setNewKeyword();
        Keyword keyword1 = setNewKeyword();
        Keyword keywordToFindById = keywordDao.findById(keyword1.getId());
        assertEquals(keyword1, keywordToFindById);
    }

    @Test
    public void deleteById_deletesKeywordByTheirId() throws Exception {
        Keyword keyword = setNewKeyword();
        Keyword keyword1 = new Keyword("javascript");
        keywordDao.add(keyword1);
        keywordDao.deleteById(keyword.getId());
        assertEquals(1, keywordDao.getAll().size());
    }

    public Keyword setNewKeyword() {
        Keyword keyword = new Keyword("java");
        keywordDao.add(keyword);
        return keyword;
    }
}