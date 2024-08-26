package core;

import org.apache.pdfbox.contentstream.operator.state.Save;
import tileengine.TERenderer;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EndGame {
    TERenderer ter = new TERenderer();
    public void quitGame() {
        boolean endGame = true;
        char key;
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(47, 27, 8, 5);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(47, 27, "Save Game: S\n Quit Game: Q");
        StdDraw.show();
        while (endGame) {
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                if ((key == 's') || (key == 'S')) {
                    //Save game
                    endGame = false;
                }

                if ((key == 'q') || (key == 'Q')) {
                    System.exit(0);
                }

            }
        }

    }

    public void pressedSave() {
        //save game
    }

    public void pressedQuit() {
        System.exit(0);
    }
public void endGame(TETile[][] world) {
    boolean waiting = true;
    char key;
    while (waiting) {
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(45, 25, 60, 60);
        StdDraw.setPenColor(Color.green);
        StdDraw.text(45, 26, "Congratulations! You win!");
        StdDraw.text(45, 23, "Press Q to Quit");
        StdDraw.text(45, 20, "Press N to start new game");
        StdDraw.show();
        while (StdDraw.hasNextKeyTyped()) {
            key = StdDraw.nextKeyTyped();
            if ((key == 'q') || (key == 'Q')) {
                System.exit(0);
            }
            else if ((key == 'n') || (key == 'N')) {
                TitleScreen titleScreen = new TitleScreen();
                Character character = new Character();
                PlayingGame playingGame = new PlayingGame();
                ArrayList<Integer> avatarCoor = new ArrayList<>();
                EndGame endGame = new EndGame();

                titleScreen.generateTitleScreen(world, 94, 55);
                long seed = titleScreen.onTitlePage(world, 94, 55);
                SavedGame saveFiles = new SavedGame();

                saveFiles.saveSeed(seed);
                World updatedWorld = new World(seed);

                avatarCoor = updatedWorld.generateWorld(world, seed, 94, 55);
                //world = updatedWorld.generateWorld(world, seed, WIDTH, HEIGHT);
                ter.renderFrame(world);
                //character.takeInput();
                updatedWorld.callPlayGame(world, avatarCoor, seed, 0, 0, 0);
                //endGame.callEndGame(world);
                ter.renderFrame(world);
            }


        }


    }
}

public void callEndGame(TETile[][] world) {
    endGame(world);
    //ter.renderFrame(world);
    //return true;


}

