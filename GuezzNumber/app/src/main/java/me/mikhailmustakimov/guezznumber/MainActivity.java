package me.mikhailmustakimov.guezznumber;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button lessButton, equalButton, moreButton;
    TextView numberTextView;

    int start, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessButton = (Button) findViewById(R.id.lessButton);
        moreButton = (Button) findViewById(R.id.moreButton);
        equalButton = (Button) findViewById(R.id.equalButton);
        numberTextView = (TextView) findViewById(R.id.numberTextView);

        start = WelcomeActivity.start;
        finish = WelcomeActivity.finish;

        numberTextView.setText(Integer.toString((start+finish)/2));

        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish = (start+finish)/2;
//                if ((start+finish)%2 != 0) finish++;

                numberTextView.setText(Integer.toString((start+finish)/2));
                if ((start-finish)==0) {
                    win();
                }

                Log.w("App state", "start="+start+"; finish="+finish);
            }
        });

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = (start+finish)/2;
//                if ((start+finish)%2 != 0) start--;

                numberTextView.setText(Integer.toString((start+finish)/2));
                if ((start-finish)==0) {
                    win();
                }

                Log.w("App state", "start="+start+"; finish="+finish);
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                win();
            }
        });
    }

    public void win(){
        lessButton.setEnabled(false);
        equalButton.setEnabled(false);
        moreButton.setEnabled(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Сыграть заново?")
                .setCancelable(false)
                .setNegativeButton("Конечно :D",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                .setPositiveButton("А зачем?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("Exit me", true);
                                startActivity(intent);
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
