package dekanat.entity.sesiaEntity;

import java.util.Date;

public class Student {

    private int id;

    private String lastname;

    private String major;

    private Date startEducation;

    private Date endEducation;

    private String reasonToEndEducation;

    private int totalCredits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getStartEducation() {
        return startEducation;
    }

    public void setStartEducation(Date startEducation) {
        this.startEducation = startEducation;
    }

    public Date getEndEducation() {
        return endEducation;
    }

    public void setEndEducation(Date endEducation) {
        this.endEducation = endEducation;
    }

    public String getReasonToEndEducation(String string) {
        return reasonToEndEducation;
    }

    public void setReasonToEndEducation(String reasonToEndEducation) {
        this.reasonToEndEducation = reasonToEndEducation;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    @Override
    public String toString() {
        return  lastname;
    }
}
