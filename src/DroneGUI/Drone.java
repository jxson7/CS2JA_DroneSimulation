package DroneGUI;
import javafx.scene.image.Image;

/**
 @author Jason Jay Dookarun
 The following creation involves developing a basic method, with appropriate declarations to create a
 drone. This includes declarations such as posX, posY, with setters and getters.
 **/
public class Drone {
    private int posX,posY;
    private final Direction direct;
    Image droneImage = new Image(getClass().getResourceAsStream("circle.png"));

    Drone(int x, int y, Direction d){
        direct  = d;
        posX = x;
        posY = y;
    }


    /**
     * setters and getters are formed as part of a process of retrieval, for future classes that attempt at access this data.
     */
    public void setPosX(){this.posX = posX;}
    public void setPosY(){this.posY = posY;}
    public int getPosX(){ return posX;}
    public int getPosY(){return posY; }
    public Direction getDirect() { return direct; }


    /**
     *
     * @param x: uses myCanvas as a parameter to draw the element upon the canvas, namely, drone.
     */
    public void displayDrone(MyCanvas x) {
        x.drawImage(droneImage,posX, posY, 40);
    }

    /**
     * @return string stating location of both x and y coordinates of the drone, including direction, generated from Direction.java.
     */
    public String toString() {
        return "Drone is at: " + posX + "," + posY + "." + "in the direction of: " + direct.toString() + ".";
    }


}
