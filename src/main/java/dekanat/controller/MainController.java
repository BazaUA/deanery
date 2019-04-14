package dekanat.controller;

import dekanat.controller.schedule.CreateController;
import dekanat.controller.schedule.EditController;
import dekanat.controller.schedule.MainControllerSchedule;
import dekanat.controller.schedule.ScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public void openStudentMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/student/main.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Курс");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void openCourseMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/course/mainCourse.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Курс");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void openDepartmentMain(MouseEvent mouseEvent) {
    }

    public void openSesiaMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/sesia/mainSesia.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Розклад Сесії");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void openScheduleMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/schedule/mainSchedule.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Розклад Занять");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();


        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                "layout/sesia/mainSesia.fxml"));
        Scene scene = new Scene(parent);
        ScreenController screenController = new ScreenController(scene);
        FXMLLoader rootScreen = new FXMLLoader(getClass().getResource( "layout/schedule/mainSchedule.fxml" ));
        screenController.add("main", rootScreen.load());
        MainControllerSchedule c = rootScreen.getController();
        c.setNavigator(screenController);
        screenController.addBeforeHook("main", (Object meta)->{
            c.setSelects();
            c.handleFilter();
            return null;
        });

        FXMLLoader createScreen = new FXMLLoader(getClass().getResource( "layout/schedule/Create.fxml" ));
        screenController.add("create", createScreen.load());
        CreateController c2 = createScreen.getController();
        c2.setNavigator(screenController);
        screenController.addAfterHook("main", (Object meta)->{
            c2.setSelects();
            return null;
        });

        FXMLLoader edit = new FXMLLoader(getClass().getResource( "layout/schedule/Edit.fxml" ));
        screenController.add("edit", edit.load());
        EditController c3 = edit.getController();
        c3.setNavigator(screenController);
        screenController.addAfterHook("edit", (Object meta)->{
            c3.setValues();
            return null;
        });
        stage.setScene(scene);
        stage.setTitle("Lessons");
        stage.show();
        screenController.activate("main");

    }
}
