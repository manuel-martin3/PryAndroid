package com.marman.framelayoutapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iV_1);
        btn = (Button)findViewById(R.id.btn_ocultar);
    }

    public void Ocultar(View view){
        btn.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.VISIBLE);
    }
}
