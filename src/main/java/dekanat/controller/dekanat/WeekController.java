package dekanat.controller.dekanat;

import dekanat.dao.WeekDao;
import dekanat.entity.WeekEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class WeekController {

    public Button submitButton;
    public TextField numberOfWeek;
    public DatePicker week_start;
    public DatePicker week_end;
    public TextField week_semester;

    WeekDao weekDao= new WeekDao();

    private void rerenderMainPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Деканат");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

        Stage old = (Stage) submitButton.getScene().getWindow();
        old.close();
    }
    public void addWeek(MouseEvent mouseEvent) throws IOException {
        WeekEntity weekEntity = new WeekEntity();
        weekEntity.setNumber(Integer.valueOf(numberOfWeek.getText()));
        LocalDate localDate = week_start.getValue();

        Instant instantStart = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        weekEntity.setStart_date(Date.from(instantStart));

        Instant instantEnd = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        weekEntity.setEnd_date(Date.from(instantEnd));

        weekEntity.setEnd_date(Date.from(instantEnd));

        weekEntity.setSemester_id(Integer.valueOf(week_semester.getText()));
        int weekId = weekDao.addWeek(weekEntity);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Додано успішно. ID: " + weekId, ButtonType.OK);
        alert.showAndWait();

        rerenderMainPage();

        Stage addingStage = (Stage) submitButton.getScene().getWindow();

        addingStage.close();

    }

    public void editWeek(MouseEvent mouseEvent) {
    }
}
