package com.tir.flyingcouch.flyingcouch_driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.fitness.data.MapValue;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Karan Shah on 4/11/2016.
 */
public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        int positionSelected = getIntent().getIntExtra("position", 9);
        String source = null, destination = null;

        TextView sourceText = (TextView) findViewById(R.id.sourceText);
        TextView destinationText = (TextView) findViewById(R.id.destinationText);

        if(positionSelected == 0){
            source = "IKEA Brooklyn";
            destination = "Empire State Building";
        } else if(positionSelected == 1){
            source = "Walmart";
            destination = "Upper East Side";
        } else if(positionSelected == 2){
            source = "IKEA Brooklyn";
            destination = "Central Park";
        }

        sourceText.setText("Source: " + source);
        destinationText.setText("Destination: " + destination);



        Button toNextPage = (Button) findViewById(R.id.acceptButton);
        Button toPreviousPage = (Button) findViewById(R.id.declineButton);
        toNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        toPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, AvailableActivity.class);
                startActivity(intent);
            }
        });




    }

    protected boolean isRouteDisplayed(){
        return false;
    }
}
