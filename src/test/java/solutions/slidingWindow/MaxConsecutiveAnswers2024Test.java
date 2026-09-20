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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"T,0,1", "F,0,1", "TF,0,1", "TF,1,2", "TFT,1,3", "FTFT,2,4", "TTFF,0,2", "TTFF,1,3", "TFTFT,2,5", "FFFFT,1,5"})
    void additionalBoundaryCases(String value, int k, int expected) {
        assertEquals(expected, test.maxConsecutiveAnswers(value, k));
    }
}
