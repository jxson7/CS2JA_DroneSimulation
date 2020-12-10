package DroneGUI;
import java.util.*;

/**
 * @author Jason Jay Dookarun
 * The following class focuses on the following: adding a drone with random coordinates to an arrayList, that collects all the drones that have been added, and the creation
 * of an arena size. These are then printed via a toString() function.
 */
public class DroneArena {
    // constructors of appropriate usage
    public int xDimensions, yDimensions;
    ArrayList<Drone> droneList = new ArrayList<>();
    Drone newDrone;
    Obstacles newObstacle;
    Avoider newAvoider;
    ArrayList<Obstacles> obstaclesList = new ArrayList<>();
    ArrayList<Avoider> avoiderList = new ArrayList<>();


    /**
     * The following drone arena takes into consideration 2 components
     * @param width: represents how wide the canvas is, via the x axis
     * @param height: represents how tall the canvas is, via the y axis
     */
    DroneArena(int width, int height){
        xDimensions = width;
        yDimensions = height;
    }

    /**
     * setters and getters implemented for future purposes
     */
    public void setxDimensions(){ this.xDimensions = xDimensions; }
    public void setyDimensions(){ this.yDimensions = yDimensions; }
    public int getX(){
        return xDimensions;
    }
    public int getY(){
        return yDimensions;
    }

    /**
     * The following method takes myCanvas as parameter and reviews all the drones available in the list. If drones do exist, then the drones
     * are displayed via a displayDrone function.
     * @param myCanvas: represents the canvas pane that is drawn upon to illustrate the drones.
     */
    public void drawArena(MyCanvas myCanvas){
        for (Drone d: droneList){
            d.displayDrone(myCanvas);
        }
    }

    /**
     * The following method takes myCanvas as parameter and reviews all the drones available in the list. If obstacle(s) do exist, then the obstacle
     * are displayed via a displayObstacle function.
     * @param canvasPane: represents the canvas pane that is drawn upon to illustrate the drones.
     */
    public void drawObstacle(MyCanvas canvasPane) {
        for (Obstacles o: obstaclesList){
            o.displayObstacles(canvasPane);
        }
    }

    /**
     * The following method takes myCanvas as parameter and reviews all the drones available in the list. If avoider(s) do exist, then the avoiders
     * are displayed via a displayObstacle function.
     * @param canvasPane: represents the canvas pane that is drawn upon to illustrate the drones.
     */
    public void drawAvoider(MyCanvas canvasPane) {
        for (Avoider a: avoiderList){
            a.displayAvoider(canvasPane);
        }
    }

    /**
     * A random value is generated. A checker is implemented to verify that the drone array list does not exceed the limit.
     * If it exceeds the limit, then the drone cannot be added, else, if it meets the requirements, random coordinates are generated, and the
     * drone (newDrone) is added into the arrayList (droneList).
     *
     */
    // focuses on adding a drone by generating a random value and then adding it into the array
    public void addDrone() {
        Random random;
        int xco, yco; // values for adding drones
        random = new Random(); // random value is generated
        if (droneList.size() < xDimensions * yDimensions){
                xco = random.nextInt((xDimensions)); // random number assigned to variable
                yco = random.nextInt((yDimensions));// random number assigned to variable
                if (xco < xDimensions - 20 && yco <= yDimensions - 20) {
                    newDrone = new Drone(xco, yco, Direction.randomDir()); // values assigned to Drone as well as a random Direction
                    droneList.add(newDrone); // drone added to arrayList
                } else{
                    System.out.println("Element cannot be added as it exceeds your created canvas");
                }
        } else{
            System.out.println("Drone cannot be added as you have reached maximum capacity. Please try again.");
        }
    }

    /**
     * A random value is generated. A checker is implemented to verify that the obstacle array list does not exceed the limit.
     * If it exceeds the limit, then the drone cannot be added, else, if it meets the requirements, random coordinates are generated, and the
     * obstacle (newObstacle) is added into the arrayList (obstacleList).
     */
    public void addObstacle() {
        Random random; // random value generated
        int xco, yco; // values for storing coordinates of obstacles
        random = new Random();
        if (droneList.size() < xDimensions * yDimensions){ // parameter to ensure the drone can be added to the list
            xco = random.nextInt((xDimensions)); // random num assigned to variable
            yco = random.nextInt((yDimensions)); // random number assigned to variable
            if (xco < xDimensions - 20 && yco <= yDimensions - 20) {
                newObstacle = new Obstacles(xco, yco, Direction.randomDir()); // values assigned to Obstacle as well as random direction
                obstaclesList.add(newObstacle); // content added to arrayList
            }else{
                System.out.println("Element cannot be added as it exceeds your created canvas");
            }
        } else{
            System.out.println("Obstacle cannot be added as you have reached maximum capacity. Please try again.");
        }
    }

    /**
     * A random value is generated. A checker is implemented to verify that the avoider array list does not exceed the limit.
     * If it exceeds the limit, then the drone cannot be added, else, if it meets the requirements, random coordinates are generated, and the
     * avoider (newAvoider) is added into the arrayList (avoiderList).
     *
     */
    public void addAvoider() {
        Random random;
        int xco, yco; // values for storing coordinates of obstacles
        random = new Random();
        if (droneList.size() < xDimensions * yDimensions){ //parameter verification, same as Drone and Obstacle
            xco = random.nextInt((xDimensions)); // random number assigned to variable
            yco = random.nextInt((yDimensions)); // random number assigned to variable
            if (xco < xDimensions - 20 && yco <= yDimensions - 20) {
                newAvoider = new Avoider(xco, yco, Direction.randomDir()); // values assigned to Obstacle as well as random direction
                avoiderList.add(newAvoider); // content added to arrayList
            }else{
                System.out.println("Element cannot be added as it exceeds your created canvas");
            }
        } else{
            System.out.println("Avoider cannot be added as you have reached maximum capacity. Please try again.");
        }
    }

    /**
     * @return the dimensions of the arena and the drone(s), avoider(s) and obstacle(s) attached to the arrayList (droneList)
     */
    // focuses on printing the arena size and all objects attached to the array
    public String toString() {
        StringBuilder s = new StringBuilder("Arena Size: " + xDimensions + " x " + yDimensions + "\n");
        s.append("_______________________" + "\n");
        s.append("Here is a list of drone and other objects in the arena:" + "\n");
        for (Drone d : droneList) {// for all the drones inside the arraylist have them in the string
            s.append(d.toString()).append("\n");
        }
        for (Obstacles o: obstaclesList){ // for all the obstacles inside the arraylist have them in the string
            s.append(o.toString()).append("\n");
        }
        for (Avoider a: avoiderList){ // for all the avoiders inside the arraylist have them in the string
            s.append(a.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * @return a print message to the user, knowing that the automated process has been stopped based on user command
     */
    public String stopProcess(){
       return "Process has been stopped" + "\n";
    }

    /**
     * The following main class is utilised as a tester mechanism whereby a custom arena is created, and drones, avoiders and obstacles are added.
     * This is then printed via a toString() method.
     * @param args: focuses on no parameters unless entered by the user.
     */
    public static void main(String [] args){
        DroneArena x = new DroneArena(20,10);
        x.addDrone(); // tester mechanism to print drone
        x.addAvoider(); // tester mechanism to execute avoider add
        x.addObstacle(); // tester mechanism to add obstacle
        System.out.println(args.toString()); // tester to experiment to string function


    }

}
