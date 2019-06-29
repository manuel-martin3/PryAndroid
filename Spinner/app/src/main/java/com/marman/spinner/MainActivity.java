package com.marman.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner sp1;
    private EditText et1, et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1=(Spinner)findViewById(R.id.sp_1);
        et1=(EditText)findViewById(R.id.txt_v1);
        et2=(EditText)findViewById(R.id.txt_v2);
        tv1=(TextView)findViewById(R.id.tv_resultado);

        String[] opciones={"Sumar", "Restar", "Multiplicar", "Dividir"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_style, opciones);
        sp1.setAdapter(adapter);
    }

    //metodo del boton
     public void Calcular(View view){
        String v1 = et1.getText().toString();
        String v2 = et2.getText().toString();
        String reultado="";

        float val_1 = Float.parseFloat(v1);
        float val_2 = Float.parseFloat(v2);
        float res=0;

        String seleccion = sp1.getSelectedItem().toString();

        if (seleccion.equals("Sumar")){
            res= val_1 + val_2;
            reultado = String.valueOf(res);
         }else if (seleccion.equals("Restar")){
            res= val_1 - val_2;
            reultado = String.valueOf(res);
        }else if (seleccion.equals("Multiplicar")) {
            res = val_1 * val_2;
            reultado = String.valueOf(res);
        }else if (seleccion.equals("Dividir")) {
            if (val_2!=0){
                res = val_1 / val_2;
                reultado = String.valueOf(res);
            }else {
                Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_LONG).show();
            }
        }

        tv1.setText(reultado.toString());
     }
}
