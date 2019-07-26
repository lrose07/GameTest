package com.example.gametest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class MainController {

    private GameGrid grid;
    private MainActivity view;

    private ArrayList<int[]> matchesFoundList = new ArrayList<>();
    private int[][] matchesArray;

    private int score = 0;

    MainController(MainActivity _view) {
        grid = new GameGrid(this);
        view = _view;
    }

    void gameButtonClicked(int x, int y) {
//        noMatches();
        // uncomment above line for testing game end conditions
        matchesFoundList.clear();
        checkForMatches(x, y);
        convertMatchesListToArray();
        sortByX(matchesArray);
        if (matchesArray.length > 1) {
            updateScore();
            grid.update(matchesArray);
        }
        if (!grid.isThereAMatchLeft()) {
            noMatches();
        }
    }

    private void checkForMatches(int initRow, int initCol) {
        int[] current = {initRow, initCol};
        if (isNotInListAlready(current)) {
            matchesFoundList.add(current);
            int comparisonSymbol = getSymbolByLocation(initRow, initCol);
            int upRow = initRow == 0 ? -1 : initRow - 1;
            int rightCol = initCol == 7 ? -1 : initCol + 1;
            int downRow = initRow == 7 ? -1 : initRow + 1;
            int leftCol = initCol == 0 ? -1 : initCol - 1;
            if (upRow != -1 && getSymbolByLocation(upRow, initCol) == comparisonSymbol) {
                checkForMatches(upRow, initCol);
            }
            if (rightCol != -1 && getSymbolByLocation(initRow, rightCol) == comparisonSymbol) {
                checkForMatches(initRow, rightCol);
            }
            if (downRow != -1 && getSymbolByLocation(downRow, initCol) == comparisonSymbol) {
                checkForMatches(downRow, initCol);
            }
            if (leftCol != -1 && getSymbolByLocation(initRow, leftCol) == comparisonSymbol) {
                checkForMatches(initRow, leftCol);
            }
        }
    }

    private boolean isNotInListAlready(int[] arr) {
        for (int[] set : matchesFoundList) {
            if ((set[0] == arr[0]) && (set[1] == arr[1])) {
                return false;
            }
        }
        return true;
    }

    private void convertMatchesListToArray() {
        matchesArray = new int[matchesFoundList.size()][2];
        for (int i = 0; i < matchesFoundList.size(); i++) {
            matchesArray[i][0] = matchesFoundList.get(i)[0];
            matchesArray[i][1] = matchesFoundList.get(i)[1];
        }
    }

    private void sortByX(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(a -> a[0]));
        matchesArray = arr.clone();
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }

    void getUpdatedGrid(int[][] grid) {
        view.updateView(grid);
    }

    private void noMatches() {
        System.out.println("no matches");
        view.updateForGameEnd();
    }

    private void updateScore() {
        double newScore = Math.pow(2, matchesArray.length-2) * 100;
        score = score + (int)newScore;
    }

    int getScore() {
        return score;
    }

    void resetGame() {
        // repopulate grid
        grid.populateGrid();
        // clear score
        score = 0;
        // update view to match new grid
        view.updateView(grid.getGrid());
    }
}
