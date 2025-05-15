package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private final List<Vehicle> inventory;
    private final ContractFileManager contractFileManager;
    private static final String INVENTORY_FILE = "C:/pluralsight/workshops/adv-dealership-project/dealership.txt";

    public Dealership() {
        this.inventory = new ArrayList<>();
        this.contractFileManager = new ContractFileManager();
        loadInventory();
    }

    private void loadInventory() {
        System.out.println("DEBUG: Current working directory: " + System.getProperty("user.dir"));
        System.out.println("DEBUG: Attempting to load inventory from: " + INVENTORY_FILE);
        File file = new File(INVENTORY_FILE);
        System.out.println("DEBUG: File exists: " + file.exists());
        System.out.println("DEBUG: File is readable: " + file.canRead());
        System.out.println("DEBUG: File absolute path: " + file.getAbsolutePath());
        System.out.println("DEBUG: File size (bytes): " + file.length());

        if (!file.exists()) {
            System.out.println("DEBUG: File does not exist at specified path.");
            loadDefaultVehicle();
            return;
        }
        if (!file.canRead()) {
            System.out.println("DEBUG: File is not readable (check permissions).");
            loadDefaultVehicle();
            return;
        }
        if (file.length() == 0) {
            System.out.println("DEBUG: File is empty.");
            loadDefaultVehicle();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("DEBUG: File opened successfully.");
            String line;
            int lineNumber = 0;
            boolean hasContent = false;
            while ((line = reader.readLine()) != null) {
                hasContent = true;
                lineNumber++;
                line = line.replace("\r", "").trim();
                System.out.println("DEBUG: Line " + lineNumber + " raw content: [" + line + "]");
                if (line.isEmpty()) {
                    System.out.println("DEBUG: Skipping empty line " + lineNumber);
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                System.out.println("DEBUG: Line " + lineNumber + " split into " + parts.length + " parts: " + String.join(", ", parts));
                if (parts.length != 8) {
                    System.out.println("DEBUG: Error at line " + lineNumber + ": Expected 8 fields, found " + parts.length);
                    continue;
                }
                try {
                    String vin = parts[0].trim();
                    if (vin.isEmpty()) {
                        System.out.println("DEBUG: Error at line " + lineNumber + ": VIN is empty");
                        continue;
                    }
                    int year = Integer.parseInt(parts[1].trim());
                    String make = parts[2].trim();
                    if (make.isEmpty()) {
                        System.out.println("DEBUG: Error at line " + lineNumber + ": Make is empty");
                        continue;
                    }
                    String model = parts[3].trim();
                    if (model.isEmpty()) {
                        System.out.println("DEBUG: Error at line " + lineNumber + ": Model is empty");
                        continue;
                    }
                    String type = parts[4].trim();
                    if (type.isEmpty()) {
                        System.out.println("DEBUG: Error at line " + lineNumber + ": Type is empty");
                        continue;
                    }
                    String color = parts[5].trim();
                    if (color.isEmpty()) {
                        System.out.println("DEBUG: Error at line " + lineNumber + ": Color is empty");
                        continue;
                    }
                    int odometer = Integer.parseInt(parts[6].trim());
                    double price = Double.parseDouble(parts[7].trim());
                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    inventory.add(vehicle);
                    System.out.println("DEBUG: Successfully added vehicle: VIN=" + vin + ", Make=" + make + ", Model=" + model);
                } catch (NumberFormatException e) {
                    System.out.println("DEBUG: Error parsing line " + lineNumber + ": Invalid number format (" + e.getMessage() + ")");
                }
            }
            if (!hasContent) {
                System.out.println("DEBUG: File contains no valid lines.");
                loadDefaultVehicle();
            }
            System.out.println("DEBUG: Inventory loading complete. Total vehicles loaded: " + inventory.size());
        } catch (IOException e) {
            System.out.println("DEBUG: Failed to read dealership.txt: " + e.getMessage());
            loadDefaultVehicle();
        }
    }

    private void loadDefaultVehicle() {
        System.out.println("DEBUG: Loading default vehicle as fallback.");
        inventory.add(new Vehicle("DEFAULT", 2023, "Ferrari", "Default", "Car", "Red", 1000, 500000.00));
        System.out.println("DEBUG: Added default vehicle. Total vehicles: " + inventory.size());
    }

    public List<Vehicle> getAllVehicles() {
        System.out.println("DEBUG: getAllVehicles called. Returning " + inventory.size() + " vehicles");
        return new ArrayList<>(inventory);
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) &&
                    (model.isEmpty() || vehicle.getModel().equalsIgnoreCase(model))) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getType().equalsIgnoreCase(type)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public void addVehicle(String vin, int year, String make, String model, String type, String color, int odometer, double price) {
        inventory.add(new Vehicle(vin, year, make, model, type, color, odometer, price));
    }

    public boolean removeVehicle(String vin) {
        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) return false;
        inventory.remove(vehicle);
        return true;
    }

    public Contract sellOrLeaseVehicle(String vin, String date, String customerName, String customerEmail, int contractType, boolean isFinanced) {
        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) return null;

        int currentYear = java.time.Year.now().getValue();
        boolean canLease = vehicle.getYear() > currentYear - 3;

        Contract contract = null;
        if (contractType == 1) {
            contract = new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
        } else if (contractType == 2 && canLease) {
            contract = new LeaseContract(date, customerName, customerEmail, vehicle);
        }

        if (contract != null) {
            contractFileManager.saveContract(contract);
        }
        return contract;
    }

    private Vehicle findVehicleByVin(String vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                return vehicle;
            }
        }
        return null;
    }
}