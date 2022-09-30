package es.imaut.automouse;

import es.imaut.automouse.timer.TimerService;
import es.imaut.automouse.ui.SettingsForm;
import es.imaut.automouse.ui.SettingsGrid;

public final class AutomouseController {
    private boolean running;

    private AutomouseController() {
    }

    private static final class AutomouseControllerInstanceHolder {
        private static final AutomouseController INSTANCE = new AutomouseController();
    }

    public static AutomouseController getInstance() {
        return AutomouseControllerInstanceHolder.INSTANCE;
    }

    public void start() {
        if (!running) {
            running = true;
            AutomouseStartEvent event = new AutomouseStartEvent(SettingsForm.getInstance());
            SettingsGrid.getInstance().onStart(event);
            TimerService.getInstance().onStart(event);
        }
    }

    public void stop() {
        if (running) {
            running = false;
            AutomouseStopEvent event = new AutomouseStopEvent();
            SettingsGrid.getInstance().onStop(event);
            TimerService.getInstance().onStop(event);
        }
    }
}
