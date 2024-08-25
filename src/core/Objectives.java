package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import tileengine.TERenderer;



public class Objectives {
    TERenderer ter = new TERenderer();
    boolean trial = false;
    Random rand;
    Boolean complete = false;


    public int objectives(TETile[][] world, int coinCountOG, Random rand, int numLoops, int x, int y, long seed) {
        EndGame endGame = new EndGame();
        Coins coin = new Coins();
        if (complete){
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        else if (coinCountOG == 1 && numLoops == 0) {
            objective1(world, rand, seed, x, y);
            complete = true;
            return 0;
        }
        else if (coinCountOG == 1) { // and it isn't the first loop
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 2 && numLoops == 0) {
            objective2(world, rand, seed);
            return 0;
        }
        else if (coinCountOG == 2) {
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 3 && numLoops == 0) {
            objective3(world, rand, seed);
            return 0;
        }
        else if (coinCountOG == 3) {
            return trialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
//        if (coinCountOG == 4) {
//            endGame.callEndGame(world);
//        }
        return 0;
    }
    public void objective1 (TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();

        Coins coins = new Coins();

        Character newAvatar = new Character();
        coins.removeCoin(world, x, y);
        //World updatedWorld = new World(seed);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 1);
        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, rand, true, 1, seed);
        //trial1Colors(world, avatarCoor.getFirst(), avatarCoor.get(1));



                }



    public void objective2(TETile[][] world, Random rand, long seed) {
        PlayingGame playGame = new PlayingGame();

        Coins coins = new Coins();
        Character newAvatar = new Character();
        //World updatedWorld = new World(seed);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 2);
        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, rand, true, 2, seed);
    }

    public void objective3(TETile[][] world, Random rand, long seed) {
        PlayingGame playGame = new PlayingGame();
        EndGame endGame = new EndGame();

        Coins coins = new Coins();
        Character newAvatar = new Character();
        //World updatedWorld = new World(seed);
        trialRoom(world);
        trialRoomIntro();
        trialContinue(world);
        coins.generateCoins(world, rand, true, 3);
        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);
        ter.renderFrame(world);

        whilePlayingTrial(world, avatarCoor, rand, true, 3, seed);
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
        ter.renderFrame(world);

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
        if (trialNum == 3){
            endGame.endGame(world);
        }
        endGame.endObjective(world, trialNum);

