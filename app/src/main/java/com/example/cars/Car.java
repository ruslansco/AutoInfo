package com.example.cars;

// Ahmed Alotaibi

// A class for the car with its setters and getters.
public class Car {
    private String name;
    private int year;

    public Car(int year, String name) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() { return year;}

    public void setYear(int year) {
        this.year = year;
    }
}
