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
    private VBox leftPane;
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
        mainStage.setTitle("26017434: Drone Simulator");
        testerTimer = false;
        borderPane.setTop(setMenuBar());
        int xCanvasSize = 512;
        int yCanvasSize = 512;
        Canvas canvas = new Canvas(xCanvasSize, yCanvasSize);
        groupComponent.getChildren().add(canvas);			// and add canvas to group
        canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize);
        mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);
        borderPane.setCenter(groupComponent);
        leftPane = new VBox();

        Scene scene = new Scene(borderPane, xCanvasSize *1.7, yCanvasSize *1.2);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (testerTimer) {
                    mainArena.addDrone();
                    leftPane.getChildren().clear();                    // clear leftPane
                    Label droneLabel = new Label(mainArena.toString());
                    leftPane.getChildren().add(droneLabel);
                    mainArena.drawArena(canvasPane);
                    System.out.println(mainArena.toString());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        layout();
        mainStage.setScene(scene);
        JLabel lbl = new JLabel("<html> Welcome to the CS2JA: Drone Simulation by Jason Jay Dookarun. <br> In this simulation " +
                "you will be able to add different objects such as drones, obstacles and avoiders. <br> You will also be able to save, create and load" +
                " a custom arena of your choice! <br> I Hope You Enjoy Your Experience! <br> <br> <br> - Jason Jay Dookarun");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, lbl , "About", JOptionPane.INFORMATION_MESSAGE);
        mainStage.show();
    }

    public void layout() {
        leftPane = new VBox();
        Label label = new Label(mainArena.toString());
        leftPane.getChildren().add(label);
        ScrollPane sideBarScroller = new ScrollPane(leftPane);
        sideBarScroller.setFitToWidth(true);
        borderPane.setRight(sideBarScroller);
        borderPane.setLeft(leftPane);
        borderPane.setBottom(createButtonPane());
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
        MenuBar menuBar = new MenuBar();
        Menu fileSection = new Menu("File");
        MenuItem loadArena = new MenuItem("Create an Arena");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Drone Simulation", JOptionPane.YES_NO_OPTION);
            if (userResponse == JOptionPane.YES_OPTION) {
                exit(0);
            }
        });
        MenuItem loadSavedFile = new MenuItem("Load Custom Arena Configurations");
        loadSavedFile.setOnAction(l -> {
            loader();
        });
        MenuItem saveContent = new MenuItem ("Save Arena Configurations");
        saveContent.setOnAction(s->{
            try {
                saver();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        loadArena.setOnAction(l -> {
            canvasPane.clearCanvas();
            String inputX = JOptionPane.showInputDialog("Enter a new X Dimension for your arena:");
            int placeholder = Integer.parseInt(inputX);
            if (placeholder == JOptionPane.CANCEL_OPTION ){
                ;
            }
            int xCanvasSize = Integer.parseInt(inputX);
            String inputY = JOptionPane.showInputDialog("Enter a new Y Dimension for your arena:");
            placeholder = Integer.parseInt(inputY);
            if (placeholder == JOptionPane.CANCEL_OPTION ){
                ;
            }
            int yCanvasSize = Integer.parseInt(inputY);
            Canvas canvas = new Canvas(xCanvasSize, yCanvasSize);
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to go ahead with these? Your canvas will be reset!", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                groupComponent.getChildren().add(canvas);			// and add canvas to group
                canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize);
                mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);
                JOptionPane.showMessageDialog(null, "Your new custom arena size is: " + xCanvasSize + " by " + yCanvasSize, "New Arena Size", JOptionPane.INFORMATION_MESSAGE);
            }

        });
        Menu helpSection = new Menu("Help");
        MenuItem help = new Menu("Help");
        help.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null, "Add Drone: adds a drone to the canvas, Add Obstacle: Adds an Obstacle to the canvas, Quit: exits the program", "Help", JOptionPane.INFORMATION_MESSAGE));
        MenuItem aboutSection = new MenuItem("About");
        aboutSection.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null, "Welcome to the CS2JA: Drone Simulation by Jason Jay Dookarun", "About", JOptionPane.INFORMATION_MESSAGE));
        fileSection.getItems().addAll(loadArena,loadSavedFile,saveContent,exit);
        helpSection.getItems().addAll(help, aboutSection);
        menuBar.getMenus().addAll(fileSection, helpSection);
        return menuBar;
    }

    private void saver() throws IOException {
        String curDir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(curDir);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showSaveDialog(null); //stores user input when they click open or cancel
        if (returnVal == JFileChooser.APPROVE_OPTION) { //if the user presses open
            File userFile = chooser.getSelectedFile();//gathers the selected file
            System.out.println("Arena saved!\n" + "File Name: " + userFile.getName() + "\nDirectory: " + ((File) userFile).getAbsolutePath()); //saves the file in chosen directory
            //Code for Saving
            FileWriter fileWriter = new FileWriter(userFile); //creates a new file
            BufferedWriter writer = new BufferedWriter(fileWriter); //adds file to the buffer
            //Saves the arena dimensions first
            writer.write(Integer.toString(mainArena.getX()));
            writer.write(" ");
            writer.write(Integer.toString(mainArena.getY()));
            writer.newLine();
            //Saves the drones in the arena one line at a time
            Label statusLbl = new Label("File have been successfully saved" + "\n");
            leftPane.getChildren().add(statusLbl);
            System.out.println("File have been successfully loaded and will be displayed below" + "\n" );
            writer.close();
        }

    }

    private void loader() {
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

                    canvasPane.clearCanvas();
                    Scanner fileReader = new Scanner(userFile);
                    int xSize = fileReader.nextInt();
                    int ySize = fileReader.nextInt();
                    Canvas canvas = new Canvas(xSize, ySize);
                    groupComponent.getChildren().add(canvas);
                    Label statusLbl = new Label("Custom Configurations have been successfully loaded " + "\n" + "___________________________" + "\n");
                    // and add canvas to group
                    leftPane.getChildren().add(statusLbl);
                    canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xSize, ySize);
                    mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);

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
        Button add = new Button("Drone Adder (Auto)");
        Button stop = new Button("Pause Animation");
        add.setOnAction(event -> testerTimer = true);
        stop.setOnAction(event -> {
            JOptionPane.showMessageDialog(null, "Process has been stopped.", "Process Stopped", JOptionPane.WARNING_MESSAGE);
            Label statusLbl = new Label(mainArena.stopProcess());
            leftPane.getChildren().add(statusLbl);
            System.out.println(mainArena.stopProcess());
            testerTimer = false;
        });
        Button addDroneButton = new Button("Add Drone Manually");
        addDroneButton.setOnAction(event -> {
            mainArena.addDrone();
            leftPane.getChildren().clear();                    // clear leftPane
            Label droneLabel = new Label(mainArena.toString());
            leftPane.getChildren().add(droneLabel);
            mainArena.drawArena(canvasPane);
            System.out.println(mainArena.toString());
        });
        Button addObstacleButton = new Button("Add Obstacle Manually");
        addObstacleButton.setOnAction(event -> {
            mainArena.addObstacle();
            leftPane.getChildren().clear();                    // clear leftPane
            Label obstacleLabel = new Label(mainArena.toString());
            leftPane.getChildren().add(obstacleLabel);
            mainArena.drawObstacle(canvasPane);
            System.out.println(mainArena.toString());
        });
        Button addAvoider = new Button("Add Avoider");
        addAvoider.setOnAction(event -> {
            mainArena.addAvoider();
            leftPane.getChildren().clear();                    // clear leftPane
            Label obstacleLabel = new Label(mainArena.toString());
            leftPane.getChildren().add(obstacleLabel);
            mainArena.drawAvoider(canvasPane);
            System.out.println(mainArena.toString());
        });
        return new HBox(add, stop, addDroneButton, addObstacleButton, addAvoider);
    }

    /**
     * Main Process: runs GUI class
     * @param args: no arguments sent
     */
    public static void main(String[] args) {
        launch(args);
    }

}
