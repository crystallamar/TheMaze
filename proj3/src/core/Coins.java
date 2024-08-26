package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Coins {
    public ArrayList<Integer> generateCoins(TETile[][] world, Random random, Boolean trial, int numTrials) {
        SavedGame save = new SavedGame();

        //return coinCoor: Coin1(x,y), coin 2 (x,y) etc.... coin 6 (x, y)
        ArrayList<Integer> coinCoor = new ArrayList<>();
        int toSaveTrialCoins = 0;
        int tempX;
        int tempY;
        int numCoinsTotal;
        if (!trial) {
            numCoinsTotal = 3;
        } else {
            numCoinsTotal = 6;
            toSaveTrialCoins = 6;
        }


        while (numCoinsTotal != 0) {
            tempX = random.nextInt(1, 94);
            tempY = random.nextInt(1, 53);
            if (ifSPC(world, tempX, tempY, trial, numCoinsTotal, numTrials)) {
                coinCoor.add(tempX);
                coinCoor.add(tempY);
                numCoinsTotal--;
            }
        }
        if (toSaveTrialCoins == 6) {
            save.saveTrialCoinPositionRed(world, coinCoor);
            save.saveTrialCoinsPositionOrange(world, coinCoor, numTrials);
            save.saveTrialCoinsPositionYellow(world, coinCoor, numTrials);
            save.saveTrialCoinsPositionGreen(world, coinCoor, numTrials);
            save.saveTrialCoinsPositionBlue(world, coinCoor, numTrials);
            save.saveTrialCoinsPositionViolet(world, coinCoor, numTrials);
            save.saveTrialCoin1Bool(false);
            save.saveTrialCoin2Bool(false);
            save.saveTrialCoin3Bool(false);
            save.saveTrialCoin4Bool(false);
            save.saveTrialCoin5Bool(false);
            save.saveTrialCoin6Bool(false);
            save.saveTrialCoinsBool();
        }
        return coinCoor;
    }

    public boolean ifSPC(TETile[][] world, int x, int y, boolean trial, int numCoinsTotal, int trialNum) {
        //IFSandPlaceCoin
        boolean placedCoin = false;

        if (!trial) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELL;
                placedCoin = true;
            }
        }
        else {
            if (trialNum == 1) {
                placedCoin = placeCoinIfTrial1(world, x, y, numCoinsTotal);
            } else if (trialNum == 2) {
                placedCoin = placeCoinIfTrial2(world, x, y, numCoinsTotal);
            } else if (trialNum == 3) {
                placedCoin = placeCoinIfTrial3(world, x, y, numCoinsTotal);
            }
        }
        return placedCoin;
    }

    public boolean placeCoinIfTrial1(TETile[][] world, int x, int y, int numCoinsTotal) {
        boolean placedCoin = false;
        if (numCoinsTotal == 1) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLRED;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 2) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLOrange;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 3) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLYellow;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 4) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLGreen;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 5) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLBlue;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 6) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELLViolet;
                placedCoin = true;
            }
        }
        return placedCoin;
    }
    public boolean placeCoinIfTrial2(TETile[][] world, int x, int y, int numCoinsTotal) {
        boolean placedCoin = false;
        if (numCoinsTotal == 1) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num1;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 2) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num2;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 3) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num3;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 4) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num4;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 5) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num5;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 6) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.num6;
                placedCoin = true;
            }
        }
        return placedCoin;
    }
    public boolean placeCoinIfTrial3(TETile[][] world, int x, int y, int numCoinsTotal) {
        boolean placedCoin = false;
        if (numCoinsTotal == 1) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterA;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 2) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterB;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 3) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterC;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 4) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterD;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 5) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterE;
                placedCoin = true;
            }
        }
        if (numCoinsTotal == 6) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.letterF;
                placedCoin = true;
            }
        }
        return placedCoin;
    }
    public int removeCoin(TETile[][] world, int x, int y) {
        if (world[x][y] == Tileset.CELL) {
            world[x][y] = Tileset.SAND;
            return 0;
        }
        if (world[x][y] == Tileset.CELLRED) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLOrange) {
            world[x][y] = Tileset.SAND;
            return 2;
        }
        if (world[x][y] == Tileset.CELLYellow) {
            world[x][y] = Tileset.SAND;
            return 3;
        }
        if (world[x][y] == Tileset.CELLGreen) {
            world[x][y] = Tileset.SAND;
            return 4;
        }
        if (world[x][y] == Tileset.CELLBlue) {
            world[x][y] = Tileset.SAND;
            return 5;
        }
        if (world[x][y] == Tileset.CELLViolet) {
            world[x][y] = Tileset.SAND;
            return 6;
        }
        if (world[x][y] == Tileset.num1) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num2) {
            world[x][y] = Tileset.SAND;
            return 2;
        }
        if (world[x][y] == Tileset.num3) {
            world[x][y] = Tileset.SAND;
            return 3;
        }
        if (world[x][y] == Tileset.num4) {
            world[x][y] = Tileset.SAND;
            return 4;
        }
        if (world[x][y] == Tileset.num5) {
            world[x][y] = Tileset.SAND;
            return 5;
        }
        if (world[x][y] == Tileset.num6) {
            world[x][y] = Tileset.SAND;
            return 6;
        }
        if (world[x][y] == Tileset.letterA) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterB) {
            world[x][y] = Tileset.SAND;
            return 2;
        }
        if (world[x][y] == Tileset.letterC) {
            world[x][y] = Tileset.SAND;
            return 3;
        }
        if (world[x][y] == Tileset.letterD) {
            world[x][y] = Tileset.SAND;
            return 4;
        }
        if (world[x][y] == Tileset.letterE) {
            world[x][y] = Tileset.SAND;
            return 5;
        }
        if (world[x][y] == Tileset.letterF) {
            world[x][y] = Tileset.SAND;
            return 6;
        }

        return 0;
    }

    public boolean isCoin(TETile[][] world, int x, int y) {
        if (world[x][y] == Tileset.CELL) {
            SavedGame saveGame = new SavedGame();
            saveGame.saveAVCoorWorld(x, y);
            return true;
        } else if (world[x][y] == Tileset.CELLRED) {
            return true;
        } else if (world[x][y] == Tileset.CELLOrange) {
            return true;
        } else if (world[x][y] == Tileset.CELLYellow) {
            return true;
        } else if (world[x][y] == Tileset.CELLGreen) {
            return true;
        } else if (world[x][y] == Tileset.CELLBlue) {
            return true;
        } else if (world[x][y] == Tileset.CELLViolet) {
            return true;
        } else if (world[x][y] == Tileset.num1) {
            return true;
        } else if (world[x][y] == Tileset.num2) {
            return true;
        } else if (world[x][y] == Tileset.num3) {
            return true;
        } else if (world[x][y] == Tileset.num4) {
            return true;
        } else if (world[x][y] == Tileset.num5) {
            return true;
        } else if (world[x][y] == Tileset.num6) {
            return true;
        } else if (world[x][y] == Tileset.letterA) {
            return true;
        } else if (world[x][y] == Tileset.letterB) {
            return true;
        } else if (world[x][y] == Tileset.letterC) {
            return true;
        } else if (world[x][y] == Tileset.letterD) {
            return true;
        } else if (world[x][y] == Tileset.letterE) {
            return true;
        } else {
            return world[x][y] == Tileset.letterF;
        }
    }

    public void generateSavedCoins(TETile[][] world, ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2,
                                   ArrayList<Integer> oGCoin3) {
        int oGCoin1X = oGCoin1.get(0);
        int oGCoin1Y = oGCoin1.get(1);

        int oGCoin2X = oGCoin2.get(0);
        int oGCoin2Y = oGCoin2.get(1);

        int oGCoin3X = oGCoin3.get(0);
        int oGCoin3Y = oGCoin3.get(1);
        world[oGCoin1X][oGCoin1Y] = Tileset.CELL;
        world[oGCoin2X][oGCoin2Y] = Tileset.CELL;
        world[oGCoin3X][oGCoin3Y] = Tileset.CELL;
    }

    public void placeTC(TETile[][] world, int trialNum, ArrayList<Integer> coin1Coor, ArrayList<Integer> coin2Coor,
                        ArrayList<Integer> coin3Coor, ArrayList<Integer> coin4Coor, ArrayList<Integer> coin5Coor,
                        ArrayList<Integer> coin6Coor) {
        SavedGame loadGame = new SavedGame();
        boolean place1 = loadGame.readTrialCoin1Bool("trialCoin1Bool");
        boolean place2 = loadGame.readTrialCoin2Bool("trialCoin2Bool");
        boolean place3 = loadGame.readTrialCoin3Bool("trialCoin3Bool");
        boolean place4 = loadGame.readTrialCoin4Bool("trialCoin4Bool");
        boolean place5 = loadGame.readTrialCoin5Bool("trialCoin5Bool");
        boolean place6 = loadGame.readTrialCoin6Bool("trialCoin6Bool");
        int coin1X = coin1Coor.get(0);
        int coin1Y = coin1Coor.get(1);
        int coin2X = coin2Coor.get(0);
        int coin2Y = coin2Coor.get(1);
        int coin3X = coin3Coor.get(0);
        int coin3Y = coin3Coor.get(1);
        int coin4X = coin4Coor.get(0);
        int coin4Y = coin4Coor.get(1);
        int coin5X = coin5Coor.get(0);
        int coin5Y = coin5Coor.get(1);
        int coin6X = coin6Coor.get(0);
        int coin6Y = coin6Coor.get(1);
        if (trialNum == 1) {
            if (!place1) {
                world[coin1X][coin1Y] = Tileset.CELLRED;
            }
            if (!place2) {
                world[coin2X][coin2Y] = Tileset.CELLOrange;
            }
            if (!place3) {
                world[coin3X][coin3Y] = Tileset.CELLYellow;
            }
            if (!place4) {
                world[coin4X][coin4Y] = Tileset.CELLGreen;
            }
            if (!place5) {
                world[coin5X][coin5Y] = Tileset.CELLBlue;
            }
            if (!place6) {
                world[coin6X][coin6Y] = Tileset.CELLViolet;
            }
        } else if (trialNum == 2) {
            if (!place1) {
                world[coin1X][coin1Y] = Tileset.num1;
            }
            if (!place2) {
                world[coin2X][coin2Y] = Tileset.num2;
            }
            if (!place3) {
                world[coin3X][coin3Y] = Tileset.num3;
            }
            if (!place4) {
                world[coin4X][coin4Y] = Tileset.num4;
            }
            if (!place5) {
                world[coin5X][coin5Y] = Tileset.num5;
            }
            if (!place6) {
                world[coin6X][coin6Y] = Tileset.num6;
            }
        } else if (trialNum == 3) {
            if (!place1) {
                world[coin1X][coin1Y] = Tileset.letterA;
            }
            if (!place2) {
                world[coin2X][coin2Y] = Tileset.letterB;
            }
            if (!place3) {
                world[coin3X][coin3Y] = Tileset.letterC;
            }
            if (!place4) {
                world[coin4X][coin4Y] = Tileset.letterD;
            }
            if (!place5) {
                world[coin5X][coin5Y] = Tileset.letterE;
            }
            if (!place6) {
                world[coin6X][coin6Y] = Tileset.letterF;
            }
        }
    }

    public void removeTrialCoinsPickedUp(TETile[][] world, ArrayList<Integer> trialCoinCoor, boolean coin1PickedUp,
                                         boolean coin2PickedUp, boolean coin3PickedUp, boolean coin4PickedUp,
                                         boolean coin5PickedUp, boolean coin6PickedUp) {
        Coins coin = new Coins();
        if (coin1PickedUp) {
            int oneX = trialCoinCoor.get(0);
            int oneY = trialCoinCoor.get(1);
            coin.removeCoin(world, oneX, oneY);
        }
        if (coin2PickedUp) {
            int twoX = trialCoinCoor.get(2);
            int twoY = trialCoinCoor.get(3);
            coin.removeCoin(world, twoX, twoY);
        }
        if (coin3PickedUp) {
            int threeX = trialCoinCoor.get(4);
            int threeY = trialCoinCoor.get(5);
            coin.removeCoin(world, threeX, threeY);
        }
        if (coin4PickedUp) {
            int fourX = trialCoinCoor.get(6);
            int fourY = trialCoinCoor.get(7);
            coin.removeCoin(world, fourX, fourY);
        }
        if (coin5PickedUp) {
            int fiveX = trialCoinCoor.get(8);
            int fiveY = trialCoinCoor.get(9);
            coin.removeCoin(world, fiveX, fiveY);
        }
        if (coin6PickedUp) {
            int sixX = trialCoinCoor.get(10);
            int sixY = trialCoinCoor.get(11);
            coin.removeCoin(world, sixX, sixY);
        }
    }
}
