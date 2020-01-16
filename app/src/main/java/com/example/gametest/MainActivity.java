package com.example.gametest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int BOARD_ROW = 8;
    private final int BOARD_COL = 8;
    private GameButton[][] allButtons;
    private MainController cont;
    private TextView score;
    private Button reset;
    private ArrayList<Score> scores;
    private TextView scoresView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont = new MainController(this);

        GridLayout mainGrid = findViewById(R.id.mg);
        mainGrid.setColumnCount(BOARD_COL);
        mainGrid.setRowCount(BOARD_ROW);

        score = findViewById(R.id.score);
        reset = findViewById(R.id.reset);
        reset.setVisibility(View.INVISIBLE);

        reset.setOnClickListener(e -> resetClicked());

        allButtons = new GameButton[BOARD_ROW][BOARD_COL];

        for(int i = 0; i < BOARD_ROW; i++) {
            for(int j = 0; j < BOARD_COL; j++) {
                allButtons[i][j] = new GameButton(this, i, j);
                allButtons[i][j].setBackgroundColor(Color.BLUE);
                allButtons[i][j].setBackgroundColor(getButtonColor(cont.getSymbolByLocation(i, j)));
//                ShapeDrawable shapedrawable = new ShapeDrawable();
//                shapedrawable.setShape(new RectShape());
//                shapedrawable.getPaint().setColor(getButtonColor(cont.getSymbolByLocation(i, j)));
//                shapedrawable.getPaint().setStyle(Paint.Style.FILL);
//                shapedrawable.getPaint().setColor(Color.BLACK);
//                shapedrawable.getPaint().setStrokeWidth(10f);
//                shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
//                allButtons[i][j].setBackground(shapedrawable);
                allButtons[i][j].setLayoutParams(new LinearLayout.LayoutParams(
                        1225 / BOARD_ROW,
                        1225 / BOARD_COL));
                // TODO: figure out how to get parent dimensions
                allButtons[i][j].setTag((BOARD_ROW*i)+(j+1));

                allButtons[i][j].setOnClickListener(e -> {
                    System.out.println(e.getTag());
                    GameButton btn = mainGrid.findViewWithTag((e.getTag()));
                    gameButtonClicked(btn.getXLoc(), btn.getYLoc());
                });

                mainGrid.addView(allButtons[i][j]);
            }
        }
    }

    private void gameButtonClicked(int x, int y) {
        cont.gameButtonClicked(x, y);
    }

    private int getButtonColor(int x) {
        int c;
        switch (x) {
            case 0:
                c = Color.RED; // red
                break;
            case 1:
                c = Color.BLUE;
                break;
            case 2:
                c = Color.GREEN;
                break;
            case 3:
                c = Color.MAGENTA;
                break;
            case 4:
                c = Color.BLACK;
                break;
            case 5:
                c = Color.CYAN;
                break;
            case 6:
                c = Color.WHITE;
                break;
            default:
                c = Color.YELLOW;

        }

        return c;
    }

    void updateForGameEnd() {
        String temp = "No more matches.\nFinal score: " + cont.getScore();
        String placeholderName = "Computer";
        GameFileWriter gfw = new GameFileWriter(cont.getScore(), placeholderName);
        scores = gfw.getAllScores();
        displayScores();
        score.setText(temp);
        reset.setVisibility(View.VISIBLE);
    }

    void displayScores() {
        scoresView = findViewById(R.id.scoresTextView);
        scoresView.setText(scores.get(0).toString());
    }

    void resetClicked() {
        cont.resetGame();
        reset.setVisibility(View.INVISIBLE);
    }

    void updateView(int[][] grid) {
        for (int row = 0; row < BOARD_ROW; row++) {
            for (int col = 0; col < BOARD_COL; col++) {
                allButtons[row][col].setBackgroundColor(getButtonColor(grid[row][col]));
            }
        }
        String temp = "Score: " + cont.getScore();
        score.setText(temp);
    }
}
