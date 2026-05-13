package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxTurbulenceSize_978Test {

    private final MaxTurbulenceSize_978 test = new MaxTurbulenceSize_978();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        assertEquals(2, test.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.maxTurbulenceSize(new int[]{100}));
        assertEquals(1, test.maxTurbulenceSize(new int[]{1, 1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.maxTurbulenceSize(new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 0}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.maxTurbulenceSize(new int[]{1, 2}));
    }

    @Test
    public void testTwoEqualElements() {
        assertEquals(1, test.maxTurbulenceSize(new int[]{5, 5}));
    }

    @Test
    public void testPerfectTurbulence() {
        assertEquals(5, test.maxTurbulenceSize(new int[]{1, 3, 1, 3, 1}));
    }

    @Test
    public void testDecreasingThenIncreasing() {
        assertEquals(3, test.maxTurbulenceSize(new int[]{5, 4, 3, 2, 3}));
    }

    @Test
    public void testAllSame() {
        assertEquals(1, test.maxTurbulenceSize(new int[]{7, 7, 7, 7, 7}));
    }

    @Test
    public void testAlternating() {
        assertEquals(6, test.maxTurbulenceSize(new int[]{2, 0, 2, 0, 2, 0}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = (i % 2 == 0) ? 1 : 2;
        }
        assertEquals(1000, test.maxTurbulenceSize(arr));
    }
}
