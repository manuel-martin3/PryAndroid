package com.marman.miwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wv=(WebView)findViewById(R.id.wv1);
        String url= getIntent().getStringExtra("sitioweb");
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl("http://"+url);
    }

    //metodo cerrar
    public void Cerrar(View view){
        finish();
    }


}
