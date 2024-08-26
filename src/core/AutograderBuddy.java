package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
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

    public TETile[][] getWorldFromInput(String input) {
        sArray = input.toCharArray();
        TETile[][] world = new TETile[94][55];
        long seed = copyOnTitleScreen(world);
        world = copyMain(world, seed);
        return world;


        // just take seed and associate it with a specific world

        // STEP ONE: read startgame vs. load game

        // STEP TWO: READ SEED IF START GAME

        // STEPTHREE: READ MOVEMENTS

        // STEP FOUR: CHECK FOR SAVING AND QUITTING

    }

    public static char getNextChar() {
        return sArray[i++];
    }

    public static boolean isIbound() {
        int n = i;
        if(n++ < sArray.length) {
            return true;
        }
        return false;
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
            }
            else {
                return seed;
            }
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
        char prevKey = '0';


        while ((sArray[i + 1] != 's') && sArray[i + 1] != 'S') {
            if (isIbound()) {
                key = getNextChar();
            }
            else {
                return seed;
            }
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







    public TETile[][] copyPlayingGame(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial, long seed, int numTrialCoinsPickedUp, int trialBoolPassedIn) {
        Character avatar = new Character();
        Boolean playingGame = true;
        //TERenderer ter = new TERenderer();

        Hover mousePointer = new Hover();
        SavedGame save = new SavedGame();
        ArrayList<Integer> OGCoin1 = new ArrayList<>();
        ArrayList<Integer> OGCoin2 = new ArrayList<>();
        ArrayList<Integer> OGCoin3 = new ArrayList<>();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        SavedGame savedGame = new SavedGame();
        if (avatarCoor.size() == 11) {
            OGCoin1.add(avatarCoor.get(5));
            OGCoin1.add(avatarCoor.get(6));
            OGCoin2.add(avatarCoor.get(7));
            OGCoin2.add(avatarCoor.get(8));
            OGCoin3.add(avatarCoor.get(9));
            OGCoin3.add(avatarCoor.get(10));
        } else {
            OGCoin1.add(avatarCoor.get(2));
            OGCoin1.add(avatarCoor.get(3));
            OGCoin2.add(avatarCoor.get(4));
            OGCoin2.add(avatarCoor.get(5));
            OGCoin3.add(avatarCoor.get(6));
            OGCoin3.add(avatarCoor.get(7));
        }
        ArrayList<Integer> mouseCoor;
        while (playingGame) {
            boolean expectingInput = true;
            boolean ifColon;
            char key;
            int initMouseXCoor = 0;
            int initMouseYCoor = 0;
            int currMouseXCoor;
            int currMouseYCoor;
            Objectives objectives = new Objectives();
            save.saveIfTrial(false);
            Boolean didCharMove = true;


            while (expectingInput) {
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


                ifColon = true;
                if (isIbound()) {
                    key = getNextChar();


                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.addAll(avatarCoor);
                    avatarCoor = avatar.moveChar(key, world, avatarCoor, rand, trial, numTrial, seed);
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
                        avatarCoor.add(OGCoin1.get(0));
                        avatarCoor.add(OGCoin1.get(1));
                        avatarCoor.add(OGCoin2.get(0));
                        avatarCoor.add(OGCoin2.get(1));
                        avatarCoor.add(OGCoin3.get(0));
                        avatarCoor.add(OGCoin3.get(1));
                    }
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
                    int numLoops = avatarCoor.get(2);
                    int trialBool = avatarCoor.get(4);
                    numTrial = avatarCoor.get(3);
                    if (trialBool == 0) {
                        trial = false; // if numCoins == 0, don't call objective
                    } else if (didCharMove) {
                        trial = true;
                        //numCoins = avatarCoor.get(2);
                        if (numLoops == 0) {

                            // SAVE GAME HERE
                            //save.saveFile("Character", avatar);
                            //save.saveFile("World", world);
                            SavedGame saveGame = new SavedGame();
                            saveGame.saveAvatarCoor(avatarCoor);
                            saveGame.saveAVCoorWorld(avatarCoor.get(0), avatarCoor.get(1));
                            numTrial = avatarCoor.get(3);
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                            //int newAvatarCoorOGCoins = avatarCoor.get(2) + 1;
                            //avX = avatarCoor.get(0);
                            //avY = avatarCoor.get(1);
                            while (!avatarCoor.isEmpty()) {
                                avatarCoor.remove(0);
                            }
                            avatarCoor.add(x);
                            avatarCoor.add(y);
                            avatarCoor.add(numTrialCoinsPickedUp);
                            avatarCoor.add(numTrial);
                            avatarCoor.add(0);
                            avatarCoor.add(OGCoin1.get(0));
                            avatarCoor.add(OGCoin1.get(1));
                            avatarCoor.add(OGCoin2.get(0));
                            avatarCoor.add(OGCoin2.get(1));
                            avatarCoor.add(OGCoin3.get(0));
                            avatarCoor.add(OGCoin3.get(1));
                            //savedGame.saveAvatarCoor(avatarCoor);
                            //int newAvatarCoorOGCoins = numLoops;
                            //avatarCoor.remove(2);
                            //avatarCoor.add(2, newAvatarCoorOGCoins);
                            copyPlayingGame(world, avatarCoor, rand, false, numTrial, seed, numTrialCoinsPickedUp, trialBool);
                        } else if (numLoops == 7) {
                            numLoops = 0;
                            numTrial++;
                            ArrayList<Integer> arrayForSecondCoinPickedUp = new ArrayList<>();
                            int xx = avatarCoor.get(0);
                            int yy = avatarCoor.get(1);
                            arrayForSecondCoinPickedUp.add(xx);
                            arrayForSecondCoinPickedUp.add(yy);
                            savedGame.saveCoinPickedUpSecond(arrayForSecondCoinPickedUp);
                            //savedGame.saveAvatarCoor(avatarCoor);

                            // RETRIEVE GAME BEFORE TRIAL
                        } else {
                            objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                        }

                    }

                    if (key == ':') {
                        while (ifColon) {
                            if (StdDraw.hasNextKeyTyped()) {
                                key = StdDraw.nextKeyTyped();
                                save.saveIfTrial(false);
                                avatar.ifExitMain(key, world, avatarCoor, seed, OGCoin1, OGCoin2, OGCoin3, numTrial);
                                ifColon = false;
                            }
                        }
                    }

                }
                else
                {
                    return world;
                }
            }

        }
        return world;
    }

        public TETile[][] copyMain(TETile[][] world, long seed) {

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
                    copyPlayingGame(world, avatarCoor, rand, false, numTrial, seed, trialCoinsPickedUp, trialBool);
                } else {
                    copyWhilePlayingTrial(world, avatarCoor, rand, true, numTrial, seed);
                }
            } else {
                SavedGame saveFiles = new SavedGame();
                saveFiles.saveIfLoadedGame(false);
                saveFiles.saveSeed(seed);
                World updatedWorld = new World(seed);
                Random rand = updatedWorld.rand;
                avatarCoor = updatedWorld.generateWorld(world, seed, 94, 55);
                // Av coor is av coor, OGCoin1 coor, OGCoin2 Coor, and OGCoin3 coor


                //world = updatedWorld.generateWorld(world, seed, WIDTH, HEIGHT);
                //character.takeInput();
                copyPlayingGame(world, avatarCoor, rand, false,0, seed, 0, 0);
                //endGame.callEndGame(world);
            }
            return world;


        }



    public TETile[][] copyWhilePlayingTrial(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial, long seed) {
        Character avatar = new Character();
        Boolean playingGame = true;
        SavedGame loadGame = new SavedGame();
        loadGame.saveIfTrial(true);
        Objectives objective = new Objectives();

        Hover mousePointer = new Hover();
        int numCoinsPickedUpInTrial;
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
                    int trialBool = 1;
                    if (numCoinsPickedUpInTrial != 7) {
                        numCoinsPickedUpInTrial = objective.trialPickUpCoin(world, x, y, numCoinsPickedUpInTrial, numTrial);

                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.add(x); //wrong but okay
                        avatarCoor.add(y); // wrong but okay
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
                        ArrayList<Integer> avXY = loadGame.readAVCoorWorld();
                        while (!avatarCoor.isEmpty()) {
                            avatarCoor.remove(0);
                        }
                        avatarCoor.addAll(avXY);
                        //avatarCoor.add(y);
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
                }
            }
        }
        return world;
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


