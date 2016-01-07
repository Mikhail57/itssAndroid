package me.mikhailmustakimov.internetclicktest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student on 07.01.2016.
 */
public class WeatherAdapter extends BaseAdapter {

    Context context;
    LayoutInflater lInflater;
    ArrayList<Weather> objects;

    public WeatherAdapter(Context context, ArrayList<Weather> weathers) {
        this.context = context;
        objects = weathers;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.row_layout, parent, false);
        }

        Weather w = getWeather(position);

        ((TextView) view.findViewById(R.id.wind)).setText(w.wind);
        ((TextView) view.findViewById(R.id.temp)).setText(w.temp);
        ((TextView) view.findViewById(R.id.cloud)).setText(w.cloud);
        ((TextView) view.findViewById(R.id.date)).setText(w.date);
        ((TextView) view.findViewById(R.id.humidity)).setText(w.humidity);
        ((TextView) view.findViewById(R.id.tod)).setText(w.tod);
        ((TextView) view.findViewById(R.id.pressure)).setText(w.pressure);

        return view;
    }

    Weather getWeather(int position) {
        return ((Weather)getItem(position));
    }
}
