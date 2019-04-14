package dekanat.mapper.sesiaMapper;


import dekanat.entity.sesiaEntity.Results;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultsMapper implements RowMapper<Results> {


    @Override
    public Results mapRow(ResultSet resultSet, int i) throws SQLException {
        Results r = new Results();
        r.setStudentsName(resultSet.getString(1));
        r.setCourseName(resultSet.getString(2));
        r.setCafedraName(resultSet.getString(3));
        r.setLecturerName(resultSet.getString(4));
        r.setSummarizeName(resultSet.getString(5));
        r.setGroupNumber(resultSet.getInt(6));
        r.setGrade(resultSet.getString(7));
        r.setGradeId(resultSet.getInt(8));
        r.setGroupId(resultSet.getInt(9));
        r.setStudentId(resultSet.getInt(10));
        r.setResultId(resultSet.getInt(11));


        return r;
    }
}