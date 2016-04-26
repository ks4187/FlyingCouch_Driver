package com.tir.flyingcouch.flyingcouch_driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Karan Shah on 4/11/2016.
 */
public class AvailableActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        listView = (ListView) findViewById(R.id.availableOrderList);

        String[] listValues = new String[]{
                "Order 1",
                "Order 2",
                "Order 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listValues);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AvailableActivity.this, MapActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }
        );
    }
}
