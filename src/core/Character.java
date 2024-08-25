package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Character {
    EndGame endGame = new EndGame();
    Coins coins = new Coins();
    int coinsPickedUp = 0;
    boolean ifGameEnd;
    Objectives trials = new Objectives();
    Random rand;

    public ArrayList<Integer> generateAvatar (TETile[][] world, Random random) {
        ArrayList<Integer> avatarCoor = new ArrayList<>();
        Boolean placeCharacter = false;
        int randX;
        int randY;
        do {
            randX = random.nextInt(90);
            randY = random.nextInt(50);
            if (world[randX][randY] == Tileset.SAND) {
                world[randX][randY] = Tileset.AVATAR;
                placeCharacter = true;
            }
        }
        while (!placeCharacter);
            avatarCoor.add(randX);
            avatarCoor.add(randY);

            return avatarCoor;
        }

        public void setAvatarCoor(TETile[][] world, ArrayList<Integer> avatarCoor) {
            world[avatarCoor.getFirst()][avatarCoor.get(1)] = Tileset.AVATAR;
        }

    public ArrayList<Integer> moveChar(char input, TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int OGCoins, long seed) {
        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int trialNum = OGCoins;
        //int numCoinsPickedUp = avatarCoor.get(2);
        if (input == 'w') {
            avatarCoor = moveCharUp(world, avatarCoor, rand, trial, trialNum, seed);
            //world[xCoor][yCoor] = Tileset.SAND;
        }
        if (input == 'a') {
            avatarCoor = moveCharLeft(world, avatarCoor, rand, trial, trialNum, seed);
            //world[xCoor][yCoor] = Tileset.SAND;

        }
        if (input == 's') {
            avatarCoor = moveCharDown(world, avatarCoor, rand, trial, trialNum, seed);
            //world[xCoor][yCoor] = Tileset.SAND;

        }
        if (input == 'd') {
            avatarCoor = moveCharRight(world, avatarCoor, rand, trial, trialNum, seed);
            //world[xCoor][yCoor] = Tileset.SAND;

        }
//
        return avatarCoor;
    }

    public void ifExitMain(char input, TETile[][] world, ArrayList<Integer> avatarCoor, long seed, ArrayList<Integer> OGCoin1, ArrayList<Integer> OGCoin2, ArrayList<Integer> OGCoin3, int numCoinsPickedUp) {
        SavedGame saveGame = new SavedGame();
        if ((input == 'q') || (input == 'Q')) {
            saveGame.createSavedFile(world, avatarCoor, seed, OGCoin1, OGCoin2, OGCoin3, numCoinsPickedUp, false);
            saveGame.readAvatarCoor("avatarCoor");
            System.exit(0);
        }
    }
    public void ifExitObjective(char input, TETile[][] world, ArrayList<Integer> avatarCoor, long seed, ArrayList<Integer> OGCoin1, ArrayList<Integer> OGCoin2, ArrayList<Integer> OGCoin3, int numCoinsPickedUp, ArrayList<Integer> trialCoinCoor, ArrayList<Boolean> trialCoinBool) {
        SavedGame saveGame = new SavedGame();
        if ((input == 'q') || (input == 'Q')) {
            saveGame.createSavedFile(world, avatarCoor, seed, OGCoin1, OGCoin2, OGCoin3, numCoinsPickedUp, true);
            saveGame.saveTrialCoins(world, trialCoinCoor, numCoinsPickedUp);
            saveGame.saveTrialCoinsBool(trialCoinBool.get(0), trialCoinBool.get(1), trialCoinBool.get(2), trialCoinBool.get(3), trialCoinBool.get(4), trialCoinBool.get(5));
            //saveGame.readAvatarCoor("avatarCoor");
            System.exit(0);
        }
    }


    public ArrayList<Integer> moveCharUp(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)


        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;



        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor, yCoor + 1, rand, trial, trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.getFirst();
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2); ///////////////////
        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);

        if (world[xCoor][yCoor + 1] == Tileset.SAND || coins.isCoin(world, xCoor, yCoor + 1)) {
            world[xCoor][yCoor] = Tileset.SAND;

            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor);
            avatarCoor.add(yCoor + 1);
            world[avatarCoor.getFirst()][avatarCoor.getLast()] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }
        //ifGameEnd = coins.triggerEndOfGame(numCoinsPickedUp);
//        if (ifGameEnd) {
//            world[xCoor][yCoor] = Tileset.SAND;
//            world[xCoor][yCoor + 1] = Tileset.AVATAR;
//            endGame.callEndGame(world);
//        }
        return avatarCoor;
    }
    public ArrayList<Integer> moveCharLeft(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)

        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor - 1, yCoor, rand, trial, trialNum, seed);

        numCoinsPickedUp = avatarPickedUpCoinArray.getFirst();
        trialNum = avatarPickedUpCoinArray.get(2);
        int trialHappening = avatarPickedUpCoinArray.get(1);
        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);
        if (world[xCoor - 1][yCoor] == Tileset.SAND || coins.isCoin(world, xCoor -1, yCoor)) {
            world[xCoor][yCoor] = Tileset.SAND;

            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor - 1);
            avatarCoor.add(yCoor);
            world[avatarCoor.getFirst()][avatarCoor.getLast()] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }
