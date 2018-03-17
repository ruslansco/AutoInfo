package com.example.cars;

// Ahmed Alotaibi

//import android.content.pm.PackageManager;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/* import android.support.v4.content.ContextCompat; */

public class MapsActivity extends AppCompatActivity
        implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private static final LatLng Acura = new
            LatLng(42.764911, -73.756344);
    private static final LatLng AmGeneral = new
            LatLng(41.682999, -86.244667);
    private static final LatLng Ironhorse = new
            LatLng(32.747050, -95.566483);

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //allow Up navigation with the app icon in the action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Marker
        // Later on, this marker should
        // be used for car makers locations
        mMap.animateCamera(
                CameraUpdateFactory.zoomIn());
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(Acura)
                .title("Acura Dealer")
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.
                                HUE_AZURE))
                .alpha(0.7f)
                .draggable(true));
        marker.setTag(0);
        mMap.animateCamera(
                CameraUpdateFactory.zoomIn());
        marker = mMap.addMarker(new MarkerOptions()
                .position(AmGeneral)
                .title("Am General Dealer")
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.
                                HUE_AZURE))
                .alpha(0.7f)
                .draggable(true));
        marker.setTag(0);
        mMap.animateCamera(
                CameraUpdateFactory.zoomIn());
        marker = mMap.addMarker(new MarkerOptions()
                .position(Ironhorse)
                .title("Ironhorse Dealer")
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.
                                HUE_AZURE))
                .alpha(0.7f)
                .draggable(true));
        marker.setTag(0);
        mMap.setOnMarkerClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }
    // Allow user to choice different types of Maps
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
   @Override
    public boolean onMarkerClick(Marker marker) {
        //Integer clickCount = (Integer) marker.getTag();
        Toast.makeText(this, marker.getTitle() +
        ": click on the icon in the " +
                "down-right below to get the direction",
                Toast.LENGTH_SHORT).show();
       try {
           Thread.sleep(10);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       return false;
    }
    // Get back to the  parent activity
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