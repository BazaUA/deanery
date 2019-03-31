package dekanat.controller.courses;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.entity.CathedraEntity;
import dekanat.entity.CourseEntity;
import dekanat.entity.CourseHasPrerequisiteCourseEntity;
import dekanat.service.CathedraService;
import dekanat.service.CourseService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CourseAddController implements Initializable {
    public TextField nameOfCourse;
    public TextField numberOfLectures;
    public TextField numberOfPractices;
    public TextField numberOfCredits;
    public ChoiceBox summarize;
    public CheckBox isNormative;
    public ChoiceBox<CathedraEntity> cathedra;
    public ListView<CourseEntity> learnedCourses;
    public Button submitButton;

    private CourseService courseService;
    private CathedraService cathedraService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        courseService = new CourseService();
        cathedraService = new CathedraService();

        summarize.setItems(new ObservableListWrapper(SUMMARIZE_LIST));
        cathedra.setItems(new ObservableListWrapper<CathedraEntity>(getAllCathedras()));

        learnedCourses.setItems(new ObservableListWrapper<CourseEntity>(getAllCourses()));

        learnedCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void addCourse(MouseEvent mouseEvent) throws IOException {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(nameOfCourse.getText());
        courseEntity.setNumberOfLectures(Integer.valueOf(numberOfLectures.getText()));
        courseEntity.setNumberOfPractices(Integer.valueOf(numberOfPractices.getText()));
        courseEntity.setNumberOfCredits(Integer.valueOf(numberOfCredits.getText()));
        courseEntity.setSummarize((String) summarize.getSelectionModel().getSelectedItem());
        courseEntity.setNormative(isNormative.isSelected());
        courseEntity.setCathedraId(cathedra.getSelectionModel().getSelectedItem().getId());

        int courseId = courseService.add(courseEntity);
        List<CourseEntity> selectedCoursesIndexes = learnedCourses.getSelectionModel().getSelectedItems();

        for (CourseEntity selectedCourse : selectedCoursesIndexes) {
            CourseHasPrerequisiteCourseEntity courseHasPrerequisiteCourseEntity = new CourseHasPrerequisiteCourseEntity();
            courseHasPrerequisiteCourseEntity.setCourseId(courseId);
            courseHasPrerequisiteCourseEntity.setCourseCathedraId(courseEntity.getCathedraId());
            courseHasPrerequisiteCourseEntity.setPrerequisiteCourseCathedraId(selectedCourse.getCathedraId());
            courseHasPrerequisiteCourseEntity.setPrerequisiteCourseId(selectedCourse.getId());
            courseService.saveCourseHasPrerequisiteCourse(courseHasPrerequisiteCourseEntity);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + courseId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();
        
        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }


    private List<CathedraEntity> getAllCathedras() {
        return cathedraService.getAll();
    }

    private List<CourseEntity> getAllCourses() {
        return courseService.getAll();
    }

    private void rerenderMainPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    private static final String ISPYT_VALUE = "Іспит";
    private static final String ZALIK_VALUE = "Залік";
    private static final List<String> SUMMARIZE_LIST = new ArrayList<>(Arrays.asList(new String[]{ISPYT_VALUE, ZALIK_VALUE}));
    private static final Integer INDEX_OF_ISPYT = 0;
    private static final Integer INDEX_OF_ZALIK = 1;


}
