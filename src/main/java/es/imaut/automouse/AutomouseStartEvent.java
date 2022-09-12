package es.imaut.automouse;

import es.imaut.automouse.ui.SettingsForm;

public class AutomouseStartEvent implements AutomouseEvent {
    private final SettingsForm form;

    public AutomouseStartEvent(SettingsForm form) {
        this.form = form;
    }

    public SettingsForm getForm() {
        return form;
    }
}
