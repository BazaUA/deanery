package dekanat.controller.student;

import dekanat.dao.sesiaDao.StudentDao;
import dekanat.entity.CourseEntity;
import dekanat.entity.CourseHasPrerequisiteCourseEntity;
import dekanat.entity.sesiaEntity.Student;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EditInfoController implements Initializable {

    public TextField nameOfStudent;
    public TextField majorOfStudent;
    public DatePicker startEducation;
    public DatePicker endEducation;
    public TextField reasonToEndEducation;
    public TextField totalCredits;

    public Button submitButton;

    private StudentDao studentDao;

    private int currentStudentId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentDao = new StudentDao();

        Platform.runLater(() -> {
            setHandlerOnClose();
            Student student = studentDao.getById(currentStudentId);

            nameOfStudent.setText(student.getLastname());
            majorOfStudent.setText(student.getMajor());
            startEducation.setValue(convertToLocalDateViaMilisecond(student.getStartEducation()));
            endEducation.setValue(convertToLocalDateViaMilisecond(student.getStartEducation()));
            reasonToEndEducation.setText(student.getReasonToEndEducation());
            totalCredits.setText(String.valueOf(student.getTotalCredits()));

        });
    }

    private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void save(MouseEvent mouseEvent) throws IOException {
        Student student = new Student();
        student.setId(currentStudentId);
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



        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Оновлено успішно. ID: " + currentStudentId, ButtonType.OK);
        alert.showAndWait();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }


    public void setCurrentCourseId(int currentCourseId) {
        this.currentStudentId = currentCourseId;
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
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/main.fxml"));
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
}
