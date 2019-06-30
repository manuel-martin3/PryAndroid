package com.marman.sharepreferenceagenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_nom, et_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nom=(EditText)findViewById(R.id.txt_nombre);
        et_datos=(EditText)findViewById(R.id.txt_datos);

    }
    //Metodo para el botón buscar

    public void Guardar(View view){
        String nombre = et_nom.getText().toString();
        String dato = et_datos.getText().toString();

        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString(nombre, dato);
        obj_editor.commit();

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();

        Limpiar();
    }

    public void Buscar(View view){
        String nombre = et_nom.getText().toString();
        String dato="";
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        dato = preferences.getString(nombre, "");

        if(dato.length()==0){
            Toast.makeText(this, "No se encontró ningún registro", Toast.LENGTH_SHORT).show();
        }else {
            et_datos.setText(dato);
        }
    }

    void Limpiar(){
        et_nom.setText("");
        et_datos.setText("");
    }


}
