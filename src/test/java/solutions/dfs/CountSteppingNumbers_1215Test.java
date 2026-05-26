package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CountSteppingNumbers_1215Test {

    private final CountSteppingNumbers_1215 test = new CountSteppingNumbers_1215();

    @Test
    public void testHappyCases() {
        List<Integer> result = test.countSteppingNumbers(0, 21);
        assertEquals(13, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        List<Integer> result = test.countSteppingNumbers(0, 0);
        assertTrue(result.contains(0));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.countSteppingNumbers(100, 200);
        assertTrue(result.contains(101));
        assertTrue(result.contains(121));
    }

    @Test
    public void testSingleDigitRange() {
        List<Integer> result = test.countSteppingNumbers(0, 9);
        assertEquals(10, result.size()); // 0-9 are all stepping numbers
    }

    @Test
    public void testRangeWithNoSteppingNumbers() {
        // 13 is not stepping (|1-3|=2), 14 is not, etc. But 12 is stepping.
        List<Integer> result = test.countSteppingNumbers(13, 20);
        // stepping numbers: none between 13-20 except... let's check
        // 13: |1-3|=2 no, 14: no, 15: no, 16: no, 17: no, 18: no, 19: no, 20: |2-0|=2 no
        assertEquals(0, result.size());
    }

    @Test
    public void testExactSteppingNumber() {
        List<Integer> result = test.countSteppingNumbers(12, 12);
        assertEquals(1, result.size());
        assertEquals(12, result.get(0));
    }

    @Test
    public void testContains10() {
        List<Integer> result = test.countSteppingNumbers(10, 10);
        assertEquals(1, result.size());
        assertEquals(10, result.get(0));
    }

    @Test
    public void testSorted() {
        List<Integer> result = test.countSteppingNumbers(0, 100);
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i) > result.get(i - 1));
        }
    }

    @Test
    public void testThreeDigitRange() {
        List<Integer> result = test.countSteppingNumbers(100, 999);
        assertTrue(result.contains(101));
        assertTrue(result.contains(123));
        assertTrue(result.contains(210));
        assertTrue(result.contains(987));
    }

    @Test
    public void testGiantRange() {
        List<Integer> result = test.countSteppingNumbers(0, 1000000);
        assertTrue(result.size() > 100);
        // verify all results are stepping numbers
        for (int num : result) {
            assertTrue(isSteppingNumber(num));
        }
    }

    private boolean isSteppingNumber(int n) {
        if (n < 10) return true;
        String s = String.valueOf(n);
        for (int i = 1; i < s.length(); i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != 1) return false;
        }
        return true;
    }
}
