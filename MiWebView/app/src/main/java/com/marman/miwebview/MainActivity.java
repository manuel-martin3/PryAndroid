package com.marman.miwebview;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.txt_web);

    }

    //Metodo boton ir
    public void Navegar(View view){
        if (!et1.getText().toString().equals("")){
            Intent i = new Intent(this, ActivityWeb.class);
            i.putExtra("sitioweb", et1.getText().toString());
            startActivity(i);
        }else {
            mensaje("Debe ingresar la dirección del sitio web...");

        }

    }

    void mensaje(String msg){
        Toast toast = Toast.makeText(MainActivity.this,
                msg,Toast.LENGTH_LONG);

        View view = toast.getView();

        view.getBackground().setColorFilter(ContextCompat.getColor(
                this, R.color.colorBGMensaje), PorterDuff.Mode.SRC_IN);

        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(ContextCompat.getColor(
                this, R.color.colorText));

        toast.show();
    }

}
