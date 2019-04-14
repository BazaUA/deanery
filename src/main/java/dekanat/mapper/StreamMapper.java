package dekanat.mapper;

import dekanat.entity.WeekEntity;
import dekanat.entity.sesiaEntity.StreamEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StreamMapper implements RowMapper<StreamEntity> {
    @Override
    public StreamEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        StreamEntity streamEntity = new StreamEntity();
        streamEntity.setId(resultSet.getInt(1));
        streamEntity.setName(resultSet.getString(2));
        return streamEntity;
    }
}