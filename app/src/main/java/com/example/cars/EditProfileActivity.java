package com.example.cars;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.example.cars.Helper.Helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ruslan Shakirov on 4/7/2018.
 */

public class EditProfileActivity extends AppCompatActivity{
    private static final String TAG = EditProfileActivity.class.getSimpleName();

    private EditText editProfileName;

    private FirebaseAuth.AuthStateListener authStateListener;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_edit_profile);
        //Hide titlebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        setTitle("Edit Profile Information");

        editProfileName = findViewById(R.id.profile_name);

        Button saveEditButton = findViewById(R.id.save_edit_button);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editProfileName.getText().toString();
                // update the user profile information in Firebase database.
                if(TextUtils.isEmpty(name)){
                    Helper.displayMessageToast(EditProfileActivity.this, "Name cannot be empty");
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    Intent firebaseUserIntent = new Intent(EditProfileActivity.this, LoginActivity.class);
                    startActivity(firebaseUserIntent);
                    finish();
                } else {
                    String user_id = user.getProviderId();
                    String id = user.getUid();
                    name = user.getEmail();
                    editProfileName.setText("");
                }
            }
        });
    }
}
