package dekanat.dao;

import dekanat.entity.CourseEntity;
import dekanat.entity.CourseHasPrerequisiteCourseEntity;
import dekanat.mapper.CourseMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CourseDAO {
    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CourseDAO() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public int addCourse(CourseEntity c) {
        String sql = "INSERT INTO courses (name, number_of_lectures, number_of_practices, summarize, is_norrmative, number_of_credits, cathedra_id) " +
                "VALUES (?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, c.getName(), c.getNumberOfLectures(), c.getNumberOfPractices(), c.getSummarize(), c.isNormative(), c.getNumberOfCredits(), c.getCathedraId());
        return getByName(c.getName()).getId();
    }

    public void updateCourse(CourseEntity c) {
        String sql = "UPDATE courses " +
                "SET name = ?, number_of_lectures = ?, number_of_practices = ?, summarize = ?, " +
                "is_norrmative = ?,number_of_credits = ?,cathedra_id = ? " +
                "WHERE courses.id = ? ";
        jdbcTemplate.update(sql, c.getName(), c.getNumberOfLectures(), c.getNumberOfPractices(), c.getSummarize(), c.isNormative(), c.getNumberOfCredits(), c.getCathedraId(), c.getId());
    }

    public void deleteCourse(int id) {
        String sql1 = "DELETE FROM course_has_prerequisite_courses WHERE course_id = ? ";
        jdbcTemplate.update(sql1, id);

        String sql = "DELETE FROM courses WHERE courses.id = ? ";
        jdbcTemplate.update(sql, id);
    }

    public List<CourseEntity> getAllCourses() {

        String sql = "SELECT * FROM courses";
        RowMapper<CourseEntity> rowMapper = new CourseMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public CourseEntity getById(int id) {
        String sql = "SELECT * FROM `Courses` WHERE `Courses`.`id` = ?";
        RowMapper<CourseEntity> rowMapper = new CourseMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);

    }

    public CourseEntity getByName(String name) {
        String sql = "SELECT * FROM courses WHERE courses.name = ?";
        RowMapper<CourseEntity> rowMapper = new CourseMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, name);

    }

    public List<CourseEntity> getAllPrerequisiteCourses(int id) {
        String sql = "SELECT c.id, c.name, c.number_of_lectures, c.number_of_practices, " +
                "c.summarize, c.is_norrmative, c.number_of_credits, c.cathedra_id " +
                "FROM course_has_prerequisite_courses as chpc " +
                "left join courses as c on chpc.prerequisite_course_id = c.id " +
                "where chpc.course_id = ?";
        RowMapper<CourseEntity> rowMapper = new CourseMapper();
        return this.jdbcTemplate.query(sql, rowMapper, id);
    }


    public void saveCourseHasPrerequisiteCourse(CourseHasPrerequisiteCourseEntity c) {
        String sql = "INSERT course_has_prerequisite_courses (course_id, course_cathedra_id, prerequisite_course_id, prerequisite_course_cathedra_id) " +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, c.getCourseId(), c.getCourseCathedraId(), c.getPrerequisiteCourseId(), c.getPrerequisiteCourseCathedraId());
        return;
    }

    public void deleteAllPrerequisiteCourseById(int currentCourseId) {
        String sql = "DELETE FROM course_has_prerequisite_courses " +
                "WHERE course_id = ?";
        jdbcTemplate.update(sql, currentCourseId);
        return;
    }

    public List<CourseEntity> getCoursesByCathedra(int id) {
        String sql = "SELECT * FROM courses " +
                "WHERE courses.cathedra_id = ?";
        RowMapper<CourseEntity> rowMapper = new CourseMapper();
        return this.jdbcTemplate.query(sql, rowMapper, id);
    }
}
