package Drone;


import java.util.Scanner;
import static java.lang.System.exit;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class DroneInterface {

    public int valX;
    public int valY;

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
        valX = input.nextInt();
        System.out.println("Please enter your y co-ordinate to set your arena: ");
        valY = input.nextInt();// set up scanner for user input
        myArena = new DroneArena(valX,valY);
//        KeyEvent esc = null;
//        int keyCode = esc.getKeyCode();
        char ch = ' ';
        do {
            System.out.print("Press (A):add a drone, press (I):info, press (D):view drones visually, press (E): view drones and info, press (S) for random 10, press (M): reposition drones or press (Q) to quit.  ");
            ch = input.next().charAt(0);
            input.nextLine();
            switch (ch) {
                case 'A', 'a' -> myArena.addDrone();    // add a new drone to arena
                case 'D','d' -> doDisplay();
                case 'M', 'm' -> reposition();
                case 'E', 'e' -> displayandinfo();
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'x' -> ch = 'X';
                case 's', 'S' -> animation();
                case 'q', 'Q' -> exit(0);
                // when X detected program ends
            }
        } while (ch != 'X');						// test if end
        input.close();									// close scanner
    }

    public void animation(){
        int counter = 3;
        for (int i = 0; i < counter; i++){
            myArena.moveAllDrones();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            doDisplay();

        }
        System.out.println("final display ^^ ");
    }

    private void displayandinfo(){
        doDisplay();
        System.out.print(myArena.toString());
    }

    private void reposition() {
        doDisplay();
        System.out.print(myArena.toString());

        myArena.moveAllDrones();
        doDisplay();
        System.out.print(myArena.toString());

    }

    public void keyPressed(KeyEvent esc) {
        int keyCode = esc.getKeyCode();
        System.out.println(keyCode);
    }

    // the following method allows the implementation of a display to be created via the use of variables collected from DroneArena
    void doDisplay(){
        if (myArena.getX() > 0 && myArena.getY() > 0){
            ConsoleCanvas canvas = new ConsoleCanvas(myArena.getX() + 2 , myArena.getY() + 2 );
            myArena.showDrones(canvas);
            System.out.println(canvas.toString()); // displays arena
        }
    }

    // initiates drone interface by calling the appropriate methods
    public static void main(String[] args) {
        DroneInterface r = new DroneInterface();
    }
}
