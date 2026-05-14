package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalancedStringSplit_1221Test {

    private final BalancedStringSplit_1221 test = new BalancedStringSplit_1221();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.balancedStringSplit("RLRRLLRLRL"));
        assertEquals(3, test.balancedStringSplit("RLLLLRRRLR"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.balancedStringSplit("RL"));
        assertEquals(1, test.balancedStringSplit("RRLL"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.balancedStringSplit("LLLLRRRR"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(2, test.balancedStringSplit("RLRL"));
        assertEquals(2, test.balancedStringSplit("LLRRLR"));
        assertEquals(2, test.balancedStringSplit("LRLR"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.balancedStringSplit(""));
        assertEquals(1, test.balancedStringSplit("LR"));
        assertEquals(2, test.balancedStringSplit("LRRL"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(10, test.balancedStringSplit("LR".repeat(10)));
    }

    @Test
    public void testNestedBalanced() {
        assertEquals(1, test.balancedStringSplit("LLRR"));
        assertEquals(2, test.balancedStringSplit("RLLR"));
    }

    @Test
    public void testLongAlternating() {
        assertEquals(5, test.balancedStringSplit("RLRLRLRLRL"));
    }

    @Test
    public void testSinglePair() {
        assertEquals(3, test.balancedStringSplit("RRRRLLLLLRLLLLRRRRR"));
    }

    @Test
    public void testGiantRepeated() {
        assertEquals(500, test.balancedStringSplit("RL".repeat(500)));
    }
}
