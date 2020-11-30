package DroneGUI;

import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GUI extends Application {
    int xSize = 600;
    int ySize = 600;
    myCanvas myCanvas;
    DroneArena da;





//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("26017434 Drone Simulation: GUI Edition");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // stage.setScene(new Scene(root, 300, 275));

        BorderPane border = new BorderPane();
        //border.setTop(setMainMenu());
        Canvas canvas = new Canvas(xSize, ySize);
        stage.setScene(new Scene(root, xSize, ySize));
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

//    private Node setMainMenu() {
//
//    }
}

