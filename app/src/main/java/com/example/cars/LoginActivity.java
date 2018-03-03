package com.example.cars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegister = (TextView) findViewById(R.id.link_register);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogo = (ImageView) findViewById(R.id.logo);
    }
    //widgets
    private TextView mRegister;
    private EditText mEmail, mPassword;
    private Button mLogin;
    private ProgressBar mProgressBar;
    private ImageView mLogo;





}
