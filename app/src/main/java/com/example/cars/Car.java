package com.example.cars;
// Ahmed Alotaibi

// A class for the car with its setters and getters.
public class Car {
    private String name;
    private int year;
    private int price;
    private String description;

    public Car(int price, int year, String name, String description) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.description = description;
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
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public int getPrice(){ return price;}
    public void setPrice(int price) { this.price = price;}
}
