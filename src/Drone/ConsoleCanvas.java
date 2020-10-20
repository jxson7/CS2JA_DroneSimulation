package Drone;
// TODO: complete notes redraft

public class ConsoleCanvas {
    private final char[][] canvas;
    private final int canvasX;
    private final int canvasY;

    public ConsoleCanvas(int x, int y){
        canvasX = x;
        canvasY = y;
        canvas = new char[x][y];

        for (int i = 0; i < canvasX; i++) {
            for (int j = 0; j < canvasY; j++) {
                canvas[i][j] = ' ';
                if (j == y - 1) {
                    canvas[i][j] = '#';
                }
                    if (i == 0) {
                    canvas[i][j] = '#';
                }
                if (j == 0) {
                    canvas[i][j] = '#';
                }

                if (i == x - 1) {
                    canvas[i][j] = '#';
                }
            }
        }

    }

    public void showIt(int droneX, int droneY, char ch) {

        canvas[droneX + 1][droneY + 1] = ch;//otherwise the drones would be displayed inside barrier
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < canvasX; i++) {
            for (int j = 0; j < canvasY; j++) {
                res = res + canvas[i][j] + " ";
            }
            res = res + "\n";
        }
        return res;
    }



    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas (10, 5);	// create a canvas
        c.showIt(4,3,'X');								// add a Drone at 4,3
        System.out.println(c.toString());				// display result
    }
}

