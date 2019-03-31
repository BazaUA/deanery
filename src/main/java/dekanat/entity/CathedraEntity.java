package dekanat.entity;

import java.io.Serializable;

public class CathedraEntity implements Serializable {
    private int id;
    private String name;
    private String hall;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @Override
    public String toString() {
        return name;
    }
}
