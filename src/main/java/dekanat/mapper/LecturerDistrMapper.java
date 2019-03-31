package dekanat.mapper;

import dekanat.entity.LecturerDistributionEntity;
import dekanat.entity.SemesterEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerDistrMapper implements RowMapper<LecturerDistributionEntity> {
    @Override
    public LecturerDistributionEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        LecturerDistributionEntity lecturerDistributionEntity = new LecturerDistributionEntity();
        lecturerDistributionEntity.setCourseName(resultSet.getString(1));
        lecturerDistributionEntity.setLecturerName(resultSet.getString(2));
        lecturerDistributionEntity.setSemester(resultSet.getString(3));
        lecturerDistributionEntity.setTypeOfLesson(resultSet.getString(4));
        return lecturerDistributionEntity;
    }
}
