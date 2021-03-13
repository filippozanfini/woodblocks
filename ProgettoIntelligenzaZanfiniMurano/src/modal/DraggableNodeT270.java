package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeT270 extends DraggableNode {
    public DraggableNodeT270() {
        super();
        this.setScore(4);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"T270"+".png", 150, 150, false, false)));

    }
    @Override
    public void setColor(MouseEvent e,GridPane gameMatrix){
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;
    

        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y-1);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y-2);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x-1, y-1);

        e.consume();
        
    }
}
