package com.example.gametest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    private final int BOARD_ROW = 8;
    private final int BOARD_COL = 8;
    private GameButton[][] allButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout mainGrid = findViewById(R.id.mg);
        mainGrid.setColumnCount(BOARD_COL);
        mainGrid.setRowCount(BOARD_ROW);

        allButtons = new GameButton[BOARD_ROW][BOARD_COL];

        for(int i = 0; i < BOARD_ROW; i++) {
            for(int j = 0; j < BOARD_COL; j++) {
                allButtons[i][j] = new GameButton(this, i, j);
                allButtons[i][j].setBackgroundColor(Color.BLUE);
                allButtons[i][j].setTag((8*i)+(j+1));

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
        System.out.println("clicked");
        allButtons[x][y].setBackgroundColor(Color.RED);
    }
}
