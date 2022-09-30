package es.imaut.automouse;

import es.imaut.automouse.mouse.RandomMouseMover;
import es.imaut.automouse.timer.TimerService;
import es.imaut.automouse.ui.SettingsGrid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

import static es.imaut.automouse.AutomouseWindowPositioner.position;
import static java.util.Objects.requireNonNull;

public class AutomouseApplication extends Application {
    private static final double WIDTH = 390;
    private static final double HEIGHT = 190;
    private static final String TITLE = "Automouse";

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getIconSource()));
        stage.setScene(new Scene(SettingsGrid.getInstance(), WIDTH, HEIGHT));
        stage.setOnCloseRequest(event -> AutomouseController.getInstance().stop());
        position(stage);
        RandomMouseMover.getInstance().setStage(stage);
        TimerService.getInstance().setAction(RandomMouseMover.getInstance());
        stage.show();
    }

    private InputStream getIconSource() {
        return requireNonNull(getClass().getClassLoader().getResourceAsStream("img/icon.jpg"));
    }

    public static void main(String[] args) {
        launch();
    }
}
