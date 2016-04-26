package com.tir.flyingcouch.flyingcouch_driver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Karan Shah on 4/11/2016.
 */
public class ScheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Button twoUp = (Button) findViewById(R.id.buttonUp2);
        Button threeUp = (Button) findViewById(R.id.button3Up);

        Button oneDown = (Button) findViewById(R.id.buttonDown);
        Button twoDown = (Button) findViewById(R.id.button2Down);

        final TextView listOne = (TextView) findViewById(R.id.orderFirst);
        final TextView listTwo = (TextView) findViewById(R.id.orderSecond);
        final TextView listThree = (TextView) findViewById(R.id.orderThird);


        twoUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempSwap = listTwo.getText().toString();
                listTwo.setText(listOne.getText().toString());
                listOne.setText(tempSwap);
            }
        });

        threeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempSwap = listThree.getText().toString();
                listThree.setText(listTwo.getText().toString());
                listTwo.setText(tempSwap);
            }
        });


        oneDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempSwap = listTwo.getText().toString();
                listTwo.setText(listOne.getText().toString());
                listOne.setText(tempSwap);
            }
        });

        twoDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempSwap = listThree.getText().toString();
                listThree.setText(listTwo.getText().toString());
                listTwo.setText(tempSwap);
            }
        });
    }
}
