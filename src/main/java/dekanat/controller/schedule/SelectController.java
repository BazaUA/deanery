package dekanat.controller.schedule;

import dekanat.entity.scheduleEntity.LabelValue;
import dekanat.entity.scheduleEntity.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectController {


    public ObservableList<LabelValue> getSelectOptions(String query, String labelKey, String valueKey){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            while(rs.next()) {
                v = new LabelValue(rs.getString(labelKey), rs.getString(valueKey));
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<LabelValue> getLecturersList(){
       return  getSelectOptions("SELECT * FROM lecturers", "name", "id");
    }

    public ObservableList<LabelValue> getCathedrasList(){
        return getSelectOptions("SELECT * FROM cathedras", "name", "id");
    }

    public ObservableList<LabelValue> getSemestersOptions(){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query ="Select * from semesters";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            while(rs.next()) {
                String label = rs.getString("year")+"/"+rs.getString("semester");
                v = new LabelValue(label, rs.getString("id"));
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<LabelValue> getGroupsOptions(){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query ="SELECT * FROM `groups`";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            while(rs.next()) {
                String label = rs.getString("group_no") + " group";
                v = new LabelValue(label, rs.getString("id"));
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<LabelValue> getTypeOptions(){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        list.add(new LabelValue("Group", "group"));
        list.add(new LabelValue("Stream", "stream"));
        return list;
    }

    public static String getLastId(String stringQuery){
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query =stringQuery;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            rs.next();
            return rs.getString("id");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Lesson getById(String id){
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query =" SELECT lessons.id, type, lecturer_id, group_no, name, semester_id, course_id, schedule_id from lessons\n" +
                "    LEFT OUTER JOIN `groups` on lessons.id = `groups`.id\n" +
                "    LEFT OUTER JOIN `stream` on stream.id = lessons.id\n" +
                "    where lessons.id ="+id+";";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            Lesson lesson = new Lesson();
            lesson.id = rs.getString("id");
            lesson.type = rs.getString("type");
            lesson.lecturerId = rs.getString("lecturer_id");
            lesson.group_no = rs.getString("group_no");
            lesson.semesterId = rs.getString("semester_id");
            lesson.courseId = rs.getString("course_id");
            lesson.scheduleId = rs.getString("schedule_id");
            lesson.name = rs.getString("name");

            return lesson;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public ObservableList<LabelValue> getScheduleOptions(){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query ="Select * from Schedule";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            while(rs.next()) {
                String label = rs.getString("Day") +" day / "+ rs.getString("LessonOrder")+ " lesson";
                v = new LabelValue(label, rs.getString("id"));
                v.meta = new ArrayList<>();
                v.meta.add( rs.getString("Day"));
                v.meta.add( rs.getString("LessonOrder"));
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<LabelValue> getAuditoryOptions(){
        ObservableList<LabelValue> list = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        Statement st;
        ResultSet rs;
        String query ="Select * from Auditory";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            LabelValue v;
            while(rs.next()) {
                String label = rs.getString("hall");

                v = new LabelValue(label, rs.getString("id"));


                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<LabelValue> getCourseOptions(){
        return getSelectOptions("SELECT * FROM courses", "name", "id");
    }

    public ObservableList<LabelValue> getSteamNameOptions(){
        return getSelectOptions("SELECT * FROM stream", "name", "id");
    }
}
