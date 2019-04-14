package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Results;
import dekanat.mapper.sesiaMapper.ResultsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class ResultsDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public ResultsDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Results> getResults(){
        String sql = "SELECT Student.lastname, Courses.name,Cathedras.name, Lecturers.name,Courses.summarize,Groups.group_no, dekanat.StudentsResults.grade, " +
                "StudentsResults.id, StudentsResults.group_id, StudentsResults.student_id,StudentsResults.id " +
                "From (((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id) inner join Lecturers on Lecturers.cathedra_id = Cathedras.id) " +
                "inner join Groups on Groups.course_id = Courses.id) inner join GroupToStudent on GroupToStudent.group_id = Groups.id) " +
                "inner join Student on Student.id = GroupToStudent.student_id) inner join dekanat.StudentsResults on  dekanat.StudentsResults.student_id = Student.id;";
        RowMapper<Results> rowMapper = new ResultsMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public void deleteResult(int id) {
        String sql = "DELETE FROM StudentsResults WHERE id = ? ";
        jdbcTemplate.update(sql, id);
    }

    public void updateResult(String grade,int id){
        String sql = "UPDATE StudentsResults Set dekanat.StudentsResults.grade = ? WHERE dekanat.StudentsResults.id = ?; ";
        jdbcTemplate.update(sql, grade,id);
    }

    public void createResults(Results result){
        String sql = "INSERT into StudentsResults (grade, student_id, group_id) VALUES (?,?,?) ";
        jdbcTemplate.update(sql,result.getGrade(),result.getStudentId(),result.getGroupId());

    }





}
