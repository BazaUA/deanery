package dekanat.mapper;

import dekanat.entity.SemesterEntity;
import dekanat.entity.WeekEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeekMapper implements RowMapper<WeekEntity> {
    @Override
    public WeekEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        WeekEntity weekEntity = new WeekEntity();
        weekEntity.setId(resultSet.getInt(1));
        weekEntity.setNumber(resultSet.getInt(2));
        weekEntity.setStart_date(resultSet.getDate(3));
        weekEntity.setEnd_date(resultSet.getDate(4));
        weekEntity.setSemester_id(resultSet.getInt(5));
        return weekEntity;
    }
}