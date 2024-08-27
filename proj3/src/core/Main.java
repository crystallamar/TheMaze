package core;

import tileengine.TERenderer;
import tileengine.TETile;
import utils.FileUtils;
import java.io.File;

import java.util.ArrayList;

public class Main {
    private static final int WIDTH = 94;
    private static final int HEIGHT = 55;

    public static void main(String[] args) {
        FileUtils files = new FileUtils();
        File savedGame = new File("Saved Game");
        boolean runningAutograder = false;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT); // Creates screen
        TETile[][] world = new TETile[WIDTH][HEIGHT]; // creates the world to build in

        if (runningAutograder) {
            world = AutograderBuddy.getWorldFromInput("n1724641833626swddddddddddddwwwwww");
            //AutograderBuddy auto = new AutograderBuddy();
            //world = AutograderBuddy.getWorldFromInput("n1ssdddddddddddwww");

            //world = AutograderBuddy.getWorldFromInput("n1724641833626swwddddddddddddwwwwwaaaaaaaaaaaaawwwwwwwwwdwwwww
            // wwwwwwwwwwwwaaaadddddddddddddddddddddddddddddddssssssssssssssssddwwaaaaaawwwwwwwaaaaaaaaaaaasssddddddddd
            // ddssssssssssssdddddddddddddddssswwwwwwwwwwaaawwwwwwwwwwwwaawdwwwwwwddddaaaaaaaaaaawwwwaaaaaaassssssaawww
            // wwwwwwwwwwwaaaaaaaaassssssssa"); //AFTER WWWWW is first Trial
            //world = AutograderBuddy.getWorldFromInput("n1724641833626swwddddddddddddwwwwwaaaaaaaaaaaaa1234wwwwwwwwwdwwwwgfwwwwwwwwwwwwwaaaadddddddddddddddddddddddddddddddssssssssssssssssddwwaaaaaawwwwwwwaaaaaaaaaaaasssdddddddddddssssssssssssdddddddddddddddssswwwwwwwwwwaaawwwwwwwwwwwwaawdwwwwwwddddaaaaaaaaaaawwwwaaaaaaassssssaawwwwwwwwwwwwwwaaaaaaaaassssssssa");
            //ter.renderFrame(world); // EASY WORLD 1724641833626

        } else {
            TitleScreen titleScreen = new TitleScreen();
            Character character = new Character();
            PlayingGame playingGame = new PlayingGame();
            ArrayList<Integer> avatarCoor = new ArrayList<>();
            EndGame endGame = new EndGame();
            SavedGame loadGame = new SavedGame();


            titleScreen.generateTitleScreen(world, WIDTH, HEIGHT);
            long seed = titleScreen.onTitlePage(world, WIDTH, HEIGHT);


            if (seed == 'a') {

                loadGame.saveIfLoadedGame(true);
                world = loadGame.openSavedFile();
                ter.renderFrame(world);
                seed = loadGame.readSeed("seed");
                World updatedWorld = new World(seed);
                avatarCoor = loadGame.readAvatarCoor("avatarCoor");
                ArrayList<Integer> oGCoin1 = loadGame.readOGCoin1("OGCoin1");
                ArrayList<Integer> oGCoin2 = loadGame.readOGCoin1("OGCoin2");
                ArrayList<Integer> oGCoin3 = loadGame.readOGCoin1("OGCoin3");
                int numTrial = avatarCoor.get(3);
                int trialCoinsPickedUp = avatarCoor.get(2);
                int trialBool = avatarCoor.get(4);
                Boolean ifTrial = loadGame.readIfTrial("ifTrial");
                if (!ifTrial) {
                    updatedWorld.callPlayGame(world, avatarCoor, seed, numTrial, trialCoinsPickedUp, trialBool);
                } else {
                    updatedWorld.callObjectivePlayGame(world, avatarCoor, true, numTrial, seed);
                }
                ter.renderFrame(world);
            } else {
                SavedGame saveFiles = new SavedGame();
                saveFiles.saveIfLoadedGame(false);
                saveFiles.saveSeed(seed);
                World updatedWorld = new World(seed);

                avatarCoor = updatedWorld.generateWorld(world, WIDTH, HEIGHT);
                // Av coor is av coor, OGCoin1 coor, OGCoin2 Coor, and OGCoin3 coor


                ter.renderFrame(world);
                updatedWorld.callPlayGame(world, avatarCoor, seed, 0, 0, 0);
                ter.renderFrame(world);
            }
        }
    }
}
