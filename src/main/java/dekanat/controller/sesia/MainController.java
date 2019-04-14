package dekanat.controller.sesia;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.dao.sesiaDao.*;
import dekanat.entity.sesiaEntity.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView sesiaSchedule;
    public TableView sesiaScheduletFiltered;
    public TableView<Results> gradesList;

    public ChoiceBox<Group> groupPairs;
    public ChoiceBox<Lecturer> Lecturers;
    public ChoiceBox<Cafedra> Cathedras;
    public Button deleteButton;


    private SesiaDao sesiaDao;
    private GroupDao groupDao;
    private LecturerDao lecturerDao;
    private CafedraDao cafedraDao;
    private ResultsDao resultsDao;

    @FXML
    private TableColumn dateCol;
    @FXML
    private TableColumn courseCol;
    @FXML
    private TableColumn cafedraCol;
    @FXML
    private TableColumn lecturerCol;
    @FXML
    private TableColumn summarizeCol;
    @FXML
    private TableColumn groupCol;
    @FXML
    private TableColumn auditoryCol;
    @FXML
    private TableColumn studentNameCol;
    @FXML
    private TableColumn studentGradeCol;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupDao = new GroupDao();
        cafedraDao = new CafedraDao();
        lecturerDao = new LecturerDao();

        List<Group> groupPairsList = groupDao.getAllGroupPair();
        List<Lecturer> lecturersList = lecturerDao.getAllLecturers();
        List<Cafedra> cafedrasList = cafedraDao.getAllCafedras();

        groupPairs.setItems(new ObservableListWrapper<Group>(groupPairsList));
        Lecturers.setItems(new ObservableListWrapper<Lecturer>(lecturersList));
        Cathedras.setItems(new ObservableListWrapper<Cafedra>(cafedrasList));

    }

    public void createSchedule(MouseEvent mouseEvent) {
        sesiaDao = new SesiaDao();
        dateCol = new TableColumn("Дата");
        courseCol = new TableColumn("Назва курсу");
        cafedraCol = new TableColumn("Кафедра");
        lecturerCol = new TableColumn("Викладач");
        summarizeCol = new TableColumn("Форма контролю");
        groupCol = new TableColumn("Група");
        auditoryCol = new TableColumn("Аудиторія");


        List<Sesia> sesiaList = sesiaDao.getSesiaSchedule();
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Sesia, Date>("date")
        );
        courseCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("courseName")
        );
        cafedraCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("cafedraName")
        );
        lecturerCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("lecturerName")
        );
        summarizeCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("courseSummarize")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("groupNumber")
        );
        auditoryCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("auditoriaNumber")
        );


        sesiaSchedule.setItems(new ObservableListWrapper(sesiaList));
        sesiaSchedule.getColumns().setAll(dateCol, courseCol,cafedraCol,lecturerCol,summarizeCol,groupCol,auditoryCol);
    }

    public void createFilteredSchedule(MouseEvent mouseEvent) {

        boolean isGroupEmpty = groupPairs.getSelectionModel().isEmpty();
        boolean isLecturerEmpty = Lecturers.getSelectionModel().isEmpty();
        boolean isCathedraEmpty = Cathedras.getSelectionModel().isEmpty();

        sesiaDao = new SesiaDao();

        dateCol = new TableColumn("Дата");
        courseCol = new TableColumn("Назва курсу");
        cafedraCol = new TableColumn("Кафедра");
        lecturerCol = new TableColumn("Викладач");
        summarizeCol = new TableColumn("Форма контролю");
        groupCol = new TableColumn("Група");
        auditoryCol = new TableColumn("Аудиторія");

        dateCol.setCellValueFactory(
                new PropertyValueFactory<Sesia, Date>("date")
        );
        courseCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("courseName")
        );
        cafedraCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("cafedraName")
        );
        lecturerCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("lecturerName")
        );
        summarizeCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("courseSummarize")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("groupNumber")
        );
        auditoryCol.setCellValueFactory(
                new PropertyValueFactory<Sesia,String>("auditoriaNumber")
        );


        if (isLecturerEmpty == false && isGroupEmpty == true && isCathedraEmpty == true) {
            String lecturerName = Lecturers.getValue().toString();
            System.out.println(lecturerName);
            List<Sesia> sesiaListByLecturer = sesiaDao.getSesiaScheduleByLecturer(lecturerName);
            System.out.println(sesiaListByLecturer.toString());
            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByLecturer));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }else if(isLecturerEmpty == true & isCathedraEmpty == false & isGroupEmpty == true){
            String cathedraName = Cathedras.getValue().toString();
            System.out.println(cathedraName);
            List<Sesia> sesiaListByCathedra = sesiaDao.getSesiaScheduleByCafedra(cathedraName);
            System.out.println(sesiaListByCathedra.toString());
            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByCathedra));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);

        }else if(isLecturerEmpty == true && isGroupEmpty == false && isCathedraEmpty == true){
            String group_course = groupPairs.getValue().toString();
            String group = group_course.substring(0,1);
            System.out.println(group_course);
            System.out.println(group);
            String course = group_course.substring(8);
            System.out.println(course);

            List<Sesia> sesiaListByGroup = sesiaDao.getSesiaScheduleByGroup(course,group);
            System.out.println(sesiaListByGroup.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByGroup));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);

        }

        if((isLecturerEmpty == false ) && (isGroupEmpty == false ) && (isCathedraEmpty == true)) {
            String group_course = groupPairs.getValue().toString();
            String group = group_course.substring(0, 1);
            System.out.println(group_course);
            System.out.println(group);
            String course = group_course.substring(8);
            System.out.println(course);

            String lecturerName = Lecturers.getValue().toString();

            List<Sesia> sesiaListByGroupAndLecturer = sesiaDao.getSesiaScheduleByLecturerAndGroup(lecturerName,group,course);
            System.out.println(sesiaListByGroupAndLecturer.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByGroupAndLecturer));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }

        if((isLecturerEmpty == false ) && (isCathedraEmpty == false ) && (isGroupEmpty == true)) {

            String lecturerName = Lecturers.getValue().toString();
            String cathedraName = Cathedras.getValue().toString();


            List<Sesia> sesiaListByCafedraAndLecturer = sesiaDao.getSesiaScheduleByLecturerAndCafedra(lecturerName,cathedraName);
            System.out.println(sesiaListByCafedraAndLecturer.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByCafedraAndLecturer));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }

        if((isLecturerEmpty == true ) && (isCathedraEmpty == false ) && (isGroupEmpty == false)) {

            String cathedraName = Cathedras.getValue().toString();

            String group_course = groupPairs.getValue().toString();
            String group = group_course.substring(0, 1);
            System.out.println(group_course);
            System.out.println(group);
            String course = group_course.substring(8);
            System.out.println(course);

            List<Sesia> sesiaListByCafedraAndGroup = sesiaDao.getSesiaScheduleByCafedraAndGroup(cathedraName,group,course);
            System.out.println(sesiaListByCafedraAndGroup.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListByCafedraAndGroup));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }

        if((isLecturerEmpty == false ) && (isCathedraEmpty == false ) && (isGroupEmpty == false)) {

            String cathedraName = Cathedras.getValue().toString();
            String lecturerName = Lecturers.getValue().toString();

            String group_course = groupPairs.getValue().toString();
            String group = group_course.substring(0, 1);
            System.out.println(group_course);
            System.out.println(group);
            String course = group_course.substring(8);
            System.out.println(course);

            List<Sesia> sesiaListAllOptions = sesiaDao.getSesiaScheduleByLecturerAndGroupAndCafedra(cathedraName,group,course,lecturerName);
            System.out.println(sesiaListAllOptions.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper(sesiaListAllOptions));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }

        if((isLecturerEmpty == true ) && (isCathedraEmpty == true ) && (isGroupEmpty == true)) {

            List<Sesia> sesiaListNotFiltered = sesiaDao.getSesiaSchedule();
            System.out.println(sesiaListNotFiltered.toString());

            sesiaScheduletFiltered.setItems(new ObservableListWrapper<Sesia>(sesiaListNotFiltered));
            sesiaScheduletFiltered.getColumns().setAll(dateCol, courseCol, cafedraCol, lecturerCol, summarizeCol, groupCol, auditoryCol);
        }

    }

    public void showGrades(MouseEvent mouseEvent) {

        resultsDao = new ResultsDao();

        studentNameCol = new TableColumn("Прізвище,Ім'я");
        courseCol = new TableColumn("Назва курсу");
        cafedraCol = new TableColumn("Кафедра");
        lecturerCol = new TableColumn("Викладач");
        summarizeCol = new TableColumn("Форма контролю");
        groupCol = new TableColumn("Група");
        studentGradeCol = new TableColumn<Sesia,String>("Оцінка");

        List<Results> resultsList = resultsDao.getResults();
        studentNameCol.setCellValueFactory(
                new PropertyValueFactory<Results, Date>("studentsName")
        );
        courseCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("courseName")
        );
        cafedraCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("cafedraName")
        );
        lecturerCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("lecturerName")
        );
        summarizeCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("courseSummarize")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("groupNumber")
        );
        studentGradeCol.setCellValueFactory(
                new PropertyValueFactory<Results,String>("grade")
        );
        studentGradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentGradeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Results, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Results, String> t) {
                        ((Results) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setGrade(t.getNewValue());


                        resultsDao.updateResult(t.getNewValue(),gradesList.getSelectionModel().getSelectedItem().getGradeId());
                    }
                }
        );



        gradesList.setItems(new ObservableListWrapper(resultsList));
        gradesList.getColumns().setAll(studentNameCol, courseCol,cafedraCol,lecturerCol,summarizeCol,groupCol,studentGradeCol);

    }


    public void deleteItem(MouseEvent mouseEvent) {
            resultsDao.deleteResult(gradesList.getSelectionModel().getSelectedItem().getGradeId());
            gradesList.getItems().remove(gradesList.getSelectionModel().getSelectedIndex());
            gradesList.refresh();


    }

    public void addNewGrade(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addNew.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати оцінку");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
}