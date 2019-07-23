package com.example.gametest;

import java.util.Random;

class GameGrid {
    private int[][] grid;

    private MainController cont;

    GameGrid(MainController _cont) {
        cont = _cont;
        grid = new int[8][8];
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
        // top lef corner
        if (grid[0][0] == grid[0][1] || grid[0][0] == grid[1][0]) {
            return true;
        }
        // top right corner
        if (grid[0][6] == grid[0][5] || grid[0][6] == grid[1][6] || grid[0][6] == grid[0][7]) {
            return true;
        }
        //bottom left corner
        if (grid[6][0] == grid[5][0] || grid[6][0] == grid[6][1] || grid[6][0] == grid[7][1]) {
            return true;
        }
        //bottom right corner
        if (grid[7][7] == grid[7][6] || grid[7][7] == grid[6][7]) {
            return true;
        }
        for (int i = 2; i <=4; i+=2) {
            // top row
            if (grid[0][i] == grid[0][i-1] || grid[0][i] == grid[0][i+1]
                    || grid[0][i] == grid[1][i]) {
                return true;
            }
            // bottom row
            int j = i+1;
            if (grid[7][j] == grid[7][j-1] || grid[7][j] == grid[7][j+1]
                    || grid[7][j] == grid[6][j]) {
                return true;
            }
        }
        for (int j = 3; j <= 5; j+=2) {
            // left column
            if (grid[j][0] == grid[j-1][0] || grid[j][0] == grid[j+1][0]
                    || grid[j][0] == grid[j][1]) {
                return true;
            }
            // right column
            int k = j+1;
            if (grid[k][7] == grid[k-1][7] || grid[k][7] == grid[k+1][7]
                    || grid[k][7] == grid[k][6]) {
                return true;
            }
        }
        for (int i = 1; i <= 5; i+=2) {
            for (int j = 1; j <= 5; j+=2) {
                if (grid[i][j] == grid[i-1][j]
                        || grid[i][j] == grid[i][j+1]
                        || grid[i][j] == grid[i+1][j]
                        || grid[i][j] == grid[i][j-1]) {
                    return true;
                }
                int k = i+1;
                int l = j+1;
                if (grid[k][l] == grid[k-1][l]
                        || grid[k][l] == grid[k][l+1]
                        || grid[k][l] == grid[k+1][l]
                        || grid[k][l] == grid[k][l-1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
