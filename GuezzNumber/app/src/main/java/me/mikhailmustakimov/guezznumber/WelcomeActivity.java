package me.mikhailmustakimov.guezznumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    Button nextButton;
    TextView startTextView, finishTextView;
    SeekBar startSeekBar, finishSeekBar;
    static int start, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
            moveTaskToBack(true);
            onBackPressed();
            return; // add this to prevent from doing unnecessary stuffs
        }

        nextButton = (Button) findViewById(R.id.nextButton);
        startTextView = (TextView) findViewById(R.id.startTextView);
        finishTextView = (TextView) findViewById(R.id.finishTextView);
        startSeekBar = (SeekBar) findViewById(R.id.startSeekBar);
        finishSeekBar = (SeekBar) findViewById(R.id.finishSeekBar);

        start = startSeekBar.getProgress();
        finish = finishSeekBar.getProgress();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        startSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (finishSeekBar.getProgress()<seekBar.getProgress())
                    seekBar.setProgress(finishSeekBar.getProgress()-1);
                startTextView.setText(Integer.toString(seekBar.getProgress()));
                start = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        finishSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getProgress()<startSeekBar.getProgress())
                    seekBar.setProgress(startSeekBar.getProgress()+1);
                finishTextView.setText(Integer.toString(seekBar.getProgress()));
                finish = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
