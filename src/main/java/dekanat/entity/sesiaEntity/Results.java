package dekanat.entity.sesiaEntity;

public class Results {


    private int resultId;

    private int studentId;

    private String studentsName;

    private String courseName;

    private String cafedraName;

    private String lecturerName;

    private String summarizeName;

    private int groupId;

    private int groupNumber;

    private int gradeId;

    private String grade;

    public int getStudentId() {
        return studentId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentsName() {
        return studentsName;
    }

    public void setStudentsName(String studentsName) {
        this.studentsName = studentsName;
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

    public String getSummarizeName() {
        return summarizeName;
    }

    public void setSummarizeName(String summarizeName) {
        this.summarizeName = summarizeName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Results{" +
                "studentsName='" + studentsName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", cafedraName='" + cafedraName + '\'' +
                ", lecturerName='" + lecturerName + '\'' +
                ", summarizeName='" + summarizeName + '\'' +
                ", groupNumber=" + groupNumber +
                ", grade='" + grade + '\'' +
                ", gradeId='" + gradeId + '\'' +
                '}';
    }
}
