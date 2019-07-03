package com.marman.frutapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity_Nivel2 extends AppCompatActivity {
    private TextView tv_nombre, tv_score;
    private ImageView iv_Auno, iv_Ados, iv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mp, mp_great, mp_bad;

    int score, numAleatorio_uno,numAleatorio_dos, resultado, vidas=3;
    String nombre_juagador, string_score, string_vidas;
    String numero[]={"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__nivel2);
        Toast.makeText(this, "Nivel 2 - Sumas moderadas", Toast.LENGTH_SHORT).show();

        tv_nombre = (TextView)findViewById(R.id.textView_nombre);
        tv_score = (TextView)findViewById(R.id.textView_score);
        iv_vidas=(ImageView)findViewById(R.id.imageView_vidas);
        iv_Auno=(ImageView)findViewById(R.id.imageView_NumUno);
        iv_Ados=(ImageView)findViewById(R.id.imageView_NumDos);
        et_respuesta=(EditText) findViewById(R.id.editText_resultado);

        nombre_juagador = getIntent().getStringExtra("jugador");
        tv_nombre.setText("Jugador: "+nombre_juagador);

        string_score = getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText("Score: " + score);

        string_vidas = getIntent().getStringExtra("vidas");
        vidas = Integer.parseInt(string_vidas);

        if(vidas==3){
            iv_vidas.setImageResource(R.drawable.tresvidas);
        }else if(vidas==2){
            iv_vidas.setImageResource(R.drawable.dosvidas);
        }else if(vidas==1){
            iv_vidas.setImageResource(R.drawable.unavida);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mp=MediaPlayer.create(this, R.raw.goats);
        mp.start();
        mp.setLooping(true);

        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad=MediaPlayer.create(this, R.raw.bad);

        NumAleatorio();
    }

    //Medodo para el boton comparar
    public void Comparar(View view){
        String respuesta = et_respuesta.getText().toString();

        if(!respuesta.equals("")){
            int respuesta_juagador = Integer.parseInt(respuesta);
            if(resultado == respuesta_juagador){

                mp_great.start();
                score++;
                tv_score.setText("Score: "+ score);
                et_respuesta.setText("");

                BaseDatos();
            }else {
                mp_bad.start();
                vidas--;
                BaseDatos();

                switch (vidas){
                    case 3:
                        iv_vidas.setImageResource(R.drawable.tresvidas);
                        break;
                    case 2:
                        Toast.makeText(this, "Te queda 2 manzanas", Toast.LENGTH_SHORT).show();
                        iv_vidas.setImageResource(R.drawable.dosvidas);
                        break;
                    case 1:
                        Toast.makeText(this, "Te queda 1 manzanas", Toast.LENGTH_SHORT).show();
                        iv_vidas.setImageResource(R.drawable.unavida);
                        break;
                    case 0:
                        Toast.makeText(this, "Has perdido todas tus manzanas", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        mp.stop();
                        mp.release();
                        break;
                }

                et_respuesta.setText("");
            }

            NumAleatorio();

        }else {
            Toast.makeText(this, "Escribe tu respuesta", Toast.LENGTH_SHORT).show();
        }

    }

    //metodos paara los numeros aleatorios
    public void NumAleatorio(){
        if(score<=19){

            numAleatorio_uno = (int) (Math.random()*10);
            numAleatorio_dos = (int) (Math.random()*10);

            resultado= numAleatorio_uno + numAleatorio_dos;


                for (int i=0;i<numero.length;i++){
                    int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());

                    if(numAleatorio_uno==i){
                        iv_Auno.setImageResource(id);
                    }

                    if(numAleatorio_dos==i){
                        iv_Ados.setImageResource(id);
                    }
                }


        }else {

            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);

            Intent intent = new Intent(this, Main2Activity_Nivel3.class);
            intent.putExtra("jugador", nombre_juagador);
            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);

            startActivity(intent);
            finish();
            mp.stop();
            mp.release();


        }
    }

    public void BaseDatos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase(); //apertura y escritura de a BD

        Cursor consulta = BD.rawQuery("select * from puntaje where score = (select max(score) from puntaje)", null);

        if(consulta.moveToFirst()){

            int bestScore=0;
            String temp_nombre = consulta.getString(0);
            String temp_score = consulta.getString(1);

            bestScore = Integer.parseInt(temp_score);

            if(score > bestScore){
                ContentValues modificacion = new ContentValues();
                modificacion.put("nombre", nombre_juagador);
                modificacion.put("score", score);

                BD.update("puntaje",modificacion ,"score=" + bestScore,null);
                BD.close();
            }

        }else {

            ContentValues insertar = new ContentValues();
            insertar.put("nombre", nombre_juagador);
            insertar.put("score", score);
            BD.insert("puntaje", null, insertar);
            BD.close();
        }
    }

    @Override
    public void onBackPressed(){

    }

}