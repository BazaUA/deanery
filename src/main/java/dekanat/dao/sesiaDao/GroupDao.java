package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Group;
import dekanat.mapper.sesiaMapper.GroupMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class GroupDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public GroupDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Group> getAllGroupPair(){

        String sql = "SELECT DISTINCT Courses.name, `Groups`.group_no,`Groups`.id From `Groups` inner join Courses on `Groups`.course_id = Courses.id";
        RowMapper<Group> rowMapper = new GroupMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }


    public List<Group> getGroupByStudent(int stId) {
        String sql = "SELECT DISTINCT Courses.name, `Groups`.group_no,`Groups`.id " +
                "From (`Groups` inner join Courses on `Groups`.course_id = Courses.id) " +
                "inner join GroupToStudent on GroupToStudent.group_id = `Groups`.id " +
                "WHERE student_id = ? ";
        RowMapper<Group> rowMapper = new GroupMapper();
        return this.jdbcTemplate.query(sql, rowMapper,stId);
    }
}
