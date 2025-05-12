package com.pluralsight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    private final String filePath;

    public DealershipFileManager(String filePath) {
        this.filePath = filePath;
    }

    public Dealership loadDealership() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) {
                throw new IllegalStateException("File is empty");
            }

            // Parse dealership info
            String[] dealershipInfo = lines.get(0).split("\\|");
            if (dealershipInfo.length != 3) {
                throw new IllegalStateException("Invalid dealership info format");
            }
            Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

            // Parse vehicles
            for (int i = 1; i < lines.size(); i++) {
                String[] vehicleInfo = lines.get(i).split("\\|");
                if (vehicleInfo.length != 8) {
                    System.err.println("Skipping invalid vehicle entry at line " + (i + 1));
                    continue;
                }
                Vehicle vehicle = new Vehicle(
                        vehicleInfo[0],
                        Integer.parseInt(vehicleInfo[1]),
                        vehicleInfo[2],
                        vehicleInfo[3],
                        vehicleInfo[4],
                        vehicleInfo[5],
                        Integer.parseInt(vehicleInfo[6]),
                        Double.parseDouble(vehicleInfo[7])
                );
                dealership.addVehicle(vehicle);
            }

            return dealership;
        } catch (Exception e) {
            System.err.println("Error loading dealership: " + e.getMessage());
            return new Dealership("Default Dealership", "Unknown Address", "000-000-0000");
        }
    }

    public void saveDealership(Dealership dealership) {
        try {
            List<String> lines = new ArrayList<>();
            lines.add(dealership.toString());
            for (Vehicle vehicle : dealership.getInventory()) {
                lines.add(vehicle.toString());
            }
            Files.write(Paths.get(filePath), lines);
        } catch (Exception e) {
            System.err.println("Error saving dealership: " + e.getMessage());
        }
    }
}