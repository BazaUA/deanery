package dekanat.entity;

import java.io.Serializable;

public class CourseHasPrerequisiteCourseEntity implements Serializable {
    private int courseId;
    private int courseCathedraId;
    private int prerequisiteCourseId;
    private int prerequisiteCourseCathedraId;

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

    public int getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(int prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }

    public int getPrerequisiteCourseCathedraId() {
        return prerequisiteCourseCathedraId;
    }

    public void setPrerequisiteCourseCathedraId(int prerequisiteCourseCathedraId) {
        this.prerequisiteCourseCathedraId = prerequisiteCourseCathedraId;
    }

    @Override
    public String toString() {
        return "CourseHasPrerequisiteCourseEntity{" +
                "courseId=" + courseId +
                ", courseCathedraId=" + courseCathedraId +
                ", prerequisiteCourseId=" + prerequisiteCourseId +
                ", prerequisiteCourseCathedraId=" + prerequisiteCourseCathedraId +
                '}';
    }
}
