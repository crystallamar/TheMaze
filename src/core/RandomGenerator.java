package core;

import java.util.Random;

public class RandomGenerator {
    Random randomNum = new Random();

    public int generateNumRooms(long seed, int rand) {
        randomNum.setSeed(seed);
        int numRooms = randomNum.nextInt(rand);
        return numRooms;
    }
    public int generateSizeRoomX(long seed, int x) {
        randomNum.setSeed(seed);
        int sizeRoomX = randomNum.nextInt(x);
        return sizeRoomX;
    }

    public int generateSizeRoomY(long seed, int y) {
        randomNum.setSeed(seed);
        int sizeRoomY = randomNum.nextInt(y);
        return sizeRoomY;
    }

}
