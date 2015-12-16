package com.example.student.memorine;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ButtonAdapter extends BaseAdapter {

    public String[] buttons;

    private Context mContext;

    private int rows, cols;

    private int[] hideTextArray;
    private boolean[] hasBeenClicked;

    List<Button> Buttons;

    // Gets the context so it can be used later
    public ButtonAdapter(Context c, int rows, int cols) {
        mContext = c;
        buttons = new String[rows*cols];
        hasBeenClicked = new boolean[rows*cols];
        for (int i=0; i<rows*cols; i++) {
            buttons[i] = "?";
            hasBeenClicked[i] = false;
        }
        this.rows = rows;
        this.cols = cols;
        Buttons = new ArrayList<>();
        MyOnClickListener onClickListener = new MyOnClickListener();


        for (int i=0; i<rows*cols; i++) {
            Button btn = new Button(mContext);
            btn.setPadding(8, 8, 8, 8);

            btn.setOnClickListener(onClickListener);
            Buttons.add(btn);
        }
    }

    public void setButtonsHideText(int[] array) {
        hideTextArray = array.clone();
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return buttons.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public int getRowsCount() {
        return rows;
    }

    public int getColsCount() {
        return cols;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            int square = parent.getWidth()/cols;
            if (square > parent.getHeight()/rows){
                square = parent.getHeight()/rows;
            }
            square -= 2;
            Buttons.get(position).setLayoutParams(new GridView.LayoutParams(square, square));

            btn = Buttons.get(position);
        }
        else {
            btn = (Button) convertView;
        }

        btn.setText(buttons[position]);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundResource(R.drawable.card_back);
        btn.setId(position);

        if (hasBeenClicked[btn.getId()]) {
            btn.setText(Integer.toString(hideTextArray[btn.getId()]));
        }

        return btn;
    }


    class MyOnClickListener implements View.OnClickListener
    {
        int lastClickedButtonId = -1;
        int currentClickedButtonId = -1;

        public void onClick(View v)
        {
            currentClickedButtonId = v.getId();
            Log.e("onClick", "Method has been called. lastClickedButton" + lastClickedButtonId + "; currentClickedButton=" + currentClickedButtonId);

            Buttons.get(currentClickedButtonId).setText(Integer.toString(hideTextArray[currentClickedButtonId]));

            if (lastClickedButtonId >=0) {
                if ((currentClickedButtonId != lastClickedButtonId) && (!hasBeenClicked[currentClickedButtonId])) {
                    if (hideTextArray[currentClickedButtonId] != hideTextArray[lastClickedButtonId]) {

                        Handler handler = new Handler();
                        final int tempLastClickedButtonId = lastClickedButtonId;
                        final int tempCurrentClickedButtonId = currentClickedButtonId;
                        for (int i=0; i<Buttons.size(); i++) {
                            Buttons.get(i).setClickable(false);
                        }

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("delayHandler", "delayHandler has been called. tempLastClickedButtonId="+tempLastClickedButtonId+"; tempCurrentClickedButtonId"+tempCurrentClickedButtonId);
                                Buttons.get(tempLastClickedButtonId).setText("?");
                                Buttons.get(tempCurrentClickedButtonId).setText("?");
                                for (int i=0; i<Buttons.size(); i++) {
                                    Buttons.get(i).setClickable(true);
                                }

                            }
                        }, 500);

                        lastClickedButtonId = -1;
                    } else {
                        hasBeenClicked[currentClickedButtonId] = true;
                        hasBeenClicked[lastClickedButtonId] = true;
                        Buttons.get(currentClickedButtonId).setBackgroundResource(R.drawable.solved_card_back);
                        Buttons.get(lastClickedButtonId).setBackgroundResource(R.drawable.solved_card_back);
                        buttons[currentClickedButtonId] = Integer.toString(hideTextArray[currentClickedButtonId]);
                        buttons[lastClickedButtonId] = Integer.toString(hideTextArray[lastClickedButtonId]);

                        lastClickedButtonId = -1;
                    }
                }
            } else {
                lastClickedButtonId = currentClickedButtonId;
            }


        }
    }
}
