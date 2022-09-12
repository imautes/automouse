package es.imaut.automouse.timer;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Timer {
    private ScheduledExecutorService executor;

    public void start(int sleepInterval, Runnable runnable) {
        this.executor = newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(runnable, 0, sleepInterval, SECONDS);
    }

    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(0, SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