public boolean endObjective(TETile[][] world, int trialNum) {
        SavedGame retrieveGame = new SavedGame();
        PlayingGame game;
        Character avatar = new Character();
        //retrieveGame.readFile("PlayingGame");
        long seed = retrieveGame.readSeed("seed");
        World genWorld = new World(seed);
        Grass genGrass = new Grass();
        genGrass.generateGrass(world, 94, 55);
        //genWorld.generateWorld(world, seed, 94, 55);
        ArrayList<Integer> avatarCoor = retrieveGame.readAvatarCoor("avatarCoor");
        ArrayList<Integer> avXY = retrieveGame.readAVCoorWorld();
        avatar.setAvatarCoor(world, avXY);
        ArrayList<Integer> OGavatarCoor = retrieveGame.readOGAvCoor("OGAvCoor");
        ArrayList<Integer> OGCoin1;
        ArrayList<Integer> OGCoin2;
        ArrayList<Integer> OGCoin3;
        ArrayList<Integer> firstCoinPickedUp;
        ArrayList<Integer> secondCoinPickedUp;


        world[OGavatarCoor.get(0)][OGavatarCoor.get(1)] = Tileset.SAND;
        OGCoin1 = retrieveGame.readOGCoin1("OGCoin1");
        OGCoin2 = retrieveGame.readOGCoin2("OGCoin2");
        OGCoin3 = retrieveGame.readOGCoin3("OGCoin3");
       // ArrayList<Integer> avCoorXY = new ArrayList<>();
        //avCoorXY.add(avatarCoor.get(0));
        //avCoorXY.add(avatarCoor.get(1));
        int trialCoinsPickedUp = avatarCoor.get(2);
        int OGCoins = avatarCoor.get(3);
        int boolTrial = avatarCoor.get(4);
        while (!avatarCoor.isEmpty()){
            avatarCoor.remove(0);
        }
        avatarCoor.add(avXY.get(0));
        avatarCoor.add(avXY.get(1));
        avatarCoor.add(trialCoinsPickedUp);
        avatarCoor.add(OGCoins);
        avatarCoor.add(boolTrial);
        avatarCoor.addAll(OGCoin1);
        avatarCoor.addAll(OGCoin2);
        avatarCoor.addAll(OGCoin3);


        genWorld.generateSavedWorld(world, avatarCoor, OGCoin1, OGCoin2, OGCoin3, trialCoinsPickedUp, boolTrial, OGCoins);



//        int whichCoin = 0;
//        int first = 0;
//
        if (trialNum == 1) {
            if (OGCoin1.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(OGCoin1); // Save first coin coor
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp"); //
                avatar.removeOGCoin(world,OGCoin1);
                avatar.setAvatarCoor(world, avatarCoor);

            }


            //avatar.removeOGCoin1(world, OGCoin1);
            //first++;
            //whichCoin = 1;
            // Store Coor if picked up
//

            else if (OGCoin2.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(OGCoin2);
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world,OGCoin2);
                avatar.setAvatarCoor(world, avatarCoor);


//            //avatar.removeOGCoin2(world, OGCoin2);
//            first++;
//            whichCoin = 2;
//
            }
            else if (OGCoin3.equals(avXY)) {
                retrieveGame.saveCoinPickedUpFirst(OGCoin3);
                //firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world,OGCoin3);
                avatar.setAvatarCoor(world, avatarCoor);

            }
//            //avatar.removeOGCoin3(world, OGCoin3);
//            whichCoin = 3;
//            first++;
        }
        else if (trialNum == 2) {//NOT SURE NEEDED
            if (OGCoin1.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world,firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(OGCoin1);
                avatar.removeOGCoin(world, OGCoin1);
                avatar.setAvatarCoor(world, avatarCoor);
            }

            if (OGCoin2.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world,firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(OGCoin2);

                avatar.removeOGCoin(world, OGCoin2);
                avatar.setAvatarCoor(world, avatarCoor);

            }
            if (OGCoin3.equals(avXY)) {
                firstCoinPickedUp = retrieveGame.readFirstCoinPickedUp("FirstCoinPickedUp");
                avatar.removeOGCoin(world,firstCoinPickedUp);
                retrieveGame.saveCoinPickedUpSecond(OGCoin3);

                avatar.removeOGCoin(world, OGCoin3);
                avatar.setAvatarCoor(world, avatarCoor);

            }

            //        if (first == 1) {
//            if (whichCoin == 1) {
//                firstCoinPickedUp = OGCoin1;
//                avatar.removeOGCoin1(world, firstCoinPickedUp);
//            }
//            else if (whichCoin == 2) {
//                firstCoinPickedUp = OGCoin2;
//                avatar.removeOGCoin1(world, firstCoinPickedUp);
//
//            }
//            else if (whichCoin == 3) {
//                firstCoinPickedUp = OGCoin3;
//                avatar.removeOGCoin1(world, firstCoinPickedUp);
//
//            }
//        }
//        else if (first == 2){
//            if (whichCoin == 1) {
//                    secondCoinPickedUp = OGCoin1;
//                    avatar.removeOGCoin1(world, firstCoinPickedUp);
//            }
//            else if (whichCoin == 2) {
//                    secondCoinPickedUp = OGCoin2;
//            }
//            else if (whichCoin == 3) {
//                    secondCoinPickedUp = OGCoin3;
//            }
//            }
//        else if (first == 3) {
//            if (whichCoin == 1) {
//                firstCoinPickedUp = OGCoin1;
//            }
//            else if (whichCoin == 2) {
//                firstCoinPickedUp = OGCoin2;
//            }
//            else if (whichCoin == 3) {
//                firstCoinPickedUp = OGCoin3;
//        }
//    }

}
    return false;

}
//    try {
//        ObjectInputStream objectInputStream = null;
//        FileInputStream streamIn = new FileInputStream("Character");
//        objectInputStream = new ObjectInputStream(streamIn);
//
//} catch (FileNotFoundException e) {
//        throw new RuntimeException(e);
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//    World newWorld = new World(seed);
//        newWorld.generateWorld(world, seed, 94, 55);
//        ter.renderFrame(world);
//        return false;
//}
}
