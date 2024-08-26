package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Hallways {
    RandomGenerator randGen = new RandomGenerator();

    public void generateHallways(TETile[][] world, Random random,  ArrayList<ArrayList<Integer>> centerCoor) {
        //rand.setSeed(seed);
        ArrayList<ArrayList<Integer>> sortedArrayOfCoor = new ArrayList<>();
        ArrayList<Integer> coorRoom1 = new ArrayList<>();
        ArrayList<Integer> coorRoom2 = new ArrayList<>();

        sortedArrayOfCoor.addAll(sortCenterCoor(centerCoor));



        for (int i = 1; i < sortedArrayOfCoor.size(); i++) {

            coorRoom1 = sortedArrayOfCoor.get(i - 1);
            coorRoom2 = sortedArrayOfCoor.get(i);
            connectRooms(world, coorRoom1, coorRoom2, random);
        }

        //Connect Random rooms together
        int numRandRoomsToConnect = random.nextInt(5, 10);

        for (int i = 0; i < numRandRoomsToConnect; i++) {
            int randomRoom2 = random.nextInt(0, sortedArrayOfCoor.size() - 1);
            coorRoom1 = sortedArrayOfCoor.get(i);
            coorRoom2 = sortedArrayOfCoor.get(randomRoom2);
            if (coorRoom1 == coorRoom2) {
                randomRoom2 += 1;
            }
            connectRooms(world, coorRoom1, coorRoom2, random);
            numRandRoomsToConnect--;
        }
    }



    public void moveUp(TETile[][] world, int x, int y, int rand) {
        for (int i = 0; i < rand; i++) {
            y++;
            world[x][y] = Tileset.SAND;
        }
    }

    public void moveDown(TETile[][] world, int x, int y, int rand) {
        for (int i = 0; i < rand; i++) {
            y--;
            world[x][y] = Tileset.SAND;
        }
    }

    public void moveRight(TETile[][] world, int x, int y, int rand) {
        for (int i = 0; i < rand; i++) {
            x++;
            world[x][y] = Tileset.SAND;
        }
    }

    public void moveLeft(TETile[][] world, int x, int y, int rand) {
        for (int i = 0; i < rand; i++) {
            x--;
            world[x][y] = Tileset.SAND;
        }
    }

    public ArrayList<ArrayList<Integer>> sortCenterCoor(ArrayList<ArrayList<Integer>> centerCoor) {
        ArrayList<ArrayList<Integer>> centerCoorCOPY = new ArrayList<>();
        ArrayList<Integer> xCoor = new ArrayList<>();
        ArrayList<ArrayList<Integer>> sortedArray = new ArrayList<>();

        centerCoorCOPY.addAll(centerCoor);

        // CREATE LIST OF XCOOR
        for (int i = centerCoor.size() - 1; i >= 0; i--) {
            xCoor.add(centerCoorCOPY.get(i).get(0));
            centerCoorCOPY.remove(i);
        }

        // SORT XCOOR TO SORT ARRAY BY LEFTMOST ROOMS
        Collections.sort(xCoor);

        // ADD SORTED ELEMENTS TO NEW SORTED ARRAY
        centerCoorCOPY.addAll(centerCoor);

        for (int i = 0; i < xCoor.size(); i++) {
            int currXCoor = xCoor.get(i);
            for (int m = 0; m < centerCoorCOPY.size(); m++) {
                if (currXCoor == centerCoorCOPY.get(m).get(0)) {
                    sortedArray.add(centerCoorCOPY.get(m));
                    centerCoorCOPY.remove(m);
                    m--;

                }
            }
        }
        return sortedArray;



    }

    public void connectRooms(TETile[][] world,  ArrayList<Integer> room1Coor, ArrayList<Integer> room2Coor,
                             Random random) {
        int tempX1 = room1Coor.get(0);
        int tempY1 = room1Coor.get(1);
        int tempX2 = room2Coor.get(0);
        int tempY2 = room2Coor.get(1);

        while (tempX1 != tempX2 || tempY1 != tempY2) {
            if(tempX1 != tempX2) {
                if (tempX1 > tempX2) {
                    int howMuchLeftToMoveXLeft = tempX1 - tempX2;
                    int toMoveXLeft = random.nextInt(howMuchLeftToMoveXLeft);
                    if (toMoveXLeft == 0) {
                        toMoveXLeft = 1;
                    }
                    moveLeft(world, tempX1, tempY1, toMoveXLeft);
                    tempX1 -= toMoveXLeft;

                } else {
                    int howMuchLeftToMoveXRight = tempX2 - tempX1;
                    int toMoveXRight = random.nextInt(howMuchLeftToMoveXRight);
                    if (toMoveXRight == 0) {
                        toMoveXRight = 1;
                    }
                    moveRight(world, tempX1, tempY1, toMoveXRight);
                    tempX1 += toMoveXRight;
                }
            }
            if (tempY1 != tempY2) {
                if (tempY1 > tempY2) {
                    int howMuchLeftToMoveYDown = tempY1 - tempY2;
                    int toMoveYDown = random.nextInt(howMuchLeftToMoveYDown);
                    if (toMoveYDown == 0) {
                        toMoveYDown = 1;
                    }
                    moveDown(world, tempX1, tempY1, toMoveYDown);
                    tempY1 -= toMoveYDown;
                }
                else if (tempY1 < tempY2){
                    int howMuchLeftToMoveYUp = tempY2 - tempY1;
                    int toMoveYUp = random.nextInt(howMuchLeftToMoveYUp);
                    if (toMoveYUp == 0) {
                        toMoveYUp = 1;
                    }
                    moveUp(world, tempX1, tempY1, toMoveYUp);
                    tempY1 += toMoveYUp;
                }
            }
        }
    }
}
