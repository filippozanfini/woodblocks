package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class WoodBlockController implements Initializable {

    @FXML
    private GridPane gameMatrix;

    @FXML
    private Label recordLabel;

    @FXML
    private ImageView block1;

    @FXML
    private ImageView block2;

    @FXML
    private ImageView block3;

    @FXML
    private Label currentRecord;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	currentRecord.setText("0");
    	recordLabel.setText("0");
    }

}

