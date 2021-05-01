package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
@Id("in")
public class DraggableNodeL2180 extends DraggableNode {
    
    @Param(0)
    private int ID;    
    @Param(1)
    private int row = 0;
    @Param(2)
    private int col = 0;
    @Param(3)
    private String type;

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;
    private Rectangle rectangle5;

    public DraggableNodeL2180() {

        super();
        rectangle1 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle2 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle3 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle4 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle5 = new Rectangle(42,42,Color.web("A1866B")); 

        this.setScore(5);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"L2_180"+".png", 150, 150, false, false)));
        this.setType(this.getClass().getName());
    }

    @Override
    public boolean checkSpace(int x, int y) {
        
        if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y) || !GameMatrix.checkAvailability(x+1, y+1) || !GameMatrix.checkAvailability(x+1, y+2) || !GameMatrix.checkAvailability(x-1, y)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean setColor(GridPane gameMatrix, boolean conferma) {
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;

        if(!checkAvailability(x, y) || !checkAvailability(x+1, y) || !checkAvailability(x+1, y+1) || !checkAvailability(x+1, y+2) || !checkAvailability(x-1, y)) {
            rimuoviAnteprima(gameMatrix);
            return false;
        }

        if(conferma) {
            rimuoviAnteprima(gameMatrix);
            if(GameMatrix.checkAvailability(x, y) && GameMatrix.checkAvailability(x+1, y) && GameMatrix.checkAvailability(x+1, y+1) && GameMatrix.checkAvailability(x+1, y+2) && GameMatrix.checkAvailability(x-1, y)) {
                aggiungiBlocco(gameMatrix, x, y);
                return true;
            } 
            else {
                return false;
            }
        }
        else {
                if(gameMatrix.getChildren().contains(rectangle1) && gameMatrix.getChildren().contains(rectangle2) &&
                    gameMatrix.getChildren().contains(rectangle3) && gameMatrix.getChildren().contains(rectangle4)){
                    rimuoviAnteprima(gameMatrix);
                    mostraAnteprima(gameMatrix, x, y);
                } 
                else {
                    mostraAnteprima(gameMatrix, x, y);
                }
                return false;
        }
    }
    @Override
    public boolean setColorEMBASP(GridPane gameMatrix, boolean conferma,int x,int y,DraggableNode node){
        System.out.println("ci sono");
     
      
          node.setLayoutX(150);
          node.setLayoutY(550);
          aggiungiBlocco(gameMatrix, x, y);
        /*  Timer timer = new Timer();
          timer.schedule(new TimerTask(){
              
            @Override
            public void run() {
                aggiungiBlocco(gameMatrix, x, y);
            }
          }, 1000);*/
                 
          return true;
        }
    private void mostraAnteprima(GridPane gameMatrix, int x, int y) {
       
        if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y) || !GameMatrix.checkAvailability(x+1, y+1) || !GameMatrix.checkAvailability(x+1, y+2) || !GameMatrix.checkAvailability(x-1, y)) {
            return;
        }

        gameMatrix.add(rectangle1,x, y);
        gameMatrix.add(rectangle2,x+1, y);
        gameMatrix.add(rectangle3,x+1, y+1);
        gameMatrix.add(rectangle4,x+1, y+2);
        gameMatrix.add(rectangle5,x-1, y);

    }

    private void rimuoviAnteprima(GridPane gameMatrix) {
        gameMatrix.getChildren().remove(rectangle1);
        gameMatrix.getChildren().remove(rectangle2);
        gameMatrix.getChildren().remove(rectangle3);
        gameMatrix.getChildren().remove(rectangle4);
        gameMatrix.getChildren().remove(rectangle5);
    }

    private void aggiungiBlocco(GridPane gameMatrix, int x, int y) {
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y+1);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y+2);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x-1, y);

        GameMatrix.add(x, y, this.getType());
        GameMatrix.add(x+1, y, this.getType());
        GameMatrix.add(x+1, y+1, this.getType());
        GameMatrix.add(x+1, y+2, this.getType());
        GameMatrix.add(x-1, y, this.getType());
        GameMatrix.checkFull(gameMatrix);

    }


}
