package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;
import java.util.ArrayList;

import java.util.Random;

public class PlayingGame {
    EndGame endGame = new EndGame();
    Character avatar = new Character();
    Boolean playingGame = true;
    TERenderer ter = new TERenderer();

    Hover mousePointer = new Hover();
    SavedGame save = new SavedGame();

    public void playingGame(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial,
                            long seed, int numTrialCoinsPickedUp, int trialBoolPassedIn) {
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        Coins coin = new Coins();
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
            boolean expectingInput = true;
            boolean ifColon;
            char key;
            int initMouseXCoor = 0;
            int initMouseYCoor = 0;
            int currMouseXCoor = 0;
            int currMouseYCoor = 0;
            Objectives objectives = new Objectives();
            save.saveIfTrial(false);
            Boolean didCharMove = true;


            while (expectingInput) {
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

                if (StdDraw.hasNextKeyTyped()) {
                    ifColon = true;
                    key = StdDraw.nextKeyTyped();

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
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
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

                            playingGame(world, avatarCoor, rand, false, numTrial, seed, numTrialCoinsPickedUp,
                                    trialBool);
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
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                        }
                    }

                    if (key == ':') {
                        while (ifColon) {
                            if (StdDraw.hasNextKeyTyped()) {
                                key = StdDraw.nextKeyTyped();
                                save.saveIfTrial(false);
                                avatar.ifExitMain(key, world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numTrial);
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