package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;

public class AutograderBuddy {

    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] getWorldFromInput(String input) {
        // just take seed and associate it with a specific world
        TETile[][] world = new TETile[94][55];

        TitleScreen titleScreen = new TitleScreen();
        titleScreen.generateTitleScreen(world, 94, 55);
        long seed = titleScreen.onTitlePage(world, 94, 55);
        SavedGame saveFiles = new SavedGame();
        Character character = new Character();

        saveFiles.saveSeed(seed);

        World updatedWorld = new World(seed);

        ArrayList<Integer> avatarCoor = updatedWorld.generateWorld(world, seed, 94, 55);

        input.toLowerCase();
        char[] chars = input.toCharArray();
        int num = 0;

        for (int i = 0; i < chars.length; i++){
            while ((chars[i] != 'l') || (chars[i] != 'n') || (chars[i] != 'q')) {
                i++;
            }
            if (chars[i] == 'l') {
                // LOAD GAME
            }

            if (chars[i] == 'n') {
                updatedWorld.generateWorld(world, seed, 94, 55);
            }
            if (chars[i] == 'q') {
                System.out.println("User has quit the game");
            }
            num = i;
        }
        for (int n = num; n < chars.length; n++) {
            while ((chars[n] != 'w') || (chars[n] != 'a') || (chars[n] != 's') || (chars[n] != 'd') || (chars[n] != ':')) {
                n++;
            }
            if (chars[n] == 'w') {

            }
        }
        // STEP ONE: read startgame vs. load game

        // STEP TWO: READ SEED IF START GAME

        // STEPTHREE: READ MOVEMENTS

        // STEP FOUR: CHECK FOR SAVING AND QUITTING


        throw new RuntimeException("Please fill out AutograderBuddy!");

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
}
