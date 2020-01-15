package com.example.gametest;

import java.util.Random;

class GameGrid {
    private int[][] grid;

    private MainController cont;

    private final int BOARD_ROW = 8;
    private final int BOARD_COL = 8;

    GameGrid(MainController _cont) {
        cont = _cont;
        grid = new int[BOARD_ROW][BOARD_COL];
        populateGrid();
    }

    void populateGrid() {
        Random num = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = num.nextInt(8);
            }
        }
    }

    void update(int[][] matchesFound) {
        for (int[] match : matchesFound) {
            int locRow = match[0];
            int locCol = match[1];
            fallDown(locRow, locCol);
        }
        passUpdatedGrid();
    }

    private void fallDown(int row, int col) {
        while (row > 0) {
            grid[row][col] = grid[row - 1][col];
            row--;
        }
        Random newNum = new Random();
        grid[row][col] = newNum.nextInt(8);
    }

    private void passUpdatedGrid() {
        cont.getUpdatedGrid(grid);
    }

    int getValueAt(int row, int col) {
        return grid[row][col];
    }

    int[][] getGrid() {
        return grid;
    }

    boolean isThereAMatchLeft() {
        // check down
        for (int row = 0; row < BOARD_ROW - 1; row++) {
            for (int col = 0; col < BOARD_COL; col++) {
                if (grid[row][col] == grid[row+1][col]) {
                    return true;
                }
            }
        }

        // check across
        for (int row = 0; row < BOARD_ROW; row++) {
            for (int col = 0; col < BOARD_COL - 1; col++) {
                if (grid[row][col] == grid[row][col+1]) {
                    return true;
                }
            }
        }

        return false;
    }
}
