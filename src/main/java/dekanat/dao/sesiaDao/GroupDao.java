package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Group;
import dekanat.entity.sesiaEntity.StreamEntity;
import dekanat.mapper.StreamMapper;
import dekanat.mapper.sesiaMapper.GroupMapper;
import dekanat.mapper.sesiaMapper.GroupPairMapper;
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

        String sql = "SELECT DISTINCT Courses.name, `Groups`.group_no,`Groups`.id,`Groups`.stream_id From `Groups` inner join Courses on `Groups`.course_id = Courses.id";
        RowMapper<Group> rowMapper = new GroupMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public List<Group> getAllGroups(){

        String sql = "SELECT * from GroupPairs";
        RowMapper<Group> rowMapper = new GroupPairMapper();
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

    public Group getByName(String course_name, int group_number,String stream_name) {
        String sql = "SELECT * FROM GroupPairs WHERE GroupPairs.course_name = ? and GroupPairs.group_number = ? and GroupPairs.stream_name = ?";
        RowMapper<Group> rowMapper = new GroupPairMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper,course_name,group_number,stream_name);

    }

    public int addGroup(Group g) {
        String sql = "INSERT INTO GroupPairs (course_name,group_number,stream_name) " +
                "VALUES (?,?,?)";

        jdbcTemplate.update(sql, g.getCourseName(),g.getGroupNumber(),g.getStream_name());
        return getByName(g.getCourseName(),g.getGroupNumber(),g.getStream_name()).getId();
    }

    public void deleteById(int id) {
        String sql1 = "DELETE FROM GroupPairs WHERE GroupPairs.id = ? ";
        jdbcTemplate.update(sql1, id);
    }
}
