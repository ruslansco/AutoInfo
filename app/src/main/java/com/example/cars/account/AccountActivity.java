package com.example.cars.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cars.EditProfileActivity;
import com.example.cars.LoginActivity;
import com.example.cars.MainActivity;
import com.example.cars.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by nulrybekkarshyga on 07.03.18.
 * Modified by Ruslan Shakirov
 */

public class AccountActivity extends AppCompatActivity {
    //firebase
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //widgets
    private Button mSignOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth mAuth;
    private ImageView imageView;
    private TextView email;
    private TextView name;
    private TextView userId;
    private ImageButton settings;
    private String displayName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide titlebar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account);
        // allow Up navigation with the app icon in the action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (TextView) findViewById(R.id.displayed_name);
        email = (TextView) findViewById(R.id.email_field);
        userId = (TextView) findViewById(R.id.user_ID);
        //imageView = (ImageView) findViewById(R.id.user_photo);
        mSignOut = (Button) findViewById(R.id.sign_out);
        settings = (ImageButton) findViewById(R.id.ed_profile);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, EditProfileActivity.class));
            }
        });
        setupFirebaseListener();
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
            }
        });
        //get firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        //get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setDataToView(user);
        //add a auth listener
        authListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("AccountActivity", "onAuthStateChanged");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setDataToView(user);
                    //loading image by Picasso
                    if (user.getPhotoUrl() != null) {
                        Log.d("MainActivity", "photoURL: " + user.getPhotoUrl());
                        Picasso.with(AccountActivity.this).load(user.getPhotoUrl()).into(imageView);
                    }
                } else {
                    //user auth state is not existed or closed, return to Login activity
                    startActivity(new Intent(AccountActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        BottomNavigationView bottomNavigationView =
                (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        Intent intent0 = new
                                Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.action_navigation:
                        Intent intent1 = new
                                Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_account:
                        break;
                }
                return false;
            }

    });
}
    private void setupFirebaseListener(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    String displayName = user.getDisplayName();
                    for (UserInfo userInfo : user.getProviderData()) {
                        if (displayName == null && userInfo.getDisplayName() != null) {
                            displayName = userInfo.getDisplayName();
                        }
                    }
                }else{
                    Toast.makeText(AccountActivity.
                            this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountActivity.
                            this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {
        email.setText("Email: " + user.getEmail());
        name.setText(user.getDisplayName());
        userId.setText("ID: " + user.getUid());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().
                addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mAuthStateListener != null){
            FirebaseAuth.getInstance().
                    removeAuthStateListener(mAuthStateListener);
        }
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