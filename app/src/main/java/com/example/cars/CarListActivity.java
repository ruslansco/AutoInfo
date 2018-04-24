package com.example.cars;
// Ahmed Alotaibi

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Objects;


public class CarListActivity extends AppCompatActivity {
    // Declare the layout xml
    private ListView carList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        //Hide titlebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        carList = findViewById(R.id.carList);
        Intent intent = getIntent();
        int makeId = intent.getIntExtra("makeId", 1);
        carList.setAdapter(new CarListAdapter(this, MainActivity.getCarsByMakeId(makeId), makeId));
    }
}
