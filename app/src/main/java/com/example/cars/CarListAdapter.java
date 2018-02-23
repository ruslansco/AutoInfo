package com.example.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// abstract class that implements the interface

public class CarListAdapter extends BaseAdapter {
    private final int makeId;
    private Context context;
    private List<Car> cars;
    LayoutInflater inflter;

    public CarListAdapter(Context context, List<Car> cars, int makeId) {
        this.context = context;
        this.cars = cars;
        this.makeId = makeId;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    // Indicates to Android how many items (or rows)
    // are in the data set that will be presented in the AdapterView.
    public int getCount() {
        return cars.size();
    }

    @Override
    // get the data item associated with the item (or row) from the AdapterView passed as a
    // parameter to the method.
    // This method will be used by Android to fetch the appropriate data to
    // build the item/row in the AdapterView.
    public Object getItem(int i) {
        return cars.get(i);
    }

    @Override
    //This method returns the data set’s id for a item/row position of the Adapter.
    // Typically, the data set id matches the AdapterView rows
    // so this method just returns the same value.
    public long getItemId(int i) {
        return i;
    }

    @Override
    // This method creates the View (which may be a single View component
    // like a TextView or a complex set of widgets in a layout)
    // that displays the data for the specified (by position) item/row in the Adapter.
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.car_layout, null);
        TextView carYear = view.findViewById(R.id.carYear);
        TextView carName = view.findViewById(R.id.carName);
        ImageView carMakeImg = view.findViewById(R.id.carMakeImg);
        carYear.setText(cars.get(i).getYear() + "");
        carName.setText(cars.get(i).getName());
        carMakeImg.setImageBitmap(CarMakesListAdapter.getBitmapFromAssets(context, makeId + ".jpg"));
        return view;
    }
}
