package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxScore1423Test {

    private final MaxScore_1423 test = new MaxScore_1423();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        assertEquals(4, test.maxScore(new int[]{2, 2, 2}, 2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxScore(new int[]{1}, 1));
        assertEquals(55, test.maxScore(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
    }
}
