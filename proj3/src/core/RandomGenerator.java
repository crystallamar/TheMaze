package core;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    public int generateNumRooms(Random random, int rand) {
        int numRooms = random.nextInt(rand);
        return numRooms;
    }

    public int generateSizeRoomX(Random random, int x) {
        int sizeRoomX = random.nextInt(x);
        return sizeRoomX;
    }

    public int generateSizeRoomY(Random random, int y) {
        int sizeRoomY = random.nextInt(y);
        return sizeRoomY;
    }

    public int generateXCoorInReg(Random random, ArrayList<Integer> region) {
        int xStartCoor = random.nextInt(region.get(0), region.get(2));
        return xStartCoor;
    }

    public int generateYCoorInReg(Random random, ArrayList<Integer> region) {
        int yStartCoor = random.nextInt(region.get(1), region.get(3));
        return yStartCoor;
    }
}
