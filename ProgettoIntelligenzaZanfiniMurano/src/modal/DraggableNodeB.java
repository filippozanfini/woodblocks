package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeB extends DraggableNode {
    private Rectangle rectangle1;

    public DraggableNodeB() {
        super();
        rectangle1 = new Rectangle(42,42,Color.web("A1866B")); 
        this.setScore(1);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"B"+".png", 150, 150, false, false)));

    }
    @Override
    public boolean setColor(GridPane gameMatrix, boolean conferma){
        int x = (int)((this.getLayoutX()-30)/51)%100-1;
        int y = (int)(this.getLayoutY()/51)%100-1;
        if(!checkAvailability(x, y)){
            rimuoviAnteprima(gameMatrix);
            return false;
        }
        if(conferma) {
            rimuoviAnteprima(gameMatrix);
            if(GameMatrix.checkAvailability(x, y)) {
                aggiungiBlocco(gameMatrix, x, y);
                return true;
            } else {
                return false;
            }
        }
        else {
            if(gameMatrix.getChildren().contains(rectangle1)){
                rimuoviAnteprima(gameMatrix);
                mostraAnteprima(gameMatrix, x, y);
            } 
            else {
                mostraAnteprima(gameMatrix, x, y);
            }
            return false;
        }
    }
    
    private void mostraAnteprima(GridPane gameMatrix, int x, int y) {

        if(!GameMatrix.checkAvailability(x, y)) {
            return;
        }

        gameMatrix.add(rectangle1,x, y);
    }

    private void rimuoviAnteprima(GridPane gameMatrix) {
        gameMatrix.getChildren().remove(rectangle1);
    }

    private void aggiungiBlocco(GridPane gameMatrix, int x, int y) {
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        GameMatrix.add(x, y);
        
    }
}
