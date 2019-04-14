package dekanat.mapper.sesiaMapper;

import dekanat.entity.sesiaEntity.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group>{

        @Override
        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group g = new Group();
        g.setCourseName(resultSet.getString(1));
        g.setGroupNumber(resultSet.getInt(2));
        g.setId(resultSet.getInt(3));

        return g;
    }
}


