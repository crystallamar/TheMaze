package core;

import edu.princeton.cs.algs4.In;
import org.antlr.v4.runtime.misc.Utils;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;

public class SavedGame {
    //File saveFile = new File();
    FileUtils fileUtils = new FileUtils();


    public static final String savedGameDirectory = (new File(System.getProperty("user.dir")).getPath());
    //String saveFile = files.readFile("savedGame.txt");
    //String savedGame = savedGameDirectory + saveFile;


    public void createSavedFile (TETile[][] world, ArrayList<Integer> avatarCoor, long seed, ArrayList<Integer> OGCoin1, ArrayList<Integer> OGCoin2, ArrayList<Integer> OGCoin3, int numOGCoinsPickedUp, boolean trial) {
        saveSeed(seed);
        saveAvatarCoor(avatarCoor);
        saveOGCoin1(OGCoin1);
        saveOGCoin2(OGCoin2);
        saveOGCoin3(OGCoin3);
        saveNumOGCoinsPickedUp(numOGCoinsPickedUp);



    }

    public TETile[][] openSavedFile() {
        long seed = readSeed("seed");
        World genWorld = new World(seed);
        TETile[][] world = new TETile[94][55];
        Coins coin = new Coins();

        ArrayList<Integer> avatarCoor = readAvatarCoor("avatarCoor");
        ArrayList<Integer> OGCoin1 = readOGCoin1("OGCoin1");
        ArrayList<Integer> OGCoin2 = readOGCoin2("OGCoin2");
        ArrayList<Integer> OGCoin3 = readOGCoin3("OGCoin3");
        ArrayList<Integer> firstCoinPickedUp = readFirstCoinPickedUp("firstCoinPickedUp");
        Boolean ifTrial = readIfTrial("ifTrial");

        int trialCoinsPickedUp = avatarCoor.get(2);
        int trialBool = avatarCoor.get(3);
        int numOGCoins = avatarCoor.get(4);

        //Check if OGCoins have been removed
        //Trial Num

        if (!ifTrial) {
            world = genWorld.generateSavedWorld(world, avatarCoor, OGCoin1, OGCoin2, OGCoin3, trialCoinsPickedUp, trialBool, numOGCoins);
            coin.removeCoin(world, firstCoinPickedUp.get(0), firstCoinPickedUp.get(1));
            return world;
        }
        else if (ifTrial) {
            world = genWorld.generateTrialWorld(world, seed);
            return world;
        }

        return world;

    }

    public void saveSeed(long seed) {
        String seedString = Long.toString(seed);
        FileUtils.writeFile("seed", seedString);

    }

    public long readSeed(String fileName) {
        String stringSeed = FileUtils.readFile(fileName);
        long seed = Long.parseLong(stringSeed);
        return seed;
    }

    public void saveAvatarCoor(ArrayList<Integer> avatarCoor) {
        int xCoor = avatarCoor.getFirst();
        int yCoor = avatarCoor.get(1);
        int trialCoinsPickedUp = avatarCoor.get(2);
        int trialBool = avatarCoor.get(3);
        int numOGCoins = avatarCoor.get(4);

        int OGCoin1X = avatarCoor.get(5);
        int OGCoin1Y = avatarCoor.get(6);
        int OGCoin2X = avatarCoor.get(7);
        int OGCoin2Y = avatarCoor.get(8);
        int OGCoin3X = avatarCoor.get(9);
        int OGCoin3Y = avatarCoor.get(10);

        String sXCoor = Integer.toString(xCoor);
        String sYCoor = Integer.toString(yCoor);
        String sTrialCoinsPickedUp = Integer.toString(trialCoinsPickedUp);
        String sTrialBool = Integer.toString(trialBool);
        String sNumOGCoins = Integer.toString(numOGCoins);
        String sOGCOIN1X = Integer.toString(OGCoin1X);
        String sOGCOIN1Y = Integer.toString(OGCoin1Y);
        String sOGCOIN2X = Integer.toString(OGCoin2X);
        String sOGCOIN2Y = Integer.toString(OGCoin2Y);
        String sOGCOIN3X = Integer.toString(OGCoin3X);
        String sOGCOIN3Y = Integer.toString(OGCoin3Y);

        String stringCoor = sXCoor + " " + sYCoor + " " + sTrialCoinsPickedUp + " " + sTrialBool + " " + sNumOGCoins + " " + sOGCOIN1X + " " + sOGCOIN1Y + " " + sOGCOIN2X + " " + sOGCOIN2Y + " " + sOGCOIN3X + " " + sOGCOIN3Y;


//        String stringCoor = Integer.toString(xCoor);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(yCoor);
//        stringCoor += " ";
//
//        stringCoor = Integer.toString(trialCoinsPickedUp);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(trialBool);
//        stringCoor += " ";
//
//        stringCoor = Integer.toString(numOGCoins);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(OGCoin1X);
//        stringCoor += " ";
//
//        stringCoor = Integer.toString(OGCoin1Y);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(OGCoin2X);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(OGCoin2Y);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(OGCoin3X);
//        stringCoor += " ";
//
//        stringCoor += Integer.toString(OGCoin3Y);





        FileUtils.writeFile("avatarCoor", stringCoor);
    }
    public ArrayList<Integer> readAvatarCoor(String fileName) {
        ArrayList<Integer> avatarCoor = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);

