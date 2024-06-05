package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;
import tileengine.TERenderer;

import java.awt.*;

public class TitleScreen {
    TERenderer ter = new TERenderer();

    public void generateTitleScreen(TETile[][] world, int width, int height) {
        Grass grass = new Grass();
        grass.generateGrass(world, width, height);
        ter.renderFrame(world);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.text(47, 40, "61B Project: The Game");
        StdDraw.filledSquare(22, 20, 5);
        StdDraw.filledSquare(47, 20, 5);
        StdDraw.filledSquare(72, 20, 5);


        StdDraw.setPenColor(Color.green);
        StdDraw.text(22, 20, "Load Game: L");
        StdDraw.text(47, 20, "New Game: N");
        StdDraw.text(72, 20, "Quit: Q");

        StdDraw.show();

    }

    public long onTitlePage(TETile[][] world, long seed, int width, int height) {

        boolean titleOn = true;
        char key;
        while (titleOn) {
            //key = userInput.getNextChar();
            if (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();

                if ((key == 'n') || (key == 'N')) {
                    // a bunch of stuff
//                    StdDraw.setPenColor(Color.pink);
//                    StdDraw.filledSquare(5, 5, 1);
//                    StdDraw.show();
                    return seed;
                    //titleOn = false;
                } else if ((key == 'l') || (key == 'L')) {
                    //call saved
                    seed = 2468;
                    titleOn = false;
                    //  LOAD GAME //
                } else if ((key == 'q') || (key == 'Q')) {
                    System.exit(0);
                }
            }
        }
        return seed;
    }
}



//
//
//
//
//
//
//
//    public long checkN(char key, TETile[][] world, int width, int height) {
//        long userEnteredSeed = '0';
//        boolean enteringSeed = true;
//        //TERenderer ter = new TERenderer();
//        //ter.initialize(width, height);
//        if ((key == 'n') || (key == 'N')) {
//            while (enteringSeed) {
//                //ter.renderFrame(world);
//                key = userInput.getNextChar();
//                    if ((key == 's') || (key == 'S')) {
//                        enteringSeed = false;
//                        return userEnteredSeed;
//                    }
//                    else if (Character.isDigit(key)) {
//                        userEnteredSeed += key;
//                        }
//                    }
//                }
//
//
