package com.example.gametest;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

class GameButton extends AppCompatButton {
    private int xLoc;
    private int yLoc;

    public GameButton(Context c, int _x, int _y) {
        super(c);
        xLoc = _x;
        yLoc = _y;
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
