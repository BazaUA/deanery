package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Student;
import dekanat.mapper.sesiaMapper.StudentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public StudentDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public List<Student> getAllWhen(){

        String sql = "SELECT * FROM `Student`";
        RowMapper<Student> rowMapper = new StudentMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public int addStudent(Student studentEntity) {
        String sql = "INSERT INTO student (lastname, major, start_education,end_education,reason_to_end_education,total_credits) " +
                "VALUES (?,?,?,?,?,?)";

        jdbcTemplate.update(sql, studentEntity.getLastname(), studentEntity.getMajor(), studentEntity.getStartEducation(), studentEntity.getEndEducation(), studentEntity.getReasonToEndEducation(), studentEntity.getTotalCredits());
        return getByName(studentEntity.getLastname()).getId();
    }

    public void updateStudent(Student c) {
        String sql = "UPDATE student " +
                "SET lastname = ?, major = ?, start_education = ?, end_education = ?, " +
                "reason_to_end_education = ?,total_credits= ?" +
                "WHERE student.id = ? ";
        jdbcTemplate.update(sql, c.getLastname(), c.getMajor(), c.getStartEducation(), c.getEndEducation(), c.getReasonToEndEducation(), c.getTotalCredits(), c.getId());
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE student.id = ? ";
        jdbcTemplate.update(sql, id);
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        RowMapper<Student> rowMapper = new dekanat.mapper.StudentMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public Student getById(int id) {
        String sql = "SELECT * FROM student WHERE student.id = ?";
        RowMapper<Student> rowMapper = new dekanat.mapper.StudentMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);

    }

    public Student getByName(String name) {
        String sql = "SELECT * FROM student WHERE student.lastname = ?";
        RowMapper<Student> rowMapper = new dekanat.mapper.StudentMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, name);

    }

}
