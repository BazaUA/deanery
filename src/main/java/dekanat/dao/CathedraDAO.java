package dekanat.dao;

import dekanat.entity.CathedraEntity;
import dekanat.mapper.CathedraMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CathedraDAO {
    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CathedraDAO() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    public List<CathedraEntity> getAll() {

        String sql = "SELECT * FROM cathedras";
        RowMapper<CathedraEntity> rowMapper = new CathedraMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public CathedraEntity getById(int id) {
        String sql = "SELECT * FROM cathedras WHERE cathedras.id = ?";
        RowMapper<CathedraEntity> rowMapper = new CathedraMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
