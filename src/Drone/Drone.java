/*
author: Jason Jay Dookarun
Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a 
drone. This includes declarations such as posX, posY, with setters and getters.
 */
package Drone;

//setters
public class Drone {
    public int posX;
    public int posY;


    Drone(int x, int y){
    posX = x;
    posY = y;

    }
    // implemented setters and getters



    public String toString() {
        return "Drone is at: " + posX + "," + posY + ".";
    }

    /**
     * Is the drone at this x,y position
     * @param x	    x position
     * @param y 	y position
     * @return		true if drone is at x,y or false otherwise
     */

    public boolean isHere(int x,int y){
        return posX == x && posY == y;
    }


    public static void main(String[] args) {
        Drone d = new Drone(5,3);		// create drone
        System.out.println(d.toString());	// print where is
        Drone x = new Drone(5,5);
        System.out.println(x.toString());

    }

}
