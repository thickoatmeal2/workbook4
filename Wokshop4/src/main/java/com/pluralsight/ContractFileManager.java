package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class ContractFileManager {
    private static final String CONTRACTS_FILE = "contracts.txt";
    private final DecimalFormat df = new DecimalFormat("#.##");

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true))) {
            StringBuilder line = new StringBuilder();
            if (contract instanceof SalesContract salesContract) {
                line.append("SALE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(contract.getVehicle().getVin()).append("|")
                        .append(df.format(salesContract.getVehicle().getPrice())).append("|")
                        .append(df.format(salesContract.getSalesTaxRate() * salesContract.getVehicle().getPrice())).append("|")
                        .append(df.format(salesContract.getRecordingFee())).append("|")
                        .append(df.format(salesContract.getProcessingFee())).append("|")
                        .append(df.format(salesContract.getTotalPrice())).append("|")
                        .append(salesContract.isFinanced() ? "YES" : "NO").append("|")
                        .append(df.format(salesContract.getMonthlyPayment()));
            } else if (contract instanceof LeaseContract leaseContract) {
                line.append("LEASE|")
                        .append(contract.getDate()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(contract.getVehicle().getVin()).append("|")
                        .append(df.format(leaseContract.getVehicle().getPrice())).append("|")
                        .append(df.format(leaseContract.getExpectedEndingValue())).append("|")
                        .append(df.format(leaseContract.getLeaseFee())).append("|")
                        .append(df.format(leaseContract.getTotalPrice())).append("|")
                        .append(df.format(leaseContract.getMonthlyPayment()));
            }
            writer.write(line.toString());
            writer.newLine();
            System.out.println("Contract saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}