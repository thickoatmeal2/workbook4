package com.pluralsight;

public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private int mileage;
    private double price;

    public Vehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    // Getters
    public String getVin() { return vin; }
    public int getYear() { return year; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public String getColor() { return color; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }

    // Setters
    public void setVin(String vin) { this.vin = vin; }
    public void setYear(int year) { this.year = year; }
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
    public void setColor(String color) { this.color = color; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("%s|%d|%s|%s|%s|%s|%d|%.2f", vin, year, make, model, type, color, mileage, price);
    }
}