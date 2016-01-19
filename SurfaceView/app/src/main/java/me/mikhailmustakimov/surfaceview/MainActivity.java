package me.mikhailmustakimov.surfaceview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame);
        frameLayout.addView(new DrawView(this));
    }
}
