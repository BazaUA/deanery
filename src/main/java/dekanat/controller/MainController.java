package dekanat.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.controller.courses.CourseEditInfoController;
import dekanat.dao.SemesterDAO;
import dekanat.entity.CathedraEntity;
import dekanat.entity.CourseEntity;
import dekanat.entity.LecturerDistributionEntity;
import dekanat.entity.SemesterEntity;
import dekanat.service.CathedraService;
import dekanat.service.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // course fields
    // fxml

    public Button addCourseButton;
    public Button editCourseButton;
    public Button removeCourseButton;
    public Button detailsCourseButton;
    public TableView<CourseEntity> coursesTable;
    public TableColumn<CourseEntity, String> nameOfCourse;
    public TableColumn<CourseEntity, Integer> namOfLectures;
    public TableColumn<CourseEntity, Integer> numOfPrac;
    public TableColumn<CourseEntity, String> summarize;
    public TableColumn<CourseEntity, String> isNorm;
    public TableColumn<CourseEntity, String> cathedraName;


    // helpers
    private List<CourseEntity> coursesEntities;
    private ObservableList<CourseEntity> coursesListProperty = FXCollections.observableArrayList();
    // cathedra fields
    // fxml

    public TableView<CathedraEntity> cathedraTable;
    public TableColumn<CathedraEntity, String> nameOfCathedra;
    public TableColumn<CathedraEntity, String> hallCathedra;

    public Button detailsCathedraButton;
    // helpers
    private List<CathedraEntity> cathedrasEntities;
    private ObservableList<CathedraEntity> cathedrasListProperty = FXCollections.observableArrayList();


    //distribution
    public ChoiceBox<CourseEntity> distribCoursesDropdown;
    public ChoiceBox<SemesterEntity> distribSemestersDropdown;

    private List<SemesterEntity> semesterEntities;
    private ObservableList<CathedraEntity> semestersListProperty = FXCollections.observableArrayList();

    //services

    private CourseService courseService;
    private CathedraService cathedraService;
    private SemesterDAO semesterDAO;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseService = new CourseService();
        cathedraService = new CathedraService();
        semesterDAO = new SemesterDAO();

        initCourses();
        intiCathedras();
        initDropdown();

    }


    public void addCourse(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/course/add_course.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Додати курс");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        Stage main = (Stage) addCourseButton.getScene().getWindow();
        main.close();

    }

    public void editCourse(MouseEvent mouseEvent) throws IOException {
        if (!checkForNoSelectedItem(coursesTable)) {
            return;
        }

        CourseEntity courseEntity = coursesTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout/course/edit_course.fxml"));
        Parent root = fxmlLoader.load();

        CourseEditInfoController courseEditInfoController = fxmlLoader.<CourseEditInfoController>getController();
        courseEditInfoController.setCurrentCourseId(courseEntity.getId());

        Stage stage = new Stage();
        stage.setTitle("Редагувати курс");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        Stage main = (Stage) addCourseButton.getScene().getWindow();
        main.close();
    }

    public void removeCourse(MouseEvent mouseEvent) throws IOException {
        if (!checkForNoSelectedItem(coursesTable)) {
            return;
        }

        CourseEntity courseEntity = coursesTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ви впевнені що хочете видалити " + nameOfCourse.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            courseService.deleteById(courseEntity.getId());
            rerenderMainPage();
        }
    }


    public void detailsCathedra(MouseEvent mouseEvent) {
        if (!checkForNoSelectedItem(cathedraTable)) {
            return;
        }
        CathedraEntity cathedraEntity = cathedraTable.getSelectionModel().getSelectedItem();

        List<CourseEntity> courseEntities = courseService.getCoursesByCathedra(cathedraEntity.getId());
        String result = "Курси на кафедрі " + cathedraEntity.getName() + ": \n";
        for (CourseEntity courseEntity : courseEntities) {
            result += courseEntity.getName() + "\n";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.OK);
        alert.getDialogPane().setMinWidth(Region.USE_COMPUTED_SIZE);
        alert.showAndWait();
    }

    public void getDistribution(MouseEvent mouseEvent) {
        CourseEntity courseEntity = distribCoursesDropdown.getSelectionModel().getSelectedItem();
        SemesterEntity semesterEntity = distribSemestersDropdown.getSelectionModel().getSelectedItem();

        List<LecturerDistributionEntity> lecturerDistributionEntities = semesterDAO.getDistrib(courseEntity.getId(), semesterEntity.getId());
        String result = "Розподіл курсу (лекції/семінари) між викладачами в " + semesterEntity + " семестрі: \n";
        for (LecturerDistributionEntity lecturerDistributionEntity : lecturerDistributionEntities) {
            result += lecturerDistributionEntity + "\n";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.OK);
        alert.getDialogPane().setMinWidth(600);
        alert.showAndWait();

    }


    private void initCourses() {
        coursesEntities = courseService.getAll();
        for (CourseEntity courseEntity : coursesEntities) {
            courseEntity.setCathedraName(cathedraService.getById(courseEntity.getCathedraId()).getName());
            courseEntity.setIsNormativeString(courseEntity.isNormative() ? "Нормативний" : "");
            coursesListProperty.add(courseEntity);
        }

        nameOfCourse.setCellValueFactory(new PropertyValueFactory<>("name"));
        namOfLectures.setCellValueFactory(new PropertyValueFactory<>("numberOfLectures"));
        numOfPrac.setCellValueFactory(new PropertyValueFactory<>("numberOfPractices"));
        summarize.setCellValueFactory(new PropertyValueFactory<>("summarize"));
        isNorm.setCellValueFactory(new PropertyValueFactory<>("isNormativeString"));
        cathedraName.setCellValueFactory(new PropertyValueFactory<>("cathedraName"));


        coursesTable.setItems(coursesListProperty);
    }

    private void intiCathedras() {
        cathedrasEntities = cathedraService.getAll();
        for (CathedraEntity cathedraEntity : cathedrasEntities) {
            cathedrasListProperty.add(cathedraEntity);
        }

        nameOfCathedra.setCellValueFactory(new PropertyValueFactory<>("name"));
        hallCathedra.setCellValueFactory(new PropertyValueFactory<>("hall"));

        cathedraTable.setItems(cathedrasListProperty);
    }

    private void initDropdown() {
        semesterEntities = semesterDAO.getAll();

        distribSemestersDropdown.setItems(new ObservableListWrapper<>(semesterEntities));
        distribCoursesDropdown.setItems(new ObservableListWrapper<>(coursesEntities));

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

        Stage old = (Stage) addCourseButton.getScene().getWindow();
        old.close();
    }


}
