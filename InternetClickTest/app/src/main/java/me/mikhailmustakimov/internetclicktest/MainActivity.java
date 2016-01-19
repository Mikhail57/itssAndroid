package me.mikhailmustakimov.internetclicktest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity {

    Button buttonGet;
    ArrayList<Weather> weathers = new ArrayList<>();
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(receiver, new IntentFilter(GisService.CHANNEL));
        final Intent intent = new Intent(MainActivity.this, GisService.class);

        buttonGet = (Button)findViewById(R.id.btnGet);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Log.e("LOLOLO", "OnClick");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, GisService.class);
        stopService(intent);
        unregisterReceiver(receiver);
    }

    protected BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String receivedFromServer = intent.getStringExtra(GisService.INFO);
                Log.e("RECEIVER", "Received from service="+receivedFromServer);
                JSONObject json = new JSONObject(receivedFromServer);
                JSONArray gisArray = json.getJSONArray("gis");

                for (int i=0; i<gisArray.length(); i++){
                    JSONObject weather = gisArray.getJSONObject(i);
                    String date = weather.getString("date");
                    int tod = weather.getInt("tod");
                    int pressure = weather.getInt("pressure");
                    int temp = weather.getInt("temp");
                    int humidity = weather.getInt("humidity");
                    String wind = weather.getString("wind");
                    String cloud = weather.getString("cloud");
                    weathers.add(new Weather(date,wind,cloud,tod,pressure,temp,humidity));
                    Log.i("WEATHER_INFO", "weather[" + i + "] = {date=" + date + ";tod=" + tod + ";pressure=" + pressure + ";temp=" + temp + ";humidity" + humidity + ";wind=" + wind + ";cloud" + cloud);
                }
                weatherAdapter = new WeatherAdapter(MainActivity.this, weathers);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(weatherAdapter);
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Произошла ошибка при обработке результатов.",
                        Toast.LENGTH_LONG).show();
                Log.e("EXCEPTION", "EXCEPTION:"+e.getMessage());
            }
        }
    };


}
