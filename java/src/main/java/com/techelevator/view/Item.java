package com.techelevator.view;

import java.math.BigDecimal;

public abstract class Item {

    private String slot;
    private String name;
    private String itemType;
    private BigDecimal price;
    private int stock=5;

    public Item(String slot, String name, BigDecimal price, String itemType) {
        this.slot= slot;
        this.name= name;
        this.price=price;
        this.itemType= itemType;
    }

    public String getName(){
        return name;
    }

    public String getSlot(){
        return slot;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public int getStock() {
        return stock;
    }

    public abstract String getNoise();

    public void dispenseItem(){
        this.stock--;
    }
}
