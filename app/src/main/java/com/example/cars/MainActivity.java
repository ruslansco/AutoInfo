package com.example.cars;

// Ahmed Alotaibi

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.crashlytics.android.Crashlytics;
import com.example.cars.account.AccountActivity;
import com.github.florent37.hollyviewpager.HollyViewPager;
import com.github.florent37.hollyviewpager.HollyViewPagerConfigurator;

import java.util.Objects;

import io.fabric.sdk.android.Fabric;
import butterknife.ButterKnife;
import butterknife.Bind;

public class MainActivity
        extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    int pageCount = 5;
    @Bind(R.id.hollyViewPager)
    HollyViewPager hollyViewPager;
    protected void
    onCreate(Bundle savedInstanceState) {
        // call the super class onCreate
        // to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);
        // Set up Crashlytics to get report about any crashes
        // using Fabric API
        Fabric.with(this, new Crashlytics());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Objects.requireNonNull(getSupportActionBar()).hide();}
        setContentView(R.layout.activity_edit_profile);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Set Configurator for UI
        hollyViewPager.setConfigurator(
                new HollyViewPagerConfigurator(){@Override
                    public float
                getHeightPercentForPage(int page) {
                        return ((page+5)%14)/15f;}});
        hollyViewPager.setAdapter(
                new FragmentPagerAdapter(
                        getSupportFragmentManager()) {
                    @Override
                    public Fragment
                    getItem(int position) {
                        return ScrollViewFragment.
                                newInstance((String)
                                        getPageTitle(position));
                    }
                    @RequiresApi(api=Build.VERSION_CODES.KITKAT)
                    @Override
                    public int
                    getCount()
                    {return pageCount;}
                    @Override
                    public CharSequence
                    getPageTitle(int position) {
                        return "Mercedes "+position;}});
        BottomNavigationView bottomNavigationView =
                findViewById(R.id.navigation);
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
                            case R.id.action_search:break;
                            case R.id.action_navigation:
                                Intent intent0 = new
                                        Intent(MainActivity.this,
                                        MapsActivity.class);
                                startActivity(intent0);break;
                            case R.id.action_account:
                                Intent intent1 = new
                                        Intent(MainActivity.this,AccountActivity.class);
                                startActivity(intent1);break;}return false;}});
        ImageButton settings =
                findViewById(R.id.ed_profile);
        settings.setOnClickListener(new View.OnClickListener(){@Override
            public void
            onClick(View v) {
                startActivity(
                        new Intent(MainActivity.this,
                        EditProfileActivity.class));}});}}