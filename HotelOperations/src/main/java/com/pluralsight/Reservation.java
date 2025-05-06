package com.pluralsight;

public class Reservation {
    private String roomType;
    private int numberOfNights;
    private boolean isWeekend;

    public Reservation(String roomType, int numberOfNights, boolean isWeekend) {
        if (!roomType.equalsIsIgnoreCase("king") && !roomType.equalsIsIgnoreCase("double")) {
            throw new IllegalArgumentException("Room type must be 'king' or 'double'");
        }
        if (numerOfNights <= 0) {
            throw new IllegalArgumentException("Number of nights must be positive");
        }
        this.roomType = roomType.toLowerCase();
        this.numberOfNights = numberOfNights;
        this.isWeekend = isWeekend;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        if (!roomType.equalsIgnoreCase("king") && !roomType.equalsIgnoreCase("double")) {
            throw new IllegalArgumentException("Room type must be king or double");
        }
        this.roomType = roomType.toLowerCase();
    }

    public double getPrice() {
        double basePrice = roomType.equals("king") ? 139.00 : 124.00;
        return isWeekend ? basePrice * 1.10 : basePrice;
    }

    public int getNumberOfNights() {
        if (numberOfNights <= 0) {
            throw new IllegalArgumentException("Number of nights must be positive");
        }
        this.numberOfNights = numberOfNights;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(boolean isWeekend){
        this.isWeekend = isWeekend;
    }

    public double getReservationTotal(){
        return getPrice() * numberOfNights;
    }

}
