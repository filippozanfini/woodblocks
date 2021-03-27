
package controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import modal.DraggableNode;
import modal.GameMatrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class WoodBlockController implements Initializable {

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
    private Label currentRecord;
    private DraggableNode node1;
    private DraggableNode node2;
    private DraggableNode node3;
    
    private int nodeCount;
    private GameMatrix matrix;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
      matrix = GameMatrix.getInstance();
      currentRecord.setText("0");
    	recordLabel.setText("0");
    	initBlocks();
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
             
              if(nodeCount == 1) {
            	  initBlocks();
              } else {
            	  nodeCount--;
              }
            } else {
              node1.setLayoutX(165);
              node1.setLayoutY(595);
            }       
        });
        
        node2.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
          if(node2.setColor(gameMatrix, true)) {
            incrementCurrentRecord(node2);
            borderpane.getChildren().remove(node2);
           
            if(nodeCount == 1) {
              initBlocks();
            } else {
              nodeCount--;
            }
          } else {
            node2.setLayoutX(315);
            node2.setLayoutY(595);
          }
        });
        
        node3.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
          if(node3.setColor(gameMatrix, true)) {
            incrementCurrentRecord(node3);
            borderpane.getChildren().remove(node3);
           
            if(nodeCount == 1) {
              initBlocks();
            } else {
              nodeCount--;
            }
          } else {
            node3.setLayoutX(470);
            node3.setLayoutY(595);
          }
         });
    	
    	node1.setLayoutX(165);
      node1.setLayoutY(595);
      node2.setLayoutX(315);
      node2.setLayoutY(595);
      node3.setLayoutX(470);
      node3.setLayoutY(595);
          
      borderpane.getChildren().add(node1);
      borderpane.getChildren().add(node2);
      borderpane.getChildren().add(node3);
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
    	System.out.println("numero random "+ n);
    	String name = "modal.DraggableNodeT90";
    	System.out.println("name : "+ name);
    	return name;
    }
}


