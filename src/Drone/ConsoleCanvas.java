package Drone;

/**
 * author: Jason Jay Dookarun
 * Summary: the following class is developed to provide an arena via terminal and generate a drone. Drone is then displayed as part of 
 * the arena via a '#' character.
 */
public class ConsoleCanvas {
    public final char[][] canvas;
    public  final int canvasSideX;
    public  final int canvasSideY;


    public ConsoleCanvas(int x, int y) {
        canvasSideX = x;
        canvasSideY = y;
        canvas = new char[x][y];
         //implements a border for the arena, dependent on the preset size of the arena via user input
        for (int i = 0; i < canvasSideX; i++) {
            for (int j = 0; j < canvasSideY; j++) {
                if (i == 0 || i == canvasSideX - 1 ) {
                    canvas[i][j] = '#';
                }
                else if (j == 0 || j == canvasSideY -1  ) {
                    canvas[i][j] = '#';
                }
                else {
                    canvas[i][j] = ' ';
                }
                }

            }
        }

    public void showIt(int droneX, int droneY, char ch) {
        canvas[droneX + 1][droneY + 1] = ch;//otherwise the drones would be displayed inside barrier
    }


    public String toString() {
        String saver = "";
        for (int i = 0; i < canvasSideX; i++) {
            for (int j = 0; j < canvasSideY; j++) {
                saver += canvas[i][j] + " " ;
            }
            saver += "\n";
        }
        return saver;
    }


}

