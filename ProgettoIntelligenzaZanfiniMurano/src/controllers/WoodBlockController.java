package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import modal.*;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

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
  private final static int SIZE = 10;
  private InputProgram facts;
  private Block b1;
  private Block b2;
  private Block b3;
  private Output o;
  private static String encodingResource="ProgettoIntelligenzaZanfiniMurano/src/encodings/wood.txt";
  private ScheduledExecutorService executorService;
  private static Handler handler;  

  public void init(Stage g) throws Exception { 
    
    stage = g;
    matrix = GameMatrix.getInstance();
    GameMatrix.cleanAll();
    facts = new ASPInputProgram();
    currentRecord.setText("0");
    initBlocks(); 

    try {
      File recordFile = new File("record.txt");
      if(recordFile.createNewFile()) {
        saveRecordOnFile();
      } else {
        String record = getRecordFile();
        recordLabel.setText(record);
      }
    } catch(IOException e) {
      e.printStackTrace();
    } 

    init_embasp();
  }

  // EMBASP
  public void init_embasp() throws Exception{

    handler = new DesktopHandler(new DLV2DesktopService("ProgettoIntelligenzaZanfiniMurano/src/lib/dlv2.exe"));
    //handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));
    //handler = new DesktopHandler(new DLV2DesktopService("ProgettoIntelligenzaZanfiniMurano/src/lib/dlv2-mac"));

    try {
      ASPMapper.getInstance().registerClass(DraggableNode.class);
      ASPMapper.getInstance().registerClass(DraggableNodeB.class);
      ASPMapper.getInstance().registerClass(DraggableNodeC.class);
      ASPMapper.getInstance().registerClass(DraggableNodeI2H.class);
      ASPMapper.getInstance().registerClass(DraggableNodeI2V.class);
      ASPMapper.getInstance().registerClass(DraggableNodeIH.class);
      ASPMapper.getInstance().registerClass(DraggableNodeIV.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL2.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL90.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL180.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL270.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL290.class);
      ASPMapper.getInstance().registerClass(DraggableNodeL2270.class);
      ASPMapper.getInstance().registerClass(DraggableNodeS.class);
      ASPMapper.getInstance().registerClass(DraggableNodeS90.class);
      ASPMapper.getInstance().registerClass(DraggableNodeS180.class);
      ASPMapper.getInstance().registerClass(DraggableNodeS270.class);
      ASPMapper.getInstance().registerClass(DraggableNodeT.class);
      ASPMapper.getInstance().registerClass(DraggableNodeT90.class);
      ASPMapper.getInstance().registerClass(DraggableNodeT180.class);
      ASPMapper.getInstance().registerClass(DraggableNodeT270.class);
      ASPMapper.getInstance().registerClass(Block.class);
      ASPMapper.getInstance().registerClass(FullCell.class);
      ASPMapper.getInstance().registerClass(NewFull.class);


    } catch (ObjectNotValidException | IllegalAnnotationException e1) {
      e1.printStackTrace();
    }

  // 1. carico i blocchi da aggiungere come fatti
  // 2. carico le caselle piene come fatti
  
  //InputProgram facts = new ASPInputProgram();
     // fatti variabili

    facts.clearAll();
    handler.removeProgram(facts);
    handler.removeAll();
      
    InputProgram encoding = new ASPInputProgram();
    encoding.addFilesPath(encodingResource);
    handler.addProgram(encoding);
    OptionDescriptor option = new OptionDescriptor("--filter=in/4");
    OptionDescriptor option2= new OptionDescriptor("--filter=block/2");

    //handler.addOption(option);
    //handler.addOption(option2);


    Task<Void> task = new Task<Void>() {
      boolean play = true;
      @Override
      public Void call() {
          while(play) {
            try {
              Thread.sleep(500);
              facts.clearAll();
              handler.removeProgram(facts);
              add_temporary_facts(handler);
              o = handler.startSync(); 
              if(next(o) == false)
                break;
            } catch(Exception e) {
              System.out.println(e.getMessage());
            }
  
            Platform.runLater(() -> {
              play = next_move(o);
              
            });
          }
          
              
          return null;
      }
  };

  executorService = Executors.newSingleThreadScheduledExecutor();
  executorService.schedule(task, 1, TimeUnit.SECONDS);
  }
   
  private boolean next_move(Output o)  {
    AnswerSets answersets = (AnswerSets) o;
    System.out.println("ans " + answersets.getAnswerSetsString());
    GameMatrix.checkFull(gameMatrix);
    boolean trovato = false;
    DraggableNode block = null;
    
    for(AnswerSet a:answersets.getAnswersets()) {
      try { 
        for(Object obj:a.getAtoms()){
          //System.out.println(obj.toString());
          if(obj instanceof DraggableNode){
            block = (DraggableNode) obj;
            if(block.getID() == 1){
              node1.setColorEMBASP(gameMatrix, true,block.getRow(),block.getCol(), node1);
              borderpane.getChildren().remove(node1);
              incrementCurrentRecord(node1);
              trovato = true;

            }
            else if(block.getID() == 2){
              node2.setColorEMBASP(gameMatrix, true,block.getRow(),block.getCol(), node2);
              borderpane.getChildren().remove(node2);
              incrementCurrentRecord(node2);
              trovato = true;
            }
            else if(block.getID() == 3){
              node3.setColorEMBASP(gameMatrix, true,block.getRow(),block.getCol(), node3);
              borderpane.getChildren().remove(node3);
              incrementCurrentRecord(node3);
              trovato = true;
            
            }
        
            if(nodeCount == 1) {
              initBlocks();
            } else {
              nodeCount--;
            }
        
            GameMatrix.add(block.getRow(), block.getCol(), block.getType());
          }
       }
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
    if(!trovato || block == null ){
      executorService.shutdown();
      gameOverAlert();
      return false;
    }
    return true;
  }
  public boolean next(Output o)  {
    AnswerSets answersets = (AnswerSets) o;
    for(AnswerSet a:answersets.getAnswersets()) {
      try { 
        for(Object obj:a.getAtoms())
          if(obj instanceof DraggableNode)
           return true;
      }catch(Exception e){e.printStackTrace();}
    }
    return false;
  }
            

    @FXML
    void goBack(ActionEvent event) {
      FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/view/Start.fxml"));
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
      }
     
    
    }
    
    private void initBlocks() {
    	nodeCount = 3;
     

    	try {
            node1 = (DraggableNode) Class.forName(randomblock()).newInstance();
          //  node1 = new DraggableNodeC();
            node1.setPane(gameMatrix);
            node1.setID(1);
            node2 = (DraggableNode) Class.forName(randomblock()).newInstance();
            //node2 = new DraggableNodeC();
            node2.setPane(gameMatrix);
            node2.setID(2);
            node3 = (DraggableNode) Class.forName(randomblock()).newInstance();
           // node3 = new DraggableNodeC();
            node3.setPane(gameMatrix);
            node3.setID(3);

          } catch (InstantiationException e1) {
            e1.printStackTrace();
          } catch (IllegalAccessException e1) {
            e1.printStackTrace();
          } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
          } catch (Exception e1) {
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
                executorService.shutdown();
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
        executorService.shutdown();
        gameOverAlert();
        return;
      }
    }
    
    private void incrementCurrentRecord(DraggableNode node) {
    	Integer current = Integer.parseInt(currentRecord.getText());
    	currentRecord.setText(Integer.toString(current+node.getScore()));
    }
  
    private String randomblock(){

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

    private void gameOverAlert() {
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
        saveRecordOnFile();
        GameMatrix.cleanAll(); 
        restart();
      } else {
        saveRecordOnFile();
        System.exit(0);
      }
        
      });
    }

    private void restart() {
      FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/view/WoodBlock.fxml"));
      BorderPane root2;
      try {
          root2 = (BorderPane) loader2.load();
          WoodBlockController rc = loader2.getController();
          try {
            rc.init(stage);
          } catch (Exception e) {
            e.printStackTrace();
          }
          Scene scene = new Scene(root2,735,750);
          stage.setScene(scene);
          stage.show();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    private void saveRecordOnFile() {
      try {
        Integer current = Integer.parseInt(currentRecord.getText());
        Integer record = Integer.parseInt(recordLabel.getText());

        if(current > record) {
          FileWriter writer = new FileWriter("record.txt");
          writer.write(currentRecord.getText());
          writer.close();
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

    private String getRecordFile() {
      String record = "0";
      try {
        File recordFile = new File("record.txt");
        Scanner myReader = new Scanner(recordFile);
        while (myReader.hasNextLine()) {
          record = myReader.nextLine();
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      
      return record;
    }

    private void add_temporary_facts(Handler handler){ 
      for(int i=0; i<SIZE ; i++) {
			  for(int j=0; j<SIZE; j++) {
				  if(GameMatrix.get(i, j) != 0) { // aggiungo ai fatti le caselle già riempite 
					  try {
               // System.out.println("piena "+ i + " " + j);
                facts.addObjectInput(new FullCell(i,j));
            } catch (Exception e) {
                e.printStackTrace();
              }
          }
			  }
		  }
      
      try {
        if(borderpane.getChildren().contains(node1)){
          System.out.println(" nodo1 " + node1.getType());
          
          b1 = new Block(1,node1.getType());
          facts.addObjectInput(b1);
        }
        if(borderpane.getChildren().contains(node2)){
          System.out.println(" nodo2 " + node2.getType());
          b2 = new Block(2,node2.getType());
          facts.addObjectInput(b2);
        }
        if(borderpane.getChildren().contains(node3)){
          System.out.println(" nodo3 " + node3.getType());
          b3 = new Block(3,node3.getType());
          facts.addObjectInput(b3);
        }
      } catch (Exception e) {
        e.printStackTrace();}
   
    handler.addProgram(facts);
    handler.startSync();
   
  }
}


