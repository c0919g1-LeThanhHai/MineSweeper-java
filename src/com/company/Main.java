package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập kích thước bản đồ: ");
        int size = input.nextInt();
        checkMap(createMap(size));
    }

    public static String[][] createMap(int size) {
        String[][] map = new String[size][size];
        final int MAP_HEIGHT = map.length;
        final int MAP_WIDTH = map[0].length;
        Random rd = new Random();
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = ".  ";
            }
        }
        for (int i = 0; i < MAP_HEIGHT; i++) {
            int createBomb = rd.nextInt(2);
            int bombIndex = rd.nextInt(size);
            if (createBomb == 1) {
                map[i][bombIndex] = "*  ";
            }
        }
        return map;
    }

    public static void displayMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void checkMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                String currentCell = map[i][j];
                if (currentCell.equals(".  ")) {
                    int mineArround = 0;
                    boolean hasNeibourLeft = j - 1 >= 0;
                    boolean hasNeibourRight = j + 1 < map.length;
                    boolean hasNeibourTop = i - 1 >= 0;
                    boolean hasNeibourBottom = i + 1 < map.length;

                    boolean hasMineLeft = hasNeibourLeft && map[i][j - 1].equals("*  ");
                    if (hasMineLeft) {
                        mineArround++;
                    }
                    boolean hasMineRight = hasNeibourRight && map[i][j + 1].equals("*  ");
                    if (hasMineRight) {
                        mineArround++;
                    }
                    boolean hasMineTopLeft = hasNeibourTop && hasNeibourLeft && map[i - 1][j - 1].equals("*  ");
                    if (hasMineTopLeft) {
                        mineArround++;
                    }
                    boolean hasMineTopRight = hasNeibourTop && hasNeibourRight && map[i - 1][j + 1].equals("*  ");
                    if (hasMineTopRight) {
                        mineArround++;
                    }
                    boolean hasMineTopCenter = hasNeibourTop && map[i - 1][j].equals("*  ");
                    if (hasMineTopCenter) {
                        mineArround++;
                    }
                    boolean hasMineBottomLeft = hasNeibourBottom && hasNeibourLeft && map[i+1][j-1].equals("*  ");
                    if(hasMineBottomLeft) {
                        mineArround++;
                    }
                    boolean hasMineBottomRight = hasNeibourBottom && hasNeibourRight && map[i+1][j+1].equals("*  ");
                    if(hasMineBottomRight) {
                        mineArround++;
                    }
                    boolean hasMineBottomCenter = hasNeibourBottom && map[i+1][j].equals("*  ");
                    if(hasMineBottomCenter) {
                        mineArround++;
                    }

                    map[i][j] = String.valueOf(mineArround + "  ");
                }
            }
        }
        displayMap(map);
    }
}
