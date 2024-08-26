package core;

import tileengine.TERenderer;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.ArrayList;

public class EndGame {
    TERenderer ter = new TERenderer();

    public void quitGame() {
        boolean endGame = true;
        char key;
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(47, 27, 8, 5);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(47, 27, "Save Game: S\n Quit Game: Q");
        StdDraw.show();
        while (endGame) {
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key == 's') || (key == 'S')) {
                    //Save game
                    endGame = false;
                }

                if ((key == 'q') || (key == 'Q')) {
                    System.exit(0);
                }

            }
        }

    }

    public void endGame(TETile[][] world) {
        boolean waiting = true;
        char key;
        while (waiting) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(45, 25, 60, 60);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(45, 26, "Congratulations! You win!");
            StdDraw.text(45, 23, "Press Q to Quit");
            StdDraw.text(45, 20, "Press N to start new game");
            StdDraw.show();
            while (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key == 'q') || (key == 'Q')) {
                    System.exit(0);
                } else if ((key == 'n') || (key == 'N')) {
                    TitleScreen titleScreen = new TitleScreen();
                    Character character = new Character();
                    PlayingGame playingGame = new PlayingGame();
                    ArrayList<Integer> avatarCoor = new ArrayList<>();
                    EndGame endGame = new EndGame();

                    titleScreen.generateTitleScreen(world, 94, 55);
                    long seed = titleScreen.onTitlePage(world, 94, 55);
                    SavedGame saveFiles = new SavedGame();

                    saveFiles.saveSeed(seed);
                    World updatedWorld = new World(seed);

                    avatarCoor = updatedWorld.generateWorld(world, seed, 94, 55);
                    ter.renderFrame(world);
                    updatedWorld.callPlayGame(world, avatarCoor, seed, 0, 0, 0);
                    ter.renderFrame(world);
                }


            }


        }
    }

    public void callEndGame(TETile[][] world) {
        endGame(world);
    }

    public boolean endObjective(TETile[][] world, int trialNum) {
        SavedGame retrieveGame = new SavedGame();
        PlayingGame game;
        Character avatar = new Character();
        //retrieveGame.readFile("PlayingGame");
        long seed = retrieveGame.readSeed("seed");
        World genWorld = new World(seed);
        Grass genGrass = new Grass();
        genGrass.generateGrass(world, 94, 55);
        ArrayList<Integer> avatarCoor = retrieveGame.readAvatarCoor("avatarCoor");
        ArrayList<Integer> avXY = retrieveGame.readAVCoorWorld();
        avatar.setAvatarCoor(world, avXY);
        ArrayList<Integer> oGavatarCoor = retrieveGame.readOGAvCoor("OGAvCoor");
        ArrayList<Integer> oGCoin1;
        ArrayList<Integer> oGCoin2;
        ArrayList<Integer> oGCoin3;
        ArrayList<Integer> firstCoinPickedUp;
        ArrayList<Integer> secondCoinPickedUp;


        world[oGavatarCoor.get(0)][oGavatarCoor.get(1)] = Tileset.SAND;
        oGCoin1 = retrieveGame.readOGCoin1("OGCoin1");
        oGCoin2 = retrieveGame.readOGCoin2("OGCoin2");
        oGCoin3 = retrieveGame.readOGCoin3("OGCoin3");

        int trialCoinsPickedUp = avatarCoor.get(2);
        int oGCoins = avatarCoor.get(3);
        int boolTrial = avatarCoor.get(4);
        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(avXY.get(0));
        avatarCoor.add(avXY.get(1));
        avatarCoor.add(trialCoinsPickedUp);
        avatarCoor.add(oGCoins);
        avatarCoor.add(boolTrial);
        avatarCoor.addAll(oGCoin1);
        avatarCoor.addAll(oGCoin2);
        avatarCoor.addAll(oGCoin3);


        genWorld.generateSavedWorld(world, avatarCoor, oGCoin1, oGCoin2, oGCoin3, trialCoinsPickedUp, boolTrial,
                oGCoins);

        if (trialNum == 1) {
            if (oGCoin1.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(oGCoin1); // Save first coin coor
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp"); //
                avatar.removeOGCoin(world, oGCoin1);
                avatar.setAvatarCoor(world, avatarCoor);

            } else if (oGCoin2.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(oGCoin2);
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world, oGCoin2);
                avatar.setAvatarCoor(world, avatarCoor);

            } else if (oGCoin3.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(oGCoin3);
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world, oGCoin3);
                avatar.setAvatarCoor(world, avatarCoor);

            }
        } else if (trialNum == 2) { //NOT SURE NEEDED
            if (oGCoin1.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world, firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(oGCoin1);
                avatar.removeOGCoin(world, oGCoin1);
                avatar.setAvatarCoor(world, avatarCoor);
            }

            if (oGCoin2.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world, firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(oGCoin2);

                avatar.removeOGCoin(world, oGCoin2);
                avatar.setAvatarCoor(world, avatarCoor);

            }
            if (oGCoin3.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world, firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(oGCoin3);

                avatar.removeOGCoin(world, oGCoin3);
                avatar.setAvatarCoor(world, avatarCoor);

            }

        }
        return false;

    }
}