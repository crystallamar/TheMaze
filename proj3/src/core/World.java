package core;

import tileengine.TETile;
import tileengine.Tileset;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class World implements Serializable {
    long seed;
    Random rand;
    //TERenderer ter = new TERenderer();
    //new Random(seed);

    public World(long seed) {
        this.seed = seed;
        rand = new Random(seed);
    }

    public ArrayList<Integer> generateWorld (TETile[][] world, long seed, int width, int height) {
        //rand.setSeed(seed);
        //Random rand = new Random(seed);
        Random rand = this.rand;
        Hallways hallways = new Hallways();
        Mountains addMountains = new Mountains();
        Sand addSand = new Sand();
        Character avatar = new Character();
        Coins coins = new Coins();
        ArrayList<Integer> avatarCoor = new ArrayList<>();
        SavedGame saveGame =  new SavedGame();
        ArrayList<Integer> coinCoor = new ArrayList<>();
        ArrayList<Integer> coinCoor1 = new ArrayList<>();
        ArrayList<Integer> coinCoor2 = new ArrayList<>();
        ArrayList<Integer> coinCoor3 = new ArrayList<>();

        ArrayList<ArrayList<Integer>> centerCoorOfSand = new ArrayList<>();

        addMountains.generateMountains(world, seed, width, height, rand);
        centerCoorOfSand = addSand.generateSand(world, rand);
        hallways.generateHallways(world, rand, centerCoorOfSand);
        coinCoor = coins.generateCoins(world, rand, false, 0);

        int coinCoor1X = coinCoor.get(0);
        int coinCoor1Y = coinCoor.get(1);
        int coinCoor2X = coinCoor.get(2);
        int coinCoor2Y = coinCoor.get(3);
        int coinCoor3X = coinCoor.get(4);
        int coinCoor3Y = coinCoor.get(5);

        coinCoor1.add(coinCoor1X);
        coinCoor1.add(coinCoor1Y);
        coinCoor2.add(coinCoor2X);
        coinCoor2.add(coinCoor2Y);
        coinCoor3.add(coinCoor3X);
        coinCoor3.add(coinCoor3Y);
        saveGame.saveOGCoin1(coinCoor1);
        saveGame.saveOGCoin2(coinCoor2);
        saveGame.saveOGCoin3(coinCoor3);

        avatarCoor = avatar.generateAvatar(world, rand);

        saveGame.saveOGAvCoor(avatarCoor);
        avatarCoor.add(coinCoor1X);
        avatarCoor.add(coinCoor1Y);
        avatarCoor.add(coinCoor2X);
        avatarCoor.add(coinCoor2Y);
        avatarCoor.add(coinCoor3X);
        avatarCoor.add(coinCoor3Y);

        saveGame.saveIfTrial(false);

        return avatarCoor; // Returns avatar coor, OGCoin1 coor, OGCoin2 coor, OGCoin 3 Coor
        //return world;
    }

    public void callPlayGame (TETile[][] world, ArrayList<Integer> avatarCoor, long seed, int numTrial,
                              int numTrialCoinsPickedUp, int trialBool) {
        PlayingGame playingGame = new PlayingGame();
        SavedGame savedGame = new SavedGame();
        // read Num OG COIns picked up
        //EndGame endGame = new EndGame();
        //boolean ifGameEnd = true;
        boolean trial;
        trial = trialBool != 0;
        playingGame.playingGame(world, avatarCoor, rand, trial, numTrial, seed, numTrialCoinsPickedUp, trialBool);
    }

    public TETile[][] generateSavedWorld (TETile[][] world, ArrayList<Integer> savedAvatarCoor,
                                          ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                          ArrayList<Integer> oGCoin3, int numTrialCoinsPickedUp, int trialBool,
                                          int numOGCoinsPickedUp) {
        int width = 94;
        int height = 55;
        Grass genGrass = new Grass();
        Random rand = this.rand;
        Hallways hallways = new Hallways();
        Mountains addMountains = new Mountains();
        Sand addSand = new Sand();
        Character avatar = new Character();
        Coins coins = new Coins();
        //ArrayList<Integer> avatarCoor = new ArrayList<>();
        SavedGame saveGame =  new SavedGame();
        //ArrayList<Integer> coinCoor;
        //        avatarCoor.addAll(saveGame.readAVCoorWorld());
        //        avatarCoor.add(savedAvatarCoor.get(2));
        //        avatarCoor.add(savedAvatarCoor.get(3));
        //        avatarCoor.add(savedAvatarCoor.get(4));
        //        avatarCoor.add(savedAvatarCoor.get(5));
        //        avatarCoor.add(savedAvatarCoor.get(6));
        //        avatarCoor.add(savedAvatarCoor.get(7));
        //        avatarCoor.add(savedAvatarCoor.get(8));
        //        avatarCoor.add(savedAvatarCoor.get(9));
        //        avatarCoor.add(savedAvatarCoor.get(10));

        ArrayList<Integer> coinCoor1 = oGCoin1;
        ArrayList<Integer> coinCoor2 = oGCoin2;
        ArrayList<Integer> coinCoor3 = oGCoin3;

        // ArrayList<Integer> savedAvatarCoorXY = saveGame.readAVCoorWorld();
        //        int trialCoinsPickedUp = avatarCoor.get(2);
        //        int trialBool = avatarCoor.get(3);
        //        int numOGCoins = avatarCoor.get(4);

        //        int OGCoin1X = avatarCoor.get(5);
        //        int OGCoin1Y = avatarCoor.get(6);
        //        int OGCoin2X = avatarCoor.get(7);
        //        int OGCoin2Y = avatarCoor.get(8);
        //        int OGCoin3X = avatarCoor.get(9);
        //        int OGCoin3Y = avatarCoor.get(10);

        numOGCoinsPickedUp = savedAvatarCoor.get(3);
        ArrayList<ArrayList<Integer>> centerCoorOfSand = new ArrayList<>();
        genGrass.generateGrass(world, width, height);
        addMountains.generateMountains(world, seed, width, height, rand);
        centerCoorOfSand = addSand.generateSand(world, rand);
        hallways.generateHallways(world, rand, centerCoorOfSand);
        coins.generateSavedCoins(world, oGCoin1, oGCoin2, oGCoin3);

        //coinCoor = coins.generateCoins(world, rand, false, 0);
        if (numOGCoinsPickedUp != 0) {
            // if (numOGCoinsPickedUp == 1) {
            //     ArrayList<Integer> firstCoinPickedUp = saveGame.readFirstCoinPickedUp("firstCoinPickedUp");
            //     world[firstCoinPickedUp.get(0)][firstCoinPickedUp.get(1)] = Tileset.SAND;
            // }
            if (numOGCoinsPickedUp == 2) {
                ArrayList<Integer> firstCoinPickedUp = saveGame.readFirstCoinPickedUp("firstCoinPickedUp");
                world[firstCoinPickedUp.get(0)][firstCoinPickedUp.get(1)] = Tileset.SAND;
                ArrayList<Integer> secondCoinPickedUp = saveGame.readSecondCoinPickedUp("secondCoinPickedUp");
                world[secondCoinPickedUp.get(0)][secondCoinPickedUp.get(1)] = Tileset.SAND;
            }
        }

        avatar.setAvatarCoor(world, savedAvatarCoor);

        //avatarCoor = savedAvatarCoor;
        //saveGame.saveOGAvCoor(avatarCoor);
        return world;
    }

    public TETile[][] generateTrialWorld(TETile[][] world, long seed) {
        Grass genGrass = new Grass();
        Objectives objective = new Objectives();
        SavedGame readFile = new SavedGame();
        Coins coin = new Coins();
        Character avatar = new Character();
        Random rand = this.rand;

        genGrass.generateGrass(world, 94, 55);
        objective.trialRoom(world); // build background of trial room

        ArrayList<Integer> trialCoinCoor = readFile.readTrialCoinsCoor("trialCoinsCoor");
        ArrayList<Boolean> trialCoinBool = readFile.readTrialCoinsBool("trialCoinsBool");
        ArrayList<Integer> avatarCoor = readFile.readAvatarCoor("avatarCoor");
        int trialNum = avatarCoor.get(3);

        int coin1X = trialCoinCoor.get(0);
        int coin1Y = trialCoinCoor.get(1);
        int coin2X = trialCoinCoor.get(2);
        int coin2Y = trialCoinCoor.get(3);
        int coin3X = trialCoinCoor.get(4);
        int coin3Y = trialCoinCoor.get(5);
        int coin4X = trialCoinCoor.get(6);
        int coin4Y = trialCoinCoor.get(7);
        int coin5X = trialCoinCoor.get(8);
        int coin5Y = trialCoinCoor.get(9);
        int coin6X = trialCoinCoor.get(10);
        int coin6Y = trialCoinCoor.get(11);

        ArrayList<Integer> coin1Coor = new ArrayList<>();
        ArrayList<Integer> coin2Coor = new ArrayList<>();

        ArrayList<Integer> coin3Coor = new ArrayList<>();
        ArrayList<Integer> coin4Coor = new ArrayList<>();

        ArrayList<Integer> coin5Coor = new ArrayList<>();
        ArrayList<Integer> coin6Coor = new ArrayList<>();

        coin1Coor.add(coin1X);
        coin1Coor.add(coin1Y);

        coin2Coor.add(coin2X);
        coin2Coor.add(coin2Y);

        coin3Coor.add(coin3X);
        coin3Coor.add(coin3Y);

        coin4Coor.add(coin4X);
        coin4Coor.add(coin4Y);

        coin5Coor.add(coin5X);
        coin5Coor.add(coin5Y);

        coin6Coor.add(coin6X);
        coin6Coor.add(coin6Y);


        boolean coin1Bool = trialCoinBool.get(0);
        boolean coin2Bool = trialCoinBool.get(1);
        boolean coin3Bool = trialCoinBool.get(2);
        boolean coin4Bool = trialCoinBool.get(3);
        boolean coin5Bool = trialCoinBool.get(4);
        boolean coin6Bool = trialCoinBool.get(5);

        //coin.generateCoins(world, rand, true, trialNum);
        coin.placeTrialCoins(world, trialNum, coin1Coor, coin2Coor, coin3Coor, coin4Coor, coin5Coor, coin6Coor);
        coin.removeTrialCoinsPickedUp(world, trialCoinCoor, coin1Bool, coin2Bool, coin3Bool, coin4Bool, coin5Bool,
                coin6Bool);

        avatar.setAvatarCoor(world, avatarCoor);
        //objective.whilePlayingTrial(world, avatarCoor, rand, true, trialNum, seed);
        //ter.renderFrame(world);
        return world;
    }

    public void callObjectivePlayGame(TETile[][] world, ArrayList<Integer> avatarCoor, Boolean trial, int numTrial,
                                      long seed) {
        Random random = this.rand;
        Objectives objective = new Objectives();
        PlayingGame playGame = new PlayingGame();

        objective.whilePlayingTrial(world, avatarCoor, seed);
        if (numTrial != 3) {
            playGame.playingGame(world, avatarCoor, random, false, numTrial, seed, 0, 0);
        }
    }
}
