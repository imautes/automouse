package es.imaut.automouse.ui;

import es.imaut.automouse.AutomouseController;
import es.imaut.automouse.AutomouseStartEvent;
import es.imaut.automouse.AutomouseStopEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.InputStream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;
import static javafx.geometry.Pos.CENTER;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.ESCAPE;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontWeight.NORMAL;

public final class SettingsGrid extends GridPane {
    private TextField sleepIntervalInput;
    private Button startStopButton;

    private SettingsGrid() {
        setAlignment(CENTER);
        setHgap(10);
        setVgap(10);

        initBackground();
        initTitle();
        initSleepIntervalInput();
        initStartButton();
        initEventHandlers();
    }

    private static final class SettingsGridInstanceHolder {
        private static final SettingsGrid INSTANCE = new SettingsGrid();
    }

    public static SettingsGrid getInstance() {
        return SettingsGridInstanceHolder.INSTANCE;
    }

    public void onStart(AutomouseStartEvent start) {
        sleepIntervalInput.setDisable(true);
        startStopButton.setText("Stop");
        startStopButton.setOnAction(event -> AutomouseController.getInstance().stop());
    }

    public void onStop(AutomouseStopEvent stop) {
        sleepIntervalInput.setDisable(false);
        startStopButton.setText("Start");
        startStopButton.setOnAction(event -> AutomouseController.getInstance().start());
    }

    private void initBackground() {
        setBackground(new Background(getBackgroundImage()));
    }

    private void initTitle() {
        Text title = new Text("Automouse");
        title.setFont(font("Tahoma", NORMAL, 20));
        setHalignment(title, HPos.CENTER);
        add(title, 0, 0, 2, 1);
    }

    private void initSleepIntervalInput() {
        Label sleepIntervalLabel = new Label("Sleep interval seconds");
        sleepIntervalInput = new TextField(valueOf(SettingsForm.getInstance().getSleepInterval()));
        numericOnly(sleepIntervalInput);
        addRow(1, sleepIntervalLabel, sleepIntervalInput);
    }

    private void initStartButton() {
        startStopButton = new Button("Start");
        startStopButton.setOnAction(event -> AutomouseController.getInstance().start());
        setHalignment(startStopButton, HPos.CENTER);
        add(startStopButton, 0, 2, 2, 1);
    }

    private void initEventHandlers() {
        addEventHandler(KEY_PRESSED, event -> {
            if (event.getCode() == ENTER) {
                AutomouseController.getInstance().start();
            } else if (event.getCode() == ESCAPE) {
                AutomouseController.getInstance().stop();
            }
        });
    }

    private BackgroundImage getBackgroundImage() {
        return new BackgroundImage(
                new Image(getBackgroundSource(), 400, 200, true, true),
                NO_REPEAT,
                NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
    }

    private InputStream getBackgroundSource() {
        return requireNonNull(getClass().getClassLoader().getResourceAsStream("img/background.jpg"));
    }

    private void numericOnly(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                textField.setText(valueOf(parseInt(newValue)));
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
            SettingsForm.getInstance().setSleepInterval(parseInt(textField.getText()));
        });
    }
}
