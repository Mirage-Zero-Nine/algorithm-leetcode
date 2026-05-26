package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxConsecutiveAnswers2024Test {

    private final MaxConsecutiveAnswers_2024 test = new MaxConsecutiveAnswers_2024();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maxConsecutiveAnswers("TTFF", 2));
        assertEquals(3, test.maxConsecutiveAnswers("TFFT", 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.maxConsecutiveAnswers("T", 0));
        assertEquals(2, test.maxConsecutiveAnswers("TF", 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    @Test
    public void testAllT() {
        assertEquals(5, test.maxConsecutiveAnswers("TTTTT", 0));
    }

    @Test
    public void testAllF() {
        assertEquals(4, test.maxConsecutiveAnswers("FFFF", 0));
    }

    @Test
    public void testAllTWithK() {
        assertEquals(5, test.maxConsecutiveAnswers("TTTTT", 2));
    }

    @Test
    public void testAlternating() {
        assertEquals(3, test.maxConsecutiveAnswers("TFTF", 1));
    }

    @Test
    public void testKEqualsLength() {
        assertEquals(6, test.maxConsecutiveAnswers("TFTFTF", 6));
    }

    @Test
    public void testKZeroMixed() {
        assertEquals(2, test.maxConsecutiveAnswers("TTFFTT", 0));
    }

    @Test
    public void testGiantCase() {
        String s = "T".repeat(5000) + "F".repeat(5000);
        assertEquals(10000, test.maxConsecutiveAnswers(s, 5000));
    }
}
