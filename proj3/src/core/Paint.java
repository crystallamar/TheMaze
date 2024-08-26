package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Paint {
    public boolean paint(TETile[][] world, Random random, String typeToPaint, ArrayList<Integer> region) {
        TETile tiles;
        boolean buildWorld = false;

        int leftX = region.get(0);
        int bottomY = region.get(1);
        int rightX = region.get(2);
        int topY = region.get(3);


        if (typeToPaint.equals("Sand")) {
            Sand sand = new Sand();
            if ((sand.isTouchingGrassBottom(world, rightX, bottomY)) && (sand.isTouchingGrassLeft(world, leftX, bottomY))) {
                if ((sand.isTouchingGrassRight(world, rightX, topY)) && (sand.isTouchingGrassTop(world, leftX, topY))) {
                    for (int x = rightX; x > leftX; x--) {
                        for (int y = topY; y > bottomY; y--) {
                            {
                                world[x][y] = Tileset.SAND;
                            }
                        }

                    }
                    buildWorld = true;
                }
            }
        }


        //if (typeToPaint == ...)
    return buildWorld;
    }
    
}
