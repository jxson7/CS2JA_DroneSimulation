package Drone;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class DroneInterface extends Component {

    public int valX, valY;
    public int xSize, ySize;
    public int counter;
    private DroneArena myArena;                // arena in which drones are shown

    /**
     * constructor for DroneInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public DroneInterface() throws IOException {

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
                    "(F) to either save/load file(s)\n" +
                    "(I) to view your existing arena size\n" +
                    "(M) to move all your drones and view the new positions\n" +
                    "(N) to enter a custom arena\n" +
                    "(Q) to exit\n" +
                    "(R) to move your drones 10 times and view the changes in real time\n");
            System.out.print("Your selection: ");


            options = input.next().charAt(0);
            input.nextLine();
            // allows a division to remove any form of clustered code :)
            switch (options) {
                case 'A', 'a' -> newDrone();    // add a new drone to arena
                case 'D', 'd' -> doDisplay();
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

    // this allows the user to save and/or load an existing arena file.
    private void fileSystem() throws IOException {
        Scanner inp = new Scanner(System.in);
        System.out.println("Please select one of the following options: Press (a) save a file, Press (b) to load a pre-existing file");
        char options;
        options = inp.next().charAt(0);
        switch (options) {
            // focuses on saving a file from a directory

            case 'a':
                new JFrame();
                JFileChooser chooser;
                File f = new File("C:\\Users\\jason\\UoR\\Y2\\Java Term1\\DroneSimulationProject");
                chooser = new JFileChooser(f);
                int retriever = chooser.showSaveDialog(this);
                if (retriever == JFileChooser.APPROVE_OPTION) {
                    // File file = chooser.getSelectedFile();
                    FileWriter fw = new FileWriter(chooser.getSelectedFile());
                    BufferedWriter fileWriter = new BufferedWriter(fw);
                    fileWriter.write("Arena Size:");
                    fileWriter.write(Integer.toString(myArena.getX()));
                    fileWriter.write(" by ");
                    fileWriter.write(Integer.toString(myArena.getY()));
                    fileWriter.newLine(); // change line
                    // Each line store one drone in the form X Y DIRECTION
                    for (Drone d : myArena.droneList) {
                        fileWriter.write("Drone: ");
                        fileWriter.write(Integer.toString(d.getPosX()));
                        fileWriter.write(" ");
                        fileWriter.write(Integer.toString(d.getPosY()));
                        fileWriter.write(" ");
                        fileWriter.write(Integer.toString(d.getDirect().ordinal()));
                        fileWriter.newLine();
                    }
                    fileWriter.close();

                }
                // focuses on loading a file from a directory
            case 'b':
                //TODO: work on splitting elements to add to Arena
                new JFrame();
                String contents;
                f = new File("C:\\Users\\jason\\UoR\\Y2\\Java Term1\\DroneSimulationProject");
                chooser = new JFileChooser(f);
                int returnVal = chooser.showOpenDialog(this); // stores if user clicks open/cancel
                if (returnVal == JFileChooser.APPROVE_OPTION) {// if user presses open
                    File userFile = chooser.getSelectedFile(); // gets the file selected by user
                    if (chooser.getSelectedFile().isFile()) { // if the file exists
                        System.out.println("Arena Loaded!\n" + "File Name: " + userFile.getName() + "\nDirectory: "
                                + userFile.getAbsolutePath());// prints file chosen and directory

                        FileReader fr = new FileReader(userFile);
                        BufferedReader br = new BufferedReader(fr);
                        contents = br.readLine();
                        String[] sizeFinder = contents.split(" ");
                        xSize = Integer.parseInt(sizeFinder[0]);
                        ySize = Integer.parseInt(sizeFinder[1]);
                        // boolean empty = f.length() == 0;

                        while (br.readLine() != null) {
                            contents = br.readLine();
                            String[] coordinates = contents.split(" ");
                            int x = Integer.parseInt(coordinates[0]);
                            int y = Integer.parseInt(coordinates[1]);
                            int direction = Integer.parseInt(coordinates[2]);
                            myArena.droneList.add(new Drone(x, y, Direction.values()[direction]));
                        }
                        br.close();
                    }
                }
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
