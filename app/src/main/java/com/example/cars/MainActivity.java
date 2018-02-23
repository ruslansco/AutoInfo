package com.example.cars;

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

    private ListView carMakesList;
    private static List<CarMake> carMakes = new ArrayList<>();
    private static Map<Integer, List<Car>> cars = new HashMap<>();
    private List<CarData> carDataList = new ArrayList<>();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference carsReference = databaseReference.child("cars");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CarData makes");
        setContentView(R.layout.activity_main);
        carMakesList = findViewById(R.id.carMakesList);
        carMakesList.setAdapter(new CarMakesListAdapter(this, carMakes));
    }
    @Override
    protected void onStart() {
        super.onStart();
        carsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carDataList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CarData carData = postSnapshot.getValue(CarData.class);
                    carDataList.add(carData);
                }
                createCartList();
                carMakesList.setAdapter(new CarMakesListAdapter(MainActivity.this, carMakes));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public static List<Car> getCarsByMakeId(int makeId){
        return cars.get(makeId);
    }
    private void createCartList() {
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