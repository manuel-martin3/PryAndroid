package com.marman.miprimerapp;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_num1);
        et2 = (EditText)findViewById(R.id.txt_num2);
        tv1 = (TextView)findViewById(R.id.txt_resultado);
        tv2 = (TextView)findViewById(R.id.txt_valResult);
        et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        Limpiar (true);
    }

    //Este método realiza la suma
    public void Sumar(View view){

        Limpiar(false);
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        if (ValidarCampos(val1,val2)){
            float num1 = Float.parseFloat(val1);
            float num2 = Float.parseFloat(val2);
            float result= num1+num2;

            tv1.setText("Resultado de la suma. ");
            tv2.setText(FormatearValor(result,"E"));
            tv2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSumar));
        }

    }


    public void Restar(View view) {

        Limpiar(false);
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        if (ValidarCampos(val1, val2)) {

        float num1 = Float.parseFloat(val1);
        float num2 = Float.parseFloat(val2);
        float result = num1 - num2;
        tv1.setText("Resultado de la resta. ");
        tv2.setText(FormatearValor(result,"E"));
        tv2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRestar));

        }
    }


    public void Multiplicar (View view) {

        Limpiar(false);
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        if (ValidarCampos(val1, val2)) {

            float num1 = Float.parseFloat(val1);
            float num2 = Float.parseFloat(val2);
            float result = num1 * num2;
            tv1.setText("Resultado de la mulltiplicación. ");
            //tv2.setText(String.valueOf(result));
            tv2.setText(FormatearValor(result,"E"));
            tv2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorMultiplicar));
        }
    }


    public void Division (View view) {

        Limpiar(false);
        String val1 = et1.getText().toString();
        String val2 = et2.getText().toString();

        if (ValidarCampos(val1, val2)) {

            float num1 = Float.parseFloat(val1);
            float num2 = Float.parseFloat(val2);
            float result = num1 / num2;
            if (num2 != 0.0) {
                tv1.setText("Resultado de la división. ");
                tv2.setText(FormatearValor(result,"F"));
                tv2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDividir));
            }
        }
    }

    public void LimpiarTodo(View view){
        Limpiar (true);
    }

    public void LimpiarResultado(View view){

        et1.setText(tv2.getText().toString());
        Limpiar (false);
        tv1.setText("Mostrará el resultado de operación ");
        tv1.setBackgroundColor(0);
        et2.setText("");
        et2.requestFocus();


    }

    public String FormatearValor(float val, String tipoVal){
        String r="";
        DecimalFormat df;
        switch (tipoVal){
            case "F":
                df = new DecimalFormat("###.##");
                r=String.valueOf(df.format(val));
                break;
            case "E":
                df = new DecimalFormat("###,###");
                r= String.valueOf(df.format(val));
                break;
        }
        return r;
    }

    public Boolean ValidarCampos(String val1, String val2){
        Boolean r = false;

        tv1.setText("Compruebe que el Primer valor y Segundo valor contengan un número.");
        if (val1.trim().length()>0 && val2.trim().length()>0){
            r = true;
            tv1.setText("Mostrará el resultado de operación");
            tv1.setBackgroundColor(0);
            tv1.setTextColor(ContextCompat.getColor(this, R.color.colorMensaje));
        }else {
            if (val1.trim().length()==0 && val2.trim().length()==0){
                tv1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorError));
                tv1.setTextColor(ContextCompat.getColor(this, R.color.colorText));
            }else {
                tv1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAlerta));
                tv1.setTextColor(ContextCompat.getColor(this, R.color.colorText));
            }
        }
        return r;
    }

    void Limpiar (Boolean todo){

        tv1.setText("El divisor debe ser mayor a 0.0 ");
        tv2.setText(String.valueOf("0"));
        tv2.setBackgroundColor(0);

        if (todo){
            et1.setText("");
            et2.setText("");
            et1.requestFocus();
            tv1.setBackgroundColor(0);
            tv1.setText("Mostrará el resultado de operación ");
        }

    }

}
