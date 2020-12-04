package DroneGUI;

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


import static java.lang.System.exit;

/**
 * @author Jason Jay Dookarun
 * The following class focuses on creating a GUI whereby this replaces DroneInterface from DroneTerminal to
 * become the new GUI interaction panel. This will allow the user to add drones and view drones visually.
 **/
public class GUI extends Application {
    private VBox leftPane;
    private MyCanvas canvasPane;
    DroneArena mainArena;

    /**
     * The following method focuses on creating and generating all the components involved as part of the project, including
     * the canvas, the arena, the status panel and the buttons involved.
     * @param mainStage: mainStage involved, that is drawn upon.
     */
    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("26017434: Drone Simulator");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(setMenuBar());
        Group groupComponent = new Group();
        int xCanvasSize = 512;
        int yCanvasSize = 512;
        Canvas canvas = new Canvas(xCanvasSize, yCanvasSize);
        groupComponent.getChildren().add(canvas);			// and add canvas to group
        canvasPane = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize);
        mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);
        borderPane.setCenter(groupComponent);
        leftPane = new VBox();
        statusPanel();
        ScrollPane sideBarScroller = new ScrollPane(leftPane);
        sideBarScroller.setFitToWidth(true);

        borderPane.setRight(sideBarScroller);
        borderPane.setLeft(leftPane);
        borderPane.setBottom(createButtonPane());
        Scene scene = new Scene(borderPane, xCanvasSize *1.7, yCanvasSize *1.2);
        mainStage.setScene(scene);
        mainStage.show();

    }
    /**
     * The following method focuses upon the creation of all additional panes such as the Help support section, followed by
     * File -> Quit
     * Help -> About, Help.
     * @return taskbar creation with Quit, Help, About
     */
    MenuBar setMenuBar(){
        MenuBar menuBar = new MenuBar();
        Menu fileSection = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(t -> {
            int userResponse = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?", "Quit Drone Simulation", JOptionPane.YES_NO_OPTION);
            if (userResponse == JOptionPane.YES_OPTION) { exit(0);}
            // quit program
        });
        Menu helpSection = new Menu("Help");
        MenuItem help = new Menu("Help");
        help.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null,"Add Drone: adds a drone to the canvas, Add Obstacle: Adds an Obstacle to the canvas, Quit: exits the program","Help", JOptionPane.INFORMATION_MESSAGE));
        MenuItem aboutSection = new MenuItem("About");
        aboutSection.setOnAction(actionEvent -> JOptionPane.showMessageDialog(null, "Welcome to the CS2JA: Drone Simulation by Jason Jay Dookarun","About", JOptionPane.INFORMATION_MESSAGE));
        fileSection.getItems().addAll(exit);
        helpSection.getItems().addAll(help,aboutSection);
        menuBar.getMenus().addAll(fileSection, helpSection);
        return menuBar;
    }

    /**
     * Focuses on creation the buttons for the user to interact with, i.e. add Drone and Stop System
     * Add Drone: Focuses on adding a drone to the Arena, updating the system via getChild() and drawing the mentioned.
     * @return Add Button, Stop System Button
     */
    private HBox createButtonPane() {
        mainArena.drawArena(canvasPane);//draw
        Button addDroneButton = new Button("Add Drone");
        addDroneButton.setOnAction(event -> {
            mainArena.addDrone();
            leftPane.getChildren().clear();					// clear leftPane
            Label droneLabel = new Label(mainArena.toString());
            leftPane.getChildren().add(droneLabel);
            mainArena.drawArena(canvasPane);
            System.out.println(mainArena.toString());
        });
        Button addObstacleButton = new Button("Add Obstacle");
        addObstacleButton.setOnAction(event -> {
            mainArena.addObstacle();
            leftPane.getChildren().clear();					// clear leftPane
            Label obstacleLabel = new Label(mainArena.toString());
            leftPane.getChildren().add(obstacleLabel);
            mainArena.drawObstacle(canvasPane);
            System.out.println(mainArena.toString());
        });
        Button stopSystemButton = new Button("Quit");
        stopSystemButton.setOnAction(event -> {
            int userResponse = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?", "Quit Drone Simulation", JOptionPane.YES_NO_OPTION);
            if (userResponse == JOptionPane.YES_OPTION) { exit(0); }});
        return new HBox( addDroneButton,addObstacleButton, stopSystemButton);
    }

    /**
     * Status panel focuses on designing the side panel that will illustrate to the user any process that take place, i.e. drone coordinates, drone direction
     */
    public void statusPanel() {
        leftPane = new VBox();
        Label label = new Label(mainArena.toString());
        leftPane.getChildren().add(label);
    }


    /**
     * Main Process: runs GUI class
     * @param args: no arguments sent
     */
    public static void main(String[] args) {
        launch(args);
    }

}
