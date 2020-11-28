package Drone;
import java.util.*;

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

    /**
     * search arraylist of droneList to see if there is a drone at x,y
     * @param x: checks value at x
     * @param y: checks value at y
     * @return null if no Drone there, otherwise return drone
     */
    public Drone getDroneAt(int x, int y) {
        Drone a = null;
        for (Drone d : droneList) {// for the list of drones in the array
            if (d.isHere(x, y)) {// check if isHere is true(if sx and sy exist)
                return a = d;// then return d meaning position id clear put drone in array
            } else {
                return a;// otherwise null
            }
        }
        return a;
    }

    // shows all the drones within the array
    public void showDrones(ConsoleCanvas c) {
        for (Drone d : droneList) {
            d.displayDrone(c);
        }
    }
    // moves all drones attached to the array
    public void moveAllDrones() {
        for (Drone d : droneList) {
            d.tryToMove(this);
        }
    }

    // verifies if movement can be made to the said position
    public boolean canMoveHere(int x, int y) {
    if (x <= 0 || x >= xDimensions || y <= 0 || y >= yDimensions){
        return false;
    }else return getDroneAt(x, y) == null;
    }

    // focuses on adding a drone by generating a random value and then adding it into the array
        public void addDrone() {
            Random random;
            int xco, yco;
            random = new Random();
            if (droneList.size() < xDimensions * yDimensions){
                do{
                     xco = random.nextInt((xDimensions));
                     yco = random.nextInt((yDimensions));
                }while(getDroneAt(xco, yco) != null);
                droneTest = new Drone(xco, yco, Direction.randomDir());
                droneList.add(droneTest);
            }
        }

        // focuses on printing the arena size and all drones attached to the array
    public String toString() {
        StringBuilder s = new StringBuilder("The arena size is " + xDimensions + " x " + yDimensions + " and: " + "\n");

        for (Drone d : droneList) {// for all the drones inside the arraylist have them in the string
            s.append(d.toString()).append("\n");
        }
        return s.toString();
    }






}
