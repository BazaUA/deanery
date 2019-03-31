package dekanat.mapper;

import dekanat.entity.SemesterEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SemesterMapper implements RowMapper<SemesterEntity> {
    @Override
    public SemesterEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        SemesterEntity semesterEntity = new SemesterEntity();
        semesterEntity.setId(resultSet.getInt(1));
        semesterEntity.setYear(resultSet.getInt(2));
        semesterEntity.setSemester(resultSet.getString(3));
        return semesterEntity;
    }
}
