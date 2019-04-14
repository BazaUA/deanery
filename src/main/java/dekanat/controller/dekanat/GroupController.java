package dekanat.controller.dekanat;

import dekanat.dao.sesiaDao.GroupDao;
import dekanat.entity.sesiaEntity.Group;
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

public class GroupController {
    public Button submitButton;
    public TextField nameOfCourse;
    public TextField numberOfGroup;
    public TextField nameOfStream;

    GroupDao groupDao = new GroupDao();

    public void addGroup(MouseEvent mouseEvent) throws IOException {
        Group group = new Group();
        group.setStream_name(nameOfStream.getText());
        group.setCourseName(nameOfCourse.getText());
        group.setGroupNumber(Integer.valueOf(numberOfGroup.getText()));
        int groupId = groupDao.addGroup(group);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + groupId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();
    }

    public void editGroup(MouseEvent mouseEvent) {
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
