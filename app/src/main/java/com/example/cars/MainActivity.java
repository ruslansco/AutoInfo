package com.example.cars;

// Ahmed Alotaibi
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cars.account.AccountActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cars.R.id.swiperefresh;

public class MainActivity extends AppCompatActivity {
    // Manipulate the the list view for user
    // which defines in xml
    private SwipeRefreshLayout swipeRefresh;
    private ListView carMakesList;
    private static List <CarMake> carMakes = new ArrayList <>();
    @SuppressLint("UseSparseArrays")
    private static Map <Integer, List <Car>> cars = new HashMap <>();
    private ImageButton settings;
    private List <CarData> carDataList = new ArrayList <>();
    // To read the data from the database,
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReference();
    DatabaseReference carsReference =
            databaseReference.child("cars");
    @Override
    // Call, which functions once the system first creates the activity
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate
        // to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_profile);
        setContentView(R.layout.activity_main);
        carMakesList = findViewById(R.id.carMakesList);
        carMakesList.setAdapter(new
                CarMakesListAdapter(this,
                carMakes));
        swipeRefresh = findViewById(swiperefresh);
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
        BottomNavigationView bottomNavigationView =
                findViewById(R.id.navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_search:
                                break;
                            case R.id.action_navigation:
                                Intent intent0 = new
                                        Intent(MainActivity.this,
                                        MapsActivity.class);
                                startActivity(intent0);
                                break;
                            case R.id.action_account:
                                Intent intent1 = new
                                        Intent(MainActivity.this,
                                        AccountActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        return false;
                    }
                });
        settings = (ImageButton) findViewById(R.id.ed_profile);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
            }
        });
    }
    // Listen for option item selections
    // so that we receive a notification
    // when the user requests a refresh by
    // selecting the refresh action bar item.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.swiperefresh:
                swipeRefresh.setRefreshing(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //  This listener to detect the change in the data at a
        //  particular path (including the children at that particular path).
        carsReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    // “onDataChange” will return the data as an object of DataSnapshot
                    // and it can be used in a similar manner as depicted in the code.
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        carDataList = new ArrayList <>();
                        for (DataSnapshot postSnapshot :
                                dataSnapshot.getChildren()) {
                            CarData carData = postSnapshot.
                                    getValue(CarData.class);
                            carDataList.add(carData);
                        }
                        // a list to connect carMakelist to the
                        // adapter to allow data flew between UIs screen
                        createCartList();
                        carMakesList.setAdapter(new
                                CarMakesListAdapter(
                                MainActivity.this,
                                carMakes));
                    }
                    @Override
                    // To handle the error while reading data.
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this,
                                "Database is not responding",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // allowing users to pick a car by make id.
    public static List <Car> getCarsByMakeId(int makeId)
    {return cars.get(makeId);}
    @SuppressLint("UseSparseArrays")
    private void createCartList() {
        // Store data in an array manner
        carMakes = new ArrayList <>();
        cars = new HashMap <>();
        for (CarData carData : carDataList) {
            int makeId = carData.getMakeId();
            CarMake carMake = new
                    CarMake(makeId,
                    carData.getMake());
            if (! carMakes.contains(carMake)) {
                carMakes.add(carMake);
            }
            List <Car> carList = new
                    ArrayList <>();
            if (cars.get(makeId) != null) {
                carList = cars.get(makeId);
            }
            //added carId so late we can use
            // it for the car's picture maybe..!!
            carList.add(new Car
                    (carData.getPrice(),
                            carData.getCarId(),
                            carData.getYear(),
                            carData.getModel(),
                            carData.getDescription()));
            cars.put(makeId, carList);
        }
    }
}