package DroneGUI;

import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI extends Application {
    private VBox rtPane;						// pane in which info on system listed
    private Random rgen = new Random();			// random number generator
    private myCanvas mc;						// canvas into which system drawn
    DroneArena ourSystem;			// simple model of solar system
    private int canvasSize = 512;				// size of canvas
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
    private void showAbout() {
        showMessage("About", "Marks BorderPane Demonstrator");
    }

    /**
     * Function to set up the menu
     */
    MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();		// create menu

        Menu mHelp = new Menu("Help");			// have entry for help
        // then add sub menus for About and Help
        MenuItem help = new MenuItem("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessage("Help", "Watch the drones move");
            }
        });
        mHelp.getItems().addAll(help);
        // add the item and then the action to perform
        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAbout();				// show the about message
            }
        });
        mHelp.getItems().addAll(mAbout); 	// add submenus to Help

        // now add File menu, which here only has Exit
        Menu mFile = new Menu("File");
        MenuItem mExit = new MenuItem("Exit");
        mExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);						// quit program
            }
        });
        mFile.getItems().addAll(mExit);

        menuBar.getMenus().addAll(mFile, mHelp);	// menu has File and Help

        return menuBar;					// return the menu, so can be added
    }

    /**
     * show where Earth is, in pane on right
     */
    public void drawStatus() {
        // clear rtPane
        rtPane = new VBox();
        // get label which has information on system - use ourSystem.toString()
        Label label = new Label(ourSystem.toString());

        // add label to rtPane
        rtPane.getChildren().add(label);
    }
    public void updateStatus() {

        rtPane.getChildren().clear();					// clear rtpane
        // now create label
        Label l = new Label(ourSystem.toString());
        rtPane.getChildren().add(l);				// add label to pane

    }
    /**
     * set up the mouse event handler, so when click on canvas, draw Earth there
     * @param canvas
     */
    private void setMouseEvents (Canvas canvas) {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        /// write here code to put sun at e.x, e.y; redraw system and update panel



                        //   ourSystem.setSystem(mc, e.getX(), e.getY());
                        //updateSystem();
                        //  drawSystem();

                    }
                });
    }

    /**
     * set up the button and return so can add to borderpane
     * @return
     */
    private HBox setButtons() {
        // create button

        // now add handler
        Random random = new Random();
        double degreeAngle = random.nextDouble()*360;//generate random number between 1 and 360
        double radAngle = degreeAngle*Math.PI/180; //convert to radians
        //	ourSystem.updateSystem(radAngle);//update
        ourSystem.drawArena(mc);//draw


        Button btnStart = new Button("Add Drone");
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                on = true;
                aDD(on);
            }
        });
        Button btnMove = new Button("Move");
        btnMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                on = false;
                System.out.println(on);
                aDD(on);

                System.out.println("works");

            }
        });
        Button btnStop = new Button("Stop");
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                on = false;
                System.out.println(on);
                aDD(on);

                System.out.println("works");

            }
        });
        return new HBox( btnStart, btnMove, btnStop);
    }
    public void aDD(Boolean run) {
        final long startNanoTime = System.nanoTime();
        //if(run == true) {


        // define handle for what do at this time
        // calculate time
        // 		System.out.println("drone added");
        ourSystem.addDrone();
        updateStatus();
        ourSystem.drawArena(mc);
        System.out.println(ourSystem.toString());


        //}
//    	else if (run == false) {
//
//    	    new AnimationTimer()			// create timer
//	    	{
//	    		public void handle(long currentNanoTime) {
//	    				// define handle for what do at this time
//	    			double t = (currentNanoTime - startNanoTime) / 1000000000.0; 			// calculate time
//	    	//		ourSystem.updateSystem(t);			// use time as an angle for calculating position of earth
//	    			//System.out.println(t);
//	    	//		ourSystem.drawSystem(mc);
//	    		}
//	    	}.stop();
//    	}
    }

    @Override
    public void start(Stage stagePrimary) throws Exception {
        stagePrimary.setTitle("27003132 Drone Simulator");

        BorderPane bp = new BorderPane();			// create border pane

        bp.setTop(setMenu());						// create menu, add to top

        Group root = new Group();					// create group
        Canvas canvas = new Canvas( canvasSize, canvasSize );
        // and canvas to draw in
        root.getChildren().add( canvas );			// and add canvas to group
        mc = new myCanvas(canvas.getGraphicsContext2D(), canvasSize, canvasSize);
        // create MyCanvas passing context on canvas onto which images put
        ourSystem = new DroneArena(mc.xCanvasSize, mc.yCanvasSize);				// create object for sun, planets, etc
        ourSystem.addDrone();
        setMouseEvents(canvas);						// set mouse handler
        bp.setCenter(root);							// put group in centre pane

        rtPane = new VBox();		// set vBox for listing data

        drawStatus();
        bp.setRight(rtPane);						// put in right pane

        bp.setBottom(setButtons());					/// add button to bottom

        Scene scene = new Scene(bp, canvasSize*1.5, canvasSize*1.2);
        // create scene so bigger than canvas,

        if(on == true) {
            new AnimationTimer()			// create timer
            {
                public void handle(long currentNanoTime) {
                    // define handle for what do at this time
                    ourSystem.addDrone();	// calculate time
                    //ourSystem.updateSystem(t);			// use time as an angle for calculating position of earth
                    System.out.println("drone added");
                    ourSystem.drawArena(mc);
                }
            }.start();
        }
        stagePrimary.setScene(scene);
        stagePrimary.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
