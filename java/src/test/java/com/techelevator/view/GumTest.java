package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void testGum() {
        Item test = new Gum("K1", "Chewy", new BigDecimal("1.00"), "Gum");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testGumNoise(){
        Item test = new Gum("K1", "Chewy", new BigDecimal("1.00"), "Gum");
        test.getNoise();
    }

}