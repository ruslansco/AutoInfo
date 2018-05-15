package com.example.cars;
// Ahmed Alotaibi

// A class for the CarMake including constructor
// and setters with getters for the object.
public class CarMake {
    private int id;
    private String name;
    public CarMake(int id, String name) {
        this.id = id;
        this.name = name;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object obj) {
        return ((CarMake) obj).id == this.id;
    }}
