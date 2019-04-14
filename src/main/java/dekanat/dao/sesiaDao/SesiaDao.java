package dekanat.dao.sesiaDao;

import dekanat.dao.MyDataSourceFactory;
import dekanat.entity.sesiaEntity.Sesia;
import dekanat.mapper.sesiaMapper.SesiaMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SesiaDao {
    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public SesiaDao(){
        dataSource = MyDataSourceFactory.getMySQLDataSource();
        System.out.println(dataSource);
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public List<Sesia> getSesiaSchedule(){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num\n" +
                "FROM ((((dekanat.Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id )\n" +
                "inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id\n" +
                "Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql, rowMapper);

    }

    public List<Sesia> getSesiaScheduleByLecturer(String lecturerName){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num \n" +
                "                FROM ((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id ) \n" +
                "                inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "                inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id \n" +
                "                Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id \n" +
                "                and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and Lecturers.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql,rowMapper, lecturerName);
    }

    public List<Sesia> getSesiaScheduleByGroup(String courseName,String groupNumber){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num\n" +
                "FROM ((((dekanat.Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id )\n" +
                "inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id\n" +
                "Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and `Groups`.group_no = ? and Courses.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql, rowMapper,groupNumber,courseName);

    }

    public List<Sesia> getSesiaScheduleByCafedra(String cafedraName){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, `Groups`.group_no, Auditory.num\n" +
                "FROM ((((dekanat.Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id )\n" +
                "inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join `Groups` on Courses.id = `Groups`.course_id) \n" +
                "inner join WhenDate on `Groups`.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id\n" +
                "Where Cathedras.name = ? and Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id and `Groups`.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql, rowMapper,cafedraName);

    }

    public List<Sesia> getSesiaScheduleByLecturerAndGroup(String lecturerName,String group,String course){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num \n" +
                "                FROM ((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id ) \n" +
                "                inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "                inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id \n" +
                "                Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id \n" +
                "                and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and Lecturers.name = ? and `Groups`.group_no = ? and Courses.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql,rowMapper, lecturerName,group,course);
    }


    public List<Sesia> getSesiaScheduleByLecturerAndCafedra(String lecturerName,String cafedraName){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num \n" +
                "                FROM ((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id ) \n" +
                "                inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "                inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id \n" +
                "                Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id \n" +
                "                and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and Lecturers.name = ? and Cathedras.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql,rowMapper, lecturerName,cafedraName);
    }

    public List<Sesia> getSesiaScheduleByLecturerAndGroupAndCafedra(String lecturerName, String cafedraName, int groupNumber){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num\n" +
                "FROM ((((dekanat.Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id )\n" +
                "inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id\n" +
                "Where Lecturers.name = ? and Cathedras.name = ? and Groups.group_no = ? and Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql, rowMapper,lecturerName,cafedraName,groupNumber);


    }

    public List<Sesia> getSesiaScheduleByLecturerAndGroupAndCafedra(String cafedraName,String groupNumber,String courseName,String lecturerName){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num \n" +
                "                FROM ((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id ) \n" +
                "                inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "                inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id \n" +
                "                Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id \n" +
                "                and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and Cathedras.name = ? and `Groups`.group_no = ? and Courses.name = ? and Lecturers.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql,rowMapper,cafedraName,groupNumber,courseName,lecturerName);
    }


    public List<Sesia> getSesiaScheduleByCafedraAndGroup(String cafedraName,String groupNumber,String courseName){
        String sql = "SELECT WhenDate.date, Courses.name, Cathedras.name, Lecturers.name, Courses.summarize, Groups.group_no, Auditory.num \n" +
                "                FROM ((((Courses inner join Cathedras on Courses.cathedra_id = Cathedras.id ) \n" +
                "                inner join Lecturers on Cathedras.id = Lecturers.cathedra_id ) inner join Groups on Courses.id = Groups.course_id) \n" +
                "                inner join WhenDate on Groups.id = WhenDate.group_id) inner join Auditory on WhenDate.auditory_id = Auditory.id \n" +
                "                Where Cathedras.id = Courses.cathedra_id and Cathedras.id = Lecturers.cathedra_id \n" +
                "                and Groups.id = WhenDate.group_id and Auditory.id = WhenDate.auditory_id and Cathedras.name = ? and `Groups`.group_no = ? and Courses.name = ? ;";
        RowMapper<Sesia> rowMapper = new SesiaMapper();
        return this.jdbcTemplate.query(sql,rowMapper,cafedraName,groupNumber,courseName);
    }





}
