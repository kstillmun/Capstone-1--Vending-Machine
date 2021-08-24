package com.techelevator.view;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipTest {

    @Test
    public void testChip() {
        Chip test = new Chip("K1", "Chewy", new BigDecimal("1.00"), "Chip");

        String actualResult = test.getName();
        String expectedResult = "Chewy";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testChipNoise(){
        Item test = new Chip("K1", "Chewy", new BigDecimal("1.00"), "Chip");
        test.getNoise();
    }

}