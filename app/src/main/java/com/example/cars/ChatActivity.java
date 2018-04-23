package com.example.cars;
import android.content.Intent;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cars.account.AccountActivity;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

/**
 * Created by Developer on 4/19/2018.
 */

public class ChatActivity extends AppCompatActivity{
    private EditText editText;
    private DatabaseReference chatRef;
    FirebaseUser user = getInstance().getCurrentUser();
    private String name="";
    private String userID;
    HashMap<String,String> map;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_chat);
        FirebaseAuth mAuth = getInstance();
        userID = user.getUid();
        editText= findViewById(R.id.edittext);
        chatRef= FirebaseDatabase.getInstance().getReference("chat");
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userID).child("name");
        ListView listView = findViewById(R.id.listview);
        ImageButton chat = findViewById(R.id.group_chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this, ChatActivity.class));
            }
        });
        ImageButton settings = findViewById(R.id.ed_profile);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this, EditProfileActivity.class));
            }
        });
        map=new HashMap<>();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name= Objects.requireNonNull(dataSnapshot.getValue()).toString();

            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        FirebaseListAdapter<Message> adapter=new FirebaseListAdapter<Message>(
                this,Message.class,R.layout.individual_row,chatRef
        ) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView msg= v.findViewById(R.id.textView1);
                msg.setText(MessageFormat.format("{0} : {1}", model.getUser_name(),
                        model.getMessage()));
            }
        };
        listView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
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
                                        ChatActivity.this, MainActivity.class);
                                startActivity(intent0);
                                break;
                            case R.id.action_navigation:
                                Intent intent1 = new
                                        Intent(ChatActivity.this,
                                        MapsActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.action_chat:
                                break;
                            case R.id.action_account:
                                Intent intent3 = new
                                        Intent(ChatActivity.this,
                                        AccountActivity.class);
                                startActivity(intent3);
                        }
                        return false;
                    }
                });
    }
    public void send(View view) {
        chatRef.push().setValue(new
                Message(editText.getText().toString(),
                name,userID));
        editText.setText("");}}
