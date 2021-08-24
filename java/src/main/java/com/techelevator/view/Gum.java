package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends Item {

    public Gum(String slot, String name, BigDecimal price, String typeOfItem) {

        super(slot,name,price,typeOfItem);
    }

    public String getNoise(){
        return "Chew Chew, Yum!";
    }
}
