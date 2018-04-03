package com.example.cars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Arrays;

@SuppressWarnings("ConstantConditions")
public class LoginActivity extends AppCompatActivity {

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    //widgets
    private TextView mRegister;
    private EditText mEmail, mPassword;
    private Button mLogin;
    private ProgressBar mProgressBar;
    private ImageView mLogo;

    //Facebook Sign-in
    private LoginButton loginButton;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";
    private Button mFacebookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide titlebar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        mRegister = (TextView) findViewById(R.id.link_register);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogo = (ImageView) findViewById(R.id.logo);
        mFacebookBtn=(Button) findViewById(R.id.facebookBtn);
        mAuth= FirebaseAuth.getInstance();
        //Instantiate Callback via Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        mFacebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFacebookBtn.setEnabled(false);
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG,"facebook:onSuccess:"+loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void  onCancel() {
                        Log.d(TAG,"facebook:onCancel:");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG,"facebook:Error:",error);
                    }
                });
            }
        });

        initImageLoader();
        initProgressBar();
        setupFirebaseAuth();
        init();
    }

    private void init() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the fields are filled out
                if (!isEmpty(mEmail.getText().toString())
                        && !isEmpty(mPassword.getText().toString())) {
                    showProgressBar();
                    FirebaseAuth.getInstance().
                            signInWithEmailAndPassword(mEmail.
                                            getText().toString(),
                            mPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    hideProgressBar();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this,
                                    "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this,
                            "You didn't fill in all the fields.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Change Intent
                Intent intent = new
                        Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
        UniversalImageLoader.setImage("assets://money_icon.png", mLogo);
        hideSoftKeyboard();
    }
    /**
     * Return true if the @param is null
     *
     * @param string
     * @return
     */
    private boolean isEmpty(String string) {
        return string.equals("");
    }
    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void initImageLoader() {
        UniversalImageLoader imageLoader = new
                UniversalImageLoader(LoginActivity.this);
        ImageLoader.getInstance().init(imageLoader.getConfig());
    }

    private void initProgressBar() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    /*
        ----------------------------- Firebase setup ---------------------------------
     */
    private void setupFirebaseAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(LoginActivity.this,
                            "Welcome ", Toast.LENGTH_SHORT).show();
                    //check if email is verified
                    if (user.isEmailVerified()) {
                        Toast.makeText(LoginActivity.this,
                                "Authenticated with: " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new
                                Intent(LoginActivity.this,
                                MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Email is not Verified\nCheck your Inbox",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User is signed out
                }
                // ...
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().
                addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI();
        }
    }

    private void updateUI() {
        // Check if user is signed in (non-null) and update UI accordingly.
        Toast.makeText(LoginActivity.this, "You're signed in ", Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent (LoginActivity.this, MainActivity.class);
        startActivity(accountIntent);
        finish();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().
                    removeAuthStateListener(mAuthListener);
        }
    }
        /*
       ----------------------------- Facebook setup ---------------------------------
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //Pass the activity result back to Facebook SDK
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mFacebookBtn.setEnabled(true);
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            mFacebookBtn.setEnabled(false);
                        }

                        // ...
                    }
                });
    }

}