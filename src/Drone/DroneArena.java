package Drone;
import java.util.*;

//TODO: Complete Redraft

public class DroneArena {
    public int xDimensions, yDimensions;
    ArrayList<Drone> droneList = new ArrayList<Drone>();
    Drone droneTest;
    DroneArena(int width, int height){
        xDimensions = width;
        yDimensions = height;
    }

   // public DroneArena(int x, int y) {// declaration
     //   xDimensions = x;
       // yDimensions = y;
        //random = new Random();
        //xco = random.nextInt();
       // //yco = random.nextInt();
        //droneList = new ArrayList<Drone>();
   // }

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
        Drone e;
        e = null;
        for (Drone droneTest : droneList) {
            if (droneTest.isHere(x, y)) return e = droneTest;
            else {
                return droneTest;
            }
        }
        return e;

    }

    public void showDrones(ConsoleCanvas c) {
        for (Drone d : droneList) {
            d.displayDrone(c);
        }

        // << loop through all the Drones calling the displayDrone method >>
    }


    public void addDrone() {
            Random random;
            random = new Random();
            int xco = (int) (Math.random() * ((xDimensions) + 1));
            int yco = (int) (Math.random() * ((yDimensions) + 1));

            droneTest = new Drone(xco, yco);

            droneList.add(droneTest);

            //return droneTest;
        }

    public String toString() {
        //return "Drone is of size " + xco + " , " + yco;
        String arena = "Drone arena is of size: " + xDimensions + ", " + yDimensions + ". ";
        for (int i = 0;i < droneList.size(); i++) {
            arena += droneList.get(i).toString();

        }
        return arena.toString();
    }


 /*   //Tester Class
    public static void main(String[] args) {
        DroneArena a = new DroneArena(20, 10);	// create drone arena
        a.addDrone();
        System.out.println(a.toString());	// print where is
    }
*/


}
