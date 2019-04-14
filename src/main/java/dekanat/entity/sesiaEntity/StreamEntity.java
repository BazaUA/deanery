package dekanat.entity.sesiaEntity;

import java.io.Serializable;

public class StreamEntity implements Serializable {

    private int id;
    private String name;
    private int lesson_id;


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

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    @Override
    public String toString() {
        return "StreamEntity{" +
                "id=" + id +
                ", name=" + name +
                ", lesson_id=" + lesson_id +
                '}';
    }
}