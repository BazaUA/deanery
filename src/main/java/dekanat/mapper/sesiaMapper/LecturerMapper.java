package dekanat.mapper.sesiaMapper;


import dekanat.entity.sesiaEntity.Lecturer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerMapper implements RowMapper<Lecturer> {

    @Override
    public Lecturer mapRow(ResultSet resultSet, int i) throws SQLException {
        Lecturer l = new Lecturer();
        l.setId(resultSet.getInt(1));
        l.setLastname(resultSet.getString(2));
        l.setPostition(resultSet.getString(3));
        l.setCafedraId(resultSet.getInt(4));
        return l;
    }

}
