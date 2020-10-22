package Drone;


import java.util.Scanner;
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
            System.out.print("Enter (A)dd drone, get (I)nformation or e(X)it > ");
            ch = input.next().charAt(0);
            input.nextLine();
            switch (ch) {
                case 'A', 'a' -> myArena.addDrone();    // add a new drone to arena
                case 'D','d' -> doDisplay();
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'x' -> ch = 'X';                // when X detected program ends
            }
        } while (ch != 'X');						// test if end
        input.close();									// close scanner
    }


   // void doDisplay() {
     //   if (myArena.getX() > 0 && myArena.getY() > 0) {
       //     ConsoleCanvas canvas = new ConsoleCanvas(myArena.getX() + 2, myArena.getY() + 2);
         //   myArena.showDrones(canvas);
           // System.out.println(canvas.toString()); // displays arena
           // if (myArena.droneList.isEmpty()) { // if no drones exist
           //     System.out.println("\nNo drones added yet!");
           // }
        //} else {
         //   System.out.println("\nArena does not exist!"); // if arena does not exist
       // }
   // }


    void doDisplay(){
        if (myArena.getX() > 0 && myArena.getY() > 0){
            ConsoleCanvas canvas = new ConsoleCanvas(myArena.getX() + 2, myArena.getY() + 2);
            myArena.showDrones(canvas);
            System.out.println(canvas.toString()); // displays arena
        }
    }
    public static void main(String[] args) {
        DroneInterface r = new DroneInterface();	// just call the interface
        }

}
