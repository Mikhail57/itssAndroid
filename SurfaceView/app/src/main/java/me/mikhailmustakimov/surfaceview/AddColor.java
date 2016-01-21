package me.mikhailmustakimov.surfaceview;

import android.graphics.Color;
import android.util.Log;

/**
 * Created by student on 21.01.2016.
 */
public class AddColor {

    static public int computeColor(int firstColor, int secondColor) {
        int red, green, blue;
        red = (Color.red(firstColor)+Color.red(secondColor)) / 2;
        green = (Color.green(firstColor)+Color.green(secondColor)) / 2;
        blue = (Color.blue(firstColor)+Color.blue(secondColor)) / 2;

        return Color.argb(255, red, green, blue);
    }

}
