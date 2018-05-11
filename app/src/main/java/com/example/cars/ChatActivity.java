package com.example.cars;
import android.app.Notification;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.style.BackgroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.example.cars.account.AccountActivity;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

/**
 * Created by Ruslan Shakirov on 4/19/2018.
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
        ImageButton back = findViewById(R.id.arrow_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatActivity.this, MainActivity.class));
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

        final FirebaseListAdapter<Message> adapter=new FirebaseListAdapter<Message>(
                this,Message.class,R.layout.individual_row,chatRef
        ) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView msgText= v.findViewById(R.id.message_text);
                TextView messageTime = v.findViewById(R.id.message_time);
                if (model.getUserID().equals(user.getUid())) {
                    msgText.setText(MessageFormat.format("{0}",
                            model.getMessage()));
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
                    v.setBackgroundResource(R.drawable.my_message);
                    msgText.setTextColor(Color.WHITE);
                    messageTime.setTextColor(Color.WHITE);
                    msgText.setGravity(Gravity.RIGHT | Gravity.END);
                    messageTime.setGravity(Gravity.RIGHT | Gravity.END);
                } else {
                    msgText.setText(MessageFormat.format("{0}: {1}",model.getUser_name(),
                            model.getMessage()));
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
                    v.setBackgroundResource(R.drawable.their_message);
                    msgText.setTextColor(Color.WHITE);
                    messageTime.setTextColor(Color.WHITE);
                    msgText.setGravity(Gravity.LEFT | Gravity.START);
                    messageTime.setGravity(Gravity.LEFT | Gravity.START);
                }
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
        if (editText.getText().toString().trim().equals("")) {
            Toast.makeText(ChatActivity.this, "Message cannot be blank", Toast.LENGTH_SHORT).show();
        } else {
        chatRef.push().setValue(new
                Message(editText.getText().toString(),
                name,userID));
        editText.setText("");}
    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
       Ringtone notification = RingtoneManager.getRingtone(getApplicationContext(), sound);
        notification.play();

        //Notification notification = builder.build();
        //notification.defaults |= Notification.DEFAULT_SOUND;
        //notification.defaults |= Notification.DEFAULT_VIBRATE;
    }}
