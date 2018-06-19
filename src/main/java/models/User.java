package models;

import java.util.Objects;

public class User {
    private String name;
    private String photo;
    private int id;

    public User(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public User(String name, String photo, String eventIds) {
        this.name = name;
        this.photo = photo;
    }

    // NAME
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // PHOTO
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    // ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(photo, user.photo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, photo, id);
    }
}
