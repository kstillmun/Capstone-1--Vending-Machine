package com.techelevator.view;

import java.math.BigDecimal;

public class Drink extends Item{

    public Drink(String slot, String name, BigDecimal price, String typeOfItem) {
        super(slot,name,price,typeOfItem);
    }

    public String getNoise(){
        return "Glug Glug, Yum!";
    }
}
