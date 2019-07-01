package com.marman.sdapp;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private EditText etNom, etContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = (EditText)findViewById(R.id.txt_nombre);
        etContent = (EditText)findViewById(R.id.txt_contenido);
    }

    //Metodo para el botón guardar
    public void Guardar(View view){
        String nombre = etNom.getText().toString();
        String contenido = etContent.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();
            File rutta = new File(tarjetaSD.getPath(), nombre);
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            crearArchivo.write(contenido);
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this,"Guardado crrectamente", Toast.LENGTH_SHORT).show();

            etNom.setText("");
            etContent.setText("");

        }catch (IOException e){
            Toast.makeText(this, "No se pudo guardar... " + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void Conultar(View view){
        String nombre = etNom.getText().toString();
        String contenido = etContent.getText().toString();
        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo=new BufferedReader(abrirArchivo);

            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while (linea != null){
                contenidoCompleto =  contenidoCompleto + linea + "\n";
                linea= leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();
            etContent.setText(contenidoCompleto);

        }catch (IOException e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }

}
