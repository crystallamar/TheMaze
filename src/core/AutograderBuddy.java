package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AutograderBuddy {


    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */


    static char[] sArray;
    static int i = 0; //where we are in sArray

    public static TETile[][] getWorldFromInput(String input) {
        sArray = input.toCharArray();
        TETile[][] world = new TETile[94][55];
        long seed = copyOnTitleScreen(world);
        
        

        // just take seed and associate it with a specific world

        // STEP ONE: read startgame vs. load game

        // STEP TWO: READ SEED IF START GAME

        // STEPTHREE: READ MOVEMENTS

        // STEP FOUR: CHECK FOR SAVING AND QUITTING


        throw new RuntimeException("Please fill out AutograderBuddy!");

    }

    public static char getNextChar() {
        return sArray[i++];
    }


    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.SAND.character()
                || t.character() == Tileset.AVATAR.character();
        //|| t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() != Tileset.SAND.character();
        //|| t.character() == Tileset.LOCKED_DOOR.character()
        //|| t.character() == Tileset.UNLOCKED_DOOR.character();
    }


    public static long copyOnTitleScreen(TETile[][] world) {
        SavedGame save = new SavedGame();
        boolean titleOn = true;
        char key;
        Grass grass = new Grass();
        long seedFromEnterSeed;
        long seed = System.currentTimeMillis();

        while (titleOn) {
            key = getNextChar();
            if ((key == 'n') || (key == 'N')) {
                grass.generateGrass(world, 94, 55);
                StdDraw.setPenColor(Color.white);
                StdDraw.filledRectangle(45, 25, 20, 10);
                StdDraw.setPenColor(Color.black);
                StdDraw.text(45, 30, "Please enter seed:");
                StdDraw.show();

                seedFromEnterSeed = copyEnteringSeed(seed);
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
        return seed;
    }

    public static long copyEnteringSeed(long seed) {

        boolean enteringSeed = true;
        long seedToReturn = seed;
        char key;
        String stringKey = "";
        char prevKey = '0';


        while ((sArray[i + 1] != 's') && sArray[i + 1] != 'S') {
            key = getNextChar();
            if (java.lang.Character.isDigit(key)) {
                seedToReturn = prevKey + key;
                prevKey = key;

                stringKey += String.valueOf(key).toString();
                StdDraw.setPenColor(Color.white);
                StdDraw.filledRectangle(45, 25, 20, 10);
                StdDraw.setPenColor(Color.black);
                StdDraw.text(45, 30, "Please enter seed:");
                StdDraw.text(45, 25, stringKey);
                StdDraw.show();
            }
        }
        
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Seed.data"));
            output.writeLong(seedToReturn);
            output.close();
        } catch (
                IOException ioe) {
            System.err.println("Issue saving seed.data");
        }

        return seedToReturn;

    }
}
