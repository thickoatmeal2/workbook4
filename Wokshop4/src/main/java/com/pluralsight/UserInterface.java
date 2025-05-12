package com.pluralsight;

import java.util.List;

public class UserInterface {
    private Dealership dealership;
    private final DealershipFileManager fileManager;
    private final Console console;

    public UserInterface(String filePath, Console console) {
        this.fileManager = new DealershipFileManager(filePath);
        this.console = console;
    }

    public void start() {
        this.dealership = fileManager.loadDealership();
        while (true) {
            console.displayMenu();
            String choice = console.readString("Select an option: ");
            if (!processChoice(choice)) {
                console.close();
                break;
            }
        }
    }

    private boolean processChoice(String choice) {
        switch (choice) {
            case "1":
                findByPriceRange();
                return true;
            case "2":
                findByMakeModel();
                return true;
            case "3":
                findByYearRange();
                return true;
            case "4":
                findByColor();
                return true;
            case "5":
                findByMileageRange();
                return true;
            case "6":
                findByType();
                return true;
            case "7":
                listAllVehicles();
                return true;
            case "8":
                addVehicle();
                return true;
            case "9":
                removeVehicle();
                return true;
            case "99":
                return false;
            default:
                console.printMessage("Invalid option. Please try again.");
                return true;
        }
    }

    private void findByPriceRange() {
        double minPrice = console.readDouble("Enter minimum price: ");
        double maxPrice = console.readDouble("Enter maximum price: ");
        List<Vehicle> vehicles = dealership.findByPrice(minPrice, maxPrice);
        console.displayVehicles(vehicles);
    }

    private void findByMakeModel() {
        String make = console.readString("Enter make: ");
        String model = console.readString("Enter model: ");
        List<Vehicle> vehicles = dealership.findByMakeModel(make, model);
        console.displayVehicles(vehicles);
    }

    private void findByYearRange() {
        int minYear = console.readInt("Enter minimum year: ");
        int maxYear = console.readInt("Enter maximum year: ");
        List<Vehicle> vehicles = dealership.findByYearRange(minYear, maxYear);
        console.displayVehicles(vehicles);
    }

    private void findByColor() {
        String color = console.readString("Enter color: ");
        List<Vehicle> vehicles = dealership.findByColor(color);
        console.displayVehicles(vehicles);
    }

    private void findByMileageRange() {
        int minMileage = console.readInt("Enter minimum mileage: ");
        int maxMileage = console.readInt("Enter maximum mileage: ");
        List<Vehicle> vehicles = dealership.findByMileageRange(minMileage, maxMileage);
        console.displayVehicles(vehicles);
    }

    private void findByType() {
        String type = console.readString("Enter vehicle type (car, truck, SUV, van): ");
        List<Vehicle> vehicles = dealership.findByType(type);
        console.displayVehicles(vehicles);
    }

    private void listAllVehicles() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        console.displayVehicles(vehicles);
    }

    private void addVehicle() {
        String vin = console.readString("Enter VIN: ");
        int year = console.readInt("Enter year: ");
        String make = console.readString("Enter make: ");
        String model = console.readString("Enter model: ");
        String type = console.readString("Enter type (car, truck, SUV, van): ");
        String color = console.readString("Enter color: ");
        int mileage = console.readInt("Enter mileage: ");
        double price = console.readDouble("Enter price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
        console.printMessage("Vehicle added successfully.");
    }

    private void removeVehicle() {
        String vin = console.readString("Enter VIN of vehicle to remove: ");
        dealership.removeVehicle(vin);
        fileManager.saveDealership(dealership);
        console.printMessage("Vehicle removed successfully.");
    }
}
