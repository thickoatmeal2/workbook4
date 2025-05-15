package com.pluralsight;

public class Vehicle {
    private final String vin;
    private final int year;
    private final String make;
    private final String model;
    private final String type;
    private final String color;
    private final int odometer;
    private final double price;

    public Vehicle(String vin, int year, String make, String model, String type, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public String getVin() { return vin; }
    public int getYear() { return year; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public String getColor() { return color; }
    public int getOdometer() { return odometer; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "VIN: " + vin + ", Year: " + year + ", Make: " + make + ", Model: " + model +
                ", Type: " + type + ", Color: " + color + ", Mileage: " + odometer + ", Price: $" + price;
    }
}