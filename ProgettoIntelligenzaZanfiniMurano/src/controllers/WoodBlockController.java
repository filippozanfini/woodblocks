
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import modal.DraggableNode;
import modal.GameMatrix;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class WoodBlockController{

    @FXML
    private HBox boxblocks;

    @FXML
    private GridPane gameMatrix;

    @FXML
    private Label recordLabel;

    @FXML
    private BorderPane borderpane;

    @FXML
    private AnchorPane anchorblocks;

    @FXML
    private Button back_button;

    @FXML
    private Label currentRecord;
    private DraggableNode node1;
    private DraggableNode node2;
    private DraggableNode node3;
    
    private int nodeCount;
    private GameMatrix matrix;
    private Stage stage;
    
 
    public void init(Stage g) { 
      stage = g;
      matrix = GameMatrix.getInstance();
     
      currentRecord.setText("0");
    	recordLabel.setText("0");
    	initBlocks(); 
    }
        
    @FXML
    void goBack(ActionEvent event) {
      /*FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/view/Start.fxml"));
      BorderPane root2;
      try {
          root2 = (BorderPane) loader2.load();
          StartController rc = loader2.getController();
          rc.init(stage);
          Scene scene = new Scene(root2,735,750);
          stage.setScene(scene);
          stage.show();
      } catch (IOException e) {
          e.printStackTrace();
      }*/
      gameOverAlert();
    

    }
    
    public void initBlocks() {
    	nodeCount = 3;
    	
    	try {
            node1 = (DraggableNode) Class.forName(randomblock()).newInstance();
            node1.setPane(gameMatrix);
            node2 = (DraggableNode) Class.forName(randomblock()).newInstance();
            node2.setPane(gameMatrix);
            node3 = (DraggableNode) Class.forName(randomblock()).newInstance();
            node3.setPane(gameMatrix);
          } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
    
    	
    	node1.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            if(node1.setColor(gameMatrix, true)) {
              incrementCurrentRecord(node1);
              borderpane.getChildren().remove(node1);

              int spaceCount = 0;
              
              if(borderpane.getChildren().contains(node2)) {
                if(GameMatrix.checkBlockAvailability(node2)) {
                  spaceCount++;
                }
              }

              if(borderpane.getChildren().contains(node3)) {
                if(GameMatrix.checkBlockAvailability(node3)) {
                  spaceCount++;
                }
              }

              if((borderpane.getChildren().contains(node2) || borderpane.getChildren().contains(node3)) && spaceCount == 0) {
                gameOverAlert();
                return;
              }

              if(nodeCount == 1) {
            	  initBlocks();
              } else {
            	  nodeCount--;
              }
            } else {
              node1.setLayoutX(130);
              node1.setLayoutY(600);
            }       
        });
        
        node2.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
          if(node2.setColor(gameMatrix, true)) {
            incrementCurrentRecord(node2);
            borderpane.getChildren().remove(node2);

            int spaceCount = 0;
              
              if(borderpane.getChildren().contains(node1)) {
                if(GameMatrix.checkBlockAvailability(node1)) {
                  spaceCount++;
                }
              }

              if(borderpane.getChildren().contains(node3)) {
                if(GameMatrix.checkBlockAvailability(node3)) {
                  spaceCount++;
                }
              }

              if((borderpane.getChildren().contains(node1) || borderpane.getChildren().contains(node3)) && spaceCount == 0) {
                gameOverAlert();
                return;
              }
           
            if(nodeCount == 1) {
              initBlocks();
            } else {
              nodeCount--;
            }
          } else {
            node2.setLayoutX(290);
            node2.setLayoutY(600);
          }
        });
        
        node3.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
          if(node3.setColor(gameMatrix, true)) {
            incrementCurrentRecord(node3);
            borderpane.getChildren().remove(node3);

            int spaceCount = 0;
              
              if(borderpane.getChildren().contains(node1)) {
                if(GameMatrix.checkBlockAvailability(node1)) {
                  spaceCount++;
                }
              }

              if(borderpane.getChildren().contains(node2)) {
                if(GameMatrix.checkBlockAvailability(node2)) {
                  spaceCount++;
                }
              }

              if((borderpane.getChildren().contains(node1) || borderpane.getChildren().contains(node2)) && spaceCount == 0) {
                gameOverAlert();
                return;
              }
           
            if(nodeCount == 1) {
              initBlocks();
            } else {
              nodeCount--;
            }
          } else {
            node3.setLayoutX(450);
            node3.setLayoutY(600);
          
          }
         });
    	
    	node1.setLayoutX(130);
      node1.setLayoutY(600);
      node2.setLayoutX(290);
      node2.setLayoutY(600);
      node3.setLayoutX(450);
      node3.setLayoutY(600);
          
      borderpane.getChildren().add(node1);
      borderpane.getChildren().add(node2);
      borderpane.getChildren().add(node3);

      if(!GameMatrix.checkBlockAvailability(node1) && !GameMatrix.checkBlockAvailability(node2) && !GameMatrix.checkBlockAvailability(node3)) {
        gameOverAlert();
        return;
      }
    }
    
    public void incrementCurrentRecord(DraggableNode node) {
    	Integer current = Integer.parseInt(currentRecord.getText());
    	currentRecord.setText(Integer.toString(current+node.getScore()));
    }
  
    public String randomblock(){

    	String [] cls = {"modal.DraggableNodeL", "modal.DraggableNodeL90", "modal.DraggableNodeL180", "modal.DraggableNodeL270", 
    			"modal.DraggableNodeL2", "modal.DraggableNodeL290", "modal.DraggableNodeL2180", "modal.DraggableNodeL2270", "modal.DraggableNodeT",
    			"modal.DraggableNodeT90", "modal.DraggableNodeT180", "modal.DraggableNodeT270", "modal.DraggableNodeIH", "modal.DraggableNodeIV", 
    			"modal.DraggableNodeI2H", "modal.DraggableNodeI2V", "modal.DraggableNodeS", "modal.DraggableNodeS90", "modal.DraggableNodeS180", 
    			"modal.DraggableNodeS270", "modal.DraggableNodeC", "modal.DraggableNodeB"};
          Random random = new Random();
          int n = random.nextInt(22);
          String name = cls[n];
        return name;
    }

    public void gameOverAlert() {
      Image image = new Image("/assets/game_over.png",100,50, false, false);
      ImageView imageView = new ImageView(image);
      Alert alert = new Alert(AlertType.WARNING);
      Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
      stage.getIcons().add(new Image(this.getClass().getResource("/assets/blocks/logo_small_icon_only.png").toString()));
      alert.setContentText("Hai perso! Nessuna mossa disponibile.");
      alert.setGraphic(imageView);
      alert.setHeaderText("GAME OVER!");
      alert.setTitle("GAME OVER");
      alert.initStyle(StageStyle.UNDECORATED);
      alert.getDialogPane().setPadding(new Insets(30));
     
      ButtonType okButton = new ButtonType("Ricomincia", ButtonData.YES);
      ButtonType exitButton = new ButtonType("Esci", ButtonData.NO);
      alert.getButtonTypes().setAll(okButton, exitButton);
      alert.showAndWait().ifPresent(type ->  {
      if (type == okButton) { 
        restart();
      } else
        System.exit(0);
        
      });
      
      
    }
    void restart() {
      FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/view/WoodBlock.fxml"));
      BorderPane root2;
      try {
          root2 = (BorderPane) loader2.load();
          WoodBlockController rc = loader2.getController();
          rc.init(stage);
          Scene scene = new Scene(root2,735,750);
          stage.setScene(scene);
          stage.show();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}


