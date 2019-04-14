package dekanat.mapper.sesiaMapper;


import dekanat.entity.sesiaEntity.Sesia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SesiaMapper implements RowMapper<Sesia> {


    @Override
    public Sesia mapRow(ResultSet resultSet, int i) throws SQLException {
        Sesia s = new Sesia();
        s.setDate(resultSet.getDate(1));
        s.setCourseName(resultSet.getString(2));
        s.setCafedraName(resultSet.getString(3));
        s.setLecturerName(resultSet.getString(4));
        s.setCourseSummarize(resultSet.getString(5));
        s.setGroupNumber(resultSet.getInt(6));
        s.setAuditoriaNumber(resultSet.getInt(7));

        return s;
    }
}