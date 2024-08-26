package core;
//package utils;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdDraw;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.pdfbox.contentstream.operator.text.SetFontAndSize;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
        private static final int WIDTH = 94;
        private static final int HEIGHT = 55;

    public static void main(String[] args) {
        //Random randWorld = new Random();
        FileUtils files = new FileUtils();
        File savedGame = new File("Saved Game");
        boolean runningAutograder = true;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT); // Creates screen
        TETile[][] world = new TETile[WIDTH][HEIGHT]; // creates the world to build in

        if (runningAutograder) {
            AutograderBuddy auto = new AutograderBuddy();
            world = auto.getWorldFromInput("n123sdddw"); //gets it to first coin - not running the correct seed
            ter.renderFrame(world);

        } else {
            //long seed = 12345;
            //long seed = System.currentTimeMillis();
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
                //Random rand = objective.getRand();
                seed = loadGame.readSeed("seed");
                World updatedWorld = new World(seed);
                avatarCoor = loadGame.readAvatarCoor("avatarCoor");
                ArrayList<Integer> OGCoin1 = loadGame.readOGCoin1("OGCoin1");
                ArrayList<Integer> OGCoin2 = loadGame.readOGCoin1("OGCoin2");
                ArrayList<Integer> OGCoin3 = loadGame.readOGCoin1("OGCoin3");
                int numTrial = avatarCoor.get(3);
                //int numTrial = loadGame.readNumOGCoinsPickedUp("numOGCoinsPickedUp");
                int trialCoinsPickedUp = avatarCoor.get(2);
                int trialBool = avatarCoor.get(4);
                //int numOGCoins = avatarCoor.get(4);
                Boolean ifTrial = loadGame.readIfTrial("ifTrial");
                if (!ifTrial) {
                    updatedWorld.callPlayGame(world, avatarCoor, seed, numTrial, trialCoinsPickedUp, trialBool);
                } else {
                    updatedWorld.callObjectivePlayGame(world, avatarCoor, true, numTrial, seed);
                }
                //world = updatedWorld.generateSavedWorld(world, avatarCoor, OGCoin1, OGCoin2, OGCoin3, trialCoinsPickedUp, trialBool, numOGCoins);
                //ter.renderFrame(world);
                ter.renderFrame(world);
            } else {
                SavedGame saveFiles = new SavedGame();
                saveFiles.saveIfLoadedGame(false);
                saveFiles.saveSeed(seed);
                World updatedWorld = new World(seed);

                avatarCoor = updatedWorld.generateWorld(world, seed, WIDTH, HEIGHT);
                // Av coor is av coor, OGCoin1 coor, OGCoin2 Coor, and OGCoin3 coor


                //world = updatedWorld.generateWorld(world, seed, WIDTH, HEIGHT);
                ter.renderFrame(world);
                //character.takeInput();
                updatedWorld.callPlayGame(world, avatarCoor, seed, 0, 0, 0);
                //endGame.callEndGame(world);
                ter.renderFrame(world);
                //playingGame.playingGame(z);
            }
        }
    }

}