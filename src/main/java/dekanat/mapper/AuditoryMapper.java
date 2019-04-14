package dekanat.mapper;

import dekanat.entity.Auditory;
import dekanat.entity.WeekEntity;
import dekanat.entity.sesiaEntity.StreamEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditoryMapper implements RowMapper<Auditory> {
    @Override
    public Auditory mapRow(ResultSet resultSet, int i) throws SQLException {
            Auditory auditory = new Auditory();
            auditory.setId(resultSet.getInt(1));
            auditory.setNumber(resultSet.getString(2));
            auditory.setBuilding(resultSet.getInt(3));
            return auditory;
        }
}
