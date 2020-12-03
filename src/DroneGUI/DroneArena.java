package DroneGUI;
import java.util.*;


/**
 * @author Jason Jay Dookarun
 * The following class focuses on the following: adding a drone with random coordinates to an arrayList, that collects all the drones that have been added, and the creation
 * of an arena size. These are then printed via a toString() function.
 */
public class DroneArena {
    public int xDimensions, yDimensions;
    ArrayList<Drone> droneList = new ArrayList<>();
    Drone newDrone;


    /**
     * The following drone arena takes into consideration 2 components
     * @param width: represents how wide the canvas is, via the x axis
     * @param height: represents how tall the canvas is, via the y axis
     */
    DroneArena(int width, int height){
        xDimensions = width;
        yDimensions = height;
    }

    /**
     * setters and getters implemented for future purposes
     */

    public void setxDimensions(){ this.xDimensions = xDimensions; }
    public void setyDimensions(){ this.yDimensions = yDimensions; }
    public int getX(){
        return xDimensions;
    }
    public int getY(){
        return yDimensions;
    }

    /**
     * The following method takes myCanvas as parameter and reviews all the drones available in the list. If drones do exist, then the drones
     * are displayed via a displayDrone function.
     * @param myCanvas: represents the canvas pane that is drawn upon to illustrate the drones.
     */
    public void drawArena(MyCanvas myCanvas){
        for (Drone d: droneList){
            d.displayDrone(myCanvas);

        }
    }

    /**
     * A random value is generated. A checker is implemented to verify that the drone array list does not exceed the limit.
     * If it exceeds the limit, then the drone cannot be added, else, if it meets the requirements, random coordinates are generated, and the
     * drone (newDrone) is added into the arrayList (droneList).
     *
     */
    // focuses on adding a drone by generating a random value and then adding it into the array
    public void addDrone() {
        Random random;
        int xco, yco;
        random = new Random();
        if (droneList.size() < xDimensions * yDimensions){
                xco = random.nextInt((xDimensions));
                yco = random.nextInt((yDimensions));
                newDrone = new Drone(xco, yco, Direction.randomDir());
                droneList.add(newDrone);
        } else{
            System.out.println("Drone cannot be added as you have reached maximum capacity. Please try again.");
        }
    }

    /**
     * @return the dimensions of the arena and the drones attached to the arrayList (droneList)
     */
    // focuses on printing the arena size and all drones attached to the array
    public String toString() {
        StringBuilder s = new StringBuilder("The arena size is " + xDimensions + " x " + yDimensions + " and consists of the following drones: " + "\n");

        for (Drone d : droneList) {// for all the drones inside the arraylist have them in the string
            s.append(d.toString()).append("\n");
        }
        return s.toString();
    }

}
