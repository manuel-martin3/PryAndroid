package com.marman.scrollviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //icono en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void Seleccion(View view){
        String fruta="";
        switch (view.getId()){
            case R.id.bananas:
                fruta= "bananas"; break;
            case R.id.cerezas:
                fruta= "cerezas"; break;
            case R.id.frambuezas:
                fruta= "frambuesas"; break;
            case R.id.fresas:
                fruta= "fresas"; break;
            case R.id.kiwi:
                fruta= "kiwis"; break;
            case R.id.mangos:
                fruta= "mangos"; break;
            case R.id.manzanas:
                fruta= "manzanas"; break;
            case R.id.melon:
                fruta= "melones"; break;
            case R.id.naranjas:
                fruta= "naranjas"; break;
            case R.id.peras:
                fruta= "peras"; break;
            case R.id.pina:
                fruta= "piñas"; break;
            case R.id.sandia:
                fruta= "sandias"; break;
            case R.id.uvas:
                fruta= "uvas"; break;
            case R.id.zarzamora:
                fruta= "zarzamoras"; break;
        }

        Toast.makeText(this, "Estas son "+fruta, Toast.LENGTH_SHORT).show();
    }

}
