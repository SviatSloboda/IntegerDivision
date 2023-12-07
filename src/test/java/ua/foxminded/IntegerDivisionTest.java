package ua.foxminded;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

class IntegerDivisionTest {

    private IntegerDivision division;

    @BeforeEach
    void setUpIntegerDivisionVariable() {
        division = new IntegerDivision();
    }

    @Test
    void createLongDivision_when_divisor0_thenIllegalArgumentException() {
        Executable operation = () -> division.getIntegerDivision(10, 0);
        assertThrows(IllegalArgumentException.class, operation, "Divisor can't be 0");
    }

    @Test
    void createLongDivision_when_dividendSmallerDivisor_thenIllegalArgumentException() {
        Executable operation = () -> division.getIntegerDivision(3, 4);
        assertThrows(IllegalArgumentException.class, operation, "Dividend can't be less than divisor");
    }

    @Test
    void createLongDivision_when_10and2_then5() {
        //given
        String expected = "_10│2\n" +
                " 10│-\n" +
                " --│5\n" +
                "  0\n";
        //when
        String actual = division.getIntegerDivision(10,2);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void createLongDivision_when_78945and4_then19736() {
        //given
        String expected = "_78945│4\n" +
                " 4    │-----\n" +
                " -    │19736\n" +
                "_38\n" +
                " 36\n" +
                " --\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "     1\n";
        //when
        String actual = division.getIntegerDivision(78945,4);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void createLongDivision_when_23and1_then23() {
        //given
        String expected = "_23│1\n" +
                " 2 │--\n" +
                " - │23\n" +
                " _3\n" +
                "  3\n" +
                "  -\n" +
                "  0\n";
        //when
        String actual = division.getIntegerDivision(23,1);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void createLongDivision_when_999999and752_then1329() {
        //given
        String expected = "_999999│752\n" +
                " 752   │----\n" +
                " ---   │1329\n" +
                "_2479\n" +
                " 2256\n" +
                " ----\n" +
                " _2239\n" +
                "  1504\n" +
                "  ----\n" +
                "  _7359\n" +
                "   6768\n" +
                "   ----\n" +
                "    591\n";
        //when
        String actual = division.getIntegerDivision(999999,752);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void createLongDivision_when_752344896and423434_then1776() {
        //given
        String expected = "_752344896│423434\n" +
                " 423434   │----\n" +
                " ------   │1776\n" +
                "_3289108\n" +
                " 2964038\n" +
                " -------\n" +
                " _3250709\n" +
                "  2964038\n" +
                "  -------\n" +
                "  _2866716\n" +
                "   2540604\n" +
                "   -------\n" +
                "    326112\n";
        //when
        String actual = division.getIntegerDivision(752344896,423434);
        //then
        assertEquals(expected,actual);
    }
}