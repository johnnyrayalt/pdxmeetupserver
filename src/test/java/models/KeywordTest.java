package models;

import org.junit.Test;

import java.security.Key;

import static org.junit.Assert.*;

public class KeywordTest {

    @Test
    public void keywordObjectInstantiatesCorrectly() throws Exception {
        Keyword keyword = setNewKeyword();
        assertTrue(keyword instanceof Keyword);
    }

    @Test
    public void getKeyword() {
        Keyword keyword = setNewKeyword();
        assertEquals("Java", keyword.getKeyword());
    }

    @Test
    public void getId() {
        Keyword keyword = setNewKeyword();
        keyword.setId(1);
        assertEquals(1, keyword.getId());
    }

    //Helpers
    public Keyword setNewKeyword() {
        return new Keyword("Java");
    }
}