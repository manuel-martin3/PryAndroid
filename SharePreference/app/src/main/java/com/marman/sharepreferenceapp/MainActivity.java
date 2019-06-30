package com.marman.sharepreferenceapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.txt_email);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        et.setText(preferences.getString("mail", ""));
    }

    //Metodo del botón guardar

    public void Guardar(View view){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_Editor = preferences.edit();
        obj_Editor.putString("mail", et.getText().toString());
        obj_Editor.commit();
        finish();
    }

}
