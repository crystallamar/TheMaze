package core;

import tileengine.TETile;

import java.util.Random;

public class World {

    public TETile[][] generateWorld (TETile[][] world, long seed, int width, int height) {
        Grass addGrass = new Grass();
        Mountains addMountains = new Mountains();

        addGrass.generateGrass(world, width, height);
        addMountains.generateMountains(world, seed, width, height);
        return world;
    }



    }
