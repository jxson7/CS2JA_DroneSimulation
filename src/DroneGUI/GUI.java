package DroneGUI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;

/**
 * @author Jason Jay Dookarun
 * The following class focuses on creating a GUI whereby this replaces DroneInterface from DroneTerminal to
 * become the new GUI interaction panel. This will allow the user to add drones and view drones visually.
 **/
    public class GUI extends Application {
        // constructor declarations
        private VBox statusPane;
        public int xCanvasSize = 512; // declaration of canvas size
        public int yCanvasSize = 512;
        private MyCanvas canvasPane;
        DroneArena mainArena;
        BorderPane borderPane = new BorderPane();
        Group groupComponent = new Group();
        private boolean testerTimer = false;

    /**
     * The following method focuses on creating and generating all the components involved as part of the project, including
     * the canvas, the arena, the status panel and the buttons involved.
     * @param mainStage: mainStage involved, that is drawn upon.
     */
    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("26017434: Drone Simulator"); //title portion of window
        testerTimer = false; //timer for future use of animations
        borderPane.setTop(setMenuBar());
        Canvas canvas = new Canvas(xCanvasSize, yCanvasSize); // assigning values to the canvas
        groupComponent.getChildren().add(canvas);			// and add canvas to group
        canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize); // drawing the canvas
        mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize); 
        borderPane.setCenter(groupComponent); // positioning the pane for visibility
        statusPane = new VBox(); // creating the status pane to see progress
        Scene scene = new Scene(borderPane, xCanvasSize *1.7, yCanvasSize *1.2); // setting dimensions of window to fit content
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (testerTimer) { // when the timer is true, the following are to be processed
                    mainArena.addDrone(); // drone is added
                    statusPane.getChildren().clear();                    // clear statusPane
                    Label droneLabel = new Label(mainArena.toString());
                    statusPane.getChildren().add(droneLabel); // then shown on the status pane
                    mainArena.drawArena(canvasPane); // and the drone is drawn on the canvas so that the user can view it
                    System.out.println(mainArena.toString()); // it is also printing in the terminal for references
                    try {
                        sleep(1000); // a timer is applied to a visual delay so that the user can keep track of progress
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        layout(); // focuses on the layout of elements, later discussed

        mainStage.setScene(scene); // the main pane with all components is set up
        JLabel lbl = new JLabel("<html> Welcome to the CS2JA: Drone Simulation: GUI Edition. <br> In this simulation " +
                "you will be able to add different objects such as drones, obstacles and avoiders. <br> You will also be able to save, create and load" +
                " a custom configurations of your choice! <br> I Hope You Enjoy Your Experience! <br> <br> <br> - Jason Jay Dookarun");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, lbl , "Welcome to DroneGUI", JOptionPane.INFORMATION_MESSAGE); // user is presented with a welcome message at the start
        mainStage.show(); // main project is shown to the user
    }

    public void layout() {
        statusPane = new VBox(); // the status panel is created
        Label label = new Label(mainArena.toString()); // content is added to the panel
        statusPane.getChildren().add(label);
        ScrollPane sideBarScroller = new ScrollPane(statusPane); //  scroll bar is set up for future
        sideBarScroller.setFitToWidth(true); // sets the scrollbar rotation
        borderPane.setRight(sideBarScroller); //orientation setup
        borderPane.setLeft(statusPane); //orientation setup
        borderPane.setBottom(createButtonPane()); //orientation setup
    }

    /**
     * The following method focuses upon the creation of all additional panes such as the Help support section, followed by
     * File -> Load Custom Arena, Quit
     * Help -> About, Help.
     *
     * With custom arena, the user is prompted with an input panel followed by a confirmation. If the user clicks yes, the new details
     * are loaded, else the process is voided.
     *
     * @return taskbar creation with Load Custom Arena, Quit, Help, About
     */
    MenuBar setMenuBar() {
        MenuBar menuBar = new MenuBar(); // creation of main menu panel
        Menu fileSection = new Menu("File"); // panel one of the menu bar
        MenuItem loadArena = new MenuItem("Load Custom Arena Size"); // option 1 for the user
        MenuItem exit = new MenuItem("Exit"); // exit setup
        exit.setOnAction(e -> {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Drone Simulation", JOptionPane.YES_NO_OPTION); // message box provided to user for options
            if (userResponse == JOptionPane.YES_OPTION) {
                exit(0);
            }
        });
        MenuItem loadSavedFile = new MenuItem("Load Saved Arena File"); // user can load a custom file
        loadSavedFile.setOnAction(l -> {
            loader(); // loader method
        });
        MenuItem clean = new MenuItem("Clear Canvas"); // user can clean canvas and clear it
        clean.setOnAction(c ->{
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear canvas?", "Clear Canvas?", JOptionPane.YES_NO_OPTION); // message box provided to user for options
            if (userResponse == JOptionPane.YES_OPTION) {
                Canvas canvas = new Canvas(xCanvasSize, yCanvasSize); // new canvas pane is set up
                canvasPane.clearCanvas(); // clears canvas
                statusPane.getChildren().clear(); // clears content of status pane
                groupComponent.getChildren().add(canvas);			// and add canvas to group
                canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize); //reuses the same canvas as previously stated
                mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize); //draws the arena
            }
        });
        MenuItem saveContent = new MenuItem ("Save Custom Configurations");
        saveContent.setOnAction(s->{
            try {
                saver(); // save function implemented to save contents
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadArena.setOnAction(l -> {
            canvasPane.clearCanvas(); // canvas is cleared to commence
            String inputX = JOptionPane.showInputDialog("Enter a new X Dimension for your arena:");
            int xCanvasSize = Integer.parseInt(inputX);// user input is stored as an integer
            String inputY = JOptionPane.showInputDialog("Enter a new Y Dimension for your arena:");
            int yCanvasSize = Integer.parseInt(inputY); // user input is stored as an integer
            Canvas canvas = new Canvas(xCanvasSize, yCanvasSize); // new canvas pane is set up
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to go ahead with these? Your canvas will be reset!", "Confirm", JOptionPane.YES_NO_OPTION); // user provided with validation question
            if (reply == JOptionPane.YES_OPTION) {
                groupComponent.getChildren().add(canvas);			// and add canvas to group
                canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize);
                mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);
                JOptionPane.showMessageDialog(null, "Your new custom arena size is: " + xCanvasSize + " by " + yCanvasSize, "New Arena Size", JOptionPane.INFORMATION_MESSAGE); //content is shown via message box
                System.out.println("Your new custom arena size is: " + xCanvasSize + " by " + yCanvasSize); // printed in terminal for validation
                System.out.println("_____________________");
            }
        });

        Menu helpSection = new Menu("Help");
        MenuItem help = new Menu("Help");
        help.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null, "<html> Help Panel <br> Use the buttons to interact with the main interface by adding a drone, obstacle or avoider. <br> " +
                "you can also view a live drone addition via Auto Add Drone, and then pause it. If you have visited us <br> before, don't forget to experiment in the File section by <br> creating, loading or saving you configurations!", "Help", JOptionPane.INFORMATION_MESSAGE)); // help message
        MenuItem aboutSection = new MenuItem("About");
        aboutSection.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null, "Welcome to the DroneGUI from the CS2JA course by Jason Jay Dookarun", "About", JOptionPane.INFORMATION_MESSAGE));
        fileSection.getItems().addAll(loadArena,loadSavedFile,saveContent,clean,exit); // adds all the new menu components to the group
        helpSection.getItems().addAll(help, aboutSection);
        menuBar.getMenus().addAll(fileSection, helpSection);
        return menuBar; // adds content to the pane as a visual identifier
    }

    private void saver() throws IOException {
        String curDir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(curDir);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showSaveDialog(null); //stores user input when they click open or cancel
        if (returnVal == JFileChooser.APPROVE_OPTION) { //if the user presses open
            File userFile = chooser.getSelectedFile();//gathers the selected file
            System.out.println("Arena saved!\n" + "File Name: " + userFile.getName() + "\nDirectory: " + userFile.getAbsolutePath()); //saves the file in chosen directory
            //Code for Saving
            FileWriter fileWriter = new FileWriter(userFile); //creates a new file
            BufferedWriter writer = new BufferedWriter(fileWriter); //adds file to the buffer
            //Saves the arena dimensions first in a category, in the following format: Arena: x y
            writer.write("Arena: ");
            writer.write(Integer.toString(mainArena.getX()));
            writer.write(" ");
            writer.write(Integer.toString(mainArena.getY()));
            writer.newLine();
            //Saves the drones in the arena one line at a time, in the following format: Drone: x y, Direction value
            for (Drone d : mainArena.droneList) {
                writer.write("Drone: ");
                writer.write(Integer.toString(d.getPosX()));
                writer.write(" ");
                writer.write(Integer.toString(d.getPosY()));
                writer.write(" ");
                writer.write(Integer.toString(d.getDirect().ordinal()));
                writer.newLine();
            }
            //Saves the obstacles in the arena one line at a time, in the following format: Obstacle: x y, Direction value
            for (Obstacles o : mainArena.obstaclesList){
                writer.write("Obstacle: ");
                writer.write(Integer.toString(o.getPosX()));
                writer.write(" ");
                writer.write(Integer.toString(o.getPosY()));
                writer.write(" ");
                writer.write(Integer.toString(o.getDirect().ordinal()));
                writer.newLine();
            }
            //Saves the Avoider in the arena one line at a time, in the following format: Avoider: x y, Direction value
            for (Avoider a : mainArena.avoiderList){
                writer.write("Avoider: ");
                writer.write(Integer.toString(a.getPosX()));
                writer.write(" ");
                writer.write(Integer.toString(a.getPosY()));
                writer.write(" ");
                writer.write(Integer.toString(a.getDirect().ordinal()));
                writer.newLine();
            }
            // User then receives a confirmation stating file has been successfully saved
            Label statusLbl = new Label("File have been successfully saved" + "\n");
            System.out.println("File has been successfully saved!");
            statusPane.getChildren().add(statusLbl); // content shown in status Pane
            writer.close(); // writer closed
            }
    }

    private void loader() {
        String curDir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(curDir); //opens the file opener to the current directory found
        chooser.setDialogTitle("Load arena from: ");// Window title
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// What files are shown
        // String contents = " ";
        int returnVal = chooser.showOpenDialog(null); // stores if user clicks open/cancel
        if (returnVal == JFileChooser.APPROVE_OPTION) {// if user presses open
            File userFile = chooser.getSelectedFile(); // gets the file selected by use
            if (chooser.getSelectedFile().isFile()) { // if the file exists
                try{
                    System.out.println("Arena Loaded!\n" + "File Name: " + userFile.getName() + "\nDirectory: "  + userFile.getAbsolutePath());// prints file chosen and directory
                    canvasPane.clearCanvas();
                    Scanner fileReader = new Scanner(userFile); // scanner used to read contents of file
                     // reads content in file in set categories
                    while (fileReader.hasNextLine()) { // if the file has another line
                        String type = fileReader.next(); // the first line as the category
                        if (type.equals("Arena:")) { // if category header is set to arena then
                            int xSize = fileReader.nextInt(); // assign first val in line as the x
                            int ySize = fileReader.nextInt(); // assign second value in the line as the y
                            mainArena = new DroneArena(xSize, ySize); // creates the custom arena using these values
                            Canvas canvas = new Canvas(xSize, ySize);
                            groupComponent.getChildren().add(canvas);			// and add canvas to group
                            canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xSize, ySize); // draws the canvas pane
                            mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);
                        }
                        if (type.equals("Drone:")) { //reads the category header
                            int a = fileReader.nextInt(); // x value assigned
                            int b = fileReader.nextInt(); // y value assigned
                            int direct = fileReader.nextInt(); // direction assigned via the 3rd value of the line
                            mainArena.droneList.add(new Drone(a,b,Direction.values()[direct])); // adds the components into drone
                            statusPane.getChildren().clear();                    // clear statusPane
                            Label droneLabel = new Label(mainArena.toString()); // adds the content to the statusPane
                            statusPane.getChildren().add(droneLabel);
                            mainArena.drawArena(canvasPane); // draws the Drones
                            System.out.println(mainArena.toString()); // printed in terminal as a verifier
                        }
                        if (type.equals("Obstacle:")) { // reads category header
                            int a = fileReader.nextInt(); // x val assigned
                            int b = fileReader.nextInt(); // y val assigned
                            int direct = fileReader.nextInt(); // direction assigned
                            mainArena.obstaclesList.add(new Obstacles(a, b, Direction.values()[direct])); // adds the components into obstacleList
                            statusPane.getChildren().clear();                    // clear statusPane
                            Label droneLabel = new Label(mainArena.toString());
                            statusPane.getChildren().add(droneLabel); // shows the content in the status pane
                            mainArena.drawObstacle(canvasPane); // draws the obstacle
                            System.out.println(mainArena.toString()); // shows content in terminal for verification
                        }
                        if (type.equals("Avoider:")) { // read category header
                            int a = fileReader.nextInt(); // x val assigned
                            int b = fileReader.nextInt(); // y val assigned
                            int direct = fileReader.nextInt(); // direction assigned
                            mainArena.avoiderList.add(new Avoider(a, b, Direction.values()[direct])); //adds elements to the avoiderList
                            statusPane.getChildren().clear();                    // clear statusPane
                            Label droneLabel = new Label(mainArena.toString());
                            statusPane.getChildren().add(droneLabel); // shows content in StatusPane
                            mainArena.drawAvoider(canvasPane);
                            System.out.println(mainArena.toString()); // prints content in terminal for verification purposes
                        }

                    }
                    Label statusLbl = new Label("File have been successfully loaded and will be displayed below" + "\n" + "___________________________" + "\n");
                    statusPane.getChildren().add(statusLbl);
                    System.out.println("File have been successfully loaded and will be displayed below" + "\n" );
                    fileReader.close();
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Focuses on creation the buttons for the user to interact with, i.e. add Drone and Stop System
     * Add Drone: Focuses on adding a drone to the Arena, updating the system via getChild() and drawing the mentioned.
     *
     * @return Add: Drone, Avoider and Obstacle and Add Drone Automatically, and Pause Drone Automatically
     */
    private HBox createButtonPane() {
        mainArena.drawArena(canvasPane);//draw
        Button add = new Button("Drone Adder (Auto)"); // button creation
        Button stop = new Button("Pause Animation"); // button creation
        add.setOnAction(event -> testerTimer = true); // if the button is clicked, timer is activated
        stop.setOnAction(event -> {
            JOptionPane.showMessageDialog(null, "Process has been stopped.", "Process Stopped", JOptionPane.WARNING_MESSAGE); // shows user the process has been stopped
            Label statusLbl = new Label(mainArena.stopProcess());
            statusPane.getChildren().add(statusLbl);
            System.out.println(mainArena.stopProcess()); // prints confirmation
            testerTimer = false;
        });
        Button addDroneButton = new Button("Add Drone Manually"); // button declaration
        addDroneButton.setOnAction(event -> {
            mainArena.addDrone(); // adds drone
            statusPane.getChildren().clear();                    // clear statusPane
            Label droneLabel = new Label(mainArena.toString());
            statusPane.getChildren().add(droneLabel); // adds details concerning drone to the status pane
            mainArena.drawArena(canvasPane);
            System.out.println(mainArena.toString()); // prints confirmation
        });
        Button addObstacleButton = new Button("Add Obstacle Manually");
        addObstacleButton.setOnAction(event -> {
            mainArena.addObstacle(); // adds obstacle
            statusPane.getChildren().clear();                    // clear statusPane
            Label obstacleLabel = new Label(mainArena.toString());
            statusPane.getChildren().add(obstacleLabel); // adds the content to status pane
            mainArena.drawObstacle(canvasPane);
            System.out.println(mainArena.toString()); // prints confirmation
        });
        Button addAvoider = new Button("Add Avoider");
        addAvoider.setOnAction(event -> {
            mainArena.addAvoider(); // adds avoider
            statusPane.getChildren().clear();                    // clear statusPane
            Label obstacleLabel = new Label(mainArena.toString());
            statusPane.getChildren().add(obstacleLabel); // adds content to status pane
            mainArena.drawAvoider(canvasPane);
            System.out.println(mainArena.toString()); // prints confirmation
        });
        return new HBox(add, stop, addDroneButton, addObstacleButton, addAvoider); // shows all buttons on screens
    }

    /**
     * Main Process: runs GUI class
     * @param args: no arguments sent
     */
    public static void main(String[] args) {
        launch(args);
    }

}
