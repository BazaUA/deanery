package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Cafedra;
import dekanat.mapper.sesiaMapper.CafedraMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class CafedraDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CafedraDao() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Cafedra> getAllCafedras() {

        String sql = "SELECT * FROM Cathedras";
        RowMapper<Cafedra> rowMapper = new CafedraMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}
