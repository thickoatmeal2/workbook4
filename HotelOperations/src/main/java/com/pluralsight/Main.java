package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        // Test Room
        System.out.println("Testing Room:");
        Room room = new Room(2, 124.00, false, false);
        System.out.println("Number of Beds: " + room.getNumberOfBeds());
        System.out.println("Price: $" + room.getPrice());
        System.out.println("Occupied: " + room.isOccupied());
        System.out.println("Dirty: " + room.isDirty());
        System.out.println("Available: " + room.isAvailable());

        // Test Room (occupied and dirty)
        Room room2 = new Room(1, 139.00, true, true);
        System.out.println("\nTesting Room (occupied, dirty):");
        System.out.println("Available: " + room2.isAvailable());

        // Test Reservation
        System.out.println("\nTesting Reservation:");
        Reservation reservation = new Reservation("king", 3, true);
        System.out.println("Room Type: " + reservation.getRoomType());
        System.out.println("Price per Night: $" + reservation.getPrice());
        System.out.println("Number of Nights: " + reservation.getNumberOfNights());
        System.out.println("Weekend: " + reservation.isWeekend());
        System.out.println("Total: $" + reservation.getReservationTotal());

        // Test Reservation setters
        try {
            reservation.setRoomType("double");
            reservation.setNumberOfNights(2);
            reservation.setIsWeekend(false);
            System.out.println("\nAfter Updates:");
            System.out.println("Room Type: " + reservation.getRoomType());
            System.out.println("Price per Night: $" + reservation.getPrice());
            System.out.println("Total: $" + reservation.getReservationTotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Employee
        System.out.println("\nTesting Employee:");
        Employee employee = new Employee("E123", "John Doe", "Housekeeping", 15.00, 45.0);
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Pay Rate: $" + employee.getPayRate());
        System.out.println("Hours Worked: " + employee.getHoursWorked());
        System.out.println("Regular Hours: " + employee.getRegularHours());
        System.out.println("Overtime Hours: " + employee.getOvertimeHours());
        System.out.println("Total Pay: $" + employee.getTotalPay());
    }
}