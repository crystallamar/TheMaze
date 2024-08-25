package tileengine;

import java.awt.*;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    static Color greenGrass = new Color(77, 198, 105);
    static Color mySand = new Color(236, 203, 111);
    static Color sandColor = new Color(205, 145, 7);
    static Color waterBackground = new Color(62, 144, 199);

    static Color treeBackground = new Color(15, 115, 58);
    static Color grassBackground = new Color(42, 175, 55);



    public static final TETile AVATAR = new TETile('@', Color.black, Color.red, "you", 0);
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", 1);
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black, "floor", 2);
    public static final TETile NOTHING = new TETile(' ', Color.gray, Color.gray, "nothing", 3);
    public static final TETile GRASS = new TETile('"', greenGrass, grassBackground, "grass", 4);
    public static final TETile WATER = new TETile('≈', Color.BLUE, waterBackground, "water", 5);
    public static final TETile FLOWER = new TETile('❀', Color.MAGENTA, Color.pink, "flower", 6);
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door", 7);
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door", 8);
    public static final TETile SAND = new TETile('▒', sandColor, mySand, "sand", 9);
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.lightGray, "mountain", 10);
    public static final TETile TREE = new TETile('♠', Color.green, treeBackground, "tree", 11);

    public static final TETile CELL = new TETile('█', Color.white, Color.black, "cell", 12);
    public static final TETile CELLRED = new TETile('█', Color.white, Color.RED, "cell", 12);

    public static final TETile CELLYellow = new TETile('█', Color.white, Color.YELLOW, "cell", 12);

    public static final TETile CELLOrange = new TETile('█', Color.white, Color.ORANGE, "cell", 12);
    public static final TETile CELLGreen = new TETile('█', Color.white, Color.GREEN, "cell", 12);

    public static final TETile CELLBlue = new TETile('█', Color.white, Color.BLUE, "cell", 12);
    public static final TETile CELLViolet = new TETile('█', Color.white, Color.PINK, "cell", 12);

    //public public static final TETile CELLViolet = new TETile('█', Color.white, Color.black, "cell", 12);static final TETile newGame = new TETile("New Game (N)", Color.gray, Color.white, "titleScreen", 13);
    public static final TETile num1 = new TETile('1', new Color(128, 192, 128), Color.RED, "num1", 2);

    public static final TETile num2 = new TETile('2', new Color(128, 192, 128), Color.ORANGE, "num1", 2);
    public static final TETile num3 = new TETile('3', new Color(128, 192, 128), Color.YELLOW, "num1", 2);
    public static final TETile num4 = new TETile('4', new Color(128, 192, 128), Color.GREEN, "num1", 2);

    public static final TETile num5 = new TETile('5', new Color(128, 192, 128), Color.BLUE, "num1", 2);
    public static final TETile num6 = new TETile('6', new Color(128, 192, 128), Color.PINK, "num1", 2);

    public static final TETile letterA = new TETile('A', new Color(128, 192, 128), Color.RED, "num1", 2);
    public static final TETile letterB = new TETile('B', new Color(128, 192, 128), Color.ORANGE, "num1", 2);

    public static final TETile letterC = new TETile('C', new Color(128, 192, 128), Color.YELLOW, "num1", 2);
    public static final TETile letterD = new TETile('D', new Color(128, 192, 128), Color.GREEN, "num1", 2);
    public static final TETile letterE = new TETile('E', new Color(128, 192, 128), Color.BLUE, "num1", 2);
    public static final TETile letterF = new TETile('F', new Color(128, 192, 128), Color.PINK, "num1", 2);

}


