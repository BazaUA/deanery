package dekanat.service;

import dekanat.dao.CourseDAO;
import dekanat.entity.CourseEntity;
import dekanat.entity.CourseHasPrerequisiteCourseEntity;
import dekanat.entity.Sesia;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        courseDAO = new CourseDAO();
    }

    public CourseEntity getById(int id) {
        return courseDAO.getById(id);
    }

    public List<CourseEntity> getAll() {
        return courseDAO.getAllCourses();
    }

    public void deleteById(int id) {
        courseDAO.deleteCourse(id);
    }

    public void update(CourseEntity course) {
        courseDAO.updateCourse(course);
    }

    public List<CourseEntity> getAllPrerequisiteCourses(int currentCourseId) {
        return courseDAO.getAllPrerequisiteCourses(currentCourseId);
    }

    public int add(CourseEntity courseEntity) {
        return courseDAO.addCourse(courseEntity);
    }

    public void saveCourseHasPrerequisiteCourse(CourseHasPrerequisiteCourseEntity courseHasPrerequisiteCourseEntity) {
        courseDAO.saveCourseHasPrerequisiteCourse(courseHasPrerequisiteCourseEntity);
    }

    public void deleteAllPrerequisiteCourseById(int currentCourseId) {
        courseDAO.deleteAllPrerequisiteCourseById(currentCourseId);
    }

    public List<CourseEntity> getCoursesByCathedra(int id) {
        return courseDAO.getCoursesByCathedra(id);
    }

}
