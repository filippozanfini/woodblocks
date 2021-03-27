package modal;

public class GameMatrix {

    private static int[][] matrix = new int[10][10];

    private static GameMatrix instance = null;

    private GameMatrix() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                matrix[i][j] = 0;
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
        if((x > 9 && y > 9) || (x<0 && y < 0)) {
            return false;
        }

        if(matrix[x][y] != 0) {
            return false;
        }

        return true;
    }

    public static void add(int x, int y) {
        matrix[x][y] = 1;
    }

    public static void remove(int x, int y) {
        matrix[x][y] = 0;
    }

    public static int get(int x, int y) {
        return matrix[x][y];
    }

}
