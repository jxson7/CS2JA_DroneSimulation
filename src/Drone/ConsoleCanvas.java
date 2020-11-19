package Drone;

/**
 * author: Jason Jay Dookarun
 * Summary: the following class is developed to provide an arena via terminal and generate a drone. Drone is then displayed as part of 
 * the arena via a '#' character.
 */

// constructor for canvasSize 
public class ConsoleCanvas {
    public final char[][] canvas;
    public  final int canvasSizeX;
    public  final int canvasSizeY;


    // constructor set up to provide the canvas for drones to be drawn upon
    public ConsoleCanvas(int x, int y) {
        canvasSizeX = x;
        canvasSizeY = y;
        canvas = new char[x][y];
         //implements a border for the arena, dependent on the preset size of the arena via user input
        for (int i = 0; i < canvasSizeX; i++) {
            for (int j = 0; j < canvasSizeY; j++) {
                if (i == 0 || i == canvasSizeX - 1 ) {
                    canvas[i][j] = '#';
                }
                else if (j == 0 || j == canvasSizeY -1  ) {
                    canvas[i][j] = '#';
                }
                else {
                    canvas[i][j] = ' ';
                }
                }

            }
        }

        // this focuses on showing the drones
    public void showIt(int droneX, int droneY, char ch) {
        canvas[droneX + 1][droneY + 1] = ch;//otherwise the drones would be displayed inside barrier
    }


    // toString() function focuses upon printing the content i.e. drones and the arena
    public String toString() {
        StringBuilder saver = new StringBuilder();
        for (int i = 0; i < canvasSizeX; i++) {
            for (int j = 0; j < canvasSizeY; j++) {
                saver.append(canvas[i][j]).append(" ");
            }
            saver.append("\n");
        }
        return saver.toString();
    }

}

