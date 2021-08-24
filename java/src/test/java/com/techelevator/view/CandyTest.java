package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void testCandy() {
        Candy test = new Candy("K1", "Chewy", new BigDecimal("1.00"), "Candy");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testCandyNoise(){
        Item test = new Candy("K1", "Chewy", new BigDecimal("1.00"), "Candy");
        test.getNoise();
    }


}