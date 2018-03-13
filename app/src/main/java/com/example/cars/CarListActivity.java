package com.example.cars;
// Ahmed Alotaibi

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class CarListActivity extends AppCompatActivity {
    // Declare the layout xml
    private ListView carList;
    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        carList = findViewById(R.id.carList);
        //allow Up navigation with the app icon in the action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final int makeId = intent.getIntExtra(
                "makeId",
                1);
        carList.setAdapter(
                new CarListAdapter(
                        this,
                        MainActivity.
                                getCarsByMakeId(makeId),
                        makeId));
        swipeRefresh = findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // need to refresh context.!
                                swipeRefresh.setRefreshing(false);
                            }
                        }, 5);
                    }
                });
    }
    // Get back to the parent activity
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}