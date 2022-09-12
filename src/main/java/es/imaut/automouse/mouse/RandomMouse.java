package es.imaut.automouse.mouse;

import java.util.Random;

public class RandomMouse extends Mouse {
    private final int maxX;
    private final int minX;
    private final int maxY;
    private final int minY;
    private final Random random;

    public RandomMouse(int minX, int maxX, int minY, int maxY) {
        this.maxX = maxX;
        this.minX = minX;
        this.maxY = maxY;
        this.minY = minY;
        this.random = new Random();
    }

    public void move() {
        move(random.nextInt(maxX - minX) + minX, random.nextInt(maxY - minY) + minY);
    }
}
