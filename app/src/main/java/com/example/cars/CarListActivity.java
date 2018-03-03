package com.example.cars;
// Ahmed Alotaibi

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class CarListActivity extends AppCompatActivity {
    private static final String TAG = "CarListActivity";
    // Declare the layout xml
    private ListView carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_list);
        carList = findViewById(R.id.carList);
        Intent intent = getIntent();
        int makeId = intent.getIntExtra("makeId", 1);
        carList.setAdapter(new CarListAdapter(this, MainActivity.getCarsByMakeId(makeId), makeId));
    }
}
