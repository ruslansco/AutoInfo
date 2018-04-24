package com.example.cars;

import android.widget.TextView;

import java.util.Date;

/**
 * Created by Ruslan Shakirov on 4/19/2018.
 */

public class Message {
    private String message,name,userID;
    private long messageTime;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser_name() {
        return name;
    }
    public String getUserID(){return userID;}
    public long getMessageTime(){ return messageTime;}
    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
    public void setUser_name(String name) {
        this.name = name;
    }
    public Message() {
    }
    public Message(String message, String name, String userID) {
        this.message = message;
        this.name = name;
        this.userID = userID;
        messageTime = new Date().getTime();
    }
}
