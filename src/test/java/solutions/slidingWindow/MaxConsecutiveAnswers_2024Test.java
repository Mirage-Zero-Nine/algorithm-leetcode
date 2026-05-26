package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class MaxConsecutiveAnswers_2024Test {

    private final MaxConsecutiveAnswers_2024 solution = new MaxConsecutiveAnswers_2024();

    @Test
    public void testHappy_example1() {
        assertEquals(4, solution.maxConsecutiveAnswers("TTFF", 2));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(3, solution.maxConsecutiveAnswers("TFFT", 1));
    }

    @Test
    public void testHappy_example3() {
        assertEquals(5, solution.maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    @Test
    public void testHappy_allSame() {
        assertEquals(5, solution.maxConsecutiveAnswers("TTTTT", 0));
    }

    @Test
    public void testHappy_flipAll() {
        assertEquals(5, solution.maxConsecutiveAnswers("TFTFT", 5));
    }

    @Test
    public void testNegative_kZeroAlternating() {
        assertEquals(1, solution.maxConsecutiveAnswers("TFTF", 0));
    }

    @Test
    public void testEdge_singleT() {
        assertEquals(1, solution.maxConsecutiveAnswers("T", 0));
    }

    @Test
    public void testEdge_singleF() {
        assertEquals(1, solution.maxConsecutiveAnswers("F", 0));
    }

    @Test
    public void testEdge_twoCharsFlipOne() {
        assertEquals(2, solution.maxConsecutiveAnswers("TF", 1));
    }

    @Test
    public void testEdge_allF() {
        assertEquals(6, solution.maxConsecutiveAnswers("FFFFFF", 3));
    }

    @Test
    public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sb.append(i % 2 == 0 ? 'T' : 'F');
        }
        int result = solution.maxConsecutiveAnswers(sb.toString(), 25000);
        assertEquals(50000, result);
    }

    @Test
    public void testEmpty() {
        assertEquals(0, solution.maxConsecutiveAnswers("", 3));
    }

    @Test
    public void testSingleCharWithK() {
        assertEquals(1, solution.maxConsecutiveAnswers("T", 5));
        assertEquals(1, solution.maxConsecutiveAnswers("F", 5));
    }

    @Test
    public void testAllT() {
        assertEquals(7, solution.maxConsecutiveAnswers("TTTTTTT", 2));
    }

    @Test
    public void testAllF() {
        assertEquals(8, solution.maxConsecutiveAnswers("FFFFFFFF", 0));
    }

    @Test
    public void testKZeroLongestRun() {
        assertEquals(2, solution.maxConsecutiveAnswers("TTFF", 0));
        assertEquals(3, solution.maxConsecutiveAnswers("TTTFFF", 0));
        assertEquals(1, solution.maxConsecutiveAnswers("TFTF", 0));
    }

    @Test
    public void testKGreaterThanOrEqualLength() {
        assertEquals(4, solution.maxConsecutiveAnswers("TFTF", 4));
        assertEquals(5, solution.maxConsecutiveAnswers("TFFTF", 10));
        assertEquals(3, solution.maxConsecutiveAnswers("TFT", 3));
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rand = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append(rand.nextBoolean() ? 'T' : 'F');
        }
        String s = sb.toString();
        int k = 15;
        int expected = bruteForce(s, k);
        assertEquals(expected, solution.maxConsecutiveAnswers(s, k));
    }

    @Test
    public void testPropertyResultBounds() {
        Random rand = new Random(42L);
        for (int t = 0; t < 50; t++) {
            int len = rand.nextInt(50) + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(rand.nextBoolean() ? 'T' : 'F');
            }
            String s = sb.toString();
            int k = rand.nextInt(len + 1);
            int result = solution.maxConsecutiveAnswers(s, k);
            assertTrue(result >= 1 && result <= len,
                    "Result " + result + " out of bounds for length " + len);
        }
    }

    @Test
    public void testLargeString1000CrossCheck() {
        Random rand = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(rand.nextBoolean() ? 'T' : 'F');
        }
        String s = sb.toString();
        // Cross-check a 100-char substring against brute-force
        String sub = s.substring(0, 100);
        int k = 10;
        assertEquals(bruteForce(sub, k), solution.maxConsecutiveAnswers(sub, k));
    }

    private int bruteForce(String s, int k) {
        int n = s.length();
        int max = 0;
        for (char c : new char[]{'T', 'F'}) {
            for (int i = 0; i < n; i++) {
                int flips = 0;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != c) flips++;
                    if (flips > k) break;
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
