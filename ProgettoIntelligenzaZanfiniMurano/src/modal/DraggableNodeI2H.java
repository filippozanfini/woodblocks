package modal;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
@Id("in")
public class DraggableNodeI2H extends DraggableNode {

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

    public DraggableNodeI2H() {
        super();
        rectangle1 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle2 = new Rectangle(42,42,Color.web("A1866B")); 
        this.setScore(2);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"I2H"+".png", 150, 150, false, false)));
        this.setType(this.getClass().getName());
    }

    @Override
    public boolean checkSpace(int x, int y) {
        if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean setColor(GridPane gameMatrix, boolean conferma) {
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;

        if(!checkAvailability(x, y) || !checkAvailability(x+1, y)) {
            rimuoviAnteprima(gameMatrix);
            return false;
        }

        if(conferma) {
        	
            rimuoviAnteprima(gameMatrix);
            
            if(GameMatrix.checkAvailability(x, y) && GameMatrix.checkAvailability(x+1, y)) {
                aggiungiBlocco(gameMatrix, x, y);
                GameMatrix.checkFull(gameMatrix);
                return true;
            } 
            else {
                return false;
            }
        }
        else {
                if(gameMatrix.getChildren().contains(rectangle1) && gameMatrix.getChildren().contains(rectangle2)){
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

          node.setLayoutX(150);
          node.setLayoutY(550);
          aggiungiBlocco(gameMatrix, x, y);
          GameMatrix.checkFull(gameMatrix);
                 
          return true;
        }
    private void mostraAnteprima(GridPane gameMatrix, int x, int y) {
      
        if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y)) {
            return;
        }

        gameMatrix.add(rectangle1,x, y);
        gameMatrix.add(rectangle2,x+1, y);
    }

    private void rimuoviAnteprima(GridPane gameMatrix) {
        gameMatrix.getChildren().remove(rectangle1);
        gameMatrix.getChildren().remove(rectangle2);
    }

    private void aggiungiBlocco(GridPane gameMatrix, int x, int y) {
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y);


        GameMatrix.add(x, y, this.getType());
        GameMatrix.add(x+1, y, this.getType());
    }

}
