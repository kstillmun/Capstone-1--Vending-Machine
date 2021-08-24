package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void testDrink() {
        Drink test = new Drink("K1", "Chewy", new BigDecimal("1.00"), "Drink");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDrinkNoise(){
        Item test = new Drink("K1", "Chewy", new BigDecimal("1.00"), "Drink");
        test.getNoise();
    }

}