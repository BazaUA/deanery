package dekanat.controller.sesia;

import com.sun.javafx.collections.ObservableListWrapper;
import dekanat.dao.sesiaDao.GroupDao;
import dekanat.dao.sesiaDao.ResultsDao;
import dekanat.dao.sesiaDao.StudentDao;
import dekanat.entity.sesiaEntity.Group;
import dekanat.entity.sesiaEntity.Results;
import dekanat.entity.sesiaEntity.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewController implements Initializable {



    public TextField studentGrade;
    public ChoiceBox<Student> studentsChoise;
    public ChoiceBox<Group> groupsChoise;

    private ResultsDao resultsDao;
    private StudentDao studentDao;
    private GroupDao groupDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultsDao = new ResultsDao();
        studentDao = new StudentDao();
        groupDao = new GroupDao();


        List<Student> studentsList = studentDao.getAllWhen();
        studentsChoise.setItems(new ObservableListWrapper<Student>(studentsList));

        studentsChoise.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    System.out.println("new");
                }else {
                    System.out.println("else");
                }
            }
        });



    }

    public void addGrade(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Додати оцінку");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public void addResults(MouseEvent mouseEvent) {
        Results newResult = new Results();

        newResult.setGroupId(groupsChoise.getSelectionModel().getSelectedItem().getId());
        newResult.setStudentId(studentsChoise.getSelectionModel().getSelectedItem().getId());
        newResult.setGrade(Integer.valueOf(studentGrade.getText()));

        resultsDao.createResults(newResult);

    }


    public void loadGroups(MouseEvent mouseEvent) {
        int stId = studentsChoise.getSelectionModel().getSelectedItem().getId();
        List<Group> studentGroups = groupDao.getGroupByStudent(stId);
        groupsChoise.setItems(new ObservableListWrapper<Group>(studentGroups));
    }
}
