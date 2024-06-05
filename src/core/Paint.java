package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;

public class Paint {

    public void paintWorld(TETile[][] world, long seed, String typeToPaint, ArrayList<Integer> region) {
        TETile tiles;

        int topY = region.get(0);
        int leftX = region.get(1);
        int bottomY = region.get(2);
        int rightX = region.get(3);

        if (typeToPaint == "Sand") {
            for (int x = rightX; x > leftX; x--) {
                for (int y = topY; y > bottomY; y--) {
                    world[x][y] = Tileset.SAND;
                }
            }
        }

        //if (typeToPaint == ...)

            }
        }