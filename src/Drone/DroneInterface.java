package Drone;


import java.util.Scanner;

import static java.lang.System.exit;

public class DroneInterface {

    private final DroneArena myArena;				// arena in which drones are shown
    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface() {

        // scanner used for input from user
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your x co-ordinate to set your arena: ");
        int valX = input.nextInt();
        System.out.println("Please enter your y co-ordinate to set your arena: ");
        int valY = input.nextInt();// set up scanner for user input
        myArena = new DroneArena(valX,valY);	// create arena of size 20*6
        char ch = ' ';
        do {
            System.out.print("Press A to add a drone, press I to gain information on drone locations and arena size, press D to view locations of drones within arena or press Q to quit.  ");
            ch = input.next().charAt(0);
            input.nextLine();
            switch (ch) {
                case 'A', 'a' -> myArena.addDrone();    // add a new drone to arena
                case 'D','d' -> doDisplay();
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'x' -> ch = 'X';
                case 'q', 'Q' -> exit(0); // when X detected program ends
            }
        } while (ch != 'X');						// test if end
        input.close();									// close scanner
    }


    // the following method allows the implementation of a display to be created via the use of variables collected from DroneArena
    void doDisplay(){
        if (myArena.getX() > 0 && myArena.getY() > 0){
            ConsoleCanvas canvas = new ConsoleCanvas(myArena.getX() + 2, myArena.getY() + 2);
            myArena.showDrones(canvas);
            if (myArena.droneList.isEmpty()){
                System.out.println("Unfortunately no drone has currently exists, please try again later");
            }
            System.out.println(canvas.toString()); // displays arena
        }
    }

    // initiates drone interface by calling the appropriate methods
    public static void main(String[] args) {
        DroneInterface r = new DroneInterface();
    }
}
