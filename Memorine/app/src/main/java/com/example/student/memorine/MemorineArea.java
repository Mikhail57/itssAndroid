package com.example.student.memorine;

import android.content.Context;
import android.widget.GridView;



public class MemorineArea {

    GridView area;
    int cols, rows;
    int[] gridForGame;
    Context mContext;
    ButtonAdapter adapter;

    public MemorineArea(Context mContext, GridView area) {
        this.area = area;
        this.cols = 4;
        this.rows = 4;
        this.mContext = mContext;
    }

    public MemorineArea(Context mContext, GridView area, int cols, int rows) {
        this.area = area;
        this.cols = cols;
        this.rows = rows;
        this.mContext = mContext;
    }

    public void startGame() {
        generateArray();
        adapter = new ButtonAdapter(mContext, rows, cols);
        adapter.setButtonsHideText(gridForGame);
        area.setAdapter(adapter);
    }


    private void generateArray() {
        int rndNumb = 0;
        gridForGame = new int[cols*rows];
        for (int i=0; i<((cols*rows)/2); i++) {
            for (int j=0; j<2; j++) {
                while (gridForGame[rndNumb] != 0) {
                    rndNumb = (int) (Math.random() * 1000);
                    rndNumb = rndNumb % (cols * rows);
                }
                gridForGame[rndNumb] = i + 1;
            }
        }
    }

    private boolean containNum(int[] array, int value){
        for (int i=0; i<array.length; i++) {
            if (array[i]==value){
                return true;
            }
        }
        return false;
    }

    public void reset() {
        startGame();
    }
}
