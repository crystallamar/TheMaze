package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Sand {
    Random rand = new Random();
    RandomGenerator randGen = new RandomGenerator();
    Paint painter = new Paint();
    Regions buildReg = new Regions();

    public ArrayList<ArrayList<Integer>> generateSand(TETile[][] world, Random random) {
        ArrayList<ArrayList<Integer>> centerCoorOfEachRoom = new ArrayList<>();
        ArrayList<ArrayList<Integer>> regions = buildReg.getRegion();

        boolean addCoor = false;
        for(int i = 0; i < regions.size(); i++) {
            ArrayList<Integer> region = regions.get(i);
            int numRooms = ranNumRooms(random) + 2;
            while (numRooms > 0) {
                ArrayList<Integer> room = new ArrayList<>();
                room.addAll(buildReg.generateRegion(random, region, 4, 4, 6));

                addCoor = painter.paint(world, random, "Sand", room);
                if (addCoor) {
                    centerCoorOfEachRoom.add(buildReg.getCenterOfRegion(room));
                }
                numRooms--;

            }
        }
        return centerCoorOfEachRoom;
    }




    // Function that generates a random number of rooms
    public int ranNumRooms(Random random) {
        int numRooms = randGen.generateNumRooms(random, 4) + 6;
        return numRooms;
    }

    public boolean isTouchingGrassBottom(TETile[][] world, int x, int y) {
        return world[x][y - 1] == Tileset.GRASS;
    }

    public boolean isTouchingGrassLeft(TETile[][] world, int x, int y) {
        return world[x - 1][y] == Tileset.GRASS;
    }
    public boolean isTouchingGrassTop(TETile[][] world, int x, int y) {
        return world[x][y + 1] == Tileset.GRASS;
    }

    public boolean isTouchingGrassRight(TETile[][] world, int x, int y) {
        return world[x + 1][y] == Tileset.GRASS;
    }


}
