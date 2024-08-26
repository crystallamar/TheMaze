package core;

import java.util.ArrayList;
import java.util.Random;

public class Regions {
    //ArrayList<ArrayList<Integer>> region = new ArrayList<>(); // (X1,Y1)(X1,Y2)(X2,Y1)(X2,Y2)
    RandomGenerator randGen = new RandomGenerator();

    // Generate Region generates a region for any purpose (i.e. size of rooms, mountains, flowewrs, etc.)
    public ArrayList<Integer> generateRegion(Random random, ArrayList<Integer> worldRegion, int xRand, int yRand,
                                             int plus) {
        ArrayList<Integer> regionBuilt = new ArrayList<>();

        int x1 = randGen.generateXCoorInReg(random, worldRegion);
        int y1 = randGen.generateYCoorInReg(random, worldRegion);

        int x2 = randGen.generateSizeRoomX(random, xRand) + plus + x1;
        int y2 = randGen.generateSizeRoomY(random, yRand) + plus + y1;

        regionBuilt.add(x1);
        regionBuilt.add(y1);
        regionBuilt.add(x2);
        regionBuilt.add(y2);

        return regionBuilt;
    }

    public ArrayList<Integer> getCenterOfRegion(ArrayList<Integer> region) {
        ArrayList<Integer> center = new ArrayList<>();

        int x1 = region.get(0);
        int y1 = region.get(1);
        int x2 = region.get(2);
        int y2 = region.get(3);

        int centerX = (x2 + x1) / 2;
        int centerY = (y2 + y1) / 2;

        center.add(centerX);
        center.add(centerY);

        return center;
    }

    public ArrayList<Integer> worldRegion1() {
        ArrayList<Integer> reg1 = new ArrayList<>();
        int reg1Left = 5;
        int reg1Bottom = 20;
        int reg1Right = 25;
        int reg1Top = 40;

        reg1.add(reg1Left);
        reg1.add(reg1Bottom);
        reg1.add(reg1Right);
        reg1.add(reg1Top);

        return reg1; // Return Boundaries for region 1 (Top, Left, Bottom, Right)
    }

    public ArrayList<Integer> worldRegion2() {
        ArrayList<Integer> reg2 = new ArrayList<>();
        int reg2Left = 5; // 0
        int reg2Bottom = 5; // 0
        int reg2Right = 25; // 30
        int reg2Top = 20; //22


        reg2.add(reg2Left);
        reg2.add(reg2Bottom);
        reg2.add(reg2Right);
        reg2.add(reg2Top);

        return reg2;
    }

    public ArrayList<Integer> worldRegion3() {
        ArrayList<Integer> reg3 = new ArrayList<>();
        int reg3Left = 26; //31
        int reg3Bottom = 20; //23
        int reg3Right = 50; // 60
        int reg3Top = 40; // 54

        reg3.add(reg3Left);
        reg3.add(reg3Bottom);
        reg3.add(reg3Right);
        reg3.add(reg3Top);

        return reg3;
    }

    public ArrayList<Integer> worldRegion4() {
        ArrayList<Integer> reg4 = new ArrayList<>();
        int reg4Left = 26; //31
        int reg4Bottom = 5; //0
        int reg4Right = 50; //60
        int reg4Top = 20; //22

        reg4.add(reg4Left);
        reg4.add(reg4Bottom);
        reg4.add(reg4Right);
        reg4.add(reg4Top);

        return reg4;
    }

    public ArrayList<Integer> worldRegion5() {
        ArrayList<Integer> reg5 = new ArrayList<>();
        int reg5Left = 51; //61
        int reg5Bottom = 20; //23
        int reg5Right = 79;
        int reg5Top = 40; //54

        reg5.add(reg5Left);
        reg5.add(reg5Bottom);
        reg5.add(reg5Right);
        reg5.add(reg5Top);

        return reg5;
    }

    public ArrayList<Integer> worldRegion6() {
        ArrayList<Integer> reg6 = new ArrayList<>();
        int reg6Left = 51; //61
        int reg6Bottom = 5; //0
        int reg6Right = 79;
        int reg6Top = 20; //22

        reg6.add(reg6Left);
        reg6.add(reg6Bottom);
        reg6.add(reg6Right);
        reg6.add(reg6Top);

        return reg6;
    }


    public ArrayList<ArrayList<Integer>> getRegion() {
        ArrayList<ArrayList<Integer>> arrayOfRegions = new ArrayList<>();

        ArrayList<Integer> reg1 = worldRegion1();
        ArrayList<Integer> reg2 = worldRegion2();
        ArrayList<Integer> reg3 = worldRegion3();
        ArrayList<Integer> reg4 = worldRegion4();
        ArrayList<Integer> reg5 = worldRegion5();
        ArrayList<Integer> reg6 = worldRegion6();

        arrayOfRegions.add(reg1);
        arrayOfRegions.add(reg2);
        arrayOfRegions.add(reg3);
        arrayOfRegions.add(reg4);
        arrayOfRegions.add(reg5);
        arrayOfRegions.add(reg6);

        return arrayOfRegions;

    }

    public ArrayList<Integer> mountainLeftWall() {
        ArrayList<Integer> mountainWall = new ArrayList<>();
        int regLeft = 0;
        int regBottom = 0;
        int regRight = 5;
        int regTop = 55;

        mountainWall.add(regLeft);
        mountainWall.add(regBottom);
        mountainWall.add(regRight);
        mountainWall.add(regTop);

        return mountainWall;
    }

    public ArrayList<Integer> mountainBottomWall() {
        ArrayList<Integer> mountainWall = new ArrayList<>();
        int regLeft = 0;
        int regBottom = 0;
        int regRight = 94;
        int regTop = 5;

        mountainWall.add(regLeft);
        mountainWall.add(regBottom);
        mountainWall.add(regRight);
        mountainWall.add(regTop);

        return mountainWall;
    }

    public ArrayList<Integer> mountainRightWall() {
        ArrayList<Integer> mountainWall = new ArrayList<>();
        int regLeft = 89;
        int regBottom = 0;
        int regRight = 94;
        int regTop = 55;

        mountainWall.add(regLeft);
        mountainWall.add(regBottom);
        mountainWall.add(regRight);
        mountainWall.add(regTop);

        return mountainWall;
    }
    public ArrayList<Integer> mountainTopWall() {
        ArrayList<Integer> mountainWall = new ArrayList<>();
        int regLeft = 0;
        int regBottom = 50;
        int regRight = 94;
        int regTop = 55;

        mountainWall.add(regLeft);
        mountainWall.add(regBottom);
        mountainWall.add(regRight);
        mountainWall.add(regTop);

        return mountainWall;
    }
}
