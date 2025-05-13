package com.pluralsight;

public class Dog extends Animal {
    private String breed;
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }
    public void bark() {
        System.out.println("Ruff, ruff!");
    }
}

