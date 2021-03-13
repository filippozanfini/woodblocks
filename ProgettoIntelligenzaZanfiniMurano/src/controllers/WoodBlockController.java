
package controllers;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import modal.DraggableNode;
import modal.DraggableNodeC;
import modal.DraggableNodeL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.shape.Rectangle;

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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        currentRecord.setText("0");
    	recordLabel.setText("0");
    	initBlocks();
    }
    
    public void initBlocks() {
    	nodeCount = 3;
    	
    	try {
            node1 = (DraggableNode) Class.forName(randomblock()).newInstance();
            node2 = (DraggableNode) Class.forName(randomblock()).newInstance();
            node3 = (DraggableNode) Class.forName(randomblock()).newInstance();
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
            node1.setColor(e, gameMatrix);
            incrementCurrentRecord(node1);
            borderpane.getChildren().remove(node1);
             
            if(nodeCount == 1) {
            	initBlocks();
            } else {
            	nodeCount--;
            }
                      
        });
        
        node2.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            node2.setColor(e, gameMatrix);
            incrementCurrentRecord(node2);
            borderpane.getChildren().remove(node2);
            
            if(nodeCount == 1) {
            	initBlocks();
            } else {
            	nodeCount--;
            }
        });
        
        node3.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            node3.setColor(e, gameMatrix);
            incrementCurrentRecord(node3);
            borderpane.getChildren().remove(node3);
            
            if(nodeCount == 1) {
            	initBlocks();
            } else {
            	nodeCount--;
            }
         });
    	
    	node1.setLayoutX(175);
        node1.setLayoutY(590);
        node2.setLayoutX(325);
        node2.setLayoutY(590);
        node3.setLayoutX(480);
        node3.setLayoutY(590);
          
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
    	String name = Array.get(cls,n).toString();
    	System.out.println("name : "+ name);
    	return name;
    }
}


