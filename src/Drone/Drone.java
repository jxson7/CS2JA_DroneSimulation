/*
  author: Jason Jay Dookarun
  Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a
  drone. This includes declarations such as posX, posY, with setters and getters.
 */

package Drone;

public class Drone {
    public int posX;
    public int posY;



    Drone(int x, int y){
    posX = x;
    posY = y;
    }

    // introduction of setters and getters for future purposes of referencing if required.
    public void setPosX(int posX){
        this.posX = posX;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    /**
     * @return string stating location of both x and y co ordinates.
     */
    public String toString() {
        return "Drone is at: " + posX + "," + posY + ".";
    }

    /**
     *
     * @param canvas: sends the canvas data to the console canvas as a method to allow a display.
     */
    public void displayDrone(ConsoleCanvas canvas) {
        char arenaBorder = '*';
        canvas.showIt(posX, posY, arenaBorder);
        // << call the showIt method in c to put a D where the drone is
    }

    // Method of validation to confirm that the drone exists in the said position
    public boolean isHere(int xVal, int yVal){
        return posX == xVal && posY == yVal;
    }


    //public static void main(String[] args) {
      //  Drone d = new Drone(5,3);		// create drone
        //System.out.println(d.toString());	// print where is
        //Drone x = new Drone(5,5);
        //System.out.println(x.toString());

    //}

}
