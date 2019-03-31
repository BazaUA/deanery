package dekanat.controller.courses;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.entity.CathedraEntity;
import dekanat.entity.CourseEntity;
import dekanat.service.CathedraService;
import dekanat.service.CourseService;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CourseInfoController implements Initializable {

    public TextField nameOfCourse;
    public TextField numberOfLectures;
    public TextField numberOfPractices;
    public TextField numberOfCredits;
    public ChoiceBox summarize;
    public CheckBox isNormative;
    public ChoiceBox cathedra;
    public ListView learnedCourses;
    public Button cancelButton;
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

        currentCourseId = 1;
        CourseEntity courseEntity = courseService.getById(currentCourseId);

        nameOfCourse.setText(courseEntity.getName());
        numberOfLectures.setText(String.valueOf(courseEntity.getNumberOfLectures()));
        numberOfPractices.setText(String.valueOf(courseEntity.getNumberOfPractices()));
        numberOfCredits.setText(String.valueOf(courseEntity.getNumberOfCredits()));
        summarize.setItems(new ObservableListWrapper(SUMMARIZE_LIST));
        summarize.getSelectionModel().select(ISPYT_VALUE.equals(courseEntity.getSummarize()) ? INDEX_OF_ISPYT : INDEX_OF_ZALIK);
        isNormative.setSelected(courseEntity.isNormative());
        cathedra.setItems(new ObservableListWrapper(getAllCathedrasNames()));
        int selectIndex = getIndexOfCathedraById(courseEntity.getId());
        if (selectIndex != -1) {
            cathedra.getSelectionModel().select(selectIndex);
        }
        learnedCourses.setItems(new ObservableListWrapper(courseService.getAllPrerequisiteCourses(currentCourseId)));
        // set selection mode multiply
        // learnedCourses.set(SelectionMode.MULTIPLE);
        learnedCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectLearnedCourses();
    }

    private void selectLearnedCourses() {
        List<CourseEntity> haveToLearn = courseService.getAllPrerequisiteCourses(currentCourseId);
        for (int i = 0; i < haveToLearn.size(); i++) {
            for (int j = 0; j < courseEntities.size(); j++) {
                if (haveToLearn.get(i).equals(courseEntities.get(j))) {
                    learnedCourses.getSelectionModel().select(j);
                }
            }
        }
    }

    private List<String> getAllCourses() {
        courseEntities = courseService.getAll();
        List<String> resultStrings = new ArrayList<>();

        courseEntities.stream().forEach(courseEntity -> resultStrings.add(courseEntity.getName()));

        return resultStrings;
    }

    private int getIndexOfCathedraById(int id) {
        for (int i = 0; i < cathedraEntities.size(); i++) {
            if (id == cathedraEntities.get(id).getId()) {
                return i;
            }
        }
        return -1;
    }

    private List<String> getAllCathedrasNames() {
        cathedraEntities = cathedraService.getAll();

        List<String> resultStrings = new ArrayList<>();

        cathedraEntities.stream().forEach(cathedraEntity -> resultStrings.add(cathedraEntity.getName()));

        return resultStrings;
    }


    private static final String ISPYT_VALUE = "іспит";
    private static final String ZALIK_VALUE = "залік";
    private static final List<String> SUMMARIZE_LIST = new ArrayList<>(Arrays.asList(new String[]{ISPYT_VALUE, ZALIK_VALUE}));
    private static final Integer INDEX_OF_ISPYT = 0;
    private static final Integer INDEX_OF_ZALIK = 1;

}
