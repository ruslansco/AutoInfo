package com.example.cars;
// Ahmed Alotaibi

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.cars.account.AccountActivity;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation1);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.
                        OnNavigationItemSelectedListener() {
                    @Override
                    public boolean
                    onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_search:
                                Intent intent0 = new
                                        Intent(CarListActivity.this,
                                        MainActivity.class);
                                startActivity(intent0);
                                break;
                            case R.id.action_navigation:
                                Intent intent1 = new
                                        Intent(CarListActivity.this,
                                        MapsActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.action_chat:
                                Intent intent2 = new
                                        Intent(CarListActivity.this,
                                        ChatActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.action_account:
                                Intent intent3 = new Intent(
                                        CarListActivity.this, AccountActivity.class);
                                startActivity(intent3);
                                break;
                        }
                        return false;
                    }
                });

    }
}
