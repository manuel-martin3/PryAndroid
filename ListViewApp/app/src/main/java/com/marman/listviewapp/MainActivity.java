package com.marman.listviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private ListView lv;

    private String nombres [] ={"Samuel", "Valentina", "Santiago", "Alejandro",
            "Valeria", "Benjamin", "Gerardo", "Carlos", "David", "Sofia"};
    private String edades [] ={"18", "25", "32", "17", "24", "20", "27", "15", "19", "23"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv1);
        lv=(ListView)findViewById(R.id.lv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_style, nombres);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("La edad de: "+ lv.getItemAtPosition(position)+" es "+edades[position]+ " años");
            }
        });


    }
}
