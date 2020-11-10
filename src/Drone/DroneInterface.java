package Drone;


import java.util.Scanner;
import static java.lang.System.exit;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class DroneInterface {

    public int valX;
    public int valY;

    private DroneArena myArena;				// arena in which drones are shown
    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface() {

        // scanner used for input from user
        Scanner input = new Scanner(System.in);
        myArena = new DroneArena(20,20); //preset size
        char options = ' ';
        do {
            System.out.print("Press (A):add a drone, press (I):info, press (D):view drones visually, press (E): view drones and info, (F) save or load a pre-existing file, press (R) for random 10, press (M): reposition drones or press (Q) to quit.  ");
            options = input.next().charAt(0);
            input.nextLine();
            switch (options) {
                case 'A', 'a' -> myArena.addDrone();    // add a new drone to arena
                case 'D','d' -> doDisplay();
                case 'E', 'e' -> displayandinfo();
                case 'F', 'f' -> fileSystem();
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'M', 'm' -> reposition();
                case 'N', 'n' -> newArena();
                case 'q', 'Q' -> exit(0);
                case 'r', 'R' -> animation();
                case 'x' -> options = 'X';
                // when X detected program ends
            }
        } while (options != 'X');						// test if end
        input.close();									// close scanner
    }



    private void newArena() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your x co-ordinate to set your arena: ");
        valX = input.nextInt();
        System.out.println("Please enter your y co-ordinate to set your arena: ");
        valY = input.nextInt();// set up scanner for user input
        myArena = new DroneArena(valX,valY);
    }

    public void animation(){
        int counter = 10;
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

    private void fileSystem() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Please select one of the following options: Press (1) to Load a File, Press (2) to Save a File");
        int options = ' ';
        options = inp.nextInt();
        switch (options){
            case '1':
                    try{

                    }catch (Exception a){
                        System.out.println("System error, please try again.");
                    }
                    break;
            case '2':
                try{
                    JFileChooser chooser = new JFileChooser((C:\\))

                }catch (Exception b){
                    System.out.println("System error, please try again.");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + options);
        }

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
