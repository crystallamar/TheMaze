package core;

import org.checkerframework.checker.units.qual.A;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Mountains {
    Regions region = new Regions();

    public void generateMountains(TETile[][] world, long seed, int width, int height) {
        generateLeftWall(world, seed, width, height);
        generateTopWall(world, seed, width, height);
        generateRightWall(world, seed, width, height);
        generateBottomWall(world, seed, width, height);

    }

    public void generateLeftWall(TETile[][] world, long seed, int width, int height) {

        ArrayList<ArrayList<Integer>> leftWall = region.generateRegion(seed, 0, 0, 3, 3, 2);
        int leftWallX1 = region.coorX1Y1(leftWall).getFirst();
        int leftWallX2 = region.coorX2Y2(leftWall).getFirst();

        for (int i = leftWallX1; i < leftWallX2; i++) {
            for (int m = 0; m < height; m++) {
                world[i][m] = Tileset.MOUNTAIN;
            }
        }

    }

    public void generateTopWall(TETile[][] world, long seed, int width, int height) {

        ArrayList<ArrayList<Integer>> topWall = region.generateRegion(seed, 0, height, 3, 3, 2);
        int topWallY1 = region.coorX1Y1(topWall).getLast(); // Should be 55
        int topWallY2 = region.coorX2Y2(topWall).getLast(); //

        int randSizeHeight = topWallY2 - topWallY1;

        while (randSizeHeight > 0) {
            for (int i = 0; i < width; i++) {
                world[i][height - randSizeHeight] = Tileset.MOUNTAIN;
            }
            randSizeHeight--;
        }
    }

    public void generateRightWall(TETile[][] world, long seed, int width, int height) {

        ArrayList<ArrayList<Integer>> rightWall = region.generateRegion(seed, height, 0, 3, 3, 2);
        int rightWallX1 = region.coorX1Y1(rightWall).getFirst();
        int rightWallX2 = region.coorX2Y2(rightWall).getFirst();

        int randSizeWidth = rightWallX2 - rightWallX1;

        while (randSizeWidth > 0) {
            for (int m = 0; m < height; m++) {
                world[width - randSizeWidth][m] = Tileset.MOUNTAIN;
            }
            randSizeWidth--;
        }


    }

    public void generateBottomWall(TETile[][] world, long seed, int width, int height) {

        ArrayList<ArrayList<Integer>> bottomWall = new ArrayList<>();

        bottomWall = region.generateRegion(seed, 0, 0, 3, 3, 2);
        int bottomWallY1 = region.coorX1Y1(bottomWall).getLast();
        int bottomWallY2 = region.coorX2Y2(bottomWall).getLast();

        int randSizeHeight = bottomWallY2 - bottomWallY1;
        while (randSizeHeight >= 0) {
            for (int i = 0; i < width; i++) {
                world[i][bottomWallY1 + randSizeHeight] = Tileset.MOUNTAIN;
            }
            randSizeHeight--;
        }

    }
}
