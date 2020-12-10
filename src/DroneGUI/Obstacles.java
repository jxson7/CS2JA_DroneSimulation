package DroneGUI;

import javafx.scene.image.Image;

/**
 * Focuses on being an extension to Drone, and inherits all components, methods via super, but re-declares the image
 */
public class Obstacles extends Drone{
    Image droneImage = new Image(getClass().getResourceAsStream("obstacle.jpg")); // image of drone

    public Obstacles(int x, int y, Direction d) {
        super(x, y, d);
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
    public void displayObstacles(MyCanvas x) {
        x.drawImage(droneImage,posX, posY, 20); // uses drawImage function from myCanvas
    }


    /**
     * @return string stating location of both x and y coordinates of the drone, including direction, generated from Direction.java.
     */
    public String toString() {
        return "Obstacle is at: " + posX + "," + posY + " , positioned: " + direct.toString() + ".";
    }

    /**
     * The following class will act as a tester class to experiment with how successful this class functions.
     * @param args: no arguments passed
     */
    public static void main(String [] args){
        Obstacles x = new Obstacles(15,50,Direction.randomDir());
        x.toString();
        System.out.println((x));
    }

}
