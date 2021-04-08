package modal;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class DraggableNode extends Pane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mousex = 0;
    private double mousey = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;
    private GridPane pane;
    private Integer score;

    public DraggableNode() {
    	score = 0;
        init();
        
    }
    public void setPane(GridPane grid) {
        pane = grid;
    }
    public void setView(Node view) {
        this.view = view;

        getChildren().add(view);
        init();
    }
    
    public void setScore(Integer score) {
    	this.score = score;
    }
    
    public Integer getScore() {
    	return score;
    }

    private void init() {

        onMousePressedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // record the current mouse X and Y position on Node
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                x = getLayoutX();
                y = getLayoutY();
                if (isMoveToFront()) {
                    toFront();
                }
            }
        });

        //Event Listener for MouseDragged
        onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // Get the exact moved X and Y

                double offsetX = event.getSceneX() - mousex;
                double offsetY = event.getSceneY() - mousey;

                x += offsetX;
                y += offsetY;

                double scaledX = x;
                double scaledY = y;

                setLayoutX(scaledX);
                setLayoutY(scaledY);

                dragging = true;

                // again set current Mouse x AND y position
                mousex = event.getSceneX();
                mousey = event.getSceneY();  
                
                setColor(pane, false);

                event.consume();
            }
        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("3");

                dragging = false;
            }
        });

    }

    /**
     * @return the dragging
     */
    protected boolean isDragging() {
        return dragging;
    }


    /**
     * @return the view
     */
    public Node getView() {
        return view;
    }

    /**
     * @param moveToFront the moveToFront to set
     */
    public void setMoveToFront(boolean moveToFront) {
        this.moveToFront = moveToFront;
    }

    /**
     * @return the moveToFront
     */
    public boolean isMoveToFront() {
        return moveToFront;
    }
    
    public void removeNode(Node n) {
        getChildren().remove(n);
    }

    protected boolean checkAvailability(int x, int y) {
        if((x > 9 || y > 9) || (x < 0 || y < 0)) {
            return false;
        }

        return true;
    }

    public abstract boolean setColor(GridPane gameMatrix, boolean conferma);
    public abstract boolean checkSpace(int x, int y);
}
