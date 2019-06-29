package com.marman.miactivityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
    }

    // metodo de los botones
    public void Siguiente(View view){
        Intent siguiente = new Intent(this, SegundoActivity.class);
        startActivity(siguiente);
    }

}
