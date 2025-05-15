package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        Dealership dealership = new Dealership();
        UserInterface ui = new UserInterface(dealership);
        ui.run();
    }
}