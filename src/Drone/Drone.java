/*
  author: Jason Jay Dookarun
  Practical 1 Notes: The following creation involves developing a basic method, with appropriate declarations to create a
  drone. This includes declarations such as posX, posY, with setters and getters.
 */

package Drone;

public class Drone {
    public int posX;
    public int posY;
    public int dx,dy;
    private Direction direct;



    Drone(int x, int y, Direction d){
    direct  = d;
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
    public int getDx(){return dx;}
    public int getDy(){return dy;}
    public void setDx(int setDx){this.dx = dx;}
    public void setDy(int setDy){this.dy = dy;}
    public Direction getDirect() { return direct; }
    public void setDirect(Direction direct) { this.direct = direct; }

    /**
     * @return string stating location of both x and y co ordinates.
     */
    public String toString() {
        return "Drone is at: " + posX + "," + posY + "." + "in the direction of: " + direct.toString() + "./n";
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

////    //TODO: Refactor
    public void tryToMove(DroneArena a) {
        switch (direct ) {
            case north:
                if (a.canMoveHere(posX - 1, posY)) // checks move eligibility
                    posX = posX - 1 ; // drone moves
                else
                    direct = direct.nextDir(); // changes direction
                break;
            case south:
                 if (a.canMoveHere(posX + 1, posY))
                  posX =  posX + 1;
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
                    posY = posY - 1 ;
                else
                    direct = direct.nextDir();
                break;
            default:
                break;
        }
    }


//    public void tryToMove(DroneArena a) {
//        int newx = posX + dx;
//        int newy = posY + dy;
//
//        switch (a.canMoveHere(newx,newy)){
//            case 0: x = newx;
//                    y = newy;
//        }
//
//    }

//
//    case NORTH:
//            if (posX - 1) >= 0{
//                posX = posX -1;
//    }
//
//            case EAST:
//                    if (posy + 1) < move
//


//    public void tryToMove(DroneArena a) {
//        if (direct == Direction.north) {
//            if (a.canMoveHere(posX - 1, posY)) // checks move eligibility
//                posX = posX - 1; // drone moves
//            else {
//                direct = direct.nextDir(); // changes direction
//            }
//        }
//        if (direct == Direction.south) {
//            if (a.canMoveHere(posX + 1, posY))
//                posX = posX + 1;
//            else
//                direct = direct.nextDir();
//        }
//        if (direct == Direction.east) {
//            if (a.canMoveHere(posX, posY + 1))
//                posY = posY + 1;
//            else
//                direct = direct.nextDir();
//        }
//        if (direct == Direction.west) {
//            if (a.canMoveHere(posX, posY - 1))
//                posY = posY - 1;
//            else
//                direct = direct.nextDir();
//        }
//    }


    // Method of validation to confirm that the drone exists in the said position
    public boolean isHere(int xVal, int yVal){
        // if the values droneX, droneY(position of drone) is the same as other x,y
        return posX == xVal && posY == yVal;
    }


    public static void main(String[] args) {
        Drone d = new Drone(5,3, Direction.east);		// create drone
        System.out.println(d.toString());	// print where is
        Drone x = new Drone(5,5, Direction.south);
        System.out.println(x.toString());
    }


}
