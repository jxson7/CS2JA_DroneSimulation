package Drone;
import java.util.*;


public class DroneArena {
    int xSize, ySize;
    ArrayList<Drone> drones;
    Random random;
    Drone droneTest;

    public DroneArena(int x, int y) {// declaration
        xSize = x;
        ySize = y;
        random = new Random();
        drones = new ArrayList<Drone>();
    }

    public int getxSize(){
        return xSize;
    }
    public int getySize(){
        return ySize;
    }

     // initialises new arena, by clearing out existing arrayList()
    public void newArena() {
        drones.clear();
        drones = new ArrayList<Drone>();
    }
    public String toString() {
        return "Drone is at " + xSize + ", " + ySize;
    }




        public void addDrone(){
        int x,y;
            x = random.nextInt(xSize);
            y = random.nextInt(ySize);
            droneTest = new Drone(x,y);
            drones.add(droneTest);

    }

    /**
     * search arraylist of drones to see if there is a drone at x,y
     * @param x
     * @param y
     * @return null if no Drone there, otherwise return drone
     */
    public Drone getDroneAt(int x, int y) {
        Drone e = null;
        for (Drone droneTest : drones) {
            if (droneTest.isHere(x, y)) {
                return e = droneTest;
            } else {
                return droneTest;
            }
        }
        return e;

    }


    /*
    Tester Class
    public static void main(String[] args) {
        DroneArena a = new DroneArena(20, 10);	// create drone arena
        a.addDrone();
        System.out.println(a.toString());	// print where is
    }
*/


}
