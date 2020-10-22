package Drone;
// TODO: complete notes redraft

/**
 * author: Jason Jay Dookarun
 * Summary: the following class is developed to provide an arena via terminal and generate a drone. Drone is then displayed as part of 
 * the arena via an *. 
 */
public class ConsoleCanvas {
    private final char[][] canvas;
    private final int canvasSideX;
    private final int canvasSideY;

    public ConsoleCanvas(int x, int y){
        canvasSideX = x;
        canvasSideY = y;
        canvas = new char[x][y];

        // implements a border for the arena, dependent on the preset size of the arena via user input
        for (int i = 0; i < canvasSideX; i++) {
            for (int j = 0; j < canvasSideY; j++) {
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
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < canvasSideX; i++) {
            for (int j = 0; j < canvasSideY; j++) {
                str.append(canvas[i][j]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }



    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas (10, 5);	// create a canvas
        c.showIt(4,3,'X');								// add a Drone at 4,3
        System.out.println(c.toString());				// display result
    }
}

