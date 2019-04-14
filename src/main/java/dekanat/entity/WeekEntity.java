package dekanat.entity;

import java.io.Serializable;
import java.util.Date;

public class WeekEntity implements Serializable {

    private int id;
    private int number;
    private Date start_date;
    private Date end_date;
    private int semester_id;
    private String semestr;

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(int semester_id) {
        this.semester_id = semester_id;
    }

    @Override
    public String toString() {
        return "WeekEntity{" +
                "id=" + id +
                ", number=" + number +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", semester_id=" + semester_id +
                '}';
    }
}


