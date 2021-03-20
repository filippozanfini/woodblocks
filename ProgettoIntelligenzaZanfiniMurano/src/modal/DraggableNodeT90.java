package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeT90 extends DraggableNode {

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;

    public DraggableNodeT90() {
        super();
        this.setScore(4);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"T90"+".png", 150, 150, false, false)));

        rectangle1 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle2 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle3 = new Rectangle(42,42,Color.web("A1866B")); 
        rectangle4 = new Rectangle(42,42,Color.web("A1866B"));

    }
    @Override
    public void setColor(GridPane gameMatrix, boolean conferma){
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;
    
        if(conferma) {
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y-1);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y-2);
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y-1);
        } else {
            if(gameMatrix.getChildren().contains(rectangle1) && gameMatrix.getChildren().contains(rectangle2) 
            && gameMatrix.getChildren().contains(rectangle3) && gameMatrix.getChildren().contains(rectangle4)) {
                gameMatrix.getChildren().remove(rectangle1);
                gameMatrix.getChildren().remove(rectangle2);
                gameMatrix.getChildren().remove(rectangle3);
                gameMatrix.getChildren().remove(rectangle4);
                gameMatrix.add(rectangle1,x, y);
                gameMatrix.add(rectangle2,x, y-1);
                gameMatrix.add(rectangle3,x, y-2);
                gameMatrix.add(rectangle4,x+1, y-1);
            } else {
                gameMatrix.add(rectangle1,x, y);
                gameMatrix.add(rectangle2,x, y-1);
                gameMatrix.add(rectangle3,x, y-2);
                gameMatrix.add(rectangle4,x+1, y-1);
            }
        }
    }
}
