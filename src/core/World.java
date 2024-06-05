package core;
import tileengine.TERenderer;
import tileengine.TETile;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import java.io.Serializable;

import java.util.Random;

public class World implements Serializable {
    public TETile[][] generateWorld (TETile[][] world, long seed, int width, int height) {

        Mountains addMountains = new Mountains();
        Sand addSand = new Sand();

        addMountains.generateMountains(world, seed, width, height);
        addSand.generateSand(world, seed);
        return world;
    }


    }


