package com.marman.appradiobutton;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;
    private TextView tv1, tv2, tv3;
    private RadioButton rb1,rb2, rb3, rb4;
    private RadioGroup rg;
    private ImageButton btUp, btDown;
    private int id=0;
    private String signo="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1= (EditText)findViewById(R.id.txt_v1);
        et2= (EditText)findViewById(R.id.txt_v2);
        tv1= (TextView)findViewById(R.id.txt_resultaro);
        tv2= (TextView)findViewById(R.id.txt_calculo);
        tv3= (TextView)findViewById(R.id.txt_operacion);

        rb1= (RadioButton)findViewById(R.id.rb_sumar);
        rb2= (RadioButton)findViewById(R.id.rb_restar);
        rb3= (RadioButton)findViewById(R.id.rb_multiplicar);
        rb4= (RadioButton)findViewById(R.id.rb_dividir);

        rg= (RadioGroup)findViewById(R.id.rg_operaciones);
        et1.requestFocus();


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }


    //metodo para el boton calcular
    void Calcular(){
        String val1=et1.getText().toString();
        String val2=et2.getText().toString();

        float n1 = Float.parseFloat(val1);
        float n2 = Float.parseFloat(val2);
        String resul = "";

        if (rb1.isChecked()==true){
            resul = String.valueOf(n1 + n2);
            tv3.setText(rb1.getText().toString());
            signo = "+";
        }else if (rb2.isChecked()==true){
            resul = String.valueOf(n1 - n2);
            tv3.setText(rb2.getText().toString());
            signo = "-";
        }if (rb3.isChecked()==true){
            resul = String.valueOf(n1 * n2);
            tv3.setText(rb3.getText().toString());
            signo = "x";
        }else if (rb4.isChecked()==true){
            resul = String.valueOf(n1 / n2);
            tv3.setText(rb4.getText().toString());
            signo = "/";
        }

        tv2.setText(et1.getText() +" "+signo.toString()+" "+et2.getText().toString());
        tv1.setText(resul);
    }

    public int ValidarPosicion() {
        int selectedId = rg.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.rb_sumar:
                id =1;
                break;
            case R.id.rb_restar:
                id =2;
                break;
            case R.id.rb_multiplicar:
                id =3;
                break;
            case R.id.rb_dividir:
                id =4;
                break;
            default:
                id=0;
                break;
        }

        return id;
    }

    void NuevaPosicion(int v){
        switch (v) {
            case 1:
                rb1.setChecked(true);
                break;

            case 2:
                rb2.setChecked(true);
                break;

            case 3:
                rb3.setChecked(true);
                break;

            case 4:
                rb4.setChecked(true);
                break;
        }

    }

    public void LimpiarTodo(View view){
        Limpiar(true);
    }

    public void LimpiarParcial(View view){
        Limpiar(false);
    }

    void Limpiar (Boolean todo){

        tv2.setText(tv1.getText().toString());
        et1.setText(tv1.getText().toString());
        tv1.setText("");
        tv3.setText("");
        et2.setText("");
        et2.requestFocus();

        if (todo){
            et1.setText("");
            et1.requestFocus();
            tv2.setText("");
        }

    }

    public void CalcularValores(View view){
        if (ValidarCampos(et1.getText().toString(), et2.getText().toString())){
            Calcular();
        }else {
            mensaje();
        }

    }


    public void Subir(View view){

        if (ValidarCampos(et1.getText().toString(), et2.getText().toString())){
            int pos = ValidarPosicion();
            if (pos>0 && pos<=4){
                id = id-1;
            }else{
                id = 4;
            }

            NuevaPosicion(id);
            Calcular();
        }else {

            mensaje();
        }

    }

    public void Bajar(View view){

        if (ValidarCampos(et1.getText().toString(), et2.getText().toString())){
            int pos = ValidarPosicion();
            if (pos>0 && pos<=4){
                id = id+1;
            }else{
                id = 1;
            }

            NuevaPosicion(id);
            Calcular();
        }else {
            mensaje();
        }
    }

    public Boolean ValidarCampos(String n1, String n2){
        boolean r= false;
        if ( n1.length()>0 && n2.length()>0 ){
            r = true;
        }else {
            if ( n1.length()==0 && n2.length()>0 ){
                et1.requestFocus();
            }else if ( n1.length()>0 && n2.length()==0 ){
                et2.requestFocus();
            }
        }

        return r;
    }

    void mensaje(){
        Toast toast = Toast.makeText(MainActivity.this,
                "Compruebe que existan valores para empezar a calcular ",
                Toast.LENGTH_LONG);

        View view = toast.getView();

        view.getBackground().setColorFilter(ContextCompat.getColor(
                this, R.color.colorBGMensaje), PorterDuff.Mode.SRC_IN);

        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(ContextCompat.getColor(
                this, R.color.colorText));

        toast.show();
    }



}
