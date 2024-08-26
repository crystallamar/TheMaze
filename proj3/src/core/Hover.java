package core;
import java.awt.*;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

public class Hover {

    public void displayTile (String area) {
        if (area.equals("Sand")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Sand");
            StdDraw.show();
        }
        if (area.equals("Mountain")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Mountain");
            StdDraw.show();
        }
        if (area.equals("Coin")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Coin");
            StdDraw.show();
        }
        if (area.equals("Grass")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Grass");
            StdDraw.show();
        }
        if (area.equals("Avatar")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Avatar");
            StdDraw.show();
        } else if (area.equals("Out of bounds")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Out of bounds");
            StdDraw.show();
        } else if (area.equals("Still need to write")) {
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(7, 52, 6, 2);
            StdDraw.setPenColor(Color.green);
            StdDraw.text(7, 52, "Still need to write");
            StdDraw.show();
        }
    }

    public void displayNothing () {
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(7, 52, 6, 2);
        StdDraw.show();
    }

    public ArrayList<Integer> mouseMoves () {
        ArrayList<Integer> mouseCoor = new ArrayList<>();

        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();

        int mouseXCoor = (int) Math.round(mouseX);
        int mouseYCoor = (int) Math.round(mouseY);

        if ((mouseXCoor <= 93) && (mouseXCoor >= 0) && (mouseYCoor <= 54) && (mouseYCoor >= 0)){
            mouseCoor.add(mouseXCoor);
            mouseCoor.add(mouseYCoor);
            return mouseCoor;
        } else {
            mouseCoor.add(0);
            mouseCoor.add(0);
            return mouseCoor;
        }
    }

    public String convertCoor(TETile[][] world, ArrayList<Integer> coor) {
        int xCoor = coor.get(0);
        int yCoor = coor.get(1);

        if (xCoor == 0 && yCoor == 0) {
            return "Out of bounds";
        } else {
            if (world[xCoor][yCoor].equals(Tileset.SAND)) {
                return "Sand";
            }
            if (world[xCoor][yCoor].equals(Tileset.MOUNTAIN)) {
                return "Mountain";
            }
            if (world[xCoor][yCoor].equals(Tileset.AVATAR)) {
                return "Avatar";
            }
            if (world[xCoor][yCoor].equals(Tileset.CELL)) {
                return "Coin";
            }
            if (world[xCoor][yCoor].equals(Tileset.GRASS)) {
                return "Grass";
            } else {
                return "Still need to write";
            }
        }
    }
}




