package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;

public class PlayingGame {
    //Character avatar = new Character();
    //Boolean playingGame = true;
    TERenderer ter = new TERenderer();

    //Hover mousePointer = new Hover();
    //SavedGame save = new SavedGame();

    public void playingGame(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial,
                            long seed, int numTrialCoinsPickedUp, int trialBoolPassedIn) {
        Character avatar = new Character();
        ArrayList<Integer> oGCoinArray;
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        SavedGame savedGame = new SavedGame();
        Objectives objectives = new Objectives();
        boolean playingGame = true;
        SavedGame save = new SavedGame();
        Hover mousePointer = new Hover();

        if (avatarCoor.size() == 11) {
            oGCoinArray = combineOGCoinsIntoArray(avatarCoor, 11);
        } else {
            oGCoinArray = combineOGCoinsIntoArray(avatarCoor, 0);
        }
        ArrayList<Integer> oGCoin1 = returnCoin1FromArray(oGCoinArray);
        ArrayList<Integer> oGCoin2 = returnCoin2FromArray(oGCoinArray);
        ArrayList<Integer> oGCoin3 = returnCoin3FromArray(oGCoinArray);
        while (playingGame) {
            int[] mouseCoords = {0, 0};
            boolean expectingInput = true;
            save.saveIfTrial(false);
            Boolean didCharMove = true;
            while (expectingInput) {
                updateMousePointer(world, mousePointer, mouseCoords);
                if (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.addAll(avatarCoor);
                    avatarCoor = avatar.moveChar(key, world, avatarCoor, trial, numTrial, seed);
                    if (temp.equals(avatarCoor)) {
                        didCharMove = false;
                    }
                    if (avatarCoor.size() == 8) {
                        avatarCoor = addBackToCoor(avX, avY, 0, 0, 0, oGCoin1, oGCoin2, oGCoin3);
                    }
                    if (avatarCoor.size() != 11) {
                        addOGCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
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
                        if (numLoops == 0) {
                            save.saveAvatarCoor(avatarCoor);
                            save.saveAVCoorWorld(avatarCoor.get(0), avatarCoor.get(1));
                            numTrial = avatarCoor.get(3);
                            objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                            avatarCoor = addBackToCoor(x, y, numTrialCoinsPickedUp, numTrial, 0, oGCoin1, oGCoin2,
                                    oGCoin3);
                            playingGame(world, avatarCoor, rand, false, numTrial, seed, numTrialCoinsPickedUp,
                                    trialBool);
                        } else if (numLoops == 7) {
                            numTrial++;
                            ArrayList<Integer> arrayForSecondCoinPickedUp = new ArrayList<>();
                            int xx = avatarCoor.get(0);
                            int yy = avatarCoor.get(1);
                            arrayForSecondCoinPickedUp.add(xx);
                            arrayForSecondCoinPickedUp.add(yy);
                            savedGame.saveCoinPickedUpSecond(arrayForSecondCoinPickedUp);
                        } else {
                            objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                        }
                    }
                    if (key == ':') {
                        handleColonKey(world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numTrialCoinsPickedUp);
                    }
                    ter.renderFrame(world);
                }
            }
        }
    }

    private void handleColonKey(TETile[][] world, ArrayList<Integer> avatarCoor,
                                long seed, ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                ArrayList<Integer> oGCoin3, int numCoinsPickedUpInTrial) {
        Character avatar = new Character();
        SavedGame loadGame = new SavedGame();
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
    private void prepareForNextStage(SavedGame loadGame, ArrayList<Integer> avatarCoor, ArrayList<Integer> oGCoin1,
                                     ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3, int numTrial) {
        ArrayList<Integer> avXY = loadGame.readAVCoorWorld();

        avatarCoor.clear();
        avatarCoor.addAll(avXY);
        avatarCoor.addAll(Arrays.asList(0, numTrial, 0));
        addOGCoinsToAvatarCoor(avatarCoor, oGCoin1, oGCoin2, oGCoin3);
    }

    public ArrayList<Integer> addBackToCoor(int x, int y, int numTrialCoinsPickedUp, int oGCoins, int trial,
                              ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3) {
        ArrayList<Integer> avatarCoor = new ArrayList<>();
        avatarCoor.add(x);
        avatarCoor.add(y);
        avatarCoor.add(numTrialCoinsPickedUp);
        avatarCoor.add(oGCoins);
        avatarCoor.add(trial);
        avatarCoor.addAll(oGCoin1);
        avatarCoor.addAll(oGCoin2);
        avatarCoor.addAll(oGCoin3);

        return avatarCoor;

    }

    public ArrayList<Integer> returnOGCoin1(ArrayList<Integer> avatarCoor, int y, int z) {
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        oGCoin1.add(avatarCoor.get(y));
        oGCoin1.add(avatarCoor.get(z));
        return oGCoin1;
    }

    public ArrayList<Integer> returnOGCoin2(ArrayList<Integer> avatarCoor, int y, int z) {
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        oGCoin2.add(avatarCoor.get(y));
        oGCoin2.add(avatarCoor.get(z));
        return oGCoin2;
    }

    public ArrayList<Integer> returnOGCoin3(ArrayList<Integer> avatarCoor, int y, int z) {
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        oGCoin3.add(avatarCoor.get(y));
        oGCoin3.add(avatarCoor.get(z));
        return oGCoin3;
    }

    public ArrayList<Integer> combineOGCoinsIntoArray(ArrayList<Integer> avatarCoor, int which) {
        ArrayList<Integer> allOGCoins = new ArrayList<>();

        if (which == 11) {
            ArrayList<Integer> oGCoin1 = returnOGCoin1(avatarCoor, 5, 6);
            ArrayList<Integer> oGCoin2 = returnOGCoin2(avatarCoor, 7, 8);
            ArrayList<Integer> oGCoin3 = returnOGCoin3(avatarCoor, 9, 10);
            allOGCoins.addAll(oGCoin1);
            allOGCoins.addAll(oGCoin2);
            allOGCoins.addAll(oGCoin3);
        } else {
            ArrayList<Integer> oGCoin1 = returnOGCoin1(avatarCoor, 2, 3);
            ArrayList<Integer> oGCoin2 = returnOGCoin2(avatarCoor, 4, 5);
            ArrayList<Integer> oGCoin3 = returnOGCoin3(avatarCoor, 6, 7);
            allOGCoins.addAll(oGCoin1);
            allOGCoins.addAll(oGCoin2);
            allOGCoins.addAll(oGCoin3);
        }
        return allOGCoins;
    }

    public ArrayList<Integer> returnCoin1FromArray(ArrayList<Integer> oGCoinArray) {
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        oGCoin1.add(oGCoinArray.get(0));
        oGCoin1.add(oGCoinArray.get(1));
        return oGCoin1;
    }
    public ArrayList<Integer> returnCoin2FromArray(ArrayList<Integer> oGCoinArray) {
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        oGCoin2.add(oGCoinArray.get(2));
        oGCoin2.add(oGCoinArray.get(3));
        return oGCoin2;
    }
    public ArrayList<Integer> returnCoin3FromArray(ArrayList<Integer> oGCoinArray) {
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        oGCoin3.add(oGCoinArray.get(4));
        oGCoin3.add(oGCoinArray.get(5));
        return oGCoin3;
    }

}
