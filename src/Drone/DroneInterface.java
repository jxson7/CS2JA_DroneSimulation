package Drone;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class DroneInterface {

    public int valX, valY;
    public int xSize, ySize;
    public int counter;
    private DroneArena myArena;                // arena in which drones are shown

    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface()  {

        // scanner used for input from user

        System.out.println("Welcome to Drone Simulator. Enter your arena size to start: ");
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your x co-ordinate to set your arena: ");
        valX = input.nextInt();
        System.out.print("Please enter your y co-ordinate to set your arena: ");
        valY = input.nextInt();// set up scanner for user input
        myArena = new DroneArena(valX, valY);
        System.out.println("___________________________________________");

        System.out.println("Welcome to the Drone Simulator by Jason J Dookarun. Please select one of the following options:\n");//preset size

        char options;
        // implements a new timer to delay the menu, so that other processes can be viewed
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // provides the user with all the appropriate options
            System.out.println("(A) to add a drone\n" +
                    "(D) to view all drones visually\n" +
                    "(E) to view all drones and the relevant information\n" +
                    "(I) to view your existing arena size\n" +
                    "(L) to load a file\n" +
                    "(M) to move all your drones and view the new positions\n" +
                    "(N) to enter a custom arena\n" +
                    "(Q) to exit\n" +
                    "(R) to move your drones 10 times and view the changes in real time\n" +
                    "(S) to save a file\n");
            System.out.print("Your selection: ");


            options = input.next().charAt(0);
            input.nextLine();
            // allows a division to remove any form of clustered code :)
            switch (options) {
                case 'A', 'a' -> newDrone();    // add a new drone to arena
                case 'D', 'd' -> doDisplay();
                case 'E', 'e' -> displayandinfo();
                case 'I', 'i' -> System.out.print(myArena.toString());
                case 'l', 'L' -> {
                    try {
                        loadFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 'M', 'm' -> reposition();
                case 'N', 'n' -> newArena();
                case 'q', 'Q' -> exit(0);
                case 'r', 'R' -> animation();
                case 'S', 's' -> {
                    try {
                        saveFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 'x' -> options = 'X';
                // when X detected program ends
            }
        } while (options != 'X');                        // test if end
        input.close();                                    // close scanner
    }

    // initiates drone interface by calling the appropriate methods
    public static void main(String[] args) throws IOException {
        DroneInterface r = new DroneInterface();
    }

    // new drone involves adding a drone, and has a exception catcher to stop further drones from being added if max capacity is met
    private void newDrone() {
        if (counter >= ((myArena.xDimensions) * (myArena.yDimensions))) {
            System.out.println("You've reached the max!");
        } else {
            myArena.addDrone();
            System.out.println("New Drone Added");
            counter++;
        }
        System.out.println("___________________________________________");
    }

    // new arena provides the user with the option to enter a new arena
    private void newArena() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your x co-ordinate to set your arena: ");
        valX = input.nextInt();
        System.out.print("Please enter your y co-ordinate to set your arena: ");
        valY = input.nextInt();// set up scanner for user input
        myArena = new DroneArena(valX, valY);
        System.out.println("___________________________________________");
    }

    // the animation process involves the movement of all drones 10 times,w/ a delay of 200 ms
    public void animation() {
        int counter = 10;
        for (int i = 0; i < counter; i++) {
            myArena.moveAllDrones();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doDisplay();
        }
        System.out.println("final display ^^ ");
        System.out.println("___________________________________________");

    }

    // display and info focuses on printing total number of drones and the current arena printout,via terminal
    private void displayandinfo() {
        System.out.println("You currently have: " + counter);
        doDisplay();
        System.out.print(myArena.toString());
        System.out.println("___________________________________________");

    }

    // reposition involves moving all drones and viewing the immediate impact
    private void reposition() {
        doDisplay();
        System.out.print(myArena.toString());

        myArena.moveAllDrones();

        doDisplay();
        System.out.print(myArena.toString());
        System.out.println("___________________________________________");
    }


    void loadFile() throws IOException {

        String curDir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(curDir);
        // f = new File("C:\\Users\\jason\\UoR\\Y2\\Java Term1\\DroneSimulationProject");
        chooser.setDialogTitle("Load arena from: ");// Window title
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// What files are shown
       // String contents = " ";
        int returnVal = chooser.showOpenDialog(null); // stores if user clicks open/cancel
        if (returnVal == JFileChooser.APPROVE_OPTION) {// if user presses open
            File userFile = chooser.getSelectedFile(); // gets the file selected by use
            if (chooser.getSelectedFile().isFile()) { // if the file exists
                try{
                System.out.println("Arena Loaded!\n" + "File Name: " + userFile.getName() + "\nDirectory: "  + userFile.getAbsolutePath());// prints file chosen and directory

                // Clear the current drone list
                if (!myArena.droneList.isEmpty()) {
                    myArena.droneList.clear();
                }


                Scanner fileReader = new Scanner(userFile);
                int xSize = fileReader.nextInt();
                int ySize = fileReader.nextInt();
                myArena = new DroneArena(xSize, ySize); // creates a new arena with the gathered dimensions

                    while (fileReader.hasNextInt()) { // while not in the end of the file
                    //contents = br.readLine();
                        int a = fileReader.nextInt();
                        int b = fileReader.nextInt();
                        int direct = fileReader.nextInt();
//                    String[] numbers = contents.split(",");
//                    int x = Integer.parseInt(numbers[0]); // First integer is drone X coordinate
//                    int y = Integer.parseInt(numbers[1]); // Second integer is drone Y coordinate
//                    int ordinal = Integer.parseInt(numbers[2]); // Third integer is drone facing Direction
//                    // creates drone and adds it do list
                        myArena.droneList.add(new Drone(a, b, Direction.values()[direct]));
                    }
                fileReader.close();

            }catch(FileNotFoundException x){
                    System.out.println("Get fucked");
                    x.printStackTrace();
                }

                }
        }
    }




    void saveFile () throws IOException {
        String curDir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(curDir);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showOpenDialog(null); //stores user input when they click open or cancel
        if (returnVal == JFileChooser.APPROVE_OPTION) { //if the user presses open
            File userFile = chooser.getSelectedFile();//gathers the selected file
            System.out.println("Arena saved!\n" + "File Name: " + userFile.getName() + "\nDirectory: " + ((File) userFile).getAbsolutePath()); //saves the file in chosen directory
            //Code for Saving
            FileWriter fileWriter = new FileWriter(userFile); //creates a new file
            BufferedWriter writer = new BufferedWriter(fileWriter); //adds file to the buffer
            //Saves the arena dimensions first
            writer.write(Integer.toString(myArena.getX()));
            writer.write(" ");
            writer.write(Integer.toString(myArena.getY()));
            writer.newLine();
            //Saves the drones in the arena one line at a time
            for (Drone d : myArena.droneList) {
                writer.write(Integer.toString(d.getPosY()));
                writer.write(" ");
                writer.write(Integer.toString(d.getPosY()));
                writer.write(" ");
                writer.write(Integer.toString(d.getDirect().ordinal()));
                writer.newLine();
            }
            writer.close();
        }
    }



    // the following method allows the implementation of a display to be created via the use of variables collected from DroneArena
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
        System.out.println("___________________________________________");
    }
}


