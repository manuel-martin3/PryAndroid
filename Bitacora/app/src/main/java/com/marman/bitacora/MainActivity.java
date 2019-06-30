package com.marman.bitacora;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= (EditText)findViewById(R.id.txt_bitacora);
        String[] setarchivos = fileList();
        String nomarchino="Bitacora.txt";

        if(ArchivoExiste(setarchivos, nomarchino)){
            try {
                InputStreamReader is_archivo = new InputStreamReader(openFileInput(nomarchino));
                BufferedReader br = new BufferedReader(is_archivo);
                String linea = br.readLine();
                String bitacoraCompleta = "";

                while (linea!= null){
                    bitacoraCompleta = bitacoraCompleta +" "+linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                is_archivo.close();
                et.setText(bitacoraCompleta);

            }catch (Exception e){

            }
        }
    }

    private boolean ArchivoExiste(String archivos [], String nombreArchivo){
        boolean res = false;

        for (int i=0; i< archivos.length; i++ ){
            if(nombreArchivo.equals(archivos[i])){
                  res=true;
            }
        }

        return res;
    }

    public void Guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(et.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }

        Toast.makeText(this, "Bitacora generada corectamente...",Toast.LENGTH_SHORT).show();
        finish();
    }

}
