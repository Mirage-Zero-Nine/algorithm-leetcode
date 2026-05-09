package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TwoSumStructure170Test {

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
}
