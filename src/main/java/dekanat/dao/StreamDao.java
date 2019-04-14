package dekanat.dao;

import dekanat.entity.SemesterEntity;
import dekanat.entity.WeekEntity;
import dekanat.entity.sesiaEntity.StreamEntity;
import dekanat.mapper.SemesterMapper;
import dekanat.mapper.StreamMapper;
import dekanat.mapper.WeekMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StreamDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public StreamDao() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    public List<StreamEntity> getAll() {
        String sql = "SELECT * FROM Stream";
        RowMapper<StreamEntity> rowMapper = new StreamMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public StreamEntity getById(int id) {
        String sql = "SELECT * FROM Stream WHERE stream.id = ?";
        RowMapper<StreamEntity> rowMapper = new StreamMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public StreamEntity getByName(String name) {
        String sql = "SELECT * FROM Stream WHERE Stream.Name = ?";
        RowMapper<StreamEntity> rowMapper = new StreamMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper,name);

    }

    public int addStream(StreamEntity s) {
        String sql = "INSERT INTO Stream (name) " +
                "VALUES (?)";

        jdbcTemplate.update(sql, s.getName());
        return getByName(s.getName()).getId();
    }

    public void deleteById(int id) {
        String sql1 = "DELETE FROM Stream WHERE Stream.id = ? ";
        jdbcTemplate.update(sql1, id);
    }

}
