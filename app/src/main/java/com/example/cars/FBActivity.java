package com.example.cars;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by rshak001 on 3/9/2018.
 */

public class FBActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstancesState) {
    super.onCreate(savedInstancesState);
    FacebookSdk.sdkInitialize(getApplicationContext());
    setContentView(R.layout.activity_login);

    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Toast.makeText(FBActivity.this,loginResult.getAccessToken().getUserId(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(FBActivity.this,"Login Cancelled!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException error) {

        }
    });
    }

}
