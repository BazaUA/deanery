package dekanat.mapper.sesiaMapper;

import dekanat.entity.sesiaEntity.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course s = new Course();
        s.setId(resultSet.getInt(1));
        s.setName(resultSet.getString(2));
        s.setNumberOfLectures(resultSet.getInt(3));
        s.setNumberOfPractices(resultSet.getInt(4));
        s.setSummarize(resultSet.getString(5));
        s.setNormative(resultSet.getBoolean(6));
        s.setNumberOfCredits(resultSet.getInt(7));
        s.setCathedraId(resultSet.getInt(8));
        return s;
    }


}
