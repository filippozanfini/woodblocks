package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private RadioButton manualMode;

    @FXML
    private RadioButton iaMode;

    @FXML
    private Button saveSettingsBtn;

    private Stage window;

    public void init(Stage stage) {
        this.window = stage;

        if(StartController.manualMode) {
            manualMode.setSelected(true);
            iaMode.setSelected(false);
        } else {
            manualMode.setSelected(false);
            iaMode.setSelected(true);
        }
    }

    @FXML
    void saveSettingsClicked(ActionEvent event) {
        if(manualMode.isSelected()) {
            StartController.manualMode = true;
        } else {
            StartController.manualMode = false;
        }

        window.close();
    }

    @FXML
    void setManualMode(ActionEvent event) {
        iaMode.setSelected(false);
        manualMode.setSelected(true);
    }

    @FXML
    void setIAMode(ActionEvent event) {
        iaMode.setSelected(true);
        manualMode.setSelected(false);
    }

}

