package core;

import tileengine.TETile;
import tileengine.Tileset;

public class Grass {
    public void generateGrass(TETile[][] world, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.GRASS; //Sets world to be grass
            }
        }
    }
}
