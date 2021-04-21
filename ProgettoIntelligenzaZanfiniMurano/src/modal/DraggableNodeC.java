package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeC extends DraggableNode {
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;

    public DraggableNodeC() {
        super();
        this.setScore(4);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"C"+".png", 150, 150, false, false)));
        rectangle1 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle2 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle3 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle4 = new Rectangle(42,42,Color.web("A1866B"));
        this.setType(this.getClass().getName());
    }

    @Override
    public boolean checkSpace(int x, int y) {
        if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y) || !GameMatrix.checkAvailability(x, y+1) || !GameMatrix.checkAvailability(x+1, y+1)) {
            return false;
        }

        return true;
    }    

    @Override
    public boolean setColor(GridPane gameMatrix, boolean conferma){
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;
    
        if(!checkAvailability(x, y) || !checkAvailability(x+1, y) || !checkAvailability(x, y+1) || !checkAvailability(x+1, y+1)) {
            rimuoviAnteprima(gameMatrix);
            return false;
        }
        if(conferma) {
            rimuoviAnteprima(gameMatrix);
            if(GameMatrix.checkAvailability(x, y) && GameMatrix.checkAvailability(x+1, y) && GameMatrix.checkAvailability(x, y+1) && GameMatrix.checkAvailability(x+1, y+1)) {
                aggiungiBlocco(gameMatrix, x, y);
                return true;
            }
            else {
                return false;
            }
        } 
        else {
            if(gameMatrix.getChildren().contains(rectangle1) && gameMatrix.getChildren().contains(rectangle2) 
            && gameMatrix.getChildren().contains(rectangle3) && gameMatrix.getChildren().contains(rectangle4)) {
                rimuoviAnteprima(gameMatrix);
                mostraAnteprima(gameMatrix, x, y);
            } 
            else {
                mostraAnteprima(gameMatrix, x, y);
            }
        }
            return false;
        }

        private void mostraAnteprima(GridPane gameMatrix, int x, int y) {
    
            if(!GameMatrix.checkAvailability(x, y) || !GameMatrix.checkAvailability(x+1, y) || !GameMatrix.checkAvailability(x, y+1) || !GameMatrix.checkAvailability(x+1, y+1)) {
                return;
            }

            gameMatrix.add(rectangle1,x, y);
            gameMatrix.add(rectangle2,x+1, y);
            gameMatrix.add(rectangle3,x, y+1);
            gameMatrix.add(rectangle4,x+1, y+1);
        }

        private void rimuoviAnteprima(GridPane gameMatrix) {
            gameMatrix.getChildren().remove(rectangle1);
            gameMatrix.getChildren().remove(rectangle2);
            gameMatrix.getChildren().remove(rectangle3);
            gameMatrix.getChildren().remove(rectangle4);
        }
        private void aggiungiBlocco(GridPane gameMatrix, int x, int y) {
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y+1);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y+1);
    
            GameMatrix.add(x, y, this.getType());
            GameMatrix.add(x+1, y, this.getType());
            GameMatrix.add(x, y+1, this.getType());
            GameMatrix.add(x+1, y+1, this.getType());
            GameMatrix.checkFull(gameMatrix);
        }

}
