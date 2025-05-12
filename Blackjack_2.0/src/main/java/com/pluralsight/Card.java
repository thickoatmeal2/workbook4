package com.pluralsight;

public class Card {

    private String suit;
    private String value;
    private boolean isfaceUp;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
        this.isfaceUp = false;
    }

    public String getSuit(){
        if(isfaceUp){
            return this.suit;
        }
        else{
            return "#";
        }
    }

    public String getValue(){
        if(isfaceUp){
            return this.value;
        }
        else{
            return "#";
        }
    }

    public int getPointValue(){
        // only return the value if the card is face up

        // determine point value and return it
        // A = 11
        // K, Q, J = 10
        // all numeric cards are equal to their face value
        switch (this.value){
            case "A": return 11;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 10;
            case "Q": return 10;
            case "K": return 10;

        }

        return -1000;
    }

    public boolean isFaceUp(){
        return this.isfaceUp;
    }

    public boolean isFaceDown(){
        return !this.isfaceUp;
    }

    public boolean flip(){
        this.isfaceUp = !this.isfaceUp;
        return this.isfaceUp;
    }

}
