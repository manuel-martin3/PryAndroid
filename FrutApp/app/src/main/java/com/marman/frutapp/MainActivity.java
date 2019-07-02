package com.marman.frutapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre;
    private ImageView iv_personaje;
    private TextView tv_bestscore;
    private MediaPlayer mp;

    int num_aleatorio = (int)(Math.random() * 10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        et_nombre = (EditText)findViewById(R.id.txt_nombre);
        iv_personaje = (ImageView)findViewById(R.id.iv_Personaje);
        tv_bestscore = (TextView)findViewById(R.id.txt_bestscore);

        int id=0;
        if(num_aleatorio == 0 || num_aleatorio == 10){
            id= getResources().getIdentifier("mango", "drawable", getPackageName());
        }else if(num_aleatorio == 1 || num_aleatorio == 9){
            id= getResources().getIdentifier("fresa", "drawable", getPackageName());
        }else if(num_aleatorio == 2 || num_aleatorio == 8) {
            id = getResources().getIdentifier("manzana", "drawable", getPackageName());
        }else if(num_aleatorio == 3 || num_aleatorio == 7) {
            id = getResources().getIdentifier("sandia", "drawable", getPackageName());
        }else if(num_aleatorio == 4 || num_aleatorio == 5 || num_aleatorio == 6) {
            id = getResources().getIdentifier("uva", "drawable", getPackageName());
        }
        iv_personaje.setImageResource(id);

        //conexion a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();
        Cursor consulta = BD.rawQuery(
                "select * from puntaje where score=(select max(score) from puntaje )", null);

        String temp_nombre;
        if(consulta.moveToFirst()){
            temp_nombre = consulta.getString(0);
            temp_nombre= consulta.getString(1);
            tv_bestscore.setText("Record "+temp_nombre+ " de "+ temp_nombre );
        }else {
            BD.close();
        }

        //metodo para la pista de audio
        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);
    }

    public void Jugar(View view){
        String nombre=et_nombre.getText().toString();
        if(!nombre.equals("")){
            mp.stop();
            mp.release();

            Intent intent = new Intent(this, Main2Activity_Nivel1.class);
            intent.putExtra("jugador", nombre);

            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Primero debes escribir tu nombre", Toast.LENGTH_SHORT).show();

            et_nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE  );
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    //metodo bloquea el boton retroceder
    @Override
    public void onBackPressed(){

    }

}
