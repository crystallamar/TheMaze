package core;

import org.checkerframework.checker.units.qual.A;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Mountains {
    Regions region = new Regions();
    RandomGenerator randGen = new RandomGenerator();

    public void generateMountains(TETile[][] world, long seed, int width, int height, Random random) {
        generateLeftWall(world, seed, width, height, random);
        generateBottomWall(world, seed, width, height, random);
        generateRightWall(world, seed, width, height, random);
        generateTopWall(world, seed, width, height, random);

    }

    public void generateLeftWall(TETile[][] world, long seed, int width, int height, Random random) {

        int leftWallX2 = randGen.generateXCoorInReg(random, region.mountainLeftWall()) + 1;
        for (int x = 0; x < leftWallX2; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }
    }

    public void generateBottomWall(TETile[][] world, long seed, int width, int height, Random random) {
        int bottomWallY2 = randGen.generateYCoorInReg(random, region.mountainBottomWall()) + 1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < bottomWallY2; y++) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }
    }

    public void generateRightWall(TETile[][] world, long seed, int width, int height, Random random) {
        int rightWallX2 = randGen.generateXCoorInReg(random, region.mountainRightWall()) - 1;
        for (int x = rightWallX2; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }
    }

    public void generateTopWall(TETile[][] world, long seed, int width, int height, Random random) {
        int topWallY2 = randGen.generateYCoorInReg(random, region.mountainTopWall()) -1;
        for (int x = 0; x < width; x++) {
            for (int y = topWallY2; y < height; y++) {
                world[x][y] = Tileset.MOUNTAIN;
            }
        }
    }

}
