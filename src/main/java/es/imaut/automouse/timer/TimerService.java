package es.imaut.automouse.timer;

import es.imaut.automouse.AutomouseStartEvent;
import es.imaut.automouse.AutomouseStopEvent;

public final class TimerService {
    private final Timer timer = new Timer();
    private Runnable action;

    private TimerService() {
    }

    private static final class TimerServiceInstanceHolder {
        private static final TimerService INSTANCE = new TimerService();
    }

    public static TimerService getInstance() {
        return TimerServiceInstanceHolder.INSTANCE;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public void onStart(AutomouseStartEvent event) {
        timer.start(event.getForm().getSleepInterval(), action);
    }

    public void onStop(AutomouseStopEvent event) {
        timer.stop();
    }
}
