package core;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    Random randomNum = new Random();

    public int generateNumRooms(long seed, int rand) {
        //randomNum.setSeed(seed);
        int numRooms = randomNum.nextInt(rand);
        return numRooms;
    }
    public int generateSizeRoomX(long seed, int x) {
        //randomNum.setSeed(seed);
        int sizeRoomX = randomNum.nextInt(x);
        return sizeRoomX;
    }

    public int generateSizeRoomY(long seed, int y) {
        //randomNum.setSeed(seed);
        int sizeRoomY = randomNum.nextInt(y);
        return sizeRoomY;
    }

    public int generateXCoorInReg(long seed, ArrayList<Integer> region) {
        randomNum.setSeed(seed);
        int xStartCoor = randomNum.nextInt(region.get(1), region.get(3));
        return xStartCoor;
    }

    public int generateYCoorInReg(long seed, ArrayList<Integer> region) {
        randomNum.setSeed(seed);
        int yStartCoor = randomNum.nextInt(region.get(2), region.get(0));
        return yStartCoor;
    }

}
