package dekanat.dao;

import dekanat.entity.Auditory;
import dekanat.entity.WeekEntity;
import dekanat.entity.sesiaEntity.StreamEntity;
import dekanat.mapper.AuditoryMapper;
import dekanat.mapper.StreamMapper;
import dekanat.mapper.WeekMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class AuditoryDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public AuditoryDao() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    public List<Auditory> getAll() {
        String sql = "SELECT * FROM Auditory";
        RowMapper<Auditory> rowMapper = new AuditoryMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public Auditory getByName(String number, int bulding) {
        String sql = "SELECT * FROM Auditory WHERE Auditory.num = ? and Auditory.building_name = ?";
        RowMapper<Auditory> rowMapper = new AuditoryMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper,number,bulding);

    }

    public int addAuditory(Auditory a) {
        String sql = "INSERT INTO Auditory (num,building_name) " +
                "VALUES (?,?)";

        jdbcTemplate.update(sql, a.getNumber(),a.getBuilding());
        return getByName(a.getNumber(),a.getBuilding()).getId();
    }

    public void deleteById(int id) {
        String sql1 = "DELETE FROM Auditory WHERE Auditory.id = ? ";
        jdbcTemplate.update(sql1, id);
    }

}
