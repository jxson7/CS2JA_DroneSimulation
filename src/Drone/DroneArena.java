package Drone;
import java.util.*;


public class DroneArena {
    public int xDimensions, yDimensions;
    ArrayList<Drone> droneList = new ArrayList<Drone>();
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
            if (d.isHere(x, y) == true) {// check if isHere is true(if sx and sy exist)
                return a = d;// then return d meaning position id clear put drone in array
            } else {
                return a;// otherwise null
            }
        }
        return a;

    }

    public void showDrones(ConsoleCanvas c) {
        for (Drone d : droneList) {
            d.displayDrone(c);
        }
    }
    public void moveAllDrones() {
        for (Drone d : droneList) {
            d.tryToMove(this);
        }
    }
    public boolean canMoveHere(int x, int y) {
        if (getDroneAt(x, y) != null || x >= xDimensions || y >= yDimensions || x < 0 || y < 0)
            return false;
        else
        return true;
    }

        public void addDrone() {
            Random random;
            random = new Random();
            int xco = (int) (Math.random() * ((xDimensions) + 1));
            int yco = (int) (Math.random() * ((yDimensions) + 1));

            droneTest = new Drone(xco, yco, Direction.randomDir());
            droneList.add(droneTest);
        }

    public String toString() {
        //return "Drone is of size " + xco + " , " + yco;
        StringBuilder arena = new StringBuilder("Drone arena is of size: " + xDimensions + ", " + yDimensions + ". ");
        for (Drone drone : droneList) {
            arena.append(drone.toString());
        }
        return arena.toString();
    }


// Tester Class
//    public static void main(String[] args) {
//        DroneArena a = new DroneArena(20, 10);	// create drone arena
//        a.addDrone();
//        System.out.println(a.toString());	// print where is
//    }

}
