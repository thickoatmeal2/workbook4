package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        UserInterface ui = new UserInterface("dealership.txt", console);
        ui.start();
    }
}