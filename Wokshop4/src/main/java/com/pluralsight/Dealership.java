package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public List<Vehicle> getInventory() { return new ArrayList<>(inventory); }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(String vin) {
        inventory = inventory.stream()
                .filter(vehicle -> !vehicle.getVin().equals(vin))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByPrice(double minPrice, double maxPrice) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByYearRange(int minYear, int maxYear) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByColor(String color) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByMileageRange(int minMileage, int maxMileage) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getMileage() >= minMileage && vehicle.getMileage() <= maxMileage)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByType(String type) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", name, address, phone);
    }
}
