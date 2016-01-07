package me.mikhailmustakimov.pauseandtoastapp;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    Button buttonToast, buttonPause;

    TextView tv;

    A a = new A();
    String s = new String();

    class A{
        public int count;

        public void inc(){
            this.count++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPause = (Button)findViewById(R.id.buttonPause);
        buttonToast = (Button)findViewById(R.id.buttonToast);
        tv = (TextView)findViewById(R.id.textView);

        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Он ЖИВ!!!", Toast.LENGTH_SHORT).show();
            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<5; i++) {
                    double j = Math.sqrt(i);
                    buttonPause.setText(Double.toString(j));
                    buttonPause.invalidate();
                    SystemClock.sleep(500);
                }
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a)
                {
                    while (a.count < 5) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        a.inc();
                        s += a.count;

                        tv.setText(s);
                    }
                }
            }
        });
        thread.start();

        synchronized (a)
        {
            while (a.count < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.inc();
                s += a.count;

                tv.setText(s);
            }
        }
    }
}
