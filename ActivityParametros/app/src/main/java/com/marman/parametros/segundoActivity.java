package com.marman.parametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class segundoActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        tv= (TextView) findViewById(R.id.tv1);

        String dat = getIntent().getStringExtra("dato");
        tv.setText("Hola "+ dat);
    }

    //Metodo de boton regresar
    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);

    }


}
