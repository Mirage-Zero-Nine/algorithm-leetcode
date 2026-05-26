package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TwoSumStructure_170Test {

    @Test
    public void testHappyCases() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(1); ts.add(3); ts.add(5);
        assertTrue(ts.find(4));
        assertFalse(ts.find(7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        assertFalse(ts.find(0));
        ts.add(0);
        assertFalse(ts.find(0));
        ts.add(0);
        assertTrue(ts.find(0));
    }

    @Test
    public void testLargeCase() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        for (int i = 1; i <= 10; i++) ts.add(i);
        assertTrue(ts.find(11));
        assertTrue(ts.find(3));
        assertFalse(ts.find(21));
    }

    @Test
    public void testEmptyStructure() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        assertFalse(ts.find(0));
        assertFalse(ts.find(1));
        assertFalse(ts.find(-1));
    }

    @Test
    public void testSingleElement() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(5);
        assertFalse(ts.find(10)); // need two 5s
        assertFalse(ts.find(5));  // only one element
    }

    @Test
    public void testDuplicateElements() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(3); ts.add(3);
        assertTrue(ts.find(6));
        assertFalse(ts.find(3)); // no pair sums to 3 with only 3s
    }

    @Test
    public void testNegativeNumbers() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-1); ts.add(-2); ts.add(-3);
        assertTrue(ts.find(-3)); // -1 + -2
        assertTrue(ts.find(-5)); // -2 + -3
        assertFalse(ts.find(0));
    }

    @Test
    public void testMixedPositiveNegative() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-1); ts.add(1);
        assertTrue(ts.find(0));
        assertFalse(ts.find(2)); // no pair
    }

    @Test
    public void testFindAfterMultipleAdds() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(1);
        assertFalse(ts.find(2));
        ts.add(1);
        assertTrue(ts.find(2));
    }

    @Test
    public void testLargeValues() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(Integer.MAX_VALUE / 2);
        ts.add(Integer.MAX_VALUE / 2);
        assertTrue(ts.find(Integer.MAX_VALUE / 2 * 2));
    }

    @Test
    public void testGiantCase() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        for (int i = 0; i < 5000; i++) ts.add(i);
        assertTrue(ts.find(9997)); // 4999 + 4998
        assertTrue(ts.find(9995)); // 4999 + 4996
        assertTrue(ts.find(1)); // 0 + 1
        assertFalse(ts.find(10000));
    }
}
