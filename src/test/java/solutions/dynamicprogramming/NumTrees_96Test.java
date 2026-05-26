package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NumTrees_96Test {

    private final NumTrees_96 test = new NumTrees_96();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.numTrees(3));
        assertEquals(14, test.numTrees(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numTrees(1));
        assertEquals(2, test.numTrees(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(42, test.numTrees(5));
    }

    @Test
    public void testN6() {
        assertEquals(132, test.numTrees(6));
    }

    @Test
    public void testN7() {
        assertEquals(429, test.numTrees(7));
    }

    @Test
    public void testN8() {
        assertEquals(1430, test.numTrees(8));
    }

    @Test
    public void testN9() {
        assertEquals(4862, test.numTrees(9));
    }

    @Test
    public void testN10() {
        assertEquals(16796, test.numTrees(10));
    }

    @Test
    public void testN15() {
        assertEquals(9694845, test.numTrees(15));
    }

    @Test
    public void testGiantN19() {
        assertEquals(1767263190, test.numTrees(19));
    }

    /**
     * Reference Catalan numbers C_0..C_19. C_n is the count of unique BSTs
     * with n distinct keys. Hardcoded so the test cannot drift if both
     * the implementation and a reference happen to share a bug.
     * C_19 = 1767263190 is the largest that fits in signed 32-bit int.
     */
    private static final int[] CATALAN = new int[]{
            1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,
            16796, 58786, 208012, 742900, 2674440, 9694845,
            35357670, 129644790, 477638700, 1767263190
    };

    /**
     * Iterable sweep 1..19 against the hardcoded Catalan reference. n=0
     * is excluded because the impl returns 0 for n<3 (specifically
     * numTrees(0)=0), which contradicts the mathematical C_0=1; document
     * this with a dedicated negative test below.
     */
    @ParameterizedTest(name = "numTrees({0}) = {1}")
    @MethodSource("oneToNineteen")
    public void testEveryValueFromOneToNineteen(int n, int expected) {
        assertEquals(expected, test.numTrees(n));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToNineteen() {
        return IntStream.rangeClosed(1, 19).mapToObj(i -> arguments(i, CATALAN[i]));
    }

    /**
     * Document existing impl behaviour for n=0: the corner-case returns
     * n directly, so numTrees(0) = 0. The mathematical C_0 = 1, but the
     * LeetCode constraint is n >= 1 so callers should not invoke n=0.
     */
    @Test
    public void testZeroBehaviorIsImplementationDefined() {
        assertEquals(0, test.numTrees(0));
    }
}
