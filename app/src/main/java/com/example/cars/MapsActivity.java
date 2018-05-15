package com.example.cars;

// Ahmed Alotaibi

//import android.content.pm.PackageManager;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cars.account.AccountActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    ArrayList<MarkerData> markersArray = new ArrayList <>();
    MarkerData location1 =
            new MarkerData(42.764297, -73.756150, "Northeast Acura dealer.");
    MarkerData location2 =
            new MarkerData(41.676863, -86.244596, "Am General LLC dealer.");
    MarkerData location3 =
            new MarkerData(39.768466, -75.105769, "IronHorse dealer.");
    MarkerData location4 =
            new MarkerData(44.115604, -70.616428, "ARCTIC CAT dealer");
    MarkerData location5 =
            new MarkerData(44.440062, -73.210947, "AUDI dealer");
    MarkerData location6 =
            new MarkerData(42.492769,-71.347019, "BENTLEY dealer");
    MarkerData location7 =
            new MarkerData(42.678813,-70.880100, "BIMOTS dealer");
    MarkerData location8 =
            new MarkerData(42.213068,-71.186591, "FERRARI cars dealer");
    MarkerData location9 =
            new MarkerData(42.316730,-71.225043, "Ford dealer");
    MarkerData location10 =
            new MarkerData(42.363931,-71.131659, "BMW dealer");
    MarkerData location11 = new MarkerData(38.40691,-122.716454, "Smothers and White, Inc.");
    MarkerData location12 = new MarkerData(34.435009,-119.746902, "SB Automotive, LP");
    MarkerData location13 = new MarkerData(33.811184,-118.163918, "Long Beach Eurocars, LLC");
    MarkerData location14 = new MarkerData(34.151691,-118.6539, "Sonic Calabasas M, Inc.");
    MarkerData location15 = new MarkerData(34.07172,-117.902496, "PMB Motorcars, LLC");
    MarkerData location16 = new MarkerData(36.59502,-121.8908, "Von Housen's Motors, Inc.");
    MarkerData location17 = new MarkerData(37.77243,-122.406921, "Euromotors Inc.");
    MarkerData location18 = new MarkerData(37.322868,-121.98172, "AN San Jose Luxury Imports, Inc.");
    MarkerData location19 = new MarkerData(37.911118,-122.062965, "Sonic Walnut Creek M, Inc.");
    MarkerData location20 = new MarkerData(37.817585,-122.263016, "Euromotors Oakland, Inc.");
    MarkerData location21 = new MarkerData(37.500282,-121.976883, "Fremont M, LLC");
    MarkerData location22 = new MarkerData(38.054298,-121.375504, "Berberian European Motors, LLC");
    MarkerData location23 = new MarkerData(34.141399,-118.0317, "Rusnak/Arcadia");
    MarkerData location24 = new MarkerData(33.800629,-118.343231,"Carwell, LLC");
    MarkerData location25 = new MarkerData(32.823601,-117.147507,"Europa Auto Imports, Inc.");
    MarkerData location26 = new MarkerData(34.028912,-118.485924,"Sonic Santa Monica M, Inc.");
    MarkerData location27 = new MarkerData(33.924465,-117.416862,"Walter's Auto Sales and Service, Inc.");
    MarkerData location28 = new MarkerData(33.86095,-117.988007,"House of Imports, Inc.");
    MarkerData location29 = new MarkerData(35.30357,-119.034927,"Sangera Buick Inc");
    MarkerData location30 = new MarkerData(33.79475,-116.501877,"VIP Motor Cars Ltd.");
    MarkerData location31 = new MarkerData(33.544868,-117.675331,"Mission Imports");
    MarkerData location32 = new MarkerData(33.130581,-117.322723,"Hoehn Motors Inc.");
    MarkerData location33 = new MarkerData(34.160126,-118.82737,"Silver Star A. G. Ltd.");
    MarkerData location34 = new MarkerData(34.138149,-118.254784,"Calstar Motors, Inc.");
    MarkerData location35 = new MarkerData(33.85965,-117.798523,"Caliber Motors, Inc.");
    MarkerData location36 = new MarkerData(33.108181,-117.093109,"GPI CA-DMII, INC");
    MarkerData location37 = new MarkerData(37.52845,-122.271385,"Autobahn Inc");
    MarkerData location38 = new MarkerData(34.077122,-118.394882,"Miller-DM, Inc.");
    MarkerData location39 = new MarkerData(39.569382,-104.988075,"European Motor Cars of Littleton, Inc.");
    MarkerData location40 = new MarkerData(38.816502,-104.840828,"Phil Long Autohaus, LLC");
    MarkerData location41 = new MarkerData(41.783199,-72.663818,"New Country Motor Cars, Inc.");
    MarkerData location42 = new MarkerData(41.36562,-72.120415,"Carriage House of New London, Inc.");
    MarkerData location43 = new MarkerData(41.421333,-72.839233,"Mauro Motors, Inc.");
    MarkerData location44 = new MarkerData(38.946129,-75.426514,"I. G. Burton & Co. Inc.");
    MarkerData location45 = new MarkerData(28.09293,-80.612755,"TT of Indian River, Inc.");
    MarkerData location46 = new MarkerData(30.45915,-84.355904,"Capital Eurocars, Inc.");
    MarkerData location47 = new MarkerData(26.518389,-81.866608,"SAI Fort Myers M, LLC");
    MarkerData location48 = new MarkerData(25.746243,-80.261131,"Bill Ussery Motors Inc.");
    MarkerData location49 = new MarkerData(25.926695,-80.219872,"L. P. Evans Motors W P B, Inc.");
    MarkerData location50 = new MarkerData(27.982317,-82.506027,"Precision Motorcars Inc.");
    private GoogleMap mMap;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and
        // get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //allow Up navigation with the app icon in the action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        ImageButton back = findViewById(R.id.arrow_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, MainActivity.class));
            }
        });
        ImageButton settings = findViewById(R.id.ed_profile);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,
                        EditProfileActivity.class));}});
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.
                        OnNavigationItemSelectedListener() {
                    @Override
                    public boolean
                    onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_search:
                                Intent intent0 = new Intent(
                                        MapsActivity.this, MainActivity.class);
                                startActivity(intent0);
                                break;
                            case R.id.action_navigation:
                                break;
                            case R.id.action_chat:
                                Intent intent1 = new
                                        Intent(MapsActivity.this,
                                        ChatActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.action_account:
                                Intent intent3 = new
                                        Intent(MapsActivity.this,
                                        AccountActivity.class);
                                startActivity(intent3);
                        }
                        return false;}});}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Marker
        mMap.animateCamera(
                CameraUpdateFactory.zoomIn());
        for(int i = 0 ; i < markersArray.size() ; i++ ) {
            createMarker(markersArray.get(i).getLatitude(),
                    markersArray.get(i).getLongitude(),
                    markersArray.get(i).getTitle());}
        mMap.setOnMarkerClickListener(this);}
   @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle() +
        ": click on the icon in the " +
                "down-right below to get the direction",
                Toast.LENGTH_SHORT).show();
       try {Thread.sleep(10);}catch(InterruptedException e) {
           e.printStackTrace();}
           return false;}
    protected void createMarker(double latitude,
                                double longitude,
                                String title) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.
                                HUE_AZURE)).alpha(0.7f));}
    private void init(){
        this.markersArray.add(location1);
        this.markersArray.add(location2);
        this.markersArray.add(location3);
        this.markersArray.add(location4);
        this.markersArray.add(location5);
        this.markersArray.add(location6);
        this.markersArray.add(location7);
        this.markersArray.add(location8);
        this.markersArray.add(location9);
        this.markersArray.add(location10);
        this.markersArray.add(location11);
        this.markersArray.add(location12);
        this.markersArray.add(location13);
        this.markersArray.add(location14);
        this.markersArray.add(location15);
        this.markersArray.add(location16);
        this.markersArray.add(location17);
        this.markersArray.add(location18);
        this.markersArray.add(location19);
        this.markersArray.add(location20);
        this.markersArray.add(location21);
        this.markersArray.add(location22);
        this.markersArray.add(location23);
        this.markersArray.add(location24);
        this.markersArray.add(location25);
        this.markersArray.add(location26);
        this.markersArray.add(location27);
        this.markersArray.add(location28);
        this.markersArray.add(location29);
        this.markersArray.add(location30);
        this.markersArray.add(location31);
        this.markersArray.add(location32);
        this.markersArray.add(location33);
        this.markersArray.add(location34);
        this.markersArray.add(location35);
        this.markersArray.add(location36);
        this.markersArray.add(location37);
        this.markersArray.add(location38);
        this.markersArray.add(location39);
        this.markersArray.add(location40);
        this.markersArray.add(location41);
        this.markersArray.add(location42);
        this.markersArray.add(location43);
        this.markersArray.add(location44);
        this.markersArray.add(location45);
        this.markersArray.add(location46);
        this.markersArray.add(location47);
        this.markersArray.add(location48);
        this.markersArray.add(location49);
        this.markersArray.add(location50);}}