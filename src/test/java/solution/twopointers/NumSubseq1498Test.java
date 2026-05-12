package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSubseq1498Test {

    private final NumSubseq_1498 test = new NumSubseq_1498();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numSubseq(new int[]{3, 5, 6, 7}, 9));
        assertEquals(6, test.numSubseq(new int[]{3, 3, 6, 8}, 10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 2));
        assertEquals(1, test.numSubseq(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(61, test.numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12));
    }
}
