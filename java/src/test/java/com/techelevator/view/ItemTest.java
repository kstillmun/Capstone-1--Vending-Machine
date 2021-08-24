package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testItem() {
        Item test = new Chip("K1", "Chewy", new BigDecimal("1.00"), "Chip");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testName() {
        Item test = new Chip("K1", "Chewy", new BigDecimal("1.00"), "Chip");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testStock() {
        Item test = new Gum("K1", "Chewy", new BigDecimal("1.00"), "Gum");
        int expectedResult=5;
        int actualResult= test.getStock();
        assertEquals(expectedResult,actualResult);
    }


    @Test
    public void testgetPrice() {
        Item test = new Gum("K1", "Chewy", new BigDecimal("1.05"), "Gum");
        BigDecimal actualResult= test.getPrice();
        BigDecimal expectedResult= new BigDecimal("1.05");
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void testSlot() {
        Item test = new Gum("K1", "Chewy", new BigDecimal("1.00"), "Gum");
        String actualResult = test.getSlot();
        String expectedResult = "K1";
        assertEquals(expectedResult, actualResult);
    }

}