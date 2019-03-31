package dekanat.controller.courses;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.entity.CathedraEntity;
import dekanat.entity.CourseEntity;
import dekanat.entity.CourseHasPrerequisiteCourseEntity;
import dekanat.service.CathedraService;
import dekanat.service.CourseService;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CourseEditInfoController implements Initializable {

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

    private List<CathedraEntity> cathedraEntities;
    private List<CourseEntity> courseEntities;

    private int currentCourseId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseService = new CourseService();
        cathedraService = new CathedraService();

        Platform.runLater(() -> {
            setHandlerOnClose();
            CourseEntity courseEntity = courseService.getById(currentCourseId);

            nameOfCourse.setText(courseEntity.getName());
            numberOfLectures.setText(String.valueOf(courseEntity.getNumberOfLectures()));
            numberOfPractices.setText(String.valueOf(courseEntity.getNumberOfPractices()));
            numberOfCredits.setText(String.valueOf(courseEntity.getNumberOfCredits()));
            summarize.setItems(new ObservableListWrapper(SUMMARIZE_LIST));
            summarize.getSelectionModel().select(courseEntity.getSummarize().equalsIgnoreCase((String) summarize.getItems().get(0)) ? 0 : 1);
            isNormative.setSelected(courseEntity.isNormative());
            cathedra.setItems(new ObservableListWrapper(getAllCathedras()));
            selectCathedra(courseEntity.getCathedraId());
            learnedCourses.setItems(new ObservableListWrapper(getAllCourses()));
            // set selection mode multiply
            // learnedCourses.set(SelectionMode.MULTIPLE);
            learnedCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectLearnedCourses();
        });
    }


    public void save(MouseEvent mouseEvent) throws IOException {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(currentCourseId);
        courseEntity.setName(nameOfCourse.getText());
        courseEntity.setNumberOfLectures(Integer.valueOf(numberOfLectures.getText()));
        courseEntity.setNumberOfPractices(Integer.valueOf(numberOfPractices.getText()));
        courseEntity.setNumberOfCredits(Integer.valueOf(numberOfCredits.getText()));
        courseEntity.setSummarize((String) summarize.getSelectionModel().getSelectedItem());
        courseEntity.setNormative(isNormative.isSelected());
        courseEntity.setCathedraId(cathedra.getSelectionModel().getSelectedItem().getId());

        courseService.update(courseEntity);
        List<CourseEntity> selectedCoursesIndexes = learnedCourses.getSelectionModel().getSelectedItems();

        courseService.deleteAllPrerequisiteCourseById(currentCourseId);

        for (CourseEntity selectedCourse : selectedCoursesIndexes) {
            CourseHasPrerequisiteCourseEntity courseHasPrerequisiteCourseEntity = new CourseHasPrerequisiteCourseEntity();
            courseHasPrerequisiteCourseEntity.setCourseId(currentCourseId);
            courseHasPrerequisiteCourseEntity.setCourseCathedraId(courseEntity.getCathedraId());
            courseHasPrerequisiteCourseEntity.setPrerequisiteCourseCathedraId(selectedCourse.getCathedraId());
            courseHasPrerequisiteCourseEntity.setPrerequisiteCourseId(selectedCourse.getId());
            courseService.saveCourseHasPrerequisiteCourse(courseHasPrerequisiteCourseEntity);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Оновлено успішно. ID: " + currentCourseId, ButtonType.OK);
        alert.showAndWait();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }


    public void setCurrentCourseId(int currentCourseId) {
        this.currentCourseId = currentCourseId;
    }

    private void setHandlerOnClose() {
        Stage addingStage = (Stage) submitButton.getScene().getWindow();
        addingStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        stage.setTitle("Деканат");
                        stage.setScene(new Scene(root, 600, 600));
                        stage.show();
                        addingStage.close();
                    }
                });
            }
        });
    }

    private void selectCathedra(int cathedraId) {
        List<CathedraEntity> cathedraEntities = cathedra.getItems();
        for (int i = 0; i < cathedraEntities.size(); i++) {
            if (cathedraEntities.get(i).getId() == cathedraId) {
                cathedra.getSelectionModel().select(i);
                return;
            }
        }
    }

    private void selectLearnedCourses() {
        List<CourseEntity> haveToLearn = courseService.getAllPrerequisiteCourses(currentCourseId);
        for (CourseEntity haveToCourse : haveToLearn) {
            for (int i = 0; i < courseEntities.size(); i++) {
                if (haveToCourse.getId() == courseEntities.get(i).getId()) {
                    learnedCourses.getSelectionModel().select(i);
                }
            }
        }
    }

    private List<CourseEntity> getAllCourses() {
        courseEntities = courseService.getAll();
        return courseEntities;
    }

    private List<CathedraEntity> getAllCathedras() {
        cathedraEntities = cathedraService.getAll();
        return cathedraEntities;
    }


    private static final String ISPYT_VALUE = "іспит";
    private static final String ZALIK_VALUE = "залік";
    private static final List<String> SUMMARIZE_LIST = new ArrayList<>(Arrays.asList(new String[]{ISPYT_VALUE, ZALIK_VALUE}));
    private static final Integer INDEX_OF_ISPYT = 0;
    private static final Integer INDEX_OF_ZALIK = 1;


}
