package models;

import java.util.Objects;

public class Keyword {
    private String keyword;
    private int id;


    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword1 = (Keyword) o;
        return id == keyword1.id &&
                Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(keyword, id);
    }
}
