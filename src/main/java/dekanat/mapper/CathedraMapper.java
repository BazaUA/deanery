package dekanat.mapper;

import dekanat.entity.CathedraEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CathedraMapper implements RowMapper<CathedraEntity> {
    @Override
    public CathedraEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        CathedraEntity cathedraEntity = new CathedraEntity();
        cathedraEntity.setId(resultSet.getInt(1));
        cathedraEntity.setName(resultSet.getString(2));
        cathedraEntity.setHall(resultSet.getString(3));
        return cathedraEntity;
    }
}
