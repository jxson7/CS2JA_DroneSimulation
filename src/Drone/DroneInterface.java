package Drone;

import Drone.DroneArena;

import java.util.Scanner;

/**
 * Simple program to show arena with multiple drones
 * @author shsmchlr
 *
 */
public class DroneInterface {

    private Scanner s;								// scanner used for input from user
    private DroneArena myArena;				// arena in which drones are shown
    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface() {
        s = new Scanner(System.in);			// set up scanner for user input
        myArena = new DroneArena(20, 6);	// create arena of size 20*6

        char ch = ' ';
        do {
            System.out.print("Enter (A)dd drone, get (I)nformation or e(X)it > ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'A', 'a' -> myArena.addDrone();    // add a new drone to arena
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'D','d' -> doDisplay();

                case 'x' -> ch = 'X';                // when X detected program ends
            }
        } while (ch != 'X');						// test if end

        s.close();									// close scanner
    }

    void doDisplay() {
        if (myArena.getX() > 0 && myArena.getY() > 0) {
            ConsoleCanvas canvas = new ConsoleCanvas(myArena.getX() + 2, myArena.getY() + 2);
            myArena.showDrones(canvas);
            System.out.println(canvas.toString()); // displays arena
            if (myArena.droneList.isEmpty()) { // if no drones exist
                System.out.println("\nNo drones added yet!");
            }
        } else {
            System.out.println("\nArena does not exist!"); // if arena does not exist
        }
    }


        public static void main(String[] args) {
        DroneInterface r = new DroneInterface();	// just call the interface
    }

}
