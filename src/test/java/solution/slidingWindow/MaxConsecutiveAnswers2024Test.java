package solution.slidingwindow;

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
}
