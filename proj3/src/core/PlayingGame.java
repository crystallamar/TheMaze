package core;

import edu.princeton.cs.algs4.StdDraw;
import org.checkerframework.checker.units.qual.C;
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
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        SavedGame savedGame = new SavedGame();
        Objectives objectives = new Objectives();
        boolean playingGame = true;
        SavedGame save = new SavedGame();
        Hover mousePointer = new Hover();

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
        while (playingGame) {
            int[] mouseCoords = {0, 0};
            boolean expectingInput = true;
            char key;
            save.saveIfTrial(false);
            Boolean didCharMove = true;
            while (expectingInput) {
                updateMousePointer(world, mousePointer, mouseCoords);

                if (StdDraw.hasNextKeyTyped()) {
                    key = StdDraw.nextKeyTyped();

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
                        //numCoins = avatarCoor.get(2);
                        if (numLoops == 0) {
                            SavedGame saveGame = new SavedGame();
                            saveGame.saveAvatarCoor(avatarCoor);
                            saveGame.saveAVCoorWorld(avatarCoor.get(0), avatarCoor.get(1));
                            numTrial = avatarCoor.get(3);
                            objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                            avatarCoor = addBackToCoor(x, y, numTrialCoinsPickedUp, numTrial, 0, oGCoin1, oGCoin2, oGCoin3);
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
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                        }
                    }
                    if (key == ':') {
                        handleColonKey(world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numTrialCoinsPickedUp);
                        ter.renderFrame(world);
                    }
                    ter.renderFrame(world);
                    int modifiers = 0;
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
}
