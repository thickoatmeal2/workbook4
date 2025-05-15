package com.pluralsight;

public class SalesContract extends Contract {
    private final double salesTaxRate = 0.05;
    private final double recordingFee = 100.0;
    private final double processingFee;
    private final boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;
        this.processingFee = vehicle.getPrice() < 10000 ? 295.0 : 495.0;
    }

    public double getSalesTaxRate() { return salesTaxRate; }
    public double getRecordingFee() { return recordingFee; }
    public double getProcessingFee() { return processingFee; }
    public boolean isFinanced() { return isFinanced; }

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
        double monthlyRate = interestRate / 12;
        return totalPrice * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
    }
}