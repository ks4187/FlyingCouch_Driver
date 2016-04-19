package com.tir.flyingcouch.flyingcouch_driver;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends AppCompatActivity {

    private static final int GPS_ERRORDIALOG_REQUEST = 10328;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(servicesOK()){
            //Remove for demo
            Toast.makeText(MainActivity.this, "Google Services Enabled", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_main);

        Button toNextPage = (Button) findViewById(R.id.loginButton);
        toNextPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvailableActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }
        else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(MainActivity.this, "Can't connect to Google Play Services", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
