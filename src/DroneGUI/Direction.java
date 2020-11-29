package DroneGUI;
import java.util.*;

/**
 * Direction is an enumeration used for directions of the drones dependent on the positioning
 */

public enum Direction {
    north, south, east, west;


    // nextDir focuses on creating the next direction
    public Direction nextDir(){
        int converter = Direction.values().length -1;
        if (this.ordinal() == converter){
            return values()[0];
        }
        else{
            return values()[this.ordinal() + 1 ];
        }
    }

    //random selector
    public static Direction randomDir() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
