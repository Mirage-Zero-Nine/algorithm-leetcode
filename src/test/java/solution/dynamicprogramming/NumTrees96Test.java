package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumTrees96Test {

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
}
