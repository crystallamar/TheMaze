package core;

import tileengine.TETile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class SArrayInfo {

    char[] sArray;
    int i = 0;

    public SArrayInfo(String s) {
        this.sArray = s.toCharArray();
    }


    public char getNextChar() {
        return sArray[i++];
    }

    public boolean isIbound() {
        int n = i;
        return n++ < sArray.length;
    }


    public long copyOnTitleScreen(TETile[][] world) {
        SavedGame save = new SavedGame();
        boolean titleOn = true;
        char key;
        Grass grass = new Grass();
        long seedFromEnterSeed;
        long seed = System.currentTimeMillis();

        while (titleOn) {
            if (isIbound()) {
                key = getNextChar();
            } else {
                return seed;
            }
            if ((key == 'n') || (key == 'N')) {
                grass.generateGrass(world, 94, 55);

                seedFromEnterSeed = copyEnteringSeed(seed);
                if (seedFromEnterSeed != 0) {
                    save.saveSeed(seedFromEnterSeed);
                    return seedFromEnterSeed;
                }
                return seedFromEnterSeed;
            } else if ((key == 'l') || ((key == 'L'))) {
                titleOn = false;
                return 'a';

                //  LOAD GAME //
            } else if ((key == 'q') || (key == 'Q')) {
                //System.exit(0);
                return 'z';
            }
        }
        return seed;
    }

    public long copyEnteringSeed(long seed) {

        long seedToReturn = seed;
        char key;
        String stringKey = "";

        //int n = i;
        while ((sArray[i] != 's') && sArray[i] != 'S') {
            if (isIbound()) {
                key = getNextChar();
            } else {
                return seed;
            }
            if (java.lang.Character.isDigit(key)) {
                stringKey += String.valueOf(key);
            }
        }
        if (!stringKey.isEmpty()) {
            seedToReturn = Long.parseLong(stringKey);
        }
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Seed.txt"));
            output.writeLong(seedToReturn);
            output.close();
        } catch (IOException ioe) {
            System.err.println("Issue saving seed.txt");
        }

        return seedToReturn;
    }



    public TETile[][] copyPlayingGame(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, long seed,
                                      int numTrial) {
        Character avatar = new Character();
        boolean playingGame = true;
        boolean trial = false;
        //int numTrial = avatarCoor.get(3);
        int numTrialCoinsPickedUp = avatarCoor.get(2);
        SavedGame saveGame = new SavedGame();
        SavedGame save = new SavedGame();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);

        // Initialize original coin positions
        ArrayList<Integer> originalCoinPositions1 = new ArrayList<>();
        ArrayList<Integer> originalCoinPositions2 = new ArrayList<>();
        ArrayList<Integer> originalCoinPositions3 = new ArrayList<>();
        initializeCoinPositions(avatarCoor, originalCoinPositions1, originalCoinPositions2, originalCoinPositions3);

        while (playingGame) {
            save.saveIfTrial(false);
            if (!isIbound()) {
                return world;
            }

            char key = getNextChar();
            ArrayList<Integer> tempCoor = new ArrayList<>(avatarCoor);
            avatarCoor = avatar.moveChar(key, world, avatarCoor, trial, numTrial, seed);

            boolean didCharMove = !tempCoor.equals(avatarCoor);
            if (avatarCoor.size() == 8) {
                resetAvatarCoordinates(avatarCoor, avX, avY, originalCoinPositions1, originalCoinPositions2,
                        originalCoinPositions3);
            } else if (avatarCoor.size() != 11) {
                addOriginalCoinPositions(avatarCoor, originalCoinPositions1, originalCoinPositions2,
                        originalCoinPositions3);
            }

            int x = avatarCoor.get(0);
            int y = avatarCoor.get(1);
            int numLoops = avatarCoor.get(2);
            int trialBool = avatarCoor.get(4);
            numTrial = avatarCoor.get(3);

            if (trialBool == 0) {
                trial = false;
            } else if (didCharMove) {
                trial = true;
                processTrial(world, avatarCoor, rand, seed, originalCoinPositions1, originalCoinPositions2,
                        originalCoinPositions3);
            }

            if (key == ':') {
                processColonKey(world, avatarCoor, seed, save);
            }
        }

        return world;
    }

    public void initializeCoinPositions(ArrayList<Integer> avatarCoor, ArrayList<Integer> coin1,
                                        ArrayList<Integer> coin2, ArrayList<Integer> coin3) {
        if (avatarCoor.size() == 11) {
            coin1.add(avatarCoor.get(5));
            coin1.add(avatarCoor.get(6));
            coin2.add(avatarCoor.get(7));
            coin2.add(avatarCoor.get(8));
            coin3.add(avatarCoor.get(9));
            coin3.add(avatarCoor.get(10));
        } else {
            coin1.add(avatarCoor.get(2));
            coin1.add(avatarCoor.get(3));
            coin2.add(avatarCoor.get(4));
            coin2.add(avatarCoor.get(5));
            coin3.add(avatarCoor.get(6));
            coin3.add(avatarCoor.get(7));
        }
    }

    public void resetAvatarCoordinates(ArrayList<Integer> avatarCoor, int avX, int avY,
                                       ArrayList<Integer> coin1, ArrayList<Integer> coin2,
                                       ArrayList<Integer> coin3) {
        avatarCoor.clear();
        avatarCoor.add(avX);
        avatarCoor.add(avY);
        avatarCoor.add(0); // reset numTrialCoinsPickedUp
        avatarCoor.add(0); // reset numTrial
        avatarCoor.add(0); // reset trialBool
        avatarCoor.addAll(coin1);
        avatarCoor.addAll(coin2);
        avatarCoor.addAll(coin3);
    }

    public void addOriginalCoinPositions(ArrayList<Integer> avatarCoor, ArrayList<Integer> coin1,
                                         ArrayList<Integer> coin2, ArrayList<Integer> coin3) {
        avatarCoor.addAll(coin1);
        avatarCoor.addAll(coin2);
        avatarCoor.addAll(coin3);
    }

    public void processTrial(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, long seed,
                             ArrayList<Integer> coin1, ArrayList<Integer> coin2, ArrayList<Integer> coin3) {
        SavedGame saveGame = new SavedGame();
        int numLoops = avatarCoor.get(2);
        int numTrial = avatarCoor.get(3);
        int x = avatarCoor.get(0);
        int y = avatarCoor.get(1);
        if (numLoops == 0) {
            saveGame.saveAvatarCoor(avatarCoor);
            saveGame.saveAVCoorWorld(x, y);
            copyObjectives(world, avatarCoor.get(3), rand, numLoops, x, y, seed);
            resetAvatarCoordinates(avatarCoor, x, y, coin1, coin2, coin3);
            copyPlayingGame(world, avatarCoor, rand, seed, numTrial);
        } else if (numLoops == 7) {
            numTrial++;
            ArrayList<Integer> arrayForSecondCoinPickedUp = new ArrayList<>();
            arrayForSecondCoinPickedUp.add(x);
            arrayForSecondCoinPickedUp.add(y);
            saveGame.saveCoinPickedUpSecond(arrayForSecondCoinPickedUp);
        } else {
            copyObjectives(world, numTrial, rand, numLoops, x, y, seed);
        }
    }

    public void processColonKey(TETile[][] world, ArrayList<Integer> avatarCoor, long seed, SavedGame save) {
        while (true) {
            if (isIbound()) {
                char key = getNextChar();
                save.saveIfTrial(false);
                boolean stop = new Character().ifExitMain(key, world, avatarCoor, seed, "z");
                if (stop) {
                    break;
                }
            }
        }
    }

    public TETile[][] copyMain(TETile[][] world, long seed) {
        SavedGame loadGame = new SavedGame();
        ArrayList<Integer> avatarCoor;
        if (seed == 'z') {
            return world;
        }
        if (seed == 'a') {
            loadGame.saveIfLoadedGame(true);
            world = loadGame.openSavedFile();
            seed = loadGame.readSeed("seed.txt");
            World updatedWorld = new World(seed);
            Random rand = updatedWorld.rand;
            avatarCoor = loadGame.readAvatarCoor("avatarCoor.txt");
            int numTrial = avatarCoor.get(3);
            int trialCoinsPickedUp = avatarCoor.get(2);
            int trialBool = avatarCoor.get(4);
            Boolean ifTrial = loadGame.readIfTrial("ifTrial.txt");
            if (!ifTrial) {
                copyPlayingGame(world, avatarCoor, rand, seed, numTrial);
            } else {
                copyPT(world, avatarCoor, seed, numTrial);
            }
        } else {
            SavedGame saveFiles = new SavedGame();
            saveFiles.saveIfLoadedGame(false);
            saveFiles.saveSeed(seed);
            World updatedWorld = new World(seed);
            Random rand = updatedWorld.rand;
            avatarCoor = updatedWorld.generateWorld(world, 94, 55);
            // Av coor is av coor, OGCoin1 coor, OGCoin2 Coor, and OGCoin3 coor
            copyPlayingGame(world, avatarCoor, rand, seed, 0);
        }
        return world;
    }

    public TETile[][] copyPT(TETile[][] world, ArrayList<Integer> avatarCoor, long seed, int numTrial) {
        Character avatar = new Character();
        SavedGame loadGame = new SavedGame();
        loadGame.saveIfTrial(true);

        Hover mousePointer = new Hover();
        boolean playingGame = true;
        boolean trial = true;
        //int numTrial = avatarCoor.get(3);

        ArrayList<Integer> oGCoin1 = loadGame.readOGCoin1("OGCoin1.txt");
        ArrayList<Integer> oGCoin2 = loadGame.readOGCoin1("OGCoin2.txt");
        ArrayList<Integer> oGCoin3 = loadGame.readOGCoin1("OGCoin3.txt");

        int initMouseXCoor = 0;
        int initMouseYCoor = 0;

        while (playingGame) {
            handleMouseMovement(mousePointer, world, initMouseXCoor, initMouseYCoor);

            if (isIbound()) {
                char key = getNextChar();

                avatarCoor = updateAvatarCoordinates(key, world, avatarCoor, trial, seed, oGCoin1,
                        oGCoin2, oGCoin3);

                int numCoinsPickedUpInTrial = avatarCoor.get(2);
                if (numCoinsPickedUpInTrial == 7) {
                    playingGame = false;
                    avatarCoor = resetAvatarCoordinates(loadGame, avatarCoor, oGCoin1, oGCoin2, oGCoin3);
                }

                if (key == ':') {
                    handleColonKey(loadGame, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3,
                            numCoinsPickedUpInTrial);
                }
            } else {
                return world;
            }
        }
        return world;
    }

    public void handleMouseMovement(Hover mousePointer, TETile[][] world, int initMouseXCoor,
                                    int initMouseYCoor) {
        ArrayList<Integer> mouseCoor = mousePointer.mouseMoves();
        int currMouseXCoor = mouseCoor.get(0);
        int currMouseYCoor = mouseCoor.get(1);
        String tileTitle = mousePointer.convertCoor(world, mouseCoor);

        if ((initMouseXCoor != currMouseXCoor) || (initMouseYCoor != currMouseYCoor)) {
            mousePointer.displayNothing();
            mousePointer.displayTile(tileTitle);
            initMouseXCoor = currMouseXCoor;
            initMouseYCoor = currMouseYCoor;
        }
    }

    public ArrayList<Integer> updateAvatarCoordinates(char key, TETile[][] world,
                                                      ArrayList<Integer> avatarCoor, boolean trial,
                                                      long seed, ArrayList<Integer> oGCoin1,
                                                      ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3) {
        Character avatar = new Character();
        int numTrial = avatarCoor.get(3);
        avatarCoor = avatar.moveChar(key, world, avatarCoor, trial, numTrial, seed);

        if (avatarCoor.size() != 11) {
            addCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
        }

        int x = avatarCoor.get(0);
        int y = avatarCoor.get(1);
        int numCoinsPickedUpInTrial = avatarCoor.get(2);

        if (numCoinsPickedUpInTrial != 7) {
            numCoinsPickedUpInTrial = copyTrialPickUpCoin(world, x, y, numCoinsPickedUpInTrial, numTrial);
            resetAvatarCoorWithCoins(avatarCoor, x, y, numCoinsPickedUpInTrial, oGCoin1, oGCoin2, oGCoin3);
        }

        return avatarCoor;
    }

    public void addCoinsToAvatarCoor(ArrayList<Integer> avatarCoor, ArrayList<Integer> oGCoin1,
                                     ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3) {
        avatarCoor.add(oGCoin1.get(0));
        avatarCoor.add(oGCoin1.get(1));
        avatarCoor.add(oGCoin2.get(0));
        avatarCoor.add(oGCoin2.get(1));
        avatarCoor.add(oGCoin3.get(0));
        avatarCoor.add(oGCoin3.get(1));
    }

    public void resetAvatarCoorWithCoins(ArrayList<Integer> avatarCoor, int x, int y,
                                         int numCoinsPickedUpInTrial, ArrayList<Integer> oGCoin1,
                                         ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3) {
        avatarCoor.clear();
        avatarCoor.add(x);
        avatarCoor.add(y);
        avatarCoor.add(numCoinsPickedUpInTrial);
        avatarCoor.add(1); // trialBool
        addCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
    }

    public ArrayList<Integer> resetAvatarCoordinates(SavedGame loadGame, ArrayList<Integer> avatarCoor,
                                                     ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                                     ArrayList<Integer> oGCoin3) {
        ArrayList<Integer> avXY = loadGame.readAVCoorWorld();
        avatarCoor.clear();
        avatarCoor.addAll(avXY);
        avatarCoor.add(0);
        avatarCoor.add(0);
        addCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
        return avatarCoor;
    }

    public void handleColonKey(SavedGame loadGame, TETile[][] world,
                               ArrayList<Integer> avatarCoor, long seed, ArrayList<Integer> oGCoin1,
                               ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3,
                               int numCoinsPickedUpInTrial) {
        boolean ifColon = true;
        Character avatar = new Character();
        while (ifColon) {
            char key = getNextChar();
            loadGame.saveIfTrial(true);
            avatar.ifExitObjective(key, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numCoinsPickedUpInTrial);
            ifColon = false;
        }
    }

    public int copyObjectives(TETile[][] world, int coinCountOG, Random rand, int numLoops, int x, int y,
                              long seed) {
        Objectives objective = new Objectives();
        Coins coin = new Coins();
        boolean complete = objective.complete;
        if (complete) {
            return copyTrialPickUpCoin(world, x, y, numLoops, coinCountOG);
        } else if (coinCountOG == 1 && numLoops == 0) {
            copyObjective1(world, rand, seed, x, y);
            complete = true;
            return 0;
        } else if (coinCountOG == 1) { // and it isn't the first loop
            return copyTrialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 2 && numLoops == 0) {
            copyObjective2(world, rand, seed, x, y);
            return 0;
        } else if (coinCountOG == 2) {
            return copyTrialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }
        if (coinCountOG == 3 && numLoops == 0) {
            copyObjective3(world, rand, seed, x, y);
            return 0;
        } else if (coinCountOG == 3) {
            return copyTrialPickUpCoin(world, x, y, numLoops, coinCountOG);
        }

        return 0;
    }
    public void copyObjective1(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        Objectives objective = new Objectives();
        SavedGame save = new SavedGame();
        Coins coins = new Coins();

        Character newAvatar = new Character();
        coins.removeCoin(world, x, y);
        objective.trialRoom(world);

        coins.generateCoins(world, rand, true, 1);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);


        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);

        copyPT(world, avatarCoor, seed, 1);
    }

    public void copyObjective2(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        SavedGame save = new SavedGame();
        Objectives objective = new Objectives();

        Coins coins = new Coins();

        Character newAvatar = new Character();
        objective.trialRoom(world);

        coins.generateCoins(world, rand, true, 2);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);
        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);

        copyPT(world, avatarCoor, seed, 2);
    }

    public void copyObjective3(TETile[][] world, Random rand, long seed, int x, int y) {
        PlayingGame playGame = new PlayingGame();
        SavedGame save = new SavedGame();
        Objectives objective = new Objectives();
        Coins coins = new Coins();
        Character newAvatar = new Character();

        objective.trialRoom(world);


        coins.generateCoins(world, rand, true, 3);
        save.saveTrialCoin1Bool(false);
        save.saveTrialCoin2Bool(false);
        save.saveTrialCoin3Bool(false);
        save.saveTrialCoin4Bool(false);
        save.saveTrialCoin5Bool(false);
        save.saveTrialCoin6Bool(false);

        ArrayList<Integer> avatarCoor = newAvatar.generateAvatar(world, rand);

        copyPT(world, avatarCoor, seed, 3);
    }

    public void copyTrialComplete(TETile[][] world, int trialNum) {
        EndGame endGame = new EndGame();
        ReadFiles readSeed = new ReadFiles();
        long seed = readSeed.readFileSeed();
        if (trialNum == 3) {
            copyEndGame(world);
        }

        endGame.endObjective(world, trialNum);

    }

    public TETile[][] copyEndGame(TETile[][] world) {
        boolean waiting = true;
        char key;
        while (waiting) {
            if (isIbound()) {
                key = getNextChar();
                if ((key == 'q') || (key == 'Q')) {
                    //System.exit(0);
                    return world;
                } else if ((key == 'n') || (key == 'N')) {
                    TitleScreen titleScreen = new TitleScreen();
                    Character character = new Character();
                    PlayingGame playingGame = new PlayingGame();
                    ArrayList<Integer> avatarCoor = new ArrayList<>();
                    EndGame endGame = new EndGame();

                    long seed = titleScreen.onTitlePage(world, 94, 55);
                    SavedGame saveFiles = new SavedGame();

                    saveFiles.saveSeed(seed);
                    World updatedWorld = new World(seed);

                    avatarCoor = updatedWorld.generateWorld(world, 94, 55);
                    int numTrialCoinsPickedUp = avatarCoor.get(2);
                    int numTrial = avatarCoor.get(3);
                    Random rand = updatedWorld.rand;
                    copyPlayingGame(world, avatarCoor, rand, seed, numTrial);

                }
            }
        }
        return world;
    }

    public int copyTrialPickUpCoin(TETile[][] world, int x, int y, int numTrialCoins, int trialNum) {
        Coins coin = new Coins();
        SavedGame save = new SavedGame();
        if (coin.isCoin(world, x, y)) {
            numTrialCoins += coin.removeCoin(world, x, y);
            return numTrialCoins;
        }

        if (numTrialCoins == 6) {
            copyTrialComplete(world, trialNum);
            numTrialCoins++;
        }
        return numTrialCoins;
    }
}
