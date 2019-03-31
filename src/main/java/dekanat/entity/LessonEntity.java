package dekanat.entity;

import java.io.Serializable;

public class LessonEntity implements Serializable {
    private int id;
    private String type;
    private int lecturerId;
    private int lecturerCathedraId;
    private int semesterId;
    private int courseId;
    private int courseCathedraId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getLecturerCathedraId() {
        return lecturerCathedraId;
    }

    public void setLecturerCathedraId(int lecturerCathedraId) {
        this.lecturerCathedraId = lecturerCathedraId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseCathedraId() {
        return courseCathedraId;
    }

    public void setCourseCathedraId(int courseCathedraId) {
        this.courseCathedraId = courseCathedraId;
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", lecturerId=" + lecturerId +
                ", lecturerCathedraId=" + lecturerCathedraId +
                ", semesterId=" + semesterId +
                ", courseId=" + courseId +
                ", courseCathedraId=" + courseCathedraId +
                '}';
    }
}
