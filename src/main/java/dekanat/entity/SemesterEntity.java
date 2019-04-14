package dekanat.entity;

import java.io.Serializable;

public class SemesterEntity implements Serializable {

    private int id;
    private int year;
    private String semester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    @Override
    public String toString() {
        return semester + " - " + year;
    }
}
