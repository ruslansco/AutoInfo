package com.example.cars;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// abstract class that implements the interface

public class CarMakesListAdapter extends BaseAdapter {
    private Context context;
    private List<CarMake> carMakes;
    LayoutInflater inflter;

    public CarMakesListAdapter(Context context, List<CarMake> carMakes) {
        this.context = context;
        this.carMakes = carMakes;
        inflter = (LayoutInflater.from(context));
    }
    @Override

    // Indicates to Android how many items (or rows)
    // are in the data set that will be presented in the AdapterView.
    public int getCount() {
        return carMakes.size();
    }
    @Override
    // get the data item associated with the item (or row) from the AdapterView passed as a
    // parameter to the method.
    // This method will be used by Android to fetch the appropriate data to
    // build the item/row in the AdapterView.
    public Object getItem(int i) {
        return carMakes.get(i);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.car_make_layout, null);
        TextView carMakeName = view.findViewById(R.id.carMakeName);

        carMakeName.setText(carMakes.get(i).getName());
        ImageView iv = view.findViewById(R.id.carMakeImg);
        iv.setImageBitmap(getBitmapFromAssets(context,carMakes.get(i).getId() + ".jpg"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarListActivity.class);
                intent.putExtra("makeId", carMakes.get(i).getId());
                context.startActivity(intent);
            }
        });
        return view;
    }
    public static Bitmap getBitmapFromAssets(Context context, String fileName) {
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is);
    }
}