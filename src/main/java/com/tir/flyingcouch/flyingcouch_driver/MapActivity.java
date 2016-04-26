package com.tir.flyingcouch.flyingcouch_driver;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.fitness.data.MapValue;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Karan Shah on 4/11/2016.
 */
public class MapActivity extends FragmentActivity {

    Firebase mRef;
    private GoogleMap map;
    private LatLngBounds bounds;

    double ikeaLat = 40.672189, ikeaLng = -74.011347;
    double walmartLat = 40.792984, walmartLng = -74.042466;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mRef = new Firebase("https://torrid-inferno-8261.firebaseio.com/sourceDestination");

        final int positionSelected = getIntent().getIntExtra("position", 9);
        String source = null, destination = null;

        final TextView sourceText = (TextView) findViewById(R.id.sourceText);
        final TextView destinationText = (TextView) findViewById(R.id.destinationText);

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.fragment)).getMap();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String getSource = map.get("Source");
                String getDestination = map.get("Destination");

                GoogleMap map1 = ((MapFragment)getFragmentManager().findFragmentById(R.id.fragment)).getMap();;
                if(positionSelected == 0){
                    sourceText.setText("Source: " + getSource);
                    destinationText.setText("Destination: " + getDestination);

                    Geocoder gc = new Geocoder(MapActivity.this);
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocationName(getDestination, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address add = list.get(0);
                    double lat = add.getLatitude();
                    double lng = add.getLongitude();
                    LatLng to = new LatLng(lat, lng);

                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(to, 14);
                    map1.animateCamera(update);
                    map1.addMarker(new MarkerOptions().position(to).title(""));

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        if(positionSelected == 1){
            source = "Walmart";
            destination = "Upper East Side";
            LatLng from = new LatLng(walmartLat, walmartLng);
            LatLng to = new LatLng(40.7731454, -73.9664888);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(to, 14);
            map.animateCamera(update);
            map.addMarker(new MarkerOptions().position(to).title(""));

        } else if(positionSelected == 2){
            source = "IKEA Brooklyn";
            destination = "JFK Airport";
            LatLng from = new LatLng(ikeaLat, ikeaLng);
            LatLng to = new LatLng(40.6413151, -73.7803278);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(to, 14);
            map.animateCamera(update);
            map.addMarker(new MarkerOptions().position(to).title(""));


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


        Button toChat = (Button) findViewById(R.id.chatButton);
        toChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });


    }

    protected boolean isRouteDisplayed(){
        return false;
    }
}
