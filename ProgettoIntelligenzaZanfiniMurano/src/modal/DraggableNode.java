package modal;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("in") 
public abstract class DraggableNode extends Pane {

    @Param(0)
    private int ID;    
    @Param(1)
    private int row = 0;
    @Param(2)
    private int col = 0;
    @Param(3)
    private String type;

    private double x = 0;
    private double y = 0;

    private double mousex = 0;
    private double mousey = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;
    private GridPane pane;
    private Integer score;

    public DraggableNode(int ID, int row , int col) {
    	score = 0;
        init();
        this.ID = ID;
        this.row = row;
        this.col = col;
        type = "";
        
    }
    public DraggableNode() {
    	score = 0;
        init();
    }
    public void setID(int id) {
        this.ID = id;
    }
    public int getID() {
        return this.ID;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getCol() {
        return col;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private void init() {

        onMousePressedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mousex = event.getSceneX();
                mousey = event.getSceneY();
                x = getLayoutX();
                y = getLayoutY();
                if (isMoveToFront()) {
                    toFront();
                }
            }
        });

        onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double offsetX = event.getSceneX() - mousex;
                double offsetY = event.getSceneY() - mousey;

                x += offsetX;
                y += offsetY;

                double scaledX = x;
                double scaledY = y;

                setLayoutX(scaledX);
                setLayoutY(scaledY);

                dragging = true;

                mousex = event.getSceneX();
                mousey = event.getSceneY();  
                
                setColor(pane, false);

                event.consume();
            }
        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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

    public void print(){

        System.out.println("Type : "+ type);
        System.out.println("Start to : x("+ getRow() + "),("+ getCol() + ")");

    }
    public abstract boolean setColorEMBASP(GridPane gameMatrix, boolean b, int row2, int col2, DraggableNode node1) throws InterruptedException;
}