        ter.renderFrame(world);
    }

    public int trialPickUpCoin(TETile[][] world, int x, int y, int numTrialCoins, int trialNum) {
        Coins coin = new Coins();
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

    public void whilePlayingTrial(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial, Long seed){
        Character avatar = new Character();
        Boolean playingGame = true;
        TERenderer ter = new TERenderer();
        EndGame endGame = new EndGame();
        SavedGame loadGame = new SavedGame();
        loadGame.saveIfTrial(true);

        Hover mousePointer = new Hover();
        int numLoops = 0;
        int numCoinsPickedUpInTrial;
        Objectives objectives = new Objectives();
        ArrayList<Integer> OGCoin1 = loadGame.readOGCoin1("OGCoin1");
        ArrayList<Integer> OGCoin2 = loadGame.readOGCoin1("OGCoin2");
        ArrayList<Integer> OGCoin3 = loadGame.readOGCoin1("OGCoin3");


        ArrayList<Integer> mouseCoor;
        while (playingGame) {
            boolean expectingInput = true;
            boolean ifColon;
            char key;
            int initMouseXCoor = 0;
            int initMouseYCoor = 0;
            int currMouseXCoor;
            int currMouseYCoor;




            while (expectingInput) {
                // AvatarCoor: x, y, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)
                mouseCoor = mousePointer.mouseMoves();

                currMouseXCoor = mouseCoor.getFirst();
                currMouseYCoor = mouseCoor.getLast();
                String tileTitle = mousePointer.convertCoor(world, mouseCoor);
                if ((initMouseXCoor != currMouseXCoor) || (initMouseYCoor != currMouseYCoor)) {
                    mousePointer.displayNothing();
                    mousePointer.displayTile(tileTitle);
                    initMouseXCoor = currMouseXCoor;
                    initMouseYCoor = currMouseYCoor;
                }

                if (StdDraw.hasNextKeyTyped()) {
                    ifColon = true;
                    key = StdDraw.nextKeyTyped();



//                    OGCoin1.add(avatarCoor.get(2));
//                    OGCoin1.add(avatarCoor.get(3));
//                    OGCoin2.add(avatarCoor.get(4));
//                    OGCoin2.add(avatarCoor.get(5));
//                    OGCoin3.add(avatarCoor.get(6));
//                    OGCoin3.add(avatarCoor.get(7));

                    avatarCoor = avatar.moveChar(key, world, avatarCoor, rand, trial, numTrial, seed);
                    if (avatarCoor.size() != 11) {
                        avatarCoor.add(OGCoin1.get(0));
                        avatarCoor.add(OGCoin1.get(1));
                        avatarCoor.add(OGCoin2.get(0));
                        avatarCoor.add(OGCoin2.get(1));
                        avatarCoor.add(OGCoin3.get(0));
                        avatarCoor.add(OGCoin3.get(1));
                    }
                    int x = avatarCoor.getFirst();
                    int y = avatarCoor.get(1);
                    numCoinsPickedUpInTrial = avatarCoor.get(2);
                    int OGCoins = avatarCoor.get(3);
                    int trialBool = avatarCoor.get(4);
                    if (numCoinsPickedUpInTrial != 7) {
                        numCoinsPickedUpInTrial = trialPickUpCoin(world, x, y, numCoinsPickedUpInTrial, numTrial);

                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.add(x);
                        avatarCoor.add(y);
                        avatarCoor.add(numCoinsPickedUpInTrial);
                        avatarCoor.add(OGCoins);
                        avatarCoor.add(trialBool);
                        avatarCoor.add(OGCoin1.get(0));
                        avatarCoor.add(OGCoin1.get(1));
                        avatarCoor.add(OGCoin2.get(0));
                        avatarCoor.add(OGCoin2.get(1));
                        avatarCoor.add(OGCoin3.get(0));
                        avatarCoor.add(OGCoin3.get(1));
                    }
                    if (numCoinsPickedUpInTrial == 7) {
                        playingGame = false;
                        expectingInput = false;
                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.add(x);
                        avatarCoor.add(y);
                        avatarCoor.add(0);
                        avatarCoor.add(OGCoins);
                        avatarCoor.add(0);
                        avatarCoor.add(OGCoin1.get(0));
                        avatarCoor.add(OGCoin1.get(1));
                        avatarCoor.add(OGCoin2.get(0));
                        avatarCoor.add(OGCoin2.get(1));
                        avatarCoor.add(OGCoin3.get(0));
                        avatarCoor.add(OGCoin3.get(1));
                        //trialBool = 0;



                    }
//                    if (OGCoins == 4) {
//                        endGame.callEndGame(world);
//                    }

                    if (key == ':') {
                        while (ifColon) {
                            if (StdDraw.hasNextKeyTyped()) {
                                key = StdDraw.nextKeyTyped();
                                loadGame.saveIfTrial(true);
                                ArrayList<Integer> trialCoinsCoor = loadGame.readTrialCoinsCoor("trialCoinsCoor");
                                ArrayList<Boolean> trialCoinsBool = loadGame.readTrialCoinsBool("trialCoinsBool");
                                avatar.ifExitObjective(key, world, avatarCoor, seed, OGCoin1, OGCoin2, OGCoin3, numCoinsPickedUpInTrial, trialCoinsCoor, trialCoinsBool);
                                ifColon = false;
                            }
                        }
                    }
                    ter.renderFrame(world);

                    int modifiers = 0;

                }
            }
        }
    }
    }


