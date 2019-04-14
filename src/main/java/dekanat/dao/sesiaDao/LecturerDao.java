package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Lecturer;
import dekanat.mapper.sesiaMapper.LecturerMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class LecturerDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public LecturerDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Lecturer> getAllLecturers(){

        String sql = "SELECT * FROM Lecturers";
        RowMapper<Lecturer> rowMapper = new LecturerMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

}
