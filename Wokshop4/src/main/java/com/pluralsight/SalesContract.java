package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTaxRate = 0.05;
    private double recordingFee = 100.0;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;
        this.processingFee = vehicle.getPrice() < 10000 ? 295.0 : 495.0;
    }

    // Getters and Setters
    public boolean isFinanced() { return isFinanced; }
    public void setFinanced(boolean isFinanced) { this.isFinanced = isFinanced; }
    public double getSalesTaxRate() { return salesTaxRate; }
    public double getRecordingFee() { return recordingFee; }
    public double getProcessingFee() { return processingFee; }

    @Override
    public double getTotalPrice() {
        double basePrice = vehicle.getPrice();
        double salesTax = basePrice * salesTaxRate;
        return basePrice + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0.0;
        double totalPrice = getTotalPrice();
        double interestRate = totalPrice >= 10000 ? 0.0425 : 0.0525;
        int months = totalPrice >= 10000 ? 48 : 24;
        // Monthly payment formula: P * (r / 12) / (1 - (1 + r/12)^(-n))
        double monthlyRate = interestRate / 12;
        return totalPrice * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
    }
}