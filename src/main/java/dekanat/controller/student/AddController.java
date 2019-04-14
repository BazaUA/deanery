package dekanat.controller.student;

import dekanat.dao.sesiaDao.StudentDao;
import dekanat.entity.sesiaEntity.Student;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    public TextField nameOfStudent;
    public TextField majorOfStudent;
    public DatePicker startEducation;
    public DatePicker endEducation;
    public TextField reasonToEndEducation;
    public TextField totalCredits;

    public Button submitButton;

    private StudentDao studentDao;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentDao = new StudentDao();

    }

    public void add(MouseEvent mouseEvent) throws IOException {
        Student student = new Student();
        student.setLastname(nameOfStudent.getText());
        student.setMajor(majorOfStudent.getText());
        LocalDate localDateStart = startEducation.getValue();
        Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
        student.setStartEducation(Date.from(instantStart));
        student.setTotalCredits(Integer.valueOf(totalCredits.getText()));
        LocalDate localDateEnd = endEducation.getValue();
        Instant instantEnd = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
        student.setEndEducation(Date.from(instantEnd));
        student.setReasonToEndEducation(reasonToEndEducation.getText());


        int studentId = studentDao.addStudent(student);


        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + studentId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }


    private void rerenderMainPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }


}
