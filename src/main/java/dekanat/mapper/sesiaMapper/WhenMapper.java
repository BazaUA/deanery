package dekanat.mapper.sesiaMapper;

import dekanat.entity.sesiaEntity.When;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WhenMapper implements RowMapper<When> {

    @Override
    public When mapRow(ResultSet resultSet, int i) throws SQLException {
        When w = new When();
        w.setId(resultSet.getInt(1));
        w.setDate(resultSet.getDate(2));
        w.setGroupId(resultSet.getInt(3));
        w.setAuditoriaId(resultSet.getInt(4));


        return w;
    }

}