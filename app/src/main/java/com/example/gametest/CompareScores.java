package com.example.gametest;

import java.util.Comparator;

class CompareScores implements Comparator<Score> {
    public int compare(Score a, Score b) {
        return Integer.compare(a.getScoreNum(), b.getScoreNum());
    }
}
