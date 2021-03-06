package me.mikhailmustakimov.internetclicktest;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class GisService extends Service {

    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";

    public static final String TAG = "GIS_APP";

    @Override
    public void onCreate() {
        Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Служба запущена",
                Toast.LENGTH_SHORT).show();

        GisAsyncTask t = new GisAsyncTask();
        t.execute();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class GisAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {
            Intent i = new Intent(CHANNEL);
            i.putExtra(INFO, aVoid);
            sendBroadcast(i);
            Log.e(TAG, "aVoid="+aVoid);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;
            try {
                URL url = new URL("http://icomms.ru/inf/meteo.php?tid=20");
                Scanner in = new Scanner((InputStream) url.getContent());
                result = "{\"gis\":" + in.nextLine() + "}";
            } catch (Exception e) {
                result = "не удалось загрузить информацию о погоде. Error:"+e;
            }
            return result;
        }
    }
}
