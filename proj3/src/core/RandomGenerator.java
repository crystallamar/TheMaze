package core;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    //Random randomNum = new Random();


    public int generateNumRooms(Random random, int rand) {
        //randomNum.setSeed(seed);
        int numRooms = random.nextInt(rand);
        return numRooms;
    }

    public int generateSizeRoomX(Random random, int x) {
        //randomNum.setSeed(seed);
        int sizeRoomX = random.nextInt(x);
        return sizeRoomX;
    }

    public int generateSizeRoomY(Random random, int y) {
        //randomNum.setSeed(seed);
        int sizeRoomY = random.nextInt(y);
        return sizeRoomY;
    }

    public int generateXCoorInReg(Random random, ArrayList<Integer> region) {
        //randomNum.setSeed(seed);
        int xStartCoor = random.nextInt(region.get(0), region.get(2));
        return xStartCoor;
    }

    public int generateYCoorInReg(Random random, ArrayList<Integer> region) {
        //randomNum.setSeed(seed);
        int yStartCoor = random.nextInt(region.get(1), region.get(3));
        return yStartCoor;
    }
}
