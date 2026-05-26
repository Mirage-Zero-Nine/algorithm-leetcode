package solutions.slidingwindow;

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

    @Test
    public void testTakeAllFromLeft() {
        assertEquals(6, test.maxScore(new int[]{3, 3, 1, 1, 1}, 2));
    }

    @Test
    public void testTakeAllFromRight() {
        assertEquals(6, test.maxScore(new int[]{1, 1, 1, 3, 3}, 2));
    }

    @Test
    public void testSingleElement() {
        assertEquals(100, test.maxScore(new int[]{100}, 1));
    }

    @Test
    public void testKEqualsLength() {
        assertEquals(15, test.maxScore(new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.maxScore(new int[]{2, 3}, 1));
        assertEquals(5, test.maxScore(new int[]{2, 3}, 2));
    }

    @Test
    public void testNegativeValues() {
        // Problem states points are positive, but test with zeros
        assertEquals(0, test.maxScore(new int[]{0, 0, 0}, 2));
    }

    @Test
    public void testGiantCase() {
        int[] cards = new int[100000];
        for (int i = 0; i < 100000; i++) {
            cards[i] = 1;
        }
        cards[0] = 1000;
        cards[99999] = 1000;
        assertEquals(2998, test.maxScore(cards, 1000));
    }
}
