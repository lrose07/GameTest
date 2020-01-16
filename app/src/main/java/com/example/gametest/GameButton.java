package com.example.gametest;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

class GameButton extends AppCompatButton {
    private int xLoc;
    private int yLoc;

    public GameButton(Context c, int x, int y) {
        super(c);
        xLoc = x;
        yLoc = y;
    }

    int getXLoc() {
        return xLoc;
    }

    int getYLoc() {
        return yLoc;
    }

    void testMethod() {
        System.out.println("hit");
    }
}
