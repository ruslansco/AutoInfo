package com.example.cars;

// Ahmed Alotaibi

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Manipulate the the list view for user
    // which defines in xml
    private ListView carMakesList;
    private static List<CarMake> carMakes = new ArrayList<>();
    private static Map<Integer, List<Car>> cars = new HashMap<>();
    private List<CarData> carDataList = new ArrayList<>();

    // To read the data from the database,
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference carsReference = databaseReference.child("cars");
    @Override
    // Call, which functions once the system first creates the activity
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate
        // to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        setTitle("CarData makes");
        setContentView(R.layout.activity_main);
        carMakesList = findViewById(R.id.carMakesList);
        carMakesList.setAdapter(new CarMakesListAdapter(this, carMakes));
    }
    @Override
    protected void onStart() {
        super.onStart();
        //  This listener to detect the change in the data at a
        //  particular path (including the children at that particular path).
        carsReference.addValueEventListener(new ValueEventListener() {
            @Override

            // “onDataChange” will return the data as an object of DataSnapshot
            // and it can be used in a similar manner as depicted in the code.
            public void onDataChange(DataSnapshot dataSnapshot) {
                carDataList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CarData carData = postSnapshot.getValue(CarData.class);
                    carDataList.add(carData);
                }
                // Connect carMakelist to the adapter to allow data flew between UIs screen
                createCartList();
                carMakesList.setAdapter(new CarMakesListAdapter(MainActivity.this, carMakes));
            }
            @Override
            // To handle the error while reading data.
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    // allowing users to pick a car by make id.
    public static List<Car> getCarsByMakeId(int makeId){
        return cars.get(makeId);
    }
    private void createCartList() {
        // Store data in an array manner
        carMakes = new ArrayList<>();
        cars = new HashMap<>();
        for (CarData carData : carDataList) {
            int makeId = carData.getMakeId();
            CarMake carMake = new CarMake(makeId, carData.getMake());
            if (!carMakes.contains(carMake)) {
                carMakes.add(carMake);
            }
            List<Car> carList = new ArrayList<>();
            if (cars.get(makeId) != null) {
                carList = cars.get(makeId);
            }
            carList.add(new Car(carData.getYear(), carData.getModel()));
            cars.put(makeId, carList);
        }
    }
}