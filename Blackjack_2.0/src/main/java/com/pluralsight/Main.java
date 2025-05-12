package com.pluralsight;

public class Main {
    public static void main(String[] args) {

        Deck d = new Deck();
        d.shuffle();

        Hand h1 = new Hand();

        Card c1 = d.deal();

        c1.flip();

        display(c1);

        System.out.println(c1.getPointValue());

        Card c2 = d.deal();
        c2.flip();

        display(c2);
        System.out.println(c2.getPointValue());

        h1.deal(c1);
        h1.deal(c2);

        System.out.println();
        System.out.println(h1.getValue());


    }

    public static void display(Card c){
        if(c.getSuit().equalsIgnoreCase("Hearts") || c.getSuit().equalsIgnoreCase("Diamonds") ){
            System.out.println(ColorCodes.RED + c.getValue() + " " + c.getSuit() + ColorCodes.RESET );
        }
        else{
            System.out.println(c.getValue() + " " + c.getSuit());
        }

    }
}