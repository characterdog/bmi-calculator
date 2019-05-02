package com.github.characterdog.bmicalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    @Test
    public void testBmiCalc() {
        assertEquals(24.9, MainActivity.calculateBmi(1.9, 90), 0);
        assertEquals(25.0, MainActivity.calculateBmi(2, 100), 0);
    }

    @Test
    public void testGetCategory() {
        assertEquals("Very severely underweight", MainActivity.getCategory(14.9999));
        assertEquals("Severely underweight", MainActivity.getCategory(15.9999));
        assertEquals("Underweight", MainActivity.getCategory(18.4999));
        assertEquals("Normal (healthy weight)", MainActivity.getCategory(24.9999));
        assertEquals("Overweight", MainActivity.getCategory(29.9999));
        assertEquals("Obese Class I (Moderately obese)", MainActivity.getCategory(34.9999));
        assertEquals("Obese Class II (Severely obese)", MainActivity.getCategory(39.9999));
        assertEquals("Obese Class III (Very severely obese)", MainActivity.getCategory(44.9999));
        assertEquals("Obese Class IV (MOrbidly obese)", MainActivity.getCategory(49.9999));
        assertEquals("Obese Class V  (Super obese)", MainActivity.getCategory(59.9999));
        assertEquals("Obese Class VI (Hyper obese)", MainActivity.getCategory(60));
    }

    // Begin length conversion tests
    @Test
    public void testConvertInchToCentimeter(){
        assertEquals(190.5, MainActivity.convertInchToCentimeter(75), 0);
    }

    @Test
    public void testConvertMeterToCentimeter(){
        assertEquals(175, MainActivity.convertMeterToCentimeter(1.75), 0);
    }

    @Test
    public void testConvertFootToCentimeter(){
        assertEquals(163.83, MainActivity.convertFootToCentimeter(5.375), 0);
    }

    @Test
    public void convertHandToCentimeter(){
        assertEquals(174.75199999999998, MainActivity.convertHandToCentimeter(17.2), 0);
    }

    @Test
    public void testConvertDecimeterToCentimeter(){
        assertEquals(175, MainActivity.convertDecimeterToCentimeter(17.5), 0);
    }

    @Test
    public void testConvertYardToCentimeter(){
        assertEquals(170.07840000000002, MainActivity.convertYardToCentimeter(1.86), 0);
    }
    // End of length conversion tests

    // Begin weight conversion tests
    @Test
    public void testConvertPoundToKilogram(){
        assertEquals(68.03886, MainActivity.convertPoundToKilogram(150), 0);
    }

    @Test
    public void testConvertGramToKilogram(){
        assertEquals(1.5, MainActivity.convertGramToKilogram(1500), 0);

    }

    @Test
    public void testConvertOunceToKilogram(){
        assertEquals(1.67262168, MainActivity.convertOunceToKilogram(59), 0);
    }

    @Test
    public void testConvertDecagramToKilogram(){
        assertEquals(1.75, MainActivity.convertDecagramToKilogram(175), 0);
    }

    @Test
    public void testConvertShortHundredWeightToKilogram(){
        assertEquals(136.077711, MainActivity.convertShortHundredWeightToKilogram(3), 0);
    }

    @Test
    public void testConvertLongHundredWeightToKilogram(){
        assertEquals(152.40703632, MainActivity.convertLongHundredWeightToKilogram(3), 0);
    }

    @Test
    public void testConvertAvoirdupoisPoundToKilogram(){
        assertEquals(3.17514701, MainActivity.convertAvoirdupoisPoundToKilogram(7), 0);
    }
    // End of weight conversion tests
}