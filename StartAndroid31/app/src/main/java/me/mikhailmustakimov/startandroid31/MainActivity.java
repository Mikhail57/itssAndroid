package me.mikhailmustakimov.startandroid31;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btnWeb, btnMap, btnCall;
    View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.buttonCall);
        btnMap = (Button) findViewById(R.id.buttonMap);
        btnWeb = (Button) findViewById(R.id.buttonWeb);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                switch(view.getId()) {
                    case R.id.buttonWeb:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://yandex.ru"));
                        startActivity(intent);
                        break;
                    case R.id.buttonMap:
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:55.754283,37.62002"));
                        startActivity(intent);
                        break;
                    case R.id.buttonCall:
                        intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:123456789"));
                        startActivity(intent);
                        break;
                }
            }
        };

        btnCall.setOnClickListener(listener);
        btnMap.setOnClickListener(listener);
        btnWeb.setOnClickListener(listener);
    }


}
