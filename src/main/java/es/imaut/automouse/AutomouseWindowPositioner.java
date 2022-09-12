package es.imaut.automouse;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.stage.Screen.getPrimary;

public final class AutomouseWindowPositioner {
    public static void position(Stage stage) {
        Rectangle2D screen = getPrimary().getBounds();
        Scene scene = stage.getScene();
        double x = screen.getWidth() / 2 - scene.getWidth() / 2;
        double y = screen.getHeight() / 2 - scene.getHeight() / 2;
        stage.setX(x);
        stage.setY(y);
    }
}
