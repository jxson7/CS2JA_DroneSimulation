package DroneGUI;

import javafx.scene.image.Image;

public class Avoider extends Drone {
    /**
     * focuses on inheriting the content from Drone, but re-declares the image.
     */
    Image droneImage = new Image(getClass().getResourceAsStream("circle.jpeg")); // avoider image

    public Avoider(int x, int y, Direction d) {
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
     * @param x: uses myCanvas as a parameter to draw the element upon the canvas, namely, avoider.
     */
    public void displayAvoider(MyCanvas x) {
        x.drawImage(droneImage,posX, posY, 20);
    } // uses function stated in myCanvas


    /**
     * @return string stating location of both x and y coordinates of the avoider, including direction, generated from Direction.java.
     */
    public String toString() {
        return "Avoid this drone at: " + posX + "," + posY + " , positioned: " + direct.toString() + ".";
    }

    /**
     * The following class will act as a tester class to experiment with how successful this class functions.
     * @param args: none passed
     */
    public static void main(String [] args){
        Avoider x = new Avoider(10,20,Direction.randomDir());
        x.toString();
        System.out.println((x));
    }
}
