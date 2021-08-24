package com.techelevator.view;

import java.math.BigDecimal;

public class Candy extends Item{

    public Candy(String slot, String name, BigDecimal price, String itemType) {
        super(slot, name, price, itemType);
    }

    public String getNoise(){
        return "Munch Munch, Yum!";
    }
}
