package dekanat.entity.sesiaEntity;

import java.util.Date;

public class Sesia {

    private Date date;
    private String courseName;
    private String cafedraName;
    private String lecturerName;
    private String courseSummarize;
    private int groupNumber;
    private int auditoriaNumber;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCafedraName() {
        return cafedraName;
    }

    public void setCafedraName(String cafedraName) {
        this.cafedraName = cafedraName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCourseSummarize() {
        return courseSummarize;
    }

    public void setCourseSummarize(String courseSummarize) {
        this.courseSummarize = courseSummarize;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getAuditoriaNumber() {
        return auditoriaNumber;
    }

    public void setAuditoriaNumber(int auditoriaNumber) {
        this.auditoriaNumber = auditoriaNumber;
    }

    @Override
    public String toString() {
        return "Sesia{" +
                "date=" + date +
                ", courseName='" + courseName + '\'' +
                ", cafedraName='" + cafedraName + '\'' +
                ", lecturerName='" + lecturerName + '\'' +
                ", courseSummarize='" + courseSummarize + '\'' +
                ", groupNumber=" + groupNumber +
                ", auditoriaNumber=" + auditoriaNumber +
                '}';
    }
}
