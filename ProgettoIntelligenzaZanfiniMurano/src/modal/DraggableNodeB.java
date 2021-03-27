package modal;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DraggableNodeB extends DraggableNode {
	
    public DraggableNodeB() {
        super();
        this.setScore(1);
        this.setView(new ImageView(new Image("/assets/Blocks/"+"B"+".png", 150, 150, false, false)));

    }
    @Override
    public boolean setColor(GridPane gameMatrix, boolean conferma){
        int x = (int)((this.getLayoutX()-30)/51)%100-1;
        int y = (int)(this.getLayoutY()/51)%100-1;

        System.out.println(x);
        System.out.println(y);
    
        if(conferma) {
            gameMatrix.add(new Rectangle(42,42,Color.web("725A42")),x, y);
        } else {
            gameMatrix.add(new Rectangle(42,42,Color.web("CCC")),x, y);
        }

        return true;
    }
}
