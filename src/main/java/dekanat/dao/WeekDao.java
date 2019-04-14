package dekanat.dao;

import dekanat.entity.SemesterEntity;
import dekanat.entity.WeekEntity;
import dekanat.mapper.SemesterMapper;
import dekanat.mapper.WeekMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class WeekDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public WeekDao() {
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }


    public List<WeekEntity> getAll() {
        String sql = "SELECT * FROM Week";
        RowMapper<WeekEntity> rowMapper = new WeekMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public WeekEntity getByName(int number) {
        String sql = "SELECT * FROM week WHERE week.number = ?";
        RowMapper<WeekEntity> rowMapper = new WeekMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper,number);

    }

    public int addWeek(WeekEntity w) {
        String sql = "INSERT INTO Week (number,start_date,end_date,semestr_id) " +
                "VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, w.getNumber(), w.getStart_date(),w.getEnd_date(),w.getSemester_id());
        return getByName(w.getNumber()).getId();
    }

    public void deleteById(int id) {
        String sql1 = "DELETE FROM Week WHERE Week.id = ? ";
        jdbcTemplate.update(sql1, id);
    }

}
