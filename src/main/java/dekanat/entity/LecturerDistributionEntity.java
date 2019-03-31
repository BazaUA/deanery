package dekanat.entity;

public class LecturerDistributionEntity {
    private String lecturerName;
    private String typeOfLesson;
    private String courseName;
    private String semester;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getTypeOfLesson() {
        return typeOfLesson;
    }

    public void setTypeOfLesson(String typeOfLesson) {
        this.typeOfLesson = typeOfLesson;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String  toString() {
        return
                "Імя лектора = " + lecturerName +
                ", Тип заняття = " + typeOfLesson +
                ", Назва курсу = " + courseName +
                ", Семестр = " + semester ;
    }
}
