package com.example.gametest;

class Score {
    private String name;
    private int scoreNum;

    Score(String _name, int _scoreNum) {
        name = _name;
        scoreNum = _scoreNum;
    }

    public String toString() {
        return name + "\n" + scoreNum + "\n";
    }

    int getScoreNum() {
        return scoreNum;
    }
}
