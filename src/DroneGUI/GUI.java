package DroneGUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Random;

import static java.lang.System.exit;


public class GUI extends Application {
    private VBox leftPane;						// pane in which info on system listed
    private myCanvas canvasPane;						// canvas into which system drawn
    DroneArena mainArena;			// simple model of solar system
    boolean on = false;
    /**
     * Function to show a message,
     * @param TStr		title of message block
     * @param CStr		content of message
     */
    private void showMessage(String TStr, String CStr) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(TStr);
        alert.setHeaderText(null);
        alert.setContentText(CStr);

        alert.showAndWait();
    }
    /**
     * function to show in a box ABout the programme
     */
    private void aboutPanel() {
        showMessage("About", "Welcome to Jason Dookarun's Drone Simulation. In this program, you will be able to add drones and view them visually. :) ");
    }

    /**
     * Function to set up the menu
     */
    MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();		// create menu

        Menu mHelp = new Menu("Help");			// have entry for help
        // then add sub menus for About and Help
        MenuItem help = new MenuItem("Help");
        help.setOnAction(actionEvent -> showMessage("Help", "Watch the drones move"));
        mHelp.getItems().addAll(help);
        // add the item and then the action to perform
        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(actionEvent -> {
            aboutPanel();				// show the about message
        });
        mHelp.getItems().addAll(mAbout); 	// add submenus to Help



        return menuBar;					// return the menu, so can be added
    }

    /**
     * show where Earth is, in pane on right
     */
    public void statusPanel() {
        // clear leftPane
        leftPane = new VBox();
        // get label which has information on system - use mainArena.toString()
        Label label = new Label(mainArena.toString());

        // add label to leftPane
        leftPane.getChildren().add(label);
    }
    public void updateStatus() {

        leftPane.getChildren().clear();					// clear leftPane
        // now create label
        Label l = new Label(mainArena.toString());
        leftPane.getChildren().add(l);				// add label to pane

    }

    private void setMouseEvents (Canvas canvas) {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> {
                });
    }

    private HBox setButtons() {
        // create button

        // now add handler
        Random random = new Random();
        double degreeAngle = random.nextDouble()*360;//generate random number between 1 and 360
        double radAngle = degreeAngle*Math.PI/180; //convert to radians
        //	mainArena.updateSystem(radAngle);//update
        mainArena.drawArena(canvasPane);//draw

        Button btnStart = new Button("add Drone");
        btnStart.setOnAction(event -> {
            on = true;
            addNewDrone();
        });

        Button btnStop = new Button("Stop");
        btnStop.setOnAction(event -> {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit the program?", "Exit Program Message Box",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                exit(0);
            }

        });
        return new HBox( btnStart, btnStop);
    }
    public void addNewDrone() {
        System.nanoTime();
        //if(run == true) {


        // define handle for what do at this time
        // calculate time
        // 		System.out.println("drone added");
        mainArena.addDrone();
        updateStatus();
        mainArena.drawArena(canvasPane);
        System.out.println(mainArena.toString());
    }

    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("26017434: Drone Simulator");

        BorderPane borderPane = new BorderPane();			// create border pane

        borderPane.setTop(setMenu());						// create menu, add to top

        Group root = new Group();					// create group
        // size of canvas
        int canvasSize = 550;
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        // and canvas to draw in
        root.getChildren().add( canvas );			// and add canvas to group
        canvasPane = new myCanvas(canvas.getGraphicsContext2D(), canvasSize, canvasSize);
        // create MyCanvas passing context on canvas onto which images put
        mainArena = new DroneArena(canvasPane.xCanvasSize, canvasPane.yCanvasSize);				// create object for sun, planets, etc
        //mainArena.addDrone();
        setMouseEvents(canvas);						// set mouse handler
        borderPane.setCenter(root);							// put group in centre pane

        leftPane = new VBox();		// set vBox for listing data

        statusPanel();
        borderPane.setRight(leftPane);						// put in right pane

        borderPane.setBottom(setButtons());					/// add button to bottom

        Scene scene = new Scene(borderPane, canvasSize *1.2, canvasSize *1.2);
        // create scene so bigger than canvas,

        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