        int trialCoinsPickedUp = Integer.parseInt(stringArray[2]);
        int trialBool = Integer.parseInt(stringArray[3]);
        int numOGCoins = Integer.parseInt(stringArray[4]);

        int OGCoin1X = Integer.parseInt(stringArray[5]);
        int OGCoin1Y = Integer.parseInt(stringArray[6]);
        int OGCoin2X = Integer.parseInt(stringArray[7]);
        int OGCoin2Y = Integer.parseInt(stringArray[8]);
        int OGCoin3X = Integer.parseInt(stringArray[9]);
        int OGCoin3Y = Integer.parseInt(stringArray[10]);


        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(trialCoinsPickedUp);
        avatarCoor.add(trialBool);
        avatarCoor.add(numOGCoins);
        avatarCoor.add(OGCoin1X);
        avatarCoor.add(OGCoin1Y);
        avatarCoor.add(OGCoin2X);
        avatarCoor.add(OGCoin2Y);
        avatarCoor.add(OGCoin3X);
        avatarCoor.add(OGCoin3Y);


        return avatarCoor;
    }

    public void saveOGAvCoor(ArrayList<Integer> avatarCoor) {
        int xCoor = avatarCoor.getFirst();
        int yCoor = avatarCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGAvCoor", stringCoor);
    }
    public ArrayList<Integer> readOGAvCoor(String fileName) {
        ArrayList<Integer> OGavatarCoor = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        OGavatarCoor.add(xCoor);
        OGavatarCoor.add(yCoor);
        return OGavatarCoor;
    }

    public void saveOGCoin1(ArrayList<Integer> coin1Coor) {
        int xCoor = coin1Coor.getFirst();
        int yCoor = coin1Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin1", stringCoor);
    }
    public ArrayList<Integer> readOGCoin1(String fileName) {
        ArrayList<Integer> OGCoin1 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        OGCoin1.add(xCoor);
        OGCoin1.add(yCoor);
        return OGCoin1;
    }

    public void saveOGCoin2(ArrayList<Integer> coin2Coor) {
        int xCoor = coin2Coor.getFirst();
        int yCoor = coin2Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin2", stringCoor);
    }
    public ArrayList<Integer> readOGCoin2(String fileName) {
        ArrayList<Integer> OGCoin2 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        OGCoin2.add(xCoor);
        OGCoin2.add(yCoor);
        return OGCoin2;
    }
    public void saveOGCoin3(ArrayList<Integer> coin3Coor) {
        int xCoor = coin3Coor.getFirst();
        int yCoor = coin3Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin3", stringCoor);
    }
    public ArrayList<Integer> readOGCoin3(String fileName) {
        ArrayList<Integer> OGCoin3 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        OGCoin3.add(xCoor);
        OGCoin3.add(yCoor);
        return OGCoin3;
    }

    public void saveCoinPickedUpFirst(ArrayList<Integer> coinCoor) {
        int xCoor = coinCoor.getFirst();
        int yCoor = coinCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("FirstCoinPickedUp", stringCoor);
    }
    public ArrayList<Integer> readFirstCoinPickedUp(String fileName) {
        ArrayList<Integer> firstCoin = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        firstCoin.add(xCoor);
        firstCoin.add(yCoor);
        return firstCoin;
    }

    public void saveCoinPickedUpSecond(ArrayList<Integer> coinCoor) {
        int xCoor = coinCoor.getFirst();
        int yCoor = coinCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("SecondCoinPickedUp", stringCoor);
    }
    public ArrayList<Integer> readSecondCoinPickedUp(String fileName) {
        ArrayList<Integer> secondCoin = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        secondCoin.add(xCoor);
        secondCoin.add(yCoor);
        return secondCoin;
    }





    public void saveNumOGCoinsPickedUp(int num) {
        String stringCoor = Integer.toString(num);

        FileUtils.writeFile("numOGCoinsPickedUp", stringCoor);
    }

    public int readNumOGCoinsPickedUp(String fileName) {
        String stringNum = FileUtils.readFile(fileName);
        int numCoins = Integer.parseInt(stringNum);
        return numCoins;
    }

    public void saveTrialCoins(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {

        boolean trialRedCoin = false;
        boolean trialOrangeCoin= false;
        boolean trialYellowCoin = false;
        boolean trialGreenCoin = false;
        boolean trialBlueCoin = false;
        boolean trialVioletCoin = false;

        int trialCoin1X = 0;
        int trialCoin1Y = 0;
        int trialCoin2X = 0;
        int trialCoin2Y = 0;
        int trialCoin3X = 0;
        int trialCoin3Y = 0;
        int trialCoin4X = 0;
        int trialCoin4Y = 0;
        int trialCoin5X = 0;
        int trialCoin5Y = 0;
        int trialCoin6X = 0;
        int trialCoin6Y = 0;








        while(!trialRedCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLRED || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num1 || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(0);
                trialCoin1Y = trialCoins.get(1);
                trialRedCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLRED || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num1 || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(2);
                trialCoin1Y = trialCoins.get(3);
                trialRedCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLRED || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num1 || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(4);
                trialCoin1Y = trialCoins.get(5);
                trialRedCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLRED || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num1 || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(6);
                trialCoin1Y = trialCoins.get(7);
                trialRedCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLRED || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num1 || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(8);
                trialCoin1Y = trialCoins.get(9);
                trialRedCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLRED || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num1 || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(10);
                trialCoin1Y = trialCoins.get(11);
                trialRedCoin = true;
            }
        }




            while(!trialOrangeCoin) {
                if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLOrange || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num2 || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(0);
                    trialCoin2Y = trialCoins.get(1);
                    trialOrangeCoin = true;
                } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLOrange || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num2 || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(2);
                    trialCoin2Y = trialCoins.get(3);
                    trialOrangeCoin = true;
                } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLOrange || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num2 || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(4);
                    trialCoin2Y = trialCoins.get(5);
                    trialOrangeCoin = true;
                } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLOrange || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num2 || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(6);
                    trialCoin2Y = trialCoins.get(7);
                    trialOrangeCoin = true;
                } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLOrange || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num2 || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(8);
                    trialCoin2Y = trialCoins.get(9);
                    trialOrangeCoin = true;
                } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLOrange || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num2 || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterB) {
                    trialCoin2X = trialCoins.get(10);
                    trialCoin2Y = trialCoins.get(11);
                    trialOrangeCoin = true;
                }
            }




                while(!trialYellowCoin) {
                    if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLYellow || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num3 || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(0);
                        trialCoin3Y = trialCoins.get(1);
                        trialYellowCoin = true;
                    } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLYellow || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num3 || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(2);
                        trialCoin3Y = trialCoins.get(3);
                        trialYellowCoin = true;
                    } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLYellow || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num3 || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(4);
                        trialCoin3Y = trialCoins.get(5);
                        trialYellowCoin = true;
                    } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLYellow || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num3 || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(6);
                        trialCoin3Y = trialCoins.get(7);
                        trialYellowCoin = true;
                    } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLYellow || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num3 || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(8);
                        trialCoin3Y = trialCoins.get(9);
                        trialYellowCoin = true;
                    } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLYellow || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num3 || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterC) {
                        trialCoin3X = trialCoins.get(10);
                        trialCoin3Y = trialCoins.get(11);
                        trialYellowCoin = true;
                    }
                }




                    while(!trialGreenCoin) {
                        if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLGreen || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num4 || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(0);
                            trialCoin4Y = trialCoins.get(1);
                            trialGreenCoin = true;
                        } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLGreen || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num4 || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(2);
                            trialCoin4Y = trialCoins.get(3);
                            trialGreenCoin = true;
                        } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLGreen || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num4 || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(4);
                            trialCoin4Y = trialCoins.get(5);
                            trialGreenCoin = true;
                        } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLGreen || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num4 || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(6);
                            trialCoin4Y = trialCoins.get(7);
                            trialGreenCoin = true;
                        } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLGreen || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num4 || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(8);
                            trialCoin4Y = trialCoins.get(9);
                            trialGreenCoin = true;
                        } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLGreen || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num4 || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterD) {
                            trialCoin4X = trialCoins.get(10);
                            trialCoin4Y = trialCoins.get(11);
                            trialGreenCoin = true;
                        }
                    }




                        while(!trialBlueCoin) {
                            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLBlue || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num5 || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(0);
                                trialCoin5Y = trialCoins.get(1);
                                trialBlueCoin = true;
                            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLBlue || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num5 || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(2);
                                trialCoin5Y = trialCoins.get(3);
                                trialBlueCoin = true;
                            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLBlue || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num5 || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(4);
                                trialCoin5Y = trialCoins.get(5);
                                trialBlueCoin = true;
                            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLBlue || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num5 || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(6);
                                trialCoin5Y = trialCoins.get(7);
                                trialBlueCoin = true;
                            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLBlue || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num5 || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(8);
                                trialCoin5Y = trialCoins.get(9);
                                trialBlueCoin = true;
                            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLBlue || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num5 || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterE) {
                                trialCoin5X = trialCoins.get(10);
                                trialCoin5Y = trialCoins.get(11);
                                trialBlueCoin = true;
                            }
                        }


                            while(!trialVioletCoin) {
                                if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLViolet || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num6 ||world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(0);
                                     trialCoin6Y = trialCoins.get(1);
                                    trialVioletCoin = true;
                                }
                                else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLViolet || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num6 ||world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(2);
                                     trialCoin6Y = trialCoins.get(3);
                                    trialVioletCoin = true;
                                }
                                else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLViolet || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num6 ||world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(4);
                                     trialCoin6Y = trialCoins.get(5);
                                    trialVioletCoin = true;
                                }

                                else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLViolet || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num6 ||world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(6);
                                     trialCoin6Y = trialCoins.get(7);
                                    trialVioletCoin = true;
                                }

                                else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLViolet || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num6 ||world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(8);
                                     trialCoin6Y = trialCoins.get(9);
                                    trialVioletCoin = true;
                                }

                                else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLViolet || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num6 ||world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterF) {
                                     trialCoin6X = trialCoins.get(10);
                                     trialCoin6Y = trialCoins.get(11);
                                    trialVioletCoin = true;
                                }






                            }





