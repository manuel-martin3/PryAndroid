package com.marman.reproductorapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir=2,posicion = 0;

    MediaPlayer vectormp[] = new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.equalizer);

        play_pause = (Button)findViewById(R.id.btn_play);
        btn_repetir=(Button)findViewById(R.id.btn_repetir);
        iv= (ImageView)findViewById(R.id.iV_portada);

        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);

    }

    public void PlayPause(View view){
        if(vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo para Detener reproduccion de audio
    public void Stop(View view){
        if(vectormp[posicion]!= null){
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create(this, R.raw.sound);
            vectormp[2] = MediaPlayer.create(this, R.raw.tea);
            posicion=0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para repetir una pista de audio
    public void Repetir(View view){
        if(repetir==1){
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir =2;
        }else {
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir =1;
        }
    }

    //Metodo para ir a una pista siguiente de audio
    public void Siguiente(View view){
        if(posicion<vectormp.length-1){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();

                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);
                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }

            }else {
                posicion++;
                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);
                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }

            }

        }else {
            Toast.makeText(this,"No hay más canciones",Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para ir a una pista siguiente de audio
    public void Anterior(View view){
        if(posicion>=1){

            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                posicion--;
                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);
                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }

                vectormp[posicion].start();

            }else {
                posicion--;
                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);
                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }
            }

        }else {
            Toast.makeText(this,"No hay más canciones",Toast.LENGTH_SHORT).show();
        }
    }

}
