package es.imaut.automouse.mouse;

import java.awt.*;

public class Mouse {
    private final Robot robot;

    public Mouse() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(int x, int y) {
        robot.mouseMove(x, y);
    }
}
