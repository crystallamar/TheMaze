package core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Regions {
    ArrayList<ArrayList<Integer>> region = new ArrayList<>(); // (X1,Y1)(X1,Y2)(X2,Y1)(X2,Y2)
    RandomGenerator randGen = new RandomGenerator();

    // Generate Region generates a region for any purpose (i.e. size of rooms, mountains, flowewrs, etc.)
    public ArrayList<ArrayList<Integer>> generateRegion(long seed, int startX, int startY, int xRand, int yRand, int plus) {

        int x1 = startX;
        int y1 = startY;

        int x2 = randGen.generateSizeRoomX(seed, xRand) + plus + x1;
        int y2 = randGen.generateSizeRoomY(seed, yRand) + plus + y1;

        // Now add randomized region to array to send back
        ArrayList<ArrayList<Integer>> region = new ArrayList<>(); // (X1,Y1)(X1,Y2)(X2,Y1)(X2,Y2)

        ArrayList<Integer> x1y1 = new ArrayList<>();
        //ArrayList<Integer> x1y2 = new ArrayList<>();
        //ArrayList<Integer> x2y1 = new ArrayList<>();
        ArrayList<Integer> x2y2 = new ArrayList<>();

        x1y1.add(x1);
        x1y1.add(y1);
        //x1y2.addAll(coorX1Y2(region));
        //x2y1.addAll(coorX2Y1(region));
        x2y2.add(x2);
        x2y2.add(y2);
        region.add(x1y1);
        //region.add(x1y2);
        //region.add(x2y1);
        region.add(x2y2);
        return region;
    }

    public void genRandCoor(ArrayList<Integer> region) {
        int leftBound = coorX1Y1(region).getFirst();
    }


    // Returns the coordinates of the new region
    public ArrayList<Integer> coorX1Y1(ArrayList<ArrayList<Integer>> region) {
        return region.getFirst();
    }

//    public ArrayList<Integer> coorX1Y2(ArrayList<ArrayList<Integer>> region) {
//        return region.get(1);
//    }

//    public ArrayList<Integer> coorX2Y1(ArrayList<ArrayList<Integer>> region) {
//        return region.get(2);
//    }

    public ArrayList<Integer> coorX2Y2(ArrayList<ArrayList<Integer>> region) {
        return region.getLast();
    }

    public ArrayList<Integer> worldRegion1() {
        ArrayList<Integer> reg1 = new ArrayList<>();
        int reg1Top = 40;
        int reg1Left = 5;
        int reg1Bottom = 25;
        int reg1Right = 15;

        reg1.add(reg1Top);
        reg1.add(reg1Left);
        reg1.add(reg1Bottom);
        reg1.add(reg1Right);

        return reg1; // Return Boundaries for region 1 (Top, Left, Bottom, Right)
    }

    public ArrayList<Integer> worldRegion2() {
        ArrayList<Integer> reg2 = new ArrayList<>();
        int reg2Top = 25; //22
        int reg2Left = 5; // 0
        int reg2Bottom = 5; // 0
        int reg2Right = 15; // 30

        reg2.add(reg2Top);
        reg2.add(reg2Left);
        reg2.add(reg2Bottom);
        reg2.add(reg2Right);

        return reg2;
    }

    public ArrayList<Integer> worldRegion3() {

        ArrayList<Integer> reg3 = new ArrayList<>();
        int reg3Top = 40; // 54
        int reg3Left = 16; //31
        int reg3Bottom = 25; //23
        int reg3Right = 35; // 60

        reg3.add(reg3Top);
        reg3.add(reg3Left);
        reg3.add(reg3Bottom);
        reg3.add(reg3Right);
        return reg3;
    }

    public ArrayList<Integer> worldRegion4() {

        ArrayList<Integer> reg4 = new ArrayList<>();
        int reg4Top = 25; //22
        int reg4Left = 35; //31
        int reg4Bottom = 5; //0
        int reg4Right = 35; //60

        reg4.add(reg4Top);
        reg4.add(reg4Left);
        reg4.add(reg4Bottom);
        reg4.add(reg4Right);
        return reg4;
    }

    public ArrayList<Integer> worldRegion5() {
        ArrayList<Integer> reg5 = new ArrayList<>();
        int reg5Top = 40; //54
        int reg5Left = 36; //61
        int reg5Bottom = 25; //23
        int reg5Right = 55;

        reg5.add(reg5Top);
        reg5.add(reg5Left);
        reg5.add(reg5Bottom);
        reg5.add(reg5Right);
        return reg5;
    }

    public ArrayList<Integer> worldRegion6() {
        ArrayList<Integer> reg6 = new ArrayList<>();
        int reg6Top = 25; //22
        int reg6Left = 36; //61
        int reg6Bottom = 5; //0
        int reg6Right = 55;

        reg6.add(reg6Top);
        reg6.add(reg6Left);
        reg6.add(reg6Bottom);
        reg6.add(reg6Right);
        return reg6;
    }
}
