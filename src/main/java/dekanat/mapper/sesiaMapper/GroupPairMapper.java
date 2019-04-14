package dekanat.mapper.sesiaMapper;

import dekanat.entity.sesiaEntity.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupPairMapper  implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group g = new Group();
        g.setId(resultSet.getInt(1));
        g.setCourseName(resultSet.getString(2));
        g.setGroupNumber(resultSet.getInt(3));
        g.setStream_name(resultSet.getString(4));

        return g;
    }
}