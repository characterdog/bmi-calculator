package com.github.characterdog.bmicalculator;

import org.junit.Test;

import static com.github.characterdog.bmicalculator.MainActivity.calculateBmi;
import static org.junit.Assert.*;

public class UnitTest {
    @Test
    public void testBmiCalc() {
        assertEquals(24.9, calculateBmi(1.9, 90), 0);
        assertEquals(25.0, calculateBmi(2, 100), 0);
    }
}