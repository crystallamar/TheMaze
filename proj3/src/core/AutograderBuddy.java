package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

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
        world = copyMain(world, seed);
        return world;
    }

    public static char getNextChar() {
        return sArray[i++];
    }

    public static boolean isIbound() {
        int n = i;
        return n++ < sArray.length;
    }


    public static long copyOnTitleScreen(TETile[][] world) {
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
                System.exit(0);
            }
        }
        return seed;
    }

    public static long copyEnteringSeed(long seed) {

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
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Seed.data"));
            output.writeLong(seedToReturn);
            output.close();
        } catch (IOException ioe) {
            System.err.println("Issue saving seed.data");
        }

        return seedToReturn;
    }



    public static TETile[][] copyPG(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, long seed) {
        //copyPlayingGame
        Character avatar = new Character();
        Boolean playingGame = true;
        boolean trial = false;
        //TERenderer ter = new TERenderer();
        int numTrial = avatarCoor.get(3);
        int numTrialCoinsPickedUp = avatarCoor.get(2);

        Hover mousePointer = new Hover();
        SavedGame save = new SavedGame();
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        SavedGame savedGame = new SavedGame();
        if (avatarCoor.size() == 11) {
            oGCoin1.add(avatarCoor.get(5));
            oGCoin1.add(avatarCoor.get(6));
            oGCoin2.add(avatarCoor.get(7));
            oGCoin2.add(avatarCoor.get(8));
            oGCoin3.add(avatarCoor.get(9));
            oGCoin3.add(avatarCoor.get(10));
        } else {
            oGCoin1.add(avatarCoor.get(2));
            oGCoin1.add(avatarCoor.get(3));
            oGCoin2.add(avatarCoor.get(4));
            oGCoin2.add(avatarCoor.get(5));
            oGCoin3.add(avatarCoor.get(6));
            oGCoin3.add(avatarCoor.get(7));
        }
        ArrayList<Integer> mouseCoor;
        while (playingGame) {
            //boolean expectingInput = true;
            boolean ifColon;
            char key;
            int initMouseXCoor = 0;
            int initMouseYCoor = 0;
            int currMouseXCoor;
            int currMouseYCoor;
            Objectives objectives = new Objectives();
            save.saveIfTrial(false);
            Boolean didCharMove = true;

            mouseCoor = mousePointer.mouseMoves();

            currMouseXCoor = mouseCoor.get(0);
            currMouseYCoor = mouseCoor.get(1);
            String tileTitle = mousePointer.convertCoor(world, mouseCoor);
            if ((initMouseXCoor != currMouseXCoor) || (initMouseYCoor != currMouseYCoor)) {
                mousePointer.displayNothing();
                mousePointer.displayTile(tileTitle);
                initMouseXCoor = currMouseXCoor;
                initMouseYCoor = currMouseYCoor;
            }


            ifColon = true;
            if (isIbound()) {
                key = getNextChar();

                ArrayList<Integer> temp = new ArrayList<>();
                temp.addAll(avatarCoor);
                avatarCoor = avatar.moveChar(key, world, avatarCoor, trial, numTrial, seed);
                if (temp.equals(avatarCoor)) {
                    didCharMove = false;
                }
                if (avatarCoor.size() == 8) {
                    while (!avatarCoor.isEmpty()) {
                        avatarCoor.remove(0);
                    }
                    avatarCoor.add(avX);
                    avatarCoor.add(avY);
                    avatarCoor.add(0);
                    avatarCoor.add(0);
                    avatarCoor.add(0);
                    avatarCoor.add(oGCoin1.get(0));
                    avatarCoor.add(oGCoin1.get(1));
                    avatarCoor.add(oGCoin2.get(0));
                    avatarCoor.add(oGCoin2.get(1));
                    avatarCoor.add(oGCoin3.get(0));
                    avatarCoor.add(oGCoin3.get(1));
                }
                if (avatarCoor.size() != 11) {
                    avatarCoor.add(oGCoin1.get(0));
                    avatarCoor.add(oGCoin1.get(1));
                    avatarCoor.add(oGCoin2.get(0));
                    avatarCoor.add(oGCoin2.get(1));
                    avatarCoor.add(oGCoin3.get(0));
                    avatarCoor.add(oGCoin3.get(1));
                }
                int x = avatarCoor.get(0);
                int y = avatarCoor.get(1);
                int numLoops = avatarCoor.get(2);
                int trialBool = avatarCoor.get(4);
                numTrial = avatarCoor.get(3);
                if (trialBool == 0) {
                    trial = false; // if numCoins == 0, don't call objective
                } else if (didCharMove) {
                    trial = true;
                    //numCoins = avatarCoor.get(2);
                    if (numLoops == 0) {

                        SavedGame saveGame = new SavedGame();
                        saveGame.saveAvatarCoor(avatarCoor);
                        saveGame.saveAVCoorWorld(avatarCoor.get(0), avatarCoor.get(1));
                        numTrial = avatarCoor.get(3);
                        numLoops = copyObjectives(world, numTrial, rand, numLoops, x, y, seed);


                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.add(x);
                        avatarCoor.add(y);
                        avatarCoor.add(numTrialCoinsPickedUp);
                        avatarCoor.add(numTrial);
                        avatarCoor.add(0);
                        avatarCoor.add(oGCoin1.get(0));
                        avatarCoor.add(oGCoin1.get(1));
                        avatarCoor.add(oGCoin2.get(0));
                        avatarCoor.add(oGCoin2.get(1));
                        avatarCoor.add(oGCoin3.get(0));
                        avatarCoor.add(oGCoin3.get(1));

                        copyPG(world, avatarCoor, rand, seed);
                    } else if (numLoops == 7) {
                        numLoops = 0;
                        numTrial++;
                        ArrayList<Integer> arrayForSecondCoinPickedUp = new ArrayList<>();
                        int xx = avatarCoor.get(0);
                        int yy = avatarCoor.get(1);
                        arrayForSecondCoinPickedUp.add(xx);
                        arrayForSecondCoinPickedUp.add(yy);
                        savedGame.saveCoinPickedUpSecond(arrayForSecondCoinPickedUp);

                    } else {
                        copyObjectives(world, numTrial, rand, numLoops, x, y, seed);
                    }

                }

                if (key == ':') {
                    while (ifColon) {
                        if (isIbound()) {
                            key = getNextChar();
                            save.saveIfTrial(false);
                            avatar.ifExitMain(key, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numTrial);
                            ifColon = false;
                        }
                    }
                }
            } else {
                return world;
            }
        }
        return world;

    }

    public static TETile[][] copyMain(TETile[][] world, long seed) {
        SavedGame loadGame = new SavedGame();
        ArrayList<Integer> avatarCoor;

        if (seed == 'a') {
            loadGame.saveIfLoadedGame(true);
            world = loadGame.openSavedFile();
            seed = loadGame.readSeed("seed");
            World updatedWorld = new World(seed);
            Random rand = updatedWorld.rand;
            avatarCoor = loadGame.readAvatarCoor("avatarCoor");
            int numTrial = avatarCoor.get(3);
            int trialCoinsPickedUp = avatarCoor.get(2);
            int trialBool = avatarCoor.get(4);
            Boolean ifTrial = loadGame.readIfTrial("ifTrial");
            if (!ifTrial) {
                copyPG(world, avatarCoor, rand, seed);
            } else {
                copyPT(world, avatarCoor, seed);
            }
        } else {
            SavedGame saveFiles = new SavedGame();
            saveFiles.saveIfLoadedGame(false);
            saveFiles.saveSeed(seed);
            World updatedWorld = new World(seed);
            Random rand = updatedWorld.rand;
            avatarCoor = updatedWorld.generateWorld(world, 94, 55);
            // Av coor is av coor, OGCoin1 coor, OGCoin2 Coor, and OGCoin3 coor
            copyPG(world, avatarCoor, rand, seed);
        }
        return world;
    }

    public static TETile[][] copyPT(TETile[][] world, ArrayList<Integer> avatarCoor, long seed) { //Copywhileplayingtrial
        Character avatar = new Character();
        Boolean playingGame = true;
        SavedGame loadGame = new SavedGame();
        loadGame.saveIfTrial(true);
        Objectives objective = new Objectives();
        boolean trial = true;
        int numTrial = avatarCoor.get(3);

        Hover mousePointer = new Hover();
        int numCoinsPickedUpInTrial;
        ArrayList<Integer> oGCoin1 = loadGame.readOGCoin1("OGCoin1");
        ArrayList<Integer> oGCoin2 = loadGame.readOGCoin1("OGCoin2");
        ArrayList<Integer> oGCoin3 = loadGame.readOGCoin1("OGCoin3");


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

                currMouseXCoor = mouseCoor.get(0);
                currMouseYCoor = mouseCoor.get(1);
                String tileTitle = mousePointer.convertCoor(world, mouseCoor);
                if ((initMouseXCoor != currMouseXCoor) || (initMouseYCoor != currMouseYCoor)) {
                    mousePointer.displayNothing();
                    mousePointer.displayTile(tileTitle);
                    initMouseXCoor = currMouseXCoor;
                    initMouseYCoor = currMouseYCoor;
                }

                if (isIbound()) {
                    key = getNextChar();
                    ifColon = true;

                    avatarCoor = avatar.moveChar(key, world, avatarCoor, trial, numTrial, seed);
                    if (avatarCoor.size() != 11) {
                        avatarCoor.add(oGCoin1.get(0));
                        avatarCoor.add(oGCoin1.get(1));
                        avatarCoor.add(oGCoin2.get(0));
                        avatarCoor.add(oGCoin2.get(1));
                        avatarCoor.add(oGCoin3.get(0));
                        avatarCoor.add(oGCoin3.get(1));
                    }
                    int x = avatarCoor.get(0);
                    int y = avatarCoor.get(1);
                    numCoinsPickedUpInTrial = avatarCoor.get(2);
                    int oGCoins = avatarCoor.get(3);
                    int trialBool = 1;
                    if (numCoinsPickedUpInTrial != 7) {
                        numCoinsPickedUpInTrial = copyTrialPickUpCoin(world, x, y, numCoinsPickedUpInTrial, numTrial);

                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.add(x); //wrong but okay
                        avatarCoor.add(y); // wrong but okay
                        avatarCoor.add(numCoinsPickedUpInTrial);
                        avatarCoor.add(oGCoins);
                        avatarCoor.add(trialBool);
                        avatarCoor.add(oGCoin1.get(0));
                        avatarCoor.add(oGCoin1.get(1));
                        avatarCoor.add(oGCoin2.get(0));
                        avatarCoor.add(oGCoin2.get(1));
                        avatarCoor.add(oGCoin3.get(0));
                        avatarCoor.add(oGCoin3.get(1));
                    }
                    if (numCoinsPickedUpInTrial == 7) {
                        playingGame = false;
                        expectingInput = false;
                        ArrayList<Integer> avXY = loadGame.readAVCoorWorld();
                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.addAll(avXY);
                        //avatarCoor.add(y);
                        avatarCoor.add(0);
                        avatarCoor.add(oGCoins);
                        avatarCoor.add(0);
                        avatarCoor.add(oGCoin1.get(0));
                        avatarCoor.add(oGCoin1.get(1));
                        avatarCoor.add(oGCoin2.get(0));
                        avatarCoor.add(oGCoin2.get(1));
                        avatarCoor.add(oGCoin3.get(0));
                        avatarCoor.add(oGCoin3.get(1));
                        //trialBool = 0;
                    }

                    if (key == ':') {
                        while (ifColon) {
                            key = getNextChar();
                            loadGame.saveIfTrial(true);
                            ArrayList<Integer> trialCoinsCoor = loadGame.readTrialCoinsCoor("trialCoinsCoor");

                            ArrayList<Boolean> trialCoinsBool = loadGame.readTrialCoinsBool("trialCoinsBool");
                            avatar.ifExitObjective(key, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3,
                                    numCoinsPickedUpInTrial, trialCoinsCoor, trialCoinsBool);
                            ifColon = false;
                        }
                    }
                } else {
                    return world;
                }
            }
        }
        return world;
    }

    public static int copyObjectives(TETile[][] world, int coinCountOG, Random rand, int numLoops, int x, int y,
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
    public static void copyObjective1(TETile[][] world, Random rand, long seed, int x, int y) {
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

        copyPT(world, avatarCoor, seed);
    }

    public static void copyObjective2(TETile[][] world, Random rand, long seed, int x, int y) {
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

        copyPT(world, avatarCoor, seed);
    }

    public static void copyObjective3(TETile[][] world, Random rand, long seed, int x, int y) {
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

        copyPT(world, avatarCoor, seed);
    }

    public static void copyTrialComplete(TETile[][] world, int trialNum) {
        EndGame endGame = new EndGame();
        ReadFiles readSeed = new ReadFiles();
        long seed = readSeed.readFileSeed();
        if (trialNum == 3) {
            copyEndGame(world);
        }

        endGame.endObjective(world, trialNum);

    }

    public static TETile[][] copyEndGame(TETile[][] world) {
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
                    copyPG(world, avatarCoor, rand, seed);

                }
            }
        }
        return world;
    }

    public static int copyTrialPickUpCoin(TETile[][] world, int x, int y, int numTrialCoins, int trialNum) {
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
