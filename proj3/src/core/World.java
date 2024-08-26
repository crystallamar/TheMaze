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

    public ArrayList<Integer> generateWorld(TETile[][] world, int width, int height) {

        Random random = this.rand;
        long currSeed = this.seed;
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

        addMountains.generateMountains(world, currSeed, width, height, random);
        centerCoorOfSand = addSand.generateSand(world, random);
        hallways.generateHallways(world, random, centerCoorOfSand);
        coinCoor = coins.generateCoins(world, random, false, 0);

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

        avatarCoor = avatar.generateAvatar(world, random);

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

    public void callPlayGame(TETile[][] world, ArrayList<Integer> avatarCoor, long seed, int numTrial,
                              int numTrialCoinsPickedUp, int trialBool) {
        PlayingGame playingGame = new PlayingGame();
        Random random = this.rand;
        SavedGame savedGame = new SavedGame();
        // read Num OG COIns picked up
        //EndGame endGame = new EndGame();
        //boolean ifGameEnd = true;
        boolean trial;
        trial = trialBool != 0;
        playingGame.playingGame(world, avatarCoor, random, trial, numTrial, seed, numTrialCoinsPickedUp, trialBool);
    }

    public TETile[][] generateSavedWorld(TETile[][] world, ArrayList<Integer> savedAvatarCoor,
                                          ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                          ArrayList<Integer> oGCoin3, int numTrialCoinsPickedUp, int trialBool,
                                          int numOGCoinsPickedUp) {
        int width = 94;
        int height = 55;
        Grass genGrass = new Grass();
        Random rand = this.rand;
        long seeds = this.seed;
        Hallways hallways = new Hallways();
        Mountains addMountains = new Mountains();
        Sand addSand = new Sand();
        Character avatar = new Character();
        Coins coins = new Coins();
        //ArrayList<Integer> avatarCoor = new ArrayList<>();
        SavedGame saveGame =  new SavedGame();

        ArrayList<Integer> coinCoor1 = oGCoin1;
        ArrayList<Integer> coinCoor2 = oGCoin2;
        ArrayList<Integer> coinCoor3 = oGCoin3;


        numOGCoinsPickedUp = savedAvatarCoor.get(3);
        ArrayList<ArrayList<Integer>> centerCoorOfSand = new ArrayList<>();
        genGrass.generateGrass(world, width, height);
        addMountains.generateMountains(world, seeds, width, height, rand);
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
        //Random random = this.rand;

        genGrass.generateGrass(world, 94, 55);
        objective.trialRoom(world); // build background of trial room

        ArrayList<Integer> trialCoinCoor = new ArrayList<>();

        ArrayList<Integer> trialCoinCoorRED = readFile.readTrialCoinsCoor("trialCoinsCoorRED");
        ArrayList<Integer> trialCoinCoorORANGE = readFile.readTrialCoinsCoor("trialCoinsCoorORANGE");
        ArrayList<Integer> trialCoinCoorYELLOW = readFile.readTrialCoinsCoor("trialCoinsCoorYELLOW");
        ArrayList<Integer> trialCoinCoorGREEN = readFile.readTrialCoinsCoor("trialCoinsCoorGREEN");
        ArrayList<Integer> trialCoinCoorBLUE = readFile.readTrialCoinsCoor("trialCoinsCoorBLUE");
        ArrayList<Integer> trialCoinCoorVIOLET = readFile.readTrialCoinsCoor("trialCoinsCoorVIOLET");

        ArrayList<Boolean> trialCoinBool = readFile.readTrialCoinsBool("trialCoinsBool");
        ArrayList<Integer> avatarCoor = readFile.readAvatarCoor("avatarCoor");
        int trialNum = avatarCoor.get(3);

        int coin1X = trialCoinCoorRED.get(0);
        int coin1Y = trialCoinCoorRED.get(1);
        int coin2X = trialCoinCoorORANGE.get(0);
        int coin2Y = trialCoinCoorORANGE.get(1);
        int coin3X = trialCoinCoorYELLOW.get(0);
        int coin3Y = trialCoinCoorYELLOW.get(1);
        int coin4X = trialCoinCoorGREEN.get(0);
        int coin4Y = trialCoinCoorGREEN.get(1);
        int coin5X = trialCoinCoorBLUE.get(0);
        int coin5Y = trialCoinCoorBLUE.get(1);
        int coin6X = trialCoinCoorVIOLET.get(0);
        int coin6Y = trialCoinCoorVIOLET.get(1);

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

        trialCoinCoor.addAll(trialCoinCoorRED);
        trialCoinCoor.addAll(trialCoinCoorORANGE);
        trialCoinCoor.addAll(trialCoinCoorYELLOW);
        trialCoinCoor.addAll(trialCoinCoorGREEN);
        trialCoinCoor.addAll(trialCoinCoorBLUE);
        trialCoinCoor.addAll(trialCoinCoorVIOLET);



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
        Objectives objective = new Objectives();
        PlayingGame playGame = new PlayingGame();

        objective.whilePlayingTrial(world, avatarCoor, seed, numTrial);
        if (numTrial != 3) {
            playGame.playingGame(world, avatarCoor, this.rand, false, numTrial, seed, 0, 0);
        }
    }
}
