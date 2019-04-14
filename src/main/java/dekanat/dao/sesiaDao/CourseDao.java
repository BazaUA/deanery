package dekanat.dao.sesiaDao;


import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Course;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CourseDao {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CourseDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Course> getAllCourses(){
        return null;
    }


}
