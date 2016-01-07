package me.mikhailmustakimov.dynamicbuttonsgeneration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new ButtonAdapter(this));
    }




    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;
        ArrayList<Button> buttons = new ArrayList<>();

        public ButtonAdapter(Context c) {
            mContext = c;

            for (int i=0; i<16; i++) {
                Button btn = new Button(mContext);
                btn.setText("LOL");
                btn.setOnClickListener(onClickListener);
                btn.setId(i);
                buttons.add(btn);
            }
        }

        public int getCount() {
            return buttons.size();
        }

        public Object getItem(int position) {
            return buttons.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = buttons.get(position);
            } else {
                button = (Button)convertView;
            }

            return button;
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Нажата кнопка"+view.getId(), Toast.LENGTH_SHORT).show();
        }
    };
}
