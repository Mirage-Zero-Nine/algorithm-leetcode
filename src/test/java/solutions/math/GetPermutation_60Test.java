package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GetPermutation_60Test {

    private final GetPermutation_60 test = new GetPermutation_60();

    @Test
    public void testHappyCases() {
        assertEquals("213", test.getPermutation(3, 3));
        assertEquals("2314", test.getPermutation(4, 9));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("1", test.getPermutation(1, 1));
        assertEquals("12", test.getPermutation(2, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals("2134", test.getPermutation(4, 7));
    }

    @Test
    public void testFirstPermutation() {
        assertEquals("123", test.getPermutation(3, 1));
    }

    @Test
    public void testLastPermutation() {
        assertEquals("321", test.getPermutation(3, 6));
    }

    @Test
    public void testN2K2() {
        assertEquals("21", test.getPermutation(2, 2));
    }

    @Test
    public void testN4Last() {
        assertEquals("4321", test.getPermutation(4, 24));
    }

    @Test
    public void testN5Middle() {
        assertEquals("31245", test.getPermutation(5, 49));
    }

    @Test
    public void testNegativeCaseLastOfN4() {
        // k=24 is the last permutation for n=4
        assertEquals("4321", test.getPermutation(4, 24));
    }

    @Test
    public void testGiantCase() {
        // n=9, k=362880 (9!) is the last permutation "987654321"
        assertEquals("987654321", test.getPermutation(9, 362880));
    }
}