//        ifGameEnd = coins.triggerEndOfGame(numCoinsPickedUp);
//        if (ifGameEnd) {
//            world[xCoor][yCoor] = Tileset.SAND;
//            world[xCoor - 1][yCoor] = Tileset.AVATAR;
//            endGame.callEndGame(world);
//        }

        return avatarCoor;
    }

    public ArrayList<Integer> moveCharDown(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)


        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor, yCoor - 1, rand, trial, trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.getFirst();
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2);

        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);

        if (world[xCoor][yCoor - 1] == Tileset.SAND || coins.isCoin(world, xCoor, yCoor -1)) {
            world[xCoor][yCoor] = Tileset.SAND;

            avatarCoor.remove(0);
            avatarCoor.remove(0);
            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor);
            yCoor -= 1;
            avatarCoor.add(yCoor);
            world[avatarCoor.getFirst()][avatarCoor.get(1)] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }
//        ifGameEnd = coins.triggerEndOfGame(numCoinsPickedUp);
//        if (ifGameEnd) {
//            world[xCoor][yCoor] = Tileset.SAND;
//            world[xCoor][yCoor - 1] = Tileset.AVATAR;
//            endGame.callEndGame(world);
//        }

        return avatarCoor;
    }
    public ArrayList<Integer> moveCharRight(TETile[][] world, ArrayList<Integer> avatarCoor, Random rand, Boolean trial, int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)


        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;
        // int trialNum;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor + 1, yCoor, rand, trial, trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.getFirst();
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2);
        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);

        if (world[xCoor + 1][yCoor] == Tileset.SAND || coins.isCoin(world, xCoor + 1, yCoor)) {
            world[xCoor][yCoor] = Tileset.SAND;

            avatarCoor.remove(0);
            avatarCoor.remove(0);
            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor + 1);
            avatarCoor.add(yCoor);
            world[avatarCoor.getFirst()][avatarCoor.getLast()] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);
        }
//        ifGameEnd = coins.triggerEndOfGame(numCoinsPickedUp);
//        if (ifGameEnd) {
//            world[xCoor][yCoor] = Tileset.SAND;
//            world[xCoor + 1][yCoor] = Tileset.AVATAR;
//            endGame.callEndGame(world);
//        }
        return avatarCoor;
    }

    public ArrayList<Integer> avatarPickedUpCoin(TETile[][] world, int x, int y, Random rand, Boolean trial, int OGCoins, long seed) {

// Returns numberOfCoinsPickedUpInTrial, 1 if it is a trial, and og coins
        boolean isCoin = coins.isCoin(world, x, y);
        ArrayList<Integer> numCoinsAndBool = new ArrayList<>();
        int False = 0;
        int True = 1;
        coinsPickedUp += coins.removeCoin(world, x, y);
//        if(coinsPickedUp == 7){
//            coinsPickedUp = 0;
//        }
        numCoinsAndBool.add(coinsPickedUp);

        if (isCoin && !trial) { // And Trial is false
            if (coinsPickedUp == 6) {
                numCoinsAndBool.add(True);
            }
            else {
                numCoinsAndBool.remove(0);
                numCoinsAndBool.add(0);
                numCoinsAndBool.add(True);
            }
            OGCoins++;
        }
        else if (isCoin && trial) { // And trial is true
            numCoinsAndBool.add(True);
            //trials.trialRoom(world);
            //trials.objectives(world, OGCoins, rand, coinsPickedUp, x, y, seed);
            if (OGCoins == 3) {
                endGame.callEndGame(world);
            }
        }
        else {
            numCoinsAndBool.add(False);
        }
        numCoinsAndBool.add(OGCoins);
//        else if (currNumCoinsPickedUp == 6){
//            currNumCoinsPickedUp++;
//
//            numCoinsAndBool.add(False);
//            numCoinsAndBool.add(currNumCoinsPickedUp);
//        }
        return numCoinsAndBool;

    }

    public void removeOGCoin(TETile[][] world, ArrayList<Integer> coinCoor) {
        int x = coinCoor.get(0);
        int y = coinCoor.get(1);
        world[x][y] = Tileset.SAND;

    }

//    public void removeOGCoin2(TETile[][] world, ArrayList<Integer> coinCoor) {
//        int x = coinCoor.get(0);
//        int y = coinCoor.get(1);
//        world[x][y] = Tileset.SAND;
//    }
//
//    public void removeOGCoin3(TETile[][] world, ArrayList<Integer> coinCoor) {
//        int x = coinCoor.get(0);
//        int y = coinCoor.get(1);
//        world[x][y] = Tileset.SAND;
//    }

}
