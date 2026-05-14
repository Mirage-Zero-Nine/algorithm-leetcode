package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CombinationSum4_377Test {

    private final CombinationSum4_377 test = new CombinationSum4_377();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.combinationSum4(new int[]{1, 2, 3}, 4));
        assertEquals(0, test.combinationSum4(new int[]{9}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.combinationSum4(new int[]{1, 2, 3}, 0));
        assertEquals(1, test.combinationSum4(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(274, test.combinationSum4(new int[]{1, 2, 3}, 10));
    }

    @Test
    public void testSingleElementEqualsTarget() {
        assertEquals(1, test.combinationSum4(new int[]{5}, 5));
    }

    @Test
    public void testSingleElementCannotReachTarget() {
        assertEquals(0, test.combinationSum4(new int[]{3}, 2));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.combinationSum4(new int[]{1, 2}, 3));
    }

    @Test
    public void testAllElementsLargerThanTarget() {
        assertEquals(0, test.combinationSum4(new int[]{5, 6, 7}, 4));
    }

    @Test
    public void testTargetOne() {
        assertEquals(1, test.combinationSum4(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testLargerTarget() {
        assertEquals(121415, test.combinationSum4(new int[]{1, 2, 3}, 20));
    }

    @Test
    public void testTwoElementsTarget4() {
        assertEquals(5, test.combinationSum4(new int[]{1, 2}, 4));
    }
}
