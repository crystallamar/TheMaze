package core;

import tileengine.TETile;

import java.util.ArrayList;
import java.util.Random;

public class Sand {
    RandomGenerator randGen = new RandomGenerator();
    Paint painter = new Paint();
    Regions buildReg = new Regions();

    public void generateSand(TETile[][] world, long seed) {
        ArrayList<Integer> region = new ArrayList<>();

        int numRooms = ranNumRooms(seed);

        while (numRooms > 0) {
            region.addAll(buildReg.worldRegion1());
            int startX = randGen.generateXCoorInReg(seed, region);
            int startY = randGen.generateYCoorInReg(seed, region);
            buildReg.generateRegion(seed, startX, startY, 8, 8, 3);
            painter.paintWorld(world, seed, "Sand", region);
            numRooms--;

        }
    }




    // Function that generates a random number of rooms
    public int ranNumRooms(long seed) {
        int numRooms = randGen.generateNumRooms(seed, 4) + 6;
        return numRooms;
    }




//    public int sizeRoomX(long seed) {
//        int randWidth = randGen.generateSizeRoomX(seed, 8) + 3;
//        return randWidth;
//    }
//
//    public int sizeRoomY(long seed) {
//        int randLength = randGen.generateSizeRoomY(seed, 6) + 2;
//        return randLength;
//    }

}
