package DroneGUI;

import javafx.scene.image.Image;

public class Obstacles extends Drone{
    Image droneImage = new Image(getClass().getResourceAsStream("obstacle.jpeg"));

    Obstacles(int x, int y, Direction d) {
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
    public void displayObstacle(MyCanvas x) {
        x.drawImage(droneImage,posX, posY, 40);
    }


    /**
     * @return string stating location of both x and y coordinates of the drone, including direction, generated from Direction.java.
     */
    public String toString() {
        return "Obstacle is at: " + posX + "," + posY + " ,positioned: " + direct.toString() + ".";
    }

}
