package com.example.gametest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class GameFileWriter {

    private BufferedWriter writer;
    private File file = new File("gameScores.txt");

    private ArrayList<Score> allScores;

    GameFileWriter(int score, String name) {
        extractPreviousScores();
        addScore(score, name);
        organizeScores();
        writeScores();
    }

    private void extractPreviousScores() {
        allScores = new ArrayList<>();
        File file = new File("gameScores.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String tempName = sc.next();
                int tempScoreNum = Integer.parseInt(sc.next());
                Score tempScore = new Score(tempName, tempScoreNum);
                allScores.add(tempScore);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void addScore(int score, String name) {
        allScores.add(new Score(name, score));
    }


    private void writeScores() {
        file = new File("gameScores.txt");
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            for (Score s : allScores) {
                writer.write(s.toString());
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Something strange has happened");
            }
        }
    }

    private void organizeScores() {
        Score[] arrayScores = new Score[allScores.size()];
        Score[] allScoresArray = allScores.toArray(new Score[allScores.size()]);
        System.arraycopy(allScoresArray, 0, arrayScores, 0, allScores.size());
        Arrays.sort(arrayScores, new CompareScores());

        allScores.clear();

        for (Score s : arrayScores) {
            allScores.add(s);
        }
    }

    ArrayList<Score> getAllScores() {
        return allScores;
    }
}
