package com.marman.gpsapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btgps;
    private TextView tv_ubi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_ubi=(TextView)findViewById(R.id.tv_ubicacion);
        btgps=(Button)findViewById(R.id.btn_gps);

        btgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager location_Manager = (LocationManager)  MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener location_Listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        tv_ubi.setText(""+location.getLatitude()+" "+location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {}

                    @Override
                    public void onProviderEnabled(String provider) {}

                    @Override
                    public void onProviderDisabled(String provider) {}
                };

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (location_Manager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)){
                    location_Manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, location_Listener);
                }else{
                    if (location_Manager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
                        location_Manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, location_Listener);
                }
            }
        });

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck== PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                //hacer algo

            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

            }
        }
    }
}
