package solutions.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Combine_77Test {

    private final Combine_77 test = new Combine_77();

    @Test
    public void testLeetCodeExampleOne() {
        Set<List<Integer>> expected = Set.of(
                List.of(1, 2), List.of(1, 3), List.of(1, 4),
                List.of(2, 3), List.of(2, 4), List.of(3, 4));

        assertEquals(expected, canonicalize(test.combine(4, 2)));
        assertEquals(expected, canonicalize(test.combineMath(4, 2)));
    }

    @Test
    public void testLeetCodeExampleTwo() {
        Set<List<Integer>> expected = Set.of(List.of(1));

        assertEquals(expected, canonicalize(test.combine(1, 1)));
        assertEquals(expected, canonicalize(test.combineMath(1, 1)));
    }

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.combine(4, 2);
        assertEquals(6, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.combine(0, 1).size());
        assertEquals(0, test.combineMath(0, 1).size());
        assertEquals(1, test.combine(1, 1).size());
        assertEquals(1, test.combineMath(1, 1).size());
        assertEquals(0, test.combine(3, 4).size());
        assertEquals(0, test.combineMath(3, 4).size());
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.combine(5, 3).size());
        assertEquals(252, test.combine(10, 5).size());
        assertEquals(10, test.combineMath(5, 3).size());
        assertEquals(252, test.combineMath(10, 5).size());
    }

    @Test
    public void testCombineNEqualsK() {
        List<List<Integer>> result = test.combine(3, 3);
        assertEquals(1, result.size());
        assertTrue(result.getFirst().containsAll(List.of(1, 2, 3)));

        List<List<Integer>> mathResult = test.combineMath(3, 3);
        assertEquals(List.of(List.of(1, 2, 3)), mathResult);
    }

    @Test
    public void testCombineKEquals1() {
        List<List<Integer>> result = test.combine(5, 1);
        assertEquals(5, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 1));
    }

    @Test
    public void testCombineN2K1() {
        List<List<Integer>> result = test.combine(2, 1);
        assertEquals(2, result.size());
    }

    @Test
    public void testCombineN5K2() {
        List<List<Integer>> result = test.combine(5, 2);
        assertEquals(10, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 2));
    }

    @Test
    public void testNegativeKZero() {
        assertEquals(0, test.combine(5, 0).size());
        assertEquals(0, test.combineMath(5, 0).size());
    }

    @Test
    public void testNegativeNLessThanK() {
        assertEquals(0, test.combine(2, 5).size());
        assertEquals(0, test.combineMath(2, 5).size());
    }

    @Test
    public void testGiantCase() {
        // C(20, 10) = 184756
        List<List<Integer>> result = test.combine(20, 10);
        assertEquals(184756, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 10));

        List<List<Integer>> mathResult = test.combineMath(20, 10);
        assertEquals(184756, mathResult.size());
        assertTrue(mathResult.stream().allMatch(l -> l.size() == 10));
    }

    /**
     * Exhaustively tests every input permitted by LeetCode: 1 <= n <= 20 and
     * 1 <= k <= n. The result is checked structurally rather than against a
     * second combination generator: the count must equal C(n, k), every result
     * must be a sorted k-subset of [1, n], and no result may be duplicated.
     */
    @ParameterizedTest(name = "n={0}, k={1}")
    @MethodSource("allLeetCodeInputs")
    public void testEveryLeetCodeInputWithBacktracking(int n, int k) {
        assertValidCombinations(n, k, test.combine(n, k));
    }

    @ParameterizedTest(name = "n={0}, k={1}")
    @MethodSource("allLeetCodeInputs")
    public void testEveryLeetCodeInputWithMathematicalRecurrence(int n, int k) {
        assertValidCombinations(n, k, test.combineMath(n, k));
    }

    @Test
    public void testBothApproachesProduceTheSameCombinations() {
        assertEquals(canonicalize(test.combine(8, 4)), canonicalize(test.combineMath(8, 4)));
    }

    @Test
    public void testReturnedCombinationsAreIndependentCopies() {
        List<List<Integer>> backtrackingResult = test.combine(4, 2);
        List<List<Integer>> mathResult = test.combineMath(4, 2);

        assertNotSame(backtrackingResult.get(0), backtrackingResult.get(1));
        assertNotSame(mathResult.get(0), mathResult.get(1));

        backtrackingResult.get(0).add(99);
        mathResult.get(0).add(99);

        assertTrue(backtrackingResult.get(1).stream().noneMatch(value -> value == 99));
        assertTrue(mathResult.get(1).stream().noneMatch(value -> value == 99));
    }

    private static Stream<Arguments> allLeetCodeInputs() {
        return IntStream.rangeClosed(1, 20)
                .boxed()
                .flatMap(n -> IntStream.rangeClosed(1, n)
                        .mapToObj(k -> Arguments.of(n, k)));
    }

    private static void assertValidCombinations(int n, int k, List<List<Integer>> actual) {
        assertNotNull(actual);
        long expectedCount = binomialCoefficient(n, k);
        assertEquals(expectedCount, actual.size(), "Incorrect count for n=" + n + ", k=" + k);

        Set<List<Integer>> unique = new HashSet<>();
        for (List<Integer> combination : actual) {
            assertEquals(k, combination.size(), "Incorrect length for " + combination);

            for (int index = 0; index < combination.size(); index++) {
                int value = combination.get(index);
                assertTrue(value >= 1 && value <= n,
                        "Value " + value + " is outside [1, " + n + "]");
                if (index > 0) {
                    assertTrue(combination.get(index - 1) < value,
                            "Combination is not strictly ascending: " + combination);
                }
            }

            assertTrue(unique.add(List.copyOf(combination)),
                    "Duplicate combination: " + combination);
        }
    }

    private static long binomialCoefficient(int n, int k) {
        int smallerK = Math.min(k, n - k);
        long result = 1;

        for (int i = 1; i <= smallerK; i++) {
            result = result * (n - smallerK + i) / i;
        }

        return result;
    }

    private static Set<List<Integer>> canonicalize(List<List<Integer>> combinations) {
        return combinations.stream()
                .map(List::copyOf)
                .collect(java.util.stream.Collectors.toSet());
    }
}
