package dekanat.mapper;


import dekanat.entity.sesiaEntity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student studentEntity = new Student();
        studentEntity.setId(resultSet.getInt(1));
        studentEntity.setLastname(resultSet.getString(2));
        studentEntity.setMajor(resultSet.getString(3));
        studentEntity.setStartEducation(resultSet.getDate(4));
        studentEntity.setEndEducation(resultSet.getDate(5));
        studentEntity.setReasonToEndEducation(resultSet.getString(6));
        studentEntity.setTotalCredits(resultSet.getInt(7));

        return studentEntity;
    }


}