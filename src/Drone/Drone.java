/*
  author: Jason Jay Dookarun
  Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a
  drone. This includes declarations such as posX, posY, with setters and getters.
 */

package Drone;

// initial setup of the Drone setup
public class Drone {
    private int posX;
    private int posY;
    private Direction direct;



    Drone(int x, int y, Direction d){
    direct  = d;
    posX = x;
    posY = y;
    }

    public int getPosY(){
        return posY;
    }
    public Direction getDirect() { return direct; }


    /**
     *
     * @param c: sends the canvas data to the console canvas as a method to allow a display.
     */
    public void displayDrone(ConsoleCanvas c) {
        char arenaBorder = 'X';
        c.showIt(posX, posY, arenaBorder);
        // << call the showIt method in c to put a D where the drone is
    }

    // compares eligibility of all positions to see if it is successful. If available, drone will move to suitable position.
    public void tryToMove(DroneArena a) {
        switch (direct) {
            case north:
                if (a.canMoveHere(posX - 1, posY)) // checks move eligibility
                    posX = posX - 1; // drone moves
                else
                    direct = direct.nextDir(); // changes direction
                break;
            case south:
                if (a.canMoveHere(posX + 1, posY))
                    posX = posX + 1;
                else
                    direct = direct.nextDir();
                break;
            case east:
                if (a.canMoveHere(posX, posY + 1))
                    posY = posY + 1;
                else
                    direct = direct.nextDir();
                break;
            case west:
                if (a.canMoveHere(posX, posY - 1))
                    posY = posY - 1;
                else
                    direct = direct.nextDir();
                break;
            default:
//
                break;
        }

    }

    // Method of validation to confirm that the drone exists in the said position
    public boolean isHere(int xVal, int yVal){
        // if the values droneX, droneY(position of drone) is the same as other x,y
        // if the values droneX, droneY(position of drone) is the same as other x,y
        return posX == xVal && posY == yVal;

    }

    /**
     * @return string stating location of both x and y co ordinates.
     */
    // process of toString to print coordinates and the direction of the said drone
    public String toString() {
        return "Drone is at: " + posX + "," + posY + "." + "in the direction of: " + direct.toString() + ".";
    }

}
