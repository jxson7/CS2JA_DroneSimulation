package Drone;
import java.util.*;


public enum Direction {
    north, south,east,west;

    public Direction nextDir() {
        if (this.ordinal() == 3)
            return values()[0];
        else
            return values()[this.ordinal() + 1];
    }

    public static Direction randomDir() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
