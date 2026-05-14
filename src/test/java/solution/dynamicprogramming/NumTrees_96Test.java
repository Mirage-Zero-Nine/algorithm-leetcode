package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumTrees_96Test {

    private final NumTrees_96 test = new NumTrees_96();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.numTrees(3));
        assertEquals(14, test.numTrees(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numTrees(1));
        assertEquals(2, test.numTrees(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(42, test.numTrees(5));
    }

    @Test
    public void testN6() {
        assertEquals(132, test.numTrees(6));
    }

    @Test
    public void testN7() {
        assertEquals(429, test.numTrees(7));
    }

    @Test
    public void testN8() {
        assertEquals(1430, test.numTrees(8));
    }

    @Test
    public void testN9() {
        assertEquals(4862, test.numTrees(9));
    }

    @Test
    public void testN10() {
        assertEquals(16796, test.numTrees(10));
    }

    @Test
    public void testN15() {
        assertEquals(9694845, test.numTrees(15));
    }

    @Test
    public void testGiantN19() {
        assertEquals(1767263190, test.numTrees(19));
    }
}
