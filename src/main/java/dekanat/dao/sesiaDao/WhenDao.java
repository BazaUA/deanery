package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.When;
import dekanat.mapper.sesiaMapper.WhenMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class WhenDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public WhenDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<When> getAllWhen(){

        String sql = "SELECT * FROM `When`";
        RowMapper<When> rowMapper = new WhenMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

}
