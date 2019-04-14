package dekanat.mapper.sesiaMapper;

import dekanat.entity.sesiaEntity.Cafedra;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CafedraMapper implements RowMapper<Cafedra> {

@Override
public Cafedra mapRow(ResultSet resultSet, int i) throws SQLException {
    Cafedra c = new Cafedra();
    c.setId(resultSet.getInt(1));
    c.setName(resultSet.getString(2));
    c.setHall(resultSet.getInt(3));

    return c;
}

}
