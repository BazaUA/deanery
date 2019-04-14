package dekanat.controller.dekanat;

import dekanat.dao.StreamDao;
import dekanat.entity.sesiaEntity.StreamEntity;
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

public class StreamController {
    public Button submitButton;
    public TextField nameOfStream;

    public void addStream(MouseEvent mouseEvent) throws IOException {
        StreamEntity streamEntity = new StreamEntity();
        streamEntity.setName(nameOfStream.getText());
        int stremId = streamDao.addStream(streamEntity);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + stremId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }

    public void editStream(MouseEvent mouseEvent) {
    }

    StreamDao streamDao= new StreamDao();

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
