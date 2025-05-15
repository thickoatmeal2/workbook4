package com.pluralsight;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Dealership dealership;
    private final Scanner scanner;
    private final DecimalFormat df;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
        this.scanner = new Scanner(System.in);
        this.df = new DecimalFormat("#.##");
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == 99) break;
            handleMenuChoice(choice);
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nDealership Menu:");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make/model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL available vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid input for menu choice: " + e.getMessage());
            System.out.println("Please enter a number.");
            return -1;
        }
    }

    private void handleMenuChoice(int choice) {
        System.out.println("DEBUG: Handling menu choice: " + choice);
        switch (choice) {
            case 1:
                findVehiclesByPriceRange();
                break;
            case 2:
                findVehiclesByMakeModel();
                break;
            case 3:
                findVehiclesByYearRange();
                break;
            case 4:
                findVehiclesByColor();
                break;
            case 5:
                findVehiclesByMileageRange();
                break;
            case 6:
                findVehiclesByType();
                break;
            case 7:
                listAllVehicles();
                break;
            case 8:
                addVehicle();
                break;
            case 9:
                removeVehicle();
                break;
            case 10:
                sellOrLeaseVehicle();
                break;
            default:
                System.out.println("DEBUG: Invalid menu choice entered.");
                System.out.println("Invalid choice. Try again.");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        System.out.println("DEBUG: Entering displayVehicles with " + vehicles.size() + " vehicles");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
        } else {
            System.out.println("\nAvailable Vehicles:");
            System.out.printf("%-8s %-6s %-14s %-22s %-8s %-16s %-8s %-12s%n",
                    "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
            System.out.println("---------------------------------------------------------------------------------------");
            int index = 0;
            for (Vehicle vehicle : vehicles) {
                System.out.println("DEBUG: Displaying vehicle " + (index + 1) + ": VIN=" + vehicle.getVin());
                System.out.printf("%-8s %-6d %-14s %-22s %-8s %-16s %-8d $%-11.2f%n",
                        vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                        vehicle.getType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                index++;
            }
            System.out.println("DEBUG: Completed displaying " + index + " vehicles");
        }
    }

    private void findVehiclesByPriceRange() {
        System.out.print("Enter minimum price: ");
        double minPrice;
        try {
            minPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid minimum price: " + e.getMessage());
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }
        System.out.print("Enter maximum price: ");
        double maxPrice;
        try {
            maxPrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid maximum price: " + e.getMessage());
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }
        if (minPrice > maxPrice) {
            System.out.println("Minimum price cannot exceed maximum price.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    private void findVehiclesByMakeModel() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model (or press Enter to search by make only): ");
        String model = scanner.nextLine().trim();
        if (make.isEmpty()) {
            System.out.println("Make cannot be empty.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void findVehiclesByYearRange() {
        System.out.print("Enter minimum year: ");
        int minYear;
        try {
            minYear = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid minimum year: " + e.getMessage());
            System.out.println("Invalid year. Please enter a valid number.");
            return;
        }
        System.out.print("Enter maximum year: ");
        int maxYear;
        try {
            maxYear = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid maximum year: " + e.getMessage());
            System.out.println("Invalid year. Please enter a valid number.");
            return;
        }
        if (minYear > maxYear) {
            System.out.println("Minimum year cannot exceed maximum year.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    private void findVehiclesByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        if (color.isEmpty()) {
            System.out.println("Color cannot be empty.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void findVehiclesByMileageRange() {
        System.out.print("Enter minimum mileage: ");
        int minMileage;
        try {
            minMileage = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid minimum mileage: " + e.getMessage());
            System.out.println("Invalid mileage. Please enter a valid number.");
            return;
        }
        System.out.print("Enter maximum mileage: ");
        int maxMileage;
        try {
            maxMileage = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid maximum mileage: " + e.getMessage());
            System.out.println("Invalid mileage. Please enter a valid number.");
            return;
        }
        if (minMileage > maxMileage) {
            System.out.println("Minimum mileage cannot exceed maximum mileage.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    private void findVehiclesByType() {
        System.out.print("Enter type (car, truck, SUV, van): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) {
            System.out.println("Type cannot be empty.");
            return;
        }
        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    private void listAllVehicles() {
        System.out.println("DEBUG: listAllVehicles called");
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void addVehicle() {
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine().trim();
        if (vin.isEmpty()) {
            System.out.println("VIN cannot be empty.");
            return;
        }
        System.out.print("Enter year: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid year: " + e.getMessage());
            System.out.println("Invalid year. Please enter a valid number.");
            return;
        }
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        if (make.isEmpty()) {
            System.out.println("Make cannot be empty.");
            return;
        }
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();
        if (model.isEmpty()) {
            System.out.println("Model cannot be empty.");
            return;
        }
        System.out.print("Enter type (car, truck, SUV, van): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) {
            System.out.println("Type cannot be empty.");
            return;
        }
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        if (color.isEmpty()) {
            System.out.println("Color cannot be empty.");
            return;
        }
        System.out.print("Enter mileage: ");
        int odometer;
        try {
            odometer = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid mileage: " + e.getMessage());
            System.out.println("Invalid mileage. Please enter a valid number.");
            return;
        }
        System.out.print("Enter price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Invalid price: " + e.getMessage());
            System.out.println("Invalid price. Please enter a valid number.");
            return;
        }
        dealership.addVehicle(vin, year, make, model, type, color, odometer, price);
        System.out.println("Vehicle added successfully.");
    }

    private void removeVehicle() {
        System.out.print("Enter VIN of vehicle to remove: ");
        String vin = scanner.nextLine().trim();
        if (vin.isEmpty()) {
            System.out.println("VIN cannot be empty.");
            return;
        }
        boolean removed = dealership.removeVehicle(vin);
        if (removed) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void sellOrLeaseVehicle() {
        System.out.print("Enter VIN of the vehicle: ");
        String vin = scanner.nextLine().trim();
        if (vin.isEmpty()) {
            System.out.println("VIN cannot be empty.");
            return;
        }
        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin().equalsIgnoreCase(vin)) {
                vehicle = v;
                break;
            }
        }
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter contract date (YYYYMMDD): ");
        String date = scanner.nextLine().trim();
        if (date.length() != 8) {
            System.out.println("Invalid date format. Use YYYYMMDD.");
            return;
        }
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine().trim();
        if (customerEmail.isEmpty()) {
            System.out.println("Customer email cannot be empty.");
            return;
        }

        int currentYear = java.time.Year.now().getValue();
        boolean canLease = vehicle.getYear() > currentYear - 3;

        System.out.println("Is this a Sale or Lease? (1 for Sale, 2 for Lease)");
        if (!canLease) System.out.println("Note: Leasing not available for vehicles over 3 years old.");
        System.out.print("Enter choice: ");
        int contractType = getUserChoice();

        boolean isFinanced = false;
        if (contractType == 1) {
            System.out.print("Finance the purchase? (yes/no): ");
            isFinanced = scanner.nextLine().trim().equalsIgnoreCase("yes");
        } else if (contractType != 2 || !canLease) {
            System.out.println("Invalid choice or leasing not allowed.");
            return;
        }

        Contract contract = dealership.sellOrLeaseVehicle(vin, date, customerName, customerEmail, contractType, isFinanced);
        if (contract == null) {
            System.out.println("Failed to create contract.");
            return;
        }

        System.out.println("\nContract Details:");
        System.out.println("Date: " + contract.getDate());
        System.out.println("Customer: " + contract.getCustomerName());
        System.out.println("Email: " + contract.getCustomerEmail());
        System.out.println("Vehicle: " + contract.getVehicle());
        System.out.println("Total Price: $" + df.format(contract.getTotalPrice()));
        System.out.println("Monthly Payment: $" + df.format(contract.getMonthlyPayment()));
    }
}