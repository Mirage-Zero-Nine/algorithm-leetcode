package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalancedStringSplit1221Test {

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
}
