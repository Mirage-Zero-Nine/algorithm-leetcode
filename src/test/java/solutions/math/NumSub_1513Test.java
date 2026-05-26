package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSub_1513Test {

    private final NumSub_1513 test = new NumSub_1513();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.numSub("0110111"));
        assertEquals(6, test.numSub("111"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numSub("0"));
        assertEquals(1, test.numSub("1"));
        assertEquals(0, test.numSub("000"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.numSub("1111111111"));
    }

    @Test
    public void testAlternating() {
        assertEquals(3, test.numSub("101010"));
    }

    @Test
    public void testAllOnes() {
        assertEquals(3, test.numSub("11"));
        assertEquals(10, test.numSub("1111"));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.numSub("00000"));
    }

    @Test
    public void testSingleOne() {
        assertEquals(1, test.numSub("01"));
        assertEquals(1, test.numSub("10"));
    }

    @Test
    public void testMultipleGroups() {
        assertEquals(6, test.numSub("11011"));  // "11" contributes 3, "11" contributes 3
    }

    @Test
    public void testOptimizedHappyCases() {
        assertEquals(9, test.numSubOptimized("0110111"));
        assertEquals(6, test.numSubOptimized("111"));
    }

    @Test
    public void testOptimizedEdgeCases() {
        assertEquals(0, test.numSubOptimized("0"));
        assertEquals(1, test.numSubOptimized("1"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append('1');
        }
        String s = sb.toString();
        // Both methods should return the same result
        assertEquals(test.numSub(s), test.numSubOptimized(s));
    }

    @Test
    public void testConsistencyBetweenMethods() {
        String[] inputs = {"0110111", "111", "101010", "11011", "1111111111"};
        for (String input : inputs) {
            assertEquals(test.numSub(input), test.numSubOptimized(input));
        }
    }
}
