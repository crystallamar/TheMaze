package core;

import edu.princeton.cs.algs4.StdDraw;
import org.antlr.v4.runtime.misc.Utils;
import tileengine.TETile;
import tileengine.Tileset;
import tileengine.TERenderer;
import utils.FileUtils;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;


public class TitleScreen {
    TERenderer ter = new TERenderer();
    long seed = System.currentTimeMillis();


    public void generateTitleScreen(TETile[][] world, int width, int height) {
        Grass grass = new Grass();
        grass.generateGrass(world, width, height);
        ter.renderFrame(world);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.text(47, 40, "61B Project: The Game");
        StdDraw.filledSquare(22, 20, 5);
        StdDraw.filledSquare(47, 20, 5);
        StdDraw.filledSquare(72, 20, 5);


        StdDraw.setPenColor(Color.green);
        StdDraw.text(22, 20, "Load Game: L");
        StdDraw.text(47, 20, "New Game: N");
        StdDraw.text(72, 20, "Quit: Q");

        StdDraw.show();

    }

    public long onTitlePage(TETile[][] world, int width, int height) {
        SavedGame save = new SavedGame();
        boolean titleOn = true;
        char key;
        Grass grass = new Grass();
        long seedFromEnterSeed;

        //SavedGame saveFile = new SavedGame();
        while (titleOn) {
            //key = userInput.getNextChar();
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key == 'n') || (key == 'N')) {
                    grass.generateGrass(world, width, height);
                    StdDraw.setPenColor(Color.white);
                    StdDraw.filledRectangle(45, 25, 20, 10);
                    StdDraw.setPenColor(Color.black);
                    StdDraw.text(45, 30, "Please enter seed:");
                    StdDraw.show();

                    seedFromEnterSeed = enterSeed(seed);
                    if (seedFromEnterSeed != 0) {
                        save.saveSeed(seedFromEnterSeed);
                        return seedFromEnterSeed;
                    }
                    return seedFromEnterSeed;
                } else if ((key == 'l') || (key == 'L')) {
                    //String savedGame = files.readFile("Saved Game")

                    //ter.renderFrame(world);
                    //call saved
                    titleOn = false;
                    return 'a';

                    //  LOAD GAME //
                } else if ((key == 'q') || (key == 'Q')) {
                    System.exit(0);
                }
            }
        }
        return seed;
    }

    public long enterSeed(long seed) {
        boolean enteringSeed = true;
        long seedToReturn = seed;
        char key;
        String stringKey = "";
        //char prevKey = '0';


        while (enteringSeed) {
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key != 's') || (key != 'S')) {

                    if (java.lang.Character.isDigit(key)) {
//                        seedToReturn = prevKey + key;
//                        prevKey = key;

                        stringKey += String.valueOf(key);
                        StdDraw.setPenColor(Color.white);
                        StdDraw.filledRectangle(45, 25, 20, 10);
                        StdDraw.setPenColor(Color.black);
                        StdDraw.text(45, 30, "Please enter seed:");
                        StdDraw.text(45, 25, stringKey);
                        StdDraw.show();
                    }

                }
                if ((key == 's') || (key == 'S')) {
                    if (stringKey != "") {
                        seedToReturn = Integer.parseInt(stringKey);
                    }
                    enteringSeed = false;
                }
            }
        }


        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Seed.data"));
            output.writeLong(seedToReturn);
            output.close();
        } catch (IOException ioe) {
            System.err.println("Issue saving seed.data");
        }

        return seedToReturn;

    }

    public String forAutoGrader() {
        long seedToConvert = enterSeed(seed);
        return Long.toString(seedToConvert);


    }

}