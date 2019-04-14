package dekanat.controller.dekanat;

import dekanat.dao.SemesterDAO;
import dekanat.entity.CourseEntity;
import dekanat.entity.SemesterEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SemestrController {

    public TextField numberOfSemestr;
    public TextField yearOfStudy;
    public Button submitButton;

    SemesterDAO semesterDAO= new SemesterDAO();

    public void addSemestr(MouseEvent mouseEvent) throws IOException {
        SemesterEntity semesterEntity = new SemesterEntity();
        semesterEntity.setYear(Integer.valueOf(yearOfStudy.getText()));
        semesterEntity.setSemester(numberOfSemestr.getText());
        int semestrId = semesterDAO.addSemestr(semesterEntity);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + semestrId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }

    private void rerenderMainPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

        Stage old = (Stage) submitButton.getScene().getWindow();
        old.close();
    }

}
