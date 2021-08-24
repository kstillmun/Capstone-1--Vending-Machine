package com.techelevator.view;

import java.math.BigDecimal;

public class Chip extends Item{

    public Chip(String slot, String name, BigDecimal price, String typeOfItem) {
        super(slot,name,price,typeOfItem);
    }

    public String getNoise(){
        return "Crunch Crunch, Yum!";
    }
}
