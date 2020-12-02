package DroneGUI;
import java.util.*;
import javafx.scene.image.Image;



/**
 * the drone arena focuses on viewing where the drone is, the size of the arena, and adding drones to the aforementioned
 */

// constructor focused on creating dimensions and the array list for the setup of adding drones
public class DroneArena {
    public int xDimensions;
    public int yDimensions;
    ArrayList<Drone> droneList = new ArrayList<>();
    Drone droneTest;

    DroneArena(int width, int height){
        xDimensions = width;
        yDimensions = height;
    }

    //setters and getters implemented for future purposes
    public void setxDimensions(){ this.xDimensions = xDimensions; }
    public void setyDimensions(){ this.yDimensions = yDimensions; }
    public int getX(){
        return xDimensions;
    }
    public int getY(){
        return yDimensions;
    }

    public void drawArena(myCanvas myCanvas){
        for (Drone d: droneList){
            d.displayDrone(myCanvas);

        }
    }

    // focuses on adding a drone by generating a random value and then adding it into the array
    public void addDrone() {
        Random random;
        int xco, yco;
        random = new Random();
        if (droneList.size() < xDimensions * yDimensions){
                xco = random.nextInt((xDimensions));
                yco = random.nextInt((yDimensions));
                droneTest = new Drone(xco, yco, Direction.randomDir());
                droneList.add(droneTest);

        }
    }

    // focuses on printing the arena size and all drones attached to the array
    public String toString() {
        StringBuilder s = new StringBuilder("The arena size is " + xDimensions + " x " + yDimensions + " and consists of the following drones: " + "\n");

        for (Drone d : droneList) {// for all the drones inside the arraylist have them in the string
            s.append(d.toString()).append("\n");
        }
        return s.toString();
    }

}
