package modal;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameMatrix {

    private static int[][] matrix = new int[10][10];
    private static String[][] typeBlockMatrix = new String[10][10];

    private static GameMatrix instance = null;

    private GameMatrix() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                matrix[i][j] = 0;
                typeBlockMatrix[i][j] = "";
            }
        }
    }

    public static GameMatrix getInstance() {
        if(instance == null) {
            instance = new GameMatrix();
        }

        return instance;
    }

    public static boolean checkAvailability(int x, int y) {
        if((x > 9 || y > 9) || (x<0 || y < 0)) {
            return false;
        }

        if(matrix[x][y] != 0) {
            return false;
        }

        return true;
    }

    public static void add(int x, int y, String type) {
        matrix[x][y] = 1;
        typeBlockMatrix[x][y] = type;
    }

    public static void remove(int x, int y) {
        matrix[x][y] = 0;
        typeBlockMatrix[x][y] = "";
    }

    public static int get(int x, int y) {
        return matrix[x][y];
    }

    public static String getType(int x, int y) {
        return typeBlockMatrix[x][y];
    }

    public static void checkFull(GridPane gridPane) {
        checkFullRow(gridPane);
        checkFullColumn(gridPane);
    }

    private static void checkFullRow(GridPane gridPane) {
        int rowCount = 0;

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(matrix[j][i] != 0) {
                    rowCount++;
                }
            }
                if(rowCount == 10) {
                    for(int j=0; j<10; j++) {
                        System.out.println("Sto eliminando una riga....");
                        remove(j, i);
                    }
    
                    ObservableList<Node> blocks = gridPane.getChildren();
                    ArrayList<Node> nodeToRemove = new ArrayList<Node>();
                
                    int count = 0;
                    for(Node n : blocks) {
                        if(count == 0) {
                            count++;
                            continue;
                        }
                    
                        if(gridPane.getRowIndex(n) == i) {
                            Rectangle r = (Rectangle) n;
                            nodeToRemove.add(r);
                        }
                    }
    
                    for(Node n : nodeToRemove) {
                        gridPane.getChildren().remove(n);
                    }
                }

            rowCount = 0;
        }
    }

    private static void checkFullColumn(GridPane gridPane) {
        int columnCount = 0;

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(matrix[i][j] != 0) {
                    columnCount++;
                }
            }


            if(columnCount == 10) {
                for(int j=0; j<10; j++) {
                    System.out.println("Sto eliminando una colonna....");
                    remove(i, j);
                }

                ObservableList<Node> blocks = gridPane.getChildren();
                ArrayList<Node> nodeToRemove = new ArrayList<Node>();
            
                int count = 0;
                for(Node n : blocks) {
                    if(count == 0) {
                        count++;
                        continue;
                    }
                
                    if(gridPane.getColumnIndex(n) == i) {
                        Rectangle r = (Rectangle) n;
                        nodeToRemove.add(r);
                    }
                }

                for(Node n : nodeToRemove) {
                    gridPane.getChildren().remove(n);
                }
            }

            columnCount = 0;
        }
    }

    public static boolean checkBlockAvailability(DraggableNode node) {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(node.checkSpace(i, j)) {
                    return true;
                }
            }
        }

        return false;
    }
    public static void cleanAll() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                remove(i,j);
                matrix[i][j] = 0;
                typeBlockMatrix[i][j] = "";

                }
            }
        }
}
