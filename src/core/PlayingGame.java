package core;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;
import tileengine.Tileset;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import static java.awt.event.MouseEvent.MOUSE_MOVED;
import java.awt.AWTEvent;
import java.util.Random;

public class PlayingGame {
    EndGame endGame = new EndGame();
    Character avatar = new Character();
    Boolean playingGame = true;
    TERenderer ter = new TERenderer();

    Hover mousePointer = new Hover();
    //int numLoops = 0;
    int numCoins;
    SavedGame save = new SavedGame();



//    public void playingGame(TETile[][] world, ArrayList<Integer> avatarCoor) {
//       // PointerInfo pointerInfo = new PointerInfo();
//        while (playingGame) {
//            boolean expectingInput = true;
//            boolean ifColon;
//            char key;
//            while (expectingInput) {
//                if (StdDraw.hasNextKeyTyped()) {
//                    ifColon = true;
//                    key = StdDraw.nextKeyTyped();
//                    avatarCoor = avatar.moveChar(key, world, avatarCoor);
//                    if (key == ':') {
//                        while(ifColon) {
//                            if(StdDraw.hasNextKeyTyped()) {
//                                key = StdDraw.nextKeyTyped();
//                                avatar.ifExit(key);
//                                ifColon = false;
//                            }
//                        }
//                    }
//                    ter.renderFrame(world);
//
//                    int modifiers = 0;
//                   // pointerInfo.mouseMoved(Component.(AWTEvent.MOUSE_MOTION_EVENT_MASK);
//                //(MOUSE_MOVED));
//
//                }
//            }
//        }
//    }

    public void playingGame(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int numTrial, long seed, int numTrialCoinsPickedUp, int trialBoolPassedIn) {
        // PointerInfo pointerInfo = new PointerInfo();
        ArrayList<Integer> OGCoin1 = new ArrayList<>();
        ArrayList<Integer> OGCoin2 = new ArrayList<>();
        ArrayList<Integer> OGCoin3 = new ArrayList<>();
        int avX = avatarCoor.get(0);
        int avY = avatarCoor.get(1);
        Coins coin = new Coins();

        OGCoin1.add(avatarCoor.get(2));
        OGCoin1.add(avatarCoor.get(3));
        OGCoin2.add(avatarCoor.get(4));
        OGCoin2.add(avatarCoor.get(5));
        OGCoin3.add(avatarCoor.get(6));
        OGCoin3.add(avatarCoor.get(7));
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

                if (StdDraw.hasNextKeyTyped()) {
                    ifColon = true;
                    key = StdDraw.nextKeyTyped();





                    avatarCoor = avatar.moveChar(key, world, avatarCoor, rand, trial, numTrial, seed);
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
                    }
                    else {
                        trial = true;
                        //numCoins = avatarCoor.get(2);
                        if (numLoops == 0) {

                            // SAVE GAME HERE
                            //save.saveFile("Character", avatar);
                            //save.saveFile("World", world);
                            SavedGame saveGame = new SavedGame();
                            saveGame.saveAvatarCoor(avatarCoor);
                            numTrial = avatarCoor.get(3);
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                            int newAvatarCoorOGCoins = avatarCoor.get(2) + 1;
                            avX = avatarCoor.get(0);
                            avY = avatarCoor.get(1);
                            while (!avatarCoor.isEmpty()){
                                avatarCoor.remove(0);
                            }
                            avatarCoor.add(avX);
                            avatarCoor.add(avY);
                            avatarCoor.add(numTrialCoinsPickedUp);
                            avatarCoor.add(numTrial);
                            avatarCoor.add(trialBool);
                            avatarCoor.add(OGCoin1.get(0));
                            avatarCoor.add(OGCoin1.get(1));
                            avatarCoor.add(OGCoin2.get(0));
                            avatarCoor.add(OGCoin2.get(1));
                            avatarCoor.add(OGCoin3.get(0));
                            avatarCoor.add(OGCoin3.get(1));
                            //int newAvatarCoorOGCoins = numLoops;
                            //avatarCoor.remove(2);
                            //avatarCoor.add(2, newAvatarCoorOGCoins);
                            playingGame(world, avatarCoor, rand, false, numTrial, seed, numTrialCoinsPickedUp, trialBool);
                        } else if (numLoops == 7) {
                            numLoops = 0;
                            numTrial++;
                            // RETRIEVE GAME BEFORE TRIAL
                        } else {
                            numLoops = objectives.objectives(world, numTrial, rand, numLoops, x, y, seed);
                        }


                        //else {
                            //save.saveFile("PlayingGame", playingGame);

                            //numLoops = objectives.objectives(world, numCoins, rand, numLoops, x, y);

                            //numLoops = objectives.trial1Colors(world, avatarCoor.getFirst(), avatarCoor.get(1), numLoops);


                            // int updatedNumLoops = objectives.trial1Colors(world, avatarCoor.getFirst(), avatarCoor.get(1), numLoops);
                            //numLoops = updatedNumLoops;

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
                    ter.renderFrame(world);

                    int modifiers = 0;
                    // pointerInfo.mouseMoved(Component.(AWTEvent.MOUSE_MOTION_EVENT_MASK);
                    //(MOUSE_MOVED));

                }
            }
        }
    }
}