package dekanat.controller.dekanat;

import dekanat.dao.AuditoryDao;
import dekanat.dao.SemesterDAO;
import dekanat.dao.StreamDao;
import dekanat.dao.WeekDao;
import dekanat.dao.sesiaDao.GroupDao;
import dekanat.entity.Auditory;
import dekanat.entity.CathedraEntity;
import dekanat.entity.SemesterEntity;
import dekanat.entity.WeekEntity;
import dekanat.entity.sesiaEntity.Group;
import dekanat.entity.sesiaEntity.StreamEntity;
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
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public TableView semestrsTable;
    public TableColumn semestrsYear;
    public TableColumn semestrsNumber;

    public Button addSemestr;
    public Button editSemestr;
    public Button deleteSemestr;

    public SemesterDAO semesterDAO;
    public WeekDao weekDao;
    public StreamDao streamDao;
    public GroupDao groupDao;
    public AuditoryDao auditoryDao;


    public TableView weeksTable;
    public TableColumn weekNumber;
    public TableColumn weekStart;
    public TableColumn weekEnd;
    public TableColumn weekSemestr;

    public Button addWeek;
    public Button editWeek;
    public Button deleteWeek;

    public TableView streamTable;
    public TableColumn streamName;

    public Button addStream;
    public Button editStream;
    public Button deleteStream;

    public TableView groupTable;

    public TableColumn course;
    public TableColumn group;
    public TableColumn stream;

    public Button addGroup;
    public Button editGroup;
    public Button deleteGroup;
    public TableColumn auditoryNumber;
    public TableColumn auditorybuilding;
    public TableView auditoryTable;
    public Button addAuditory;
    public Button editAuditory;
    public Button deleteAuditory;

    private List<SemesterEntity> semesterEntities;
    private ObservableList<SemesterEntity> semesterEntityObservableList = FXCollections.observableArrayList();

    private List<WeekEntity> weekEntities;
    private ObservableList<WeekEntity> weekEntityObservableList = FXCollections.observableArrayList();

    private List<StreamEntity> streamEntities;
    private ObservableList<StreamEntity> streamEntityObservableList = FXCollections.observableArrayList();

    private List<Group> groupEntities;
    private ObservableList<Group> groupEntityObservableList = FXCollections.observableArrayList();

    private List<Auditory> auditorieEntities;
    private ObservableList<Auditory> auditorieEntityObservableList = FXCollections.observableArrayList();


    public void openAddSemestrWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/add_semestr.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати семестр");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openEditSemestrWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/edit_semestr.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Редагувати семестр");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openAddWeekWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/add_week.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати тиждень");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openEditWeekWindow(MouseEvent mouseEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/edit_week.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Редагувати тиждень");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openAddStreamWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/add_stream.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати потік");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openEditStreamWindow(MouseEvent mouseEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/edit_stream.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Редагувати потік");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openAddGroupWindow(MouseEvent mouseEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/add_group.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати групу");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openEditGroupWindow(MouseEvent mouseEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/edit_group.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Редагувати групу");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openAddAuditoryWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/add_auditory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати тиждень");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void openEditAuditoryWindow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/dekanat/edit_auditory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати тиждень");
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        semesterDAO = new SemesterDAO();
        weekDao = new WeekDao();
        streamDao = new StreamDao();
        groupDao = new GroupDao();
        auditoryDao = new AuditoryDao();
        initSemesters();
        initWeeks();
        initStream();
        initGroups();
        initAuditories();

    }

    private void initAuditories() {
        auditorieEntities = auditoryDao.getAll();
        for(Auditory auditory:auditorieEntities){
            auditorieEntityObservableList.add(auditory);
        }
        auditoryNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        auditorybuilding.setCellValueFactory(new PropertyValueFactory<>("building"));
        auditoryTable.setItems(auditorieEntityObservableList);
    }

    private void initStream() {
        streamEntities = streamDao.getAll();
        for (StreamEntity streamEntity : streamEntities) {
            streamEntityObservableList.add(streamEntity);
        }
        streamName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        streamTable.setItems(streamEntityObservableList);
    }

    private void initSemesters() {
        semesterEntities = semesterDAO.getAll();
        for (SemesterEntity semesterEntity : semesterEntities) {

            semesterEntityObservableList.add(semesterEntity);
        }
        semestrsYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        semestrsNumber.setCellValueFactory(new PropertyValueFactory<>("semester"));
        semestrsTable.setItems(semesterEntityObservableList);
    }

    private void initWeeks() {
        weekEntities = weekDao.getAll();
        for (WeekEntity  weekEntity: weekEntities) {
            weekEntity.setSemestr(semesterDAO.getById(weekEntity.getSemester_id()).getSemester());
            weekEntityObservableList.add(weekEntity);
        }

        weekNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        weekStart.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        weekEnd.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        weekSemestr.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        weeksTable.setItems(weekEntityObservableList);
    }


    private void initGroups() {
        groupEntities = groupDao.getAllGroups();
        for (Group groupEntity: groupEntities) {
            groupEntityObservableList.add(groupEntity);
        }

        group.setCellValueFactory(new PropertyValueFactory<>("groupNumber"));
        course.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        stream.setCellValueFactory(new PropertyValueFactory<>("stream_name"));
        groupTable.setItems(groupEntityObservableList);

    }

    public void deleteAuditory(MouseEvent mouseEvent) {
        Auditory auditory = (Auditory) auditoryTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + auditoryNumber.getText() +  auditorybuilding.getText()+" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            auditoryDao.deleteById(auditory.getId());
        }
        auditoryTable.getItems().remove(auditory);
        auditoryTable.refresh();

    }

    public void deleteGroup(MouseEvent mouseEvent) throws IOException{
        Group group = (Group) groupTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + course.getText()  +" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            groupDao.deleteById(group.getId());
        }
        groupTable.getItems().remove(group);
        groupTable.refresh();
    }

    public void deleteStream(MouseEvent mouseEvent) throws IOException{
        StreamEntity streamEntity = (StreamEntity) streamTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + course.getText()  +" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            streamDao.deleteById(streamEntity.getId());
        }
        streamTable.getItems().remove(streamEntity);
        streamTable.refresh();


    }


    public void deleteWeek(MouseEvent mouseEvent) throws IOException{
        WeekEntity weekEntity = (WeekEntity) weeksTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + course.getText()  +" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            weekDao.deleteById(weekEntity.getId());
        }

        weeksTable.getItems().remove(weekEntity);
        weeksTable.refresh();
    }

    public void deleteSemestr(MouseEvent mouseEvent) throws IOException{
        SemesterEntity semesterEntity = (SemesterEntity) semestrsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + course.getText()  +" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            semesterDAO.deleteById(semesterEntity.getId());
        }
        semestrsTable.getItems().remove(semesterEntity);
        semestrsTable.refresh();
    }
}
