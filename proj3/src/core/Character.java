package core;

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

    public ArrayList<Integer> generateAvatar(TETile[][] world, Random random) {
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
        } while (!placeCharacter);
        avatarCoor.add(randX);
        avatarCoor.add(randY);

        return avatarCoor;
    }

    public void setAvatarCoor(TETile[][] world, ArrayList<Integer> avatarCoor) {
        SavedGame save = new SavedGame();
        world[avatarCoor.get(0)][avatarCoor.get(1)] = Tileset.AVATAR;
    }

    public ArrayList<Integer> moveChar(char input, TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial,
                                       int oGCoins, long seed) {
        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int trialNum = oGCoins;
        if (input == 'w') {
            avatarCoor = moveCharUp(world, avatarCoor, trial, trialNum, seed);
        }
        if (input == 'a') {
            avatarCoor = moveCharLeft(world, avatarCoor, trial, trialNum, seed);

        }
        if (input == 's') {
            avatarCoor = moveCharDown(world, avatarCoor, trial, trialNum, seed);

        }
        if (input == 'd') {
            avatarCoor = moveCharRight(world, avatarCoor, trial, trialNum, seed);
        }

        return avatarCoor;
    }

    public void ifExitMain(char input, TETile[][] world, ArrayList<Integer> avatarCoor, long seed) {
        SavedGame saveGame = new SavedGame();
        int numCoinsPickedUp = avatarCoor.get(2);

        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        oGCoin1.add(avatarCoor.get(5));
        oGCoin1.add(avatarCoor.get(6));

        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        oGCoin2.add(avatarCoor.get(7));
        oGCoin2.add(avatarCoor.get(8));

        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        oGCoin3.add(avatarCoor.get(9));
        oGCoin3.add(avatarCoor.get(10));

        if ((input == 'q') || (input == 'Q')) {
            saveGame.createSavedFile(world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numCoinsPickedUp, false);
            System.exit(0);
        }
    }

    public void ifExitObjective(char input, TETile[][] world, ArrayList<Integer> avatarCoor, long seed,
                                ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3,
                                int numCoinsPickedUp) {
        SavedGame saveGame = new SavedGame();
        if ((input == 'q') || (input == 'Q')) {
            saveGame.createSavedFile(world, avatarCoor, seed, oGCoin1, oGCoin2, oGCoin3, numCoinsPickedUp, true);
            saveGame.saveTrialCoinsBool();

            System.exit(0);
        }
    }

    public ArrayList<Integer> moveCharUp(TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial,
                                         int trialNum, long seed) {
        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor, yCoor + 1, trial,
                trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.get(0);
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2);
        int whichCoin = avatarPickedUpCoinArray.get(3);
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
            world[avatarCoor.get(0)][avatarCoor.get(1)] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }
        return avatarCoor;
    }
    public ArrayList<Integer> moveCharLeft(TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial,
                                           int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)

        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor - 1, yCoor, trial,
                trialNum, seed);

        numCoinsPickedUp = avatarPickedUpCoinArray.get(0);
        trialNum = avatarPickedUpCoinArray.get(2);
        int trialHappening = avatarPickedUpCoinArray.get(1);
        int whichCoin = avatarPickedUpCoinArray.get(3);

        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);
        if (world[xCoor - 1][yCoor] == Tileset.SAND || coins.isCoin(world, xCoor - 1, yCoor)) {
            world[xCoor][yCoor] = Tileset.SAND;

            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor - 1);
            avatarCoor.add(yCoor);
            world[avatarCoor.get(0)][avatarCoor.get(1)] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }

        return avatarCoor;
    }

    public ArrayList<Integer> moveCharDown(TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial,
                                           int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)


        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor, yCoor - 1, trial,
                trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.get(0);
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2);
        int whichCoin = avatarPickedUpCoinArray.get(3);


        while (!avatarCoor.isEmpty()) {
            avatarCoor.remove(0);
        }
        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(numCoinsPickedUp);
        avatarCoor.add(trialNum);
        avatarCoor.add(trialHappening);

        if (world[xCoor][yCoor - 1] == Tileset.SAND || coins.isCoin(world, xCoor, yCoor - 1)) {
            world[xCoor][yCoor] = Tileset.SAND;

            avatarCoor.remove(0);
            avatarCoor.remove(0);
            while (!avatarCoor.isEmpty()) {
                avatarCoor.remove(0);
            }
            avatarCoor.add(xCoor);
            yCoor -= 1;
            avatarCoor.add(yCoor);
            world[avatarCoor.get(0)][avatarCoor.get(1)] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);

        }

        return avatarCoor;
    }
    public ArrayList<Integer> moveCharRight(TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial,
                                            int trialNum, long seed) {
        // RETURNS XY COOR, numCoinsPickedUp, OG Coins, if Trial True (0 false, 1 true)


        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int numCoinsPickedUp;
        // int trialNum;

        ArrayList<Integer> avatarPickedUpCoinArray = avatarPickedUpCoin(world, xCoor + 1, yCoor, trial,
                trialNum, seed);
        numCoinsPickedUp = avatarPickedUpCoinArray.get(0);
        int trialHappening = avatarPickedUpCoinArray.get(1);
        trialNum = avatarPickedUpCoinArray.get(2);
        int whichCoin = avatarPickedUpCoinArray.get(3);

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
            world[avatarCoor.get(0)][avatarCoor.get(1)] = Tileset.AVATAR;
            avatarCoor.add(numCoinsPickedUp);
            avatarCoor.add(trialNum);
            avatarCoor.add(trialHappening);
        }
        return avatarCoor;
    }

    public ArrayList<Integer> avatarPickedUpCoin(TETile[][] world, int x, int y, Boolean trial,
                                                 int oGCoins, long seed) {

        // Returns numberOfCoinsPickedUpInTrial, 1 if it is a trial, and og coins,
        // AND WHICH COIN IN TRIAL HAS BEEN PICKED UP
        boolean isCoin = coins.isCoin(world, x, y);
        ArrayList<Integer> numCoinsAndBool = new ArrayList<>();
        SavedGame save = new SavedGame();
        boolean ifSaved = save.readIfLoadedGame("ifLoadedGame");
        if (ifSaved) {
            ArrayList<Integer> avatarCoor = save.readAvatarCoor("avatarCoor");
            coinsPickedUp = avatarCoor.get(2);
            save.saveIfLoadedGame(false);
        }
        if (isCoin) {
            coinsPickedUp++;
        }
        int whichCoin = coins.removeCoin(world, x, y);


        if (isCoin && trial) {
            if (coinsPickedUp == 6) {
                numCoinsAndBool.add(coinsPickedUp);
                numCoinsAndBool.add(0);
                numCoinsAndBool.add(oGCoins);

            } else {
                numCoinsAndBool.add(coinsPickedUp);
                numCoinsAndBool.add(1);
                numCoinsAndBool.add(oGCoins);
            }
        } else if (!isCoin && trial) {
            numCoinsAndBool.add(coinsPickedUp);
            numCoinsAndBool.add(1);
            numCoinsAndBool.add(oGCoins);
        } else if (isCoin && !trial) {
            numCoinsAndBool.add(0);
            numCoinsAndBool.add(1);
            oGCoins++;
            numCoinsAndBool.add(oGCoins);
        } else { //Not a coin and !trial
            numCoinsAndBool.add(coinsPickedUp);
            numCoinsAndBool.add(0);
            numCoinsAndBool.add(oGCoins);
        }

        if (whichCoin == 1) {
            save.saveTrialCoin1Bool(true);
        }
        if (whichCoin == 2) {
            save.saveTrialCoin2Bool(true);
        }
        if (whichCoin == 3) {
            save.saveTrialCoin3Bool(true);
        }
        if (whichCoin == 4) {
            save.saveTrialCoin4Bool(true);
        }
        if (whichCoin == 5) {
            save.saveTrialCoin5Bool(true);
        }
        if (whichCoin == 6) {
            save.saveTrialCoin6Bool(true);
        }


        numCoinsAndBool.add(whichCoin);
        return numCoinsAndBool;

    }

    public void removeOGCoin(TETile[][] world, ArrayList<Integer> coinCoor) {
        int x = coinCoor.get(0);
        int y = coinCoor.get(1);
        world[x][y] = Tileset.SAND;

    }

}
