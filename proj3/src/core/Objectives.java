package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tileengine.TERenderer;



public class Objectives {
    TERenderer ter = new TERenderer();
    //boolean trial = false;
    //Random rand;
    Boolean complete = false;


    public int objectives(TETile[][] world, int coinCountOG, Random random, int numLoops, int x, int y, long seed) {
        EndGame endGame = new EndGame();
        Coins coin = new Coins();
        if (complete) {
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        } else if (coinCountOG == 1 && numLoops == 0) {
            objective1(world, random, seed, x, y);
            complete = true;
            return 0;
        } else if (coinCountOG == 1) { // and it isn't the first loop
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 2 && numLoops == 0) {
            objective2(world, random, seed, x, y);
            return 0;
        } else if (coinCountOG == 2) {
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 3 && numLoops == 0) {
            objective3(world, random, seed, x, y);
            return 0;
        } else if (coinCountOG == 3) {
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        return 0;
    }
    public void objective1(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        SavedGame save = new SavedGame();
        Coins coins = new Coins();

        Character newAvatar = new Character();
        coins.removeCoin(world, x, y);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 1);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);


        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, seed, 1);

    }



    public void objective2(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        SavedGame save = new SavedGame();

        Coins coins = new Coins();
        //save.saveAVCoorWorld(x, y);

        Character newAvatar = new Character();
        //World updatedWorld = new World(seed);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 2);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);
        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, seed, 2);
    }

    public void objective3(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        EndGame endGame = new EndGame();
        SavedGame save = new SavedGame();

        Coins coins = new Coins();
        Character newAvatar = new Character();
        //save.saveAVCoorWorld(x, y);

        //World updatedWorld = new World(seed);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 3);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);

        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, seed, 3);
        endGame.callEndGame(world);
    }


    public void trialRoomIntro() {
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(47, 26, 40, 20);
        StdDraw.setPenColor(Color.green);
        StdDraw.text(47, 26, "Trial: Press C to continue");
        StdDraw.show();
    }

    public void trialRoom(TETile[][] world) {
        for (int i = 0; i < 94; i++) {
            for (int n = 0; n < 55; n++) {
                world[i][n] = Tileset.GRASS;
            }
        }

        for (int i = 20; i < 60; i++) {
            for (int n = 10; n < 40; n++) {
                world[i][n] = Tileset.SAND;
            }
        }
        //ter.renderFrame(world);

    }

    public void trialContinue(TETile[][] world) {
        boolean expectingInput = true;
        char key;
        while (expectingInput) {
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key == 'c') || (key == 'C')) {
                    trialRoom(world);
                    expectingInput = false;
                }
            }
        }
    }

    public void trialComplete(TETile[][] world, int trialNum) {
        EndGame endGame = new EndGame();
        ReadFiles readSeed = new ReadFiles();
        long seed = readSeed.readFileSeed();
        if (trialNum == 3) {
            endGame.endGame(world);
        }

        endGame.endObjective(world, trialNum);

        ter.renderFrame(world);
    }

    public int trialPickUpCoin(TETile[][] world, int x, int y, int numTrialCoins, int trialNum) {
        Coins coin = new Coins();
        SavedGame save = new SavedGame();
        if (coin.isCoin(world, x, y)) {
            numTrialCoins += coin.removeCoin(world, x, y);
            return numTrialCoins;
        }

        if (numTrialCoins == 6) {
            trialComplete(world, trialNum);
            numTrialCoins++;
        }
        return numTrialCoins;
    }

    public void whilePlayingTrial(TETile[][] world, ArrayList<Integer> avatarCoor, long seed, int numTrial) {
        Character avatar = new Character();
        SavedGame loadGame = new SavedGame();
        loadGame.saveIfTrial(true);

        Hover mousePointer = new Hover();
        ArrayList<Integer> oGCoin1 = loadGame.readOGCoin1("OGCoin1");
        ArrayList<Integer> oGCoin2 = loadGame.readOGCoin1("OGCoin2");
        ArrayList<Integer> oGCoin3 = loadGame.readOGCoin1("OGCoin3");

        boolean playingGame = true;
        while (playingGame) {
            int[] mouseCoords = {0, 0};

            while (true) {
                updateMousePointer(world, mousePointer, mouseCoords);

                if (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    avatarCoor = avatar.moveChar(key, world, avatarCoor, true, numTrial, seed);

                    if (avatarCoor.size() != 11) {
                        addOGCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
                    }

                    int x = avatarCoor.get(0), y = avatarCoor.get(1);
                    int numCoinsPickedUpInTrial = avatarCoor.get(2);

                    if (numCoinsPickedUpInTrial != 7) {
                        numCoinsPickedUpInTrial = trialPickUpCoin(world, x, y, numCoinsPickedUpInTrial, numTrial);
                        updateAvatarCoor(avatarCoor, x, y, numCoinsPickedUpInTrial, oGCoin1, oGCoin2, oGCoin3,
                                numTrial);
                    } if (numCoinsPickedUpInTrial == 7) {
                        prepareForNextStage(loadGame, avatarCoor, oGCoin1, oGCoin2, oGCoin3, numTrial);
                        playingGame = false;
                        break;
                    }

                    if (key == ':') {
                        handleColonKey(loadGame, avatar, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3,
                                numCoinsPickedUpInTrial);
                    }

                    ter.renderFrame(world);
                }
            }
        }
    }

    private void updateMousePointer(TETile[][] world, Hover mousePointer, int[] mouseCoords) {
        ArrayList<Integer> mouseCoor = mousePointer.mouseMoves();
        int currMouseXCoor = mouseCoor.get(0), currMouseYCoor = mouseCoor.get(1);

        if (mouseCoords[0] != currMouseXCoor || mouseCoords[1] != currMouseYCoor) {
            mousePointer.displayNothing();
            mousePointer.displayTile(mousePointer.convertCoor(world, mouseCoor));
            mouseCoords[0] = currMouseXCoor;
            mouseCoords[1] = currMouseYCoor;
        }
    }

    private void addOGCoinsToAvatarCoor(ArrayList<Integer> avatarCoor, ArrayList<Integer>... oGCoinList) {
        for (ArrayList<Integer> oGCoin : oGCoinList) {
            avatarCoor.add(oGCoin.get(0));
            avatarCoor.add(oGCoin.get(1));
        }
    }

    private void updateAvatarCoor(ArrayList<Integer> avatarCoor, int x, int y, int numCoinsPickedUpInTrial,
                                  ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3,
                                  int numTrial) {
        avatarCoor.clear();
        avatarCoor.add(x);
        avatarCoor.add(y);
        avatarCoor.add(numCoinsPickedUpInTrial);
        avatarCoor.add(numTrial);
        avatarCoor.add(1);
        avatarCoor.addAll(oGCoin1);
        avatarCoor.addAll(oGCoin2);
        avatarCoor.addAll(oGCoin3);
        //avatarCoor.addAll(Arrays.asList(x, y, numCoinsPickedUpInTrial, avatarCoor.get(3), 1));
        //addOGCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
    }

    private void prepareForNextStage(SavedGame loadGame, ArrayList<Integer> avatarCoor, ArrayList<Integer> oGCoin1,
                                     ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3, int numTrial) {
        ArrayList<Integer> avXY = loadGame.readAVCoorWorld();

        avatarCoor.clear();
        avatarCoor.addAll(avXY);
        avatarCoor.addAll(Arrays.asList(0, numTrial, 0));
        addOGCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
    }

    private void handleColonKey(SavedGame loadGame, Character avatar, TETile[][] world, ArrayList<Integer> avatarCoor,
                                long seed, ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                ArrayList<Integer> oGCoin3, int numCoinsPickedUpInTrial) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                loadGame.saveIfTrial(true);
                avatar.ifExitObjective(key, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3,
                        numCoinsPickedUpInTrial);
                break;
            }
        }
    }
}


