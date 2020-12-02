/*
  author: Jason Jay Dookarun
  Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a
  drone. This includes declarations such as posX, posY, with setters and getters.
 */

package DroneGUI;

import javafx.scene.image.Image;

// initial setup of the Drone setup
public class Drone {
    private final int posX;
    private final int posY;
    private final Direction direct;
    Image droneImage = new Image(getClass().getResourceAsStream("circle.png"));

    Drone(int x, int y, Direction d){
        direct  = d;
        posX = x;
        posY = y;
    }

    public int getPosX(){ return posX;}
    public int getPosY(){return posY; }
    public Direction getDirect() { return direct; }


    /**
     *
     * @param c: sends the canvas data to the console canvas as a method to allow a display.
     */
    public void displayDrone(myCanvas c) {
        c.drawImage(droneImage,posX, posY, 40);
        // << call the showIt method in c to put a D where the drone is
    }

    /**
     * @return string stating location of both x and y co ordinates.
     */
    // process of toString to print coordinates and the direction of the said drone
    public String toString() {
        return "Drone is at: " + posX + "," + posY + "." + "in the direction of: " + direct.toString() + ".";
    }


}
