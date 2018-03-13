package com.example.cars;

// Ahmed Alotaibi

// A class for the car's data with its attributes.
public class CarData {
    private int year;
    private String make;
    private int makeId;
    private String model;
    private int price;
    private String description;

    // Default constructor required for calls
    public CarData() {
    }
    public CarData(int year, String make,
                   int makeId, String model,
                   int price, String description) {
        this.year = year;
        this.make = make;
        this.makeId = makeId;
        this.model = model;
        this.price = price;
        this.description = description;
    }
    // setters and getters for the objects.
    public int getYear() {
        return year;
    }

    public void setYear(int year) { this.year = year; }

    public String getMake() {
        return make;
    }

    public void setMake(String make) { this.make = make; }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) { this.makeId = makeId; }

    public String getModel() {
        return model;
    }

    public int getPrice(){ return price;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public void setPrice(int price) { this.price = price;}

    public void setModel(String model) { this.model = model; }
}