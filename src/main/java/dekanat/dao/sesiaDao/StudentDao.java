package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Student;
import dekanat.mapper.sesiaMapper.StudentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class StudentDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public StudentDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> getAllWhen(){

        String sql = "SELECT * FROM `Student`";
        RowMapper<Student> rowMapper = new StudentMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

}
