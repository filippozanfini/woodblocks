package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeL2180 extends DraggableNode {
    public DraggableNodeL2180() {
        super();
        this.setScore(5);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"L2_180"+".png", 150, 150, false, false)));

    }
    @Override
    public void setColor(MouseEvent e,GridPane gameMatrix){
        int x =(int)((this.getLayoutX()-30)/51)%100-1;
        int y=(int)(this.getLayoutY()/51)%100-1;
    

        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y+1);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x+1, y+2);
        gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x-1, y);


        e.consume();
        
    }
}
