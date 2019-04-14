package dekanat.mapper.sesiaMapper;


import dekanat.entity.sesiaEntity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {


    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student s = new Student();
        s.setId(resultSet.getInt(1));
        s.setLastname(resultSet.getString(2));
        s.setMajor(resultSet.getString(3));
        s.setStartEducation(resultSet.getDate(4));
        s.setEndEducation(resultSet.getDate(5));
        s.getReasonToEndEducation(resultSet.getString(6));
        s.setTotalCredits(resultSet.getInt(7));

        return s;
    }
}