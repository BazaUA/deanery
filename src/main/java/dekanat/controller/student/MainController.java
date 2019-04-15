package dekanat.controller.student;

import dekanat.dao.sesiaDao.ResultsDao;
import dekanat.dao.sesiaDao.StudentDao;
import dekanat.entity.sesiaEntity.Results;
import dekanat.entity.sesiaEntity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MainController implements Initializable {

    // course fields
    // fxml

    public Button addButton;
    public Button editButton;
    public Button removeButton;
    public TableView<Student> table;
    public TableColumn<Student, String> nameOfStudent;
    public TableColumn<Student, String> majorOfStudent;
    public TableColumn<Student, String> startEducation;
    public TableColumn<Student, String> endEducation;
    public TableColumn<Student, String> reasonToEndEducation;
    public TableColumn<Student, Integer> totalCredits;


    // helpers
    private List<Student> studentEntities;
    private ObservableList<Student> studentListProperty = FXCollections.observableArrayList();

    //services

    private StudentDao studentDAO;
    private ResultsDao resultsDao;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentDAO = new StudentDao();
        resultsDao = new ResultsDao();

        init();


    }


    public void add(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/add.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Додати студента");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        Stage main = (Stage) addButton.getScene().getWindow();
        main.close();

    }

    public void edit(MouseEvent mouseEvent) throws IOException {
        if (!checkForNoSelectedItem(table)) {
            return;
        }

        Student student = table.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout/student/edit.fxml"));
        Parent root = fxmlLoader.load();

        EditInfoController studentEditInfoController = fxmlLoader.<EditInfoController>getController();
        studentEditInfoController.setCurrentCourseId(student.getId());

        Stage stage = new Stage();
        stage.setTitle("Редагувати cтудента");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        Stage main = (Stage) addButton.getScene().getWindow();
        main.close();
    }

    public void remove(MouseEvent mouseEvent) throws IOException {
        if (!checkForNoSelectedItem(table)) {
            return;
        }

        Student student = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + nameOfStudent.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            studentDAO.deleteStudent(student.getId());
            rerenderMainPage();
        }
    }


    private void init() {
        studentEntities = studentDAO.getAllStudents();
        for (Student student : studentEntities) {
            studentListProperty.add(student);
        }

        nameOfStudent.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        majorOfStudent.setCellValueFactory(new PropertyValueFactory<>("major"));
        startEducation.setCellValueFactory(new PropertyValueFactory<>("startEducation"));
        endEducation.setCellValueFactory(new PropertyValueFactory<>("endEducation"));
        reasonToEndEducation.setCellValueFactory(new PropertyValueFactory<>("reasonToEndEducation"));
        totalCredits.setCellValueFactory(new PropertyValueFactory<>("totalCredits"));


        table.setItems(studentListProperty);
    }


    private boolean checkForNoSelectedItem(TableView listView) {
        Object nameOfCourse = listView.getSelectionModel().getSelectedItem();
        if (nameOfCourse == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ви не обрали жодного елемента", ButtonType.OK);
            alert.show();
            return false;
        }
        return true;
    }

    private void rerenderMainPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

        Stage old = (Stage) addButton.getScene().getWindow();
        old.close();
    }


    public void averageMark(MouseEvent mouseEvent) {
        double result = 0;
        if (!checkForNoSelectedItem(table)) {
            return;
        }

        Student student = table.getSelectionModel().getSelectedItem();

        List<Results> results = resultsDao.getResultsByStudentId(student.getId());
        double sum = 0;

        for(Results resulttemp: results){
            sum += Integer.valueOf(resulttemp.getGrade());
        }

        result = sum/results.size();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, String.valueOf(result), ButtonType.OK);
        alert.getDialogPane().setMinWidth(600);
        alert.showAndWait();

    }

    public void getSchedule(MouseEvent mouseEvent) {
    }

    public void groupByMarks(MouseEvent mouseEvent) {

    }

    public void getLearnedCourses(MouseEvent mouseEvent) {
        String result = "";
        if (!checkForNoSelectedItem(table)) {
            return;
        }

        Student student = table.getSelectionModel().getSelectedItem();

        List<Results> results = resultsDao.getResultsByStudentId(student.getId());
        Set<String> stringSet = new HashSet<>();
        for(Results results1:results){
            stringSet.add(results1.getCourseName());
        }

        for(String name: stringSet){
            result+=name+'\n';
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.OK);
        alert.getDialogPane().setMinWidth(600);
        alert.showAndWait();
    }

    public void getAllMarks(MouseEvent mouseEvent) {
        String result = "";
        if (!checkForNoSelectedItem(table)) {
            return;
        }

        Student student = table.getSelectionModel().getSelectedItem();

        List<Results> results = resultsDao.getResultsByStudentId(student.getId());

        for(Results results1:results){
            result+=String.valueOf(results1.getGrade())+'\n';
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.OK);
        alert.getDialogPane().setMinWidth(600);
        alert.showAndWait();
    }
}
