package es.imaut.automouse.ui;

public final class SettingsForm {
    private static final int DEFAULT_SLEEP_INTERVAL = 30;
    private int sleepInterval = DEFAULT_SLEEP_INTERVAL;

    private SettingsForm() {
    }

    private static final class SettingsFormInstanceHolder {
        private static final SettingsForm INSTANCE = new SettingsForm();
    }

    public static SettingsForm getInstance() {
        return SettingsFormInstanceHolder.INSTANCE;
    }

    public int getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }
}