//        int trialCoin1X = trialCoins.get(0);
//        int trialCoin1Y = trialCoins.get(1);
//
//        int trialCoin2X = trialCoins.get(2);
//        int trialCoin2Y = trialCoins.get(3);
//
//        int trialCoin3X = trialCoins.get(4);
//        int trialCoin3Y = trialCoins.get(5);
//
//        int trialCoin4X = trialCoins.get(6);
//        int trialCoin4Y = trialCoins.get(7);
//
//        int trialCoin5X = trialCoins.get(8);
//        int trialCoin5Y = trialCoins.get(9);
//
//        int trialCoin6X = trialCoins.get(10);
//        int trialCoin6Y = trialCoins.get(11);

        String stringTrialCoins = trialCoin1X + " " + trialCoin1Y + " " + trialCoin2X + " " + trialCoin2Y + " " + trialCoin3X + " " + trialCoin3Y + " " +trialCoin4X + " " + trialCoin4Y + " " +trialCoin5X + " " +trialCoin5Y + " " +trialCoin6X + " " +trialCoin6Y;
        FileUtils.writeFile("trialCoins", stringTrialCoins);
    }

    public ArrayList<Integer> readTrialCoinsCoor (String fileName) {
        ArrayList<Integer> trialCoins = new ArrayList<>();
        String sTrialCoins = FileUtils.readFile(fileName);
        String[] stringArray = sTrialCoins.split(" ");
        int trialCoin1X = Integer.parseInt(stringArray[0]);
        int trialCoin1Y = Integer.parseInt(stringArray[1]);
        int trialCoin2X = Integer.parseInt(stringArray[2]);
        int trialCoin2Y = Integer.parseInt(stringArray[3]);
        int trialCoin3X = Integer.parseInt(stringArray[4]);
        int trialCoin3Y = Integer.parseInt(stringArray[5]);
        int trialCoin4X = Integer.parseInt(stringArray[6]);
        int trialCoin4Y = Integer.parseInt(stringArray[7]);
        int trialCoin5X = Integer.parseInt(stringArray[8]);
        int trialCoin5Y = Integer.parseInt(stringArray[9]);
        int trialCoin6X = Integer.parseInt(stringArray[10]);
        int trialCoin6Y = Integer.parseInt(stringArray[11]);

        trialCoins.add(trialCoin1X);
        trialCoins.add(trialCoin1Y);
        trialCoins.add(trialCoin2X);
        trialCoins.add(trialCoin2Y);
        trialCoins.add(trialCoin3X);
        trialCoins.add(trialCoin3Y);
        trialCoins.add(trialCoin4X);
        trialCoins.add(trialCoin4Y);;
        trialCoins.add(trialCoin5X);
        trialCoins.add(trialCoin5Y);
        trialCoins.add(trialCoin6X);
        trialCoins.add(trialCoin6Y);



        return trialCoins;
    }

    public void saveTrialCoinsBool (boolean coin1, boolean coin2, boolean coin3, boolean coin4, boolean coin5, boolean coin6) {
        String bool1 = Boolean.toString(coin1);
        String bool2 = Boolean.toString(coin2);
        String bool3 = Boolean.toString(coin3);
        String bool4 = Boolean.toString(coin4);
        String bool5 = Boolean.toString(coin5);
        String bool6 = Boolean.toString(coin6);

        String sTrialCoinsBool = bool1 + " " + bool2 + " " + bool3 + " " + bool4 + " " + bool5 + " " + bool6;
        FileUtils.writeFile("trialCoinsBool", sTrialCoinsBool);
    }

    public ArrayList<Boolean> readTrialCoinsBool (String fileName) {
        ArrayList<Boolean> trialCoinsBool = new ArrayList<>();
        String sTrialCoins = FileUtils.readFile(fileName);
        String[] stringArray = sTrialCoins.split(" ");

        boolean trialCoin1 = Boolean.parseBoolean(stringArray[0]);
        boolean trialCoin2 = Boolean.parseBoolean(stringArray[1]);
        boolean trialCoin3 = Boolean.parseBoolean(stringArray[2]);
        boolean trialCoin4 = Boolean.parseBoolean(stringArray[3]);
        boolean trialCoin5 = Boolean.parseBoolean(stringArray[4]);
        boolean trialCoin6 = Boolean.parseBoolean(stringArray[5]);

        trialCoinsBool.add(trialCoin1);
        trialCoinsBool.add(trialCoin2);
        trialCoinsBool.add(trialCoin3);
        trialCoinsBool.add(trialCoin4);
        trialCoinsBool.add(trialCoin5);
        trialCoinsBool.add(trialCoin6);

        return trialCoinsBool;
    }

    public void saveIfTrial (boolean trial) {
        String bool = Boolean.toString(trial);
        FileUtils.writeFile("ifTrial", bool);
    }

    public Boolean readIfTrial (String fileName) {
        String sTrial = FileUtils.readFile(fileName);
        boolean trial = Boolean.parseBoolean(sTrial);
        return trial;
    }



}


