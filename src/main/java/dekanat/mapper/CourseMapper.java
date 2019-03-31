package dekanat.mapper;


import dekanat.entity.CourseEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<CourseEntity> {

    @Override
    public CourseEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(resultSet.getInt(1));
        courseEntity.setName(resultSet.getString(2));
        courseEntity.setNumberOfLectures(resultSet.getInt(3));
        courseEntity.setNumberOfPractices(resultSet.getInt(4));
        courseEntity.setSummarize(resultSet.getString(5));
        courseEntity.setNormative(resultSet.getBoolean(6));
        courseEntity.setNumberOfCredits(resultSet.getInt(7));
        courseEntity.setCathedraId(resultSet.getInt(8));
        return courseEntity;
    }


}