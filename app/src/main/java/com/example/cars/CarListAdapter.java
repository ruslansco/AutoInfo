package com.example.cars;

// Ahmed Alotaibi,
// An adapter that retrieves data from
// the data set and for generating View objects based on that data.
// The generated View objects are then used to populate
// any adapter view that is bound to the adapter.

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// A abstract class that implements the interface
public class CarListAdapter extends BaseAdapter{
    private final int makeId;
    private Context context;
    private List<Car> cars;

    // LayoutInflater is used to instantiate layout
    // XML file into its corresponding View objects.
    private LayoutInflater inflter;

    // Constructor the the objects.
    public CarListAdapter(Context context,
                          List<Car> cars, int makeId) {
        this.context = context;
        this.cars = cars;
        this.makeId = makeId;
        // Get the inflater in the constructor.
        inflter = (LayoutInflater.from(context));
    }
    @Override
    // Indicates to Android how many items (or rows)
    // are in the data set that will be presented in the AdapterView.
    public int getCount() {return cars.size();}
    @Override
    // get the data item associated with the item
    // (or row) from the AdapterView passed as a
    // parameter to the method.
    // This method will be used by Android to
    // fetch the appropriate data to
    // build the item/row in the AdapterView.
    public Object getItem(int i)
    {return cars.get(i);}
    @Override
    // This method returns the data set’s id
    // for a item/row position of the Adapter.
    // Typically, the data set id matches the AdapterView rows
    // so this method just returns the same value.
    public long getItemId(int i)
    {return i;}
    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @Override
    // This method creates the View (which may be
    // a single View component
    // like a TextView or a complex set of widgets
    // in a layout that displays the data for the
    // specified (by position) item/row in the Adapter.
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Inflate the xml which gives the view
        view = inflter.
                inflate(R.layout.car_layout, null);
        // Get the widget with the id name
        // which is defined in the xml
        // of the row
        TextView carName = view.
                findViewById(R.id.carName);
        ImageView carMakeImg = view.
                findViewById(R.id.carMakeImg);
        // To redirect users to another activity.
        // We need to redirect it to another activity.
        // I am thinking to extend the app and have three layouts
        // Therefore, when we have the third layout set up,
        // I will make sure to add a button for a user
        // to find the make car location such as a dealer for a car..!!
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new
                        Intent(context, MapsActivity.class);
                context.startActivity(intent);
            }
        });*/
        // Populate the row's xml with info from the item.
        carName.setText(
                "Name: \n\t "+
                cars.get(i).getName() + " ");
        //Adding the logos into the CarMakes
        carMakeImg.setImageBitmap(
                CarMakesListAdapter.
                        getBitmapFromAssets(
                                context, makeId + ".jpg"));
        // Return the generated view
        return view;
    }
    // Which mannage by the asset Manager
    public static Bitmap getBitmapFromAssets(
            Context context, String fileName) {
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