package dekanat.controller.dekanat;

import dekanat.dao.AuditoryDao;
import dekanat.entity.Auditory;
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

public class AuditoryController {

    public Button submitButton;
    public TextField numberOfAuditory;
    public TextField building;

    AuditoryDao auditoryDao = new AuditoryDao();

    public void addAuditory(MouseEvent mouseEvent) throws IOException {
        Auditory auditory = new Auditory();
        auditory.setNumber(numberOfAuditory.getText());
        auditory.setBuilding(Integer.valueOf(building.getText()));
        int auditoryId = auditoryDao.addAuditory(auditory);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + auditoryId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();

    }

    public void editAuditory(MouseEvent mouseEvent) {
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
