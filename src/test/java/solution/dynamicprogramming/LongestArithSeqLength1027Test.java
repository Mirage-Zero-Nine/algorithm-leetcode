package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestArithSeqLength1027Test {

    private final LongestArithSeqLength_1027 test = new LongestArithSeqLength_1027();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.dp(new int[]{3, 6, 9, 12}));
        assertEquals(3, test.dp(new int[]{9, 4, 7, 2, 10}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.dp(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.dp(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
    }
}
