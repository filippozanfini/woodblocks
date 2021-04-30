package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button settingsButton;

    @FXML
    private Button startButton;

    private Stage stage;
    
    @FXML
    void startGame(ActionEvent event) {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/view/WoodBlock.fxml"));
        BorderPane root2;
        try {
            root2 = (BorderPane) loader2.load();
            WoodBlockController rc = loader2.getController();
            rc.init(stage);
            Scene scene = new Scene(root2,735,750);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } 
      
    }

    @FXML
    void settingsGame(ActionEvent event) {

    }
    public void init(Stage g){
        stage = g;
    }
   

    


}
