package com.example.cars;

/**
 * Created by Developer on 4/19/2018.
 */

public class Message {
    private String message,name,userID;
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
    public void setUser_name(String name) {
        this.name = name;
    }
    public Message() {
    }
    public Message(String message, String name,String userID) {
        this.message = message;
        this.name = name;
        this.userID = userID;
    }
}
