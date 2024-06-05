package core;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdDraw;
import org.apache.pdfbox.contentstream.operator.text.SetFontAndSize;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
        private static final int WIDTH = 94;
        private static final int HEIGHT = 55;

    public static void main(String[] args) {
        Random randWorld = new Random();

        // will set seed to input, but for now random num
        long seed = 12345;
        //long seed = 1357;
        randWorld.setSeed(seed);

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT); // Creates screen
        TETile[][] world = new TETile[WIDTH][HEIGHT]; // creates the world to build in
        World updatedWorld = new World();
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.generateTitleScreen(world, WIDTH, HEIGHT);
        seed = titleScreen.onTitlePage(world, seed, WIDTH, HEIGHT);

        world = updatedWorld.generateWorld(world, seed, WIDTH, HEIGHT);

        ter.renderFrame(world);

    }

}