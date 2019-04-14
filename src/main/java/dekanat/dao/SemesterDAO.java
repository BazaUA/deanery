package dekanat.dao;

import dekanat.entity.LecturerDistributionEntity;
import dekanat.entity.SemesterEntity;
import dekanat.mapper.LecturerDistrMapper;
import dekanat.mapper.SemesterMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SemesterDAO {
    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public SemesterDAO() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    public List<SemesterEntity> getAll() {

        String sql = "SELECT * FROM semesters";
        RowMapper<SemesterEntity> rowMapper = new SemesterMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public SemesterEntity getById(int id) {
        String sql = "SELECT * FROM semesters WHERE semesters.id = ?";
        RowMapper<SemesterEntity> rowMapper = new SemesterMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<LecturerDistributionEntity> getDistrib(int courseId, int semesterId) {

        String sql = "SELECT DISTINCT c.name, l.name, s.semester, lesso.type FROM lecturers as l " +
                "LEFT JOIN lessons as lesso on lesso.lecturer_id = l.id " +
                "LEFT JOIN courses as c on lesso.course_id=c.id " +
                "LEFT JOIN semesters as s on lesso.semester_id = s.id " +
                "WHERE c.id = ? AND s.id =? ";
        RowMapper<LecturerDistributionEntity> rowMapper = new LecturerDistrMapper();
        return this.jdbcTemplate.query(sql, rowMapper, courseId, semesterId);

    }


    public SemesterEntity getByName(String semester) {
        String sql = "SELECT * FROM semesters WHERE semesters.semester = ?";
        RowMapper<SemesterEntity> rowMapper = new SemesterMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper,semester);

    }

    public int addSemestr(SemesterEntity c) {
        String sql = "INSERT INTO semesters (year, semester) " +
                "VALUES (?,?)";

        jdbcTemplate.update(sql, c.getYear(), c.getSemester());
        return getByName(c.getSemester()).getId();
    }
    public void deleteById(int id) {
        String sql1 = "DELETE FROM Semesters WHERE Semesters.id = ? ";
        jdbcTemplate.update(sql1, id);
    }

}
