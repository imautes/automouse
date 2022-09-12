package es.imaut.automouse.mouse;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class RandomMouseMover implements Runnable {
    private Stage stage;

    private RandomMouseMover() {
    }

    private static final class RandomMouseMoverInstanceHolder {
        private static final RandomMouseMover INSTANCE = new RandomMouseMover();
    }

    public static RandomMouseMover getInstance() {
        return RandomMouseMoverInstanceHolder.INSTANCE;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        Scene scene = stage.getScene();
        double minX = stage.getX();
        double maxX = stage.getX() + scene.getWidth();
        double minY = stage.getY();
        double maxY = stage.getY() + scene.getHeight();
        new RandomMouse((int) minX, (int) maxX, (int) minY, (int) maxY).move();
    }
}
