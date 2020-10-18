/*
author: Jason Jay Dookarun
Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a 
drone. This includes declarations such as posX, posY, with setters and getters.
 */
package Drone;

//setters
public class Drone {
    private int posX;
    private int posY;


    Drone(int x, int y){
    posX = x;
    posY = y;

    }
    // implemented setters and getters

    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }

    public String toString() {
        return "Drone is at " + posX + ", " + posY;
    }


    public static void main(String[] args) {
        Drone d = new Drone(5,3);		// create drone
        System.out.println(d.toString());	// print where is
        Drone x = new Drone(5,5);
        System.out.println(x.toString());

    }

}
