package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Coins {
    public ArrayList<Integer> generateCoins(TETile[][] world, Random random, Boolean trial, int numTrials) {
        //int numCoinsTotal = 10;
        SavedGame save = new SavedGame();

        //return coinCoor: Coin1(x,y), coin 2 (x,y) etc.... coin 6 (x, y)
        ArrayList<Integer> coinCoor = new ArrayList<>();
        int toSaveTrialCoins = 0;

        int tempX;
        int tempY;
        int numCoinsTotal;
        if (!trial) {
            numCoinsTotal = 3;
        }
        else {
            numCoinsTotal = 6;
            toSaveTrialCoins = 6;
        }


        while(numCoinsTotal != 0) {
            tempX = random.nextInt(1, 94);
            tempY = random.nextInt(1, 53);
            if (ifSandPlaceCoin(world, tempX, tempY, trial, numCoinsTotal, numTrials)) {
                coinCoor.add(tempX);
                coinCoor.add(tempY);
                numCoinsTotal--;
            }
        }
        if (toSaveTrialCoins == 6) {
            save.saveTrialCoins(world, coinCoor, numTrials);
        }
        return coinCoor;
    }

    public boolean ifSandPlaceCoin(TETile[][] world, int x, int y, Boolean trial, Integer numCoinsTotal, int trialNum) {
        boolean placedCoin = false;

        if (!trial) {
            if (world[x][y] == Tileset.SAND) {
                world[x][y] = Tileset.CELL;
                placedCoin = true;
            }
        }
        else if (trialNum ==1 ){
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

        }
        else if (trialNum == 2) {
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
            } if (numCoinsTotal == 5) {
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


        }
        else if (trialNum == 3) {
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
            } if (numCoinsTotal == 5) {
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
        }


        return placedCoin;
    }

    public int removeCoin(TETile[][] world, int x, int y) {
        if (world[x][y] == Tileset.CELL) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLRED) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLYellow) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLOrange) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLGreen) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLBlue) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.CELLViolet) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num1) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num2) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num3) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num4) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num5) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.num6) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterA) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterB) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterC) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterD) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterE) {
            world[x][y] = Tileset.SAND;
            return 1;
        }
        if (world[x][y] == Tileset.letterF) {
            world[x][y] = Tileset.SAND;
            return 1;
        }

        return 0;


    }

    public boolean isCoin(TETile[][] world, int x, int y) {
        if (world[x][y] == Tileset.CELL) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.CELLRED) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.CELLOrange) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.CELLYellow) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.CELLGreen) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.CELLBlue) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.CELLViolet) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.num1) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.num2) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.num3) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.num4) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.num5) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.num6) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.letterA) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.letterB) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.letterC) {
            //world[x][y] = Tileset.SAND;
            return true;
        }
        else if (world[x][y] == Tileset.letterD) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.letterE) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        else if (world[x][y] == Tileset.letterF) {
            //world[x][y] = Tileset.SAND;
            return true;
        }

        return false;
    }

    public boolean triggerEndOfGame(int numCoinsPickedUp) {
        //EndGame endGame = new EndGame();
        boolean ifGameEnd = false;
        if (numCoinsPickedUp == 3) { //CHANGE BACK TO 10
            //endGame.endGame();
            ifGameEnd = true;

        }
        return ifGameEnd;
    }

    public void generateSavedCoins(TETile[][] world, ArrayList<Integer> OGCoin1, ArrayList<Integer> OGCoin2, ArrayList<Integer> OGCoin3) {
        int OGCoin1X = OGCoin1.get(0);
        int OGCoin1Y = OGCoin1.get(1);

        int OGCoin2X = OGCoin2.get(0);
        int OGCoin2Y = OGCoin2.get(1);

        int OGCoin3X = OGCoin3.get(0);
        int OGCoin3Y = OGCoin3.get(1);
        world[OGCoin1X][OGCoin1Y] = Tileset.CELL;
        world[OGCoin2X][OGCoin2Y] = Tileset.CELL;
        world[OGCoin3X][OGCoin3Y] = Tileset.CELL;
    }

    public void placeTrialCoins(TETile[][] world, int trialNum, int coin1X, int coin1Y, int coin2X, int coin2Y, int coin3X, int coin3Y, int coin4X, int coin4Y, int coin5X, int coin5Y, int coin6X, int coin6Y) {
        if (trialNum == 1) {
            world[coin1X][coin1Y] = Tileset.CELLRED;
            world[coin2X][coin2Y] = Tileset.CELLOrange;
            world[coin3X][coin3Y] = Tileset.CELLYellow;
            world[coin4X][coin4Y] = Tileset.CELLGreen;
            world[coin5X][coin5Y] = Tileset.CELLBlue;
            world[coin6X][coin6Y] = Tileset.CELLViolet;
        }
        if (trialNum == 2) {
            world[coin1X][coin1Y] = Tileset.num1;
            world[coin2X][coin2Y] = Tileset.num2;
            world[coin3X][coin3Y] = Tileset.num3;
            world[coin4X][coin4Y] = Tileset.num4;
            world[coin5X][coin5Y] = Tileset.num5;
            world[coin6X][coin6Y] = Tileset.num6;
        }

        if (trialNum == 3) {
            world[coin1X][coin1Y] = Tileset.letterA;
            world[coin2X][coin2Y] = Tileset.letterB;
            world[coin3X][coin3Y] = Tileset.letterC;
            world[coin4X][coin4Y] = Tileset.letterD;
            world[coin5X][coin5Y] = Tileset.letterE;
            world[coin6X][coin6Y] = Tileset.letterF;
        }
    }

    public void removeTrialCoinsPickedUp(TETile[][] world, ArrayList<Integer> trialCoinCoor, boolean coin1PickedUp, boolean coin2PickedUp, boolean coin3PickedUp, boolean coin4PickedUp, boolean coin5PickedUp, boolean coin6PickedUp) {
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
