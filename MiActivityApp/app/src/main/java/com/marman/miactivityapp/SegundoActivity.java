package com.marman.miactivityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        tv = (TextView)findViewById(R.id.textView2);
    }

    public void Anterior(View view){
        Intent primer = new Intent(this, MainActivity.class);
        startActivity(primer);
    }

}
