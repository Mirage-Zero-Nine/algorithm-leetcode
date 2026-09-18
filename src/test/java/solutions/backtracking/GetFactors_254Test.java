package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Contract tests for the non-trivial-factor products returned by {@link GetFactors_254}. */
class GetFactors_254Test {
    private final GetFactors_254 solution = new GetFactors_254();

    @Test
    void valuesBelowFourHaveNoFactorCombination() {
        assertExact(-7);
        assertExact(0);
        assertExact(1);
        assertExact(2);
        assertExact(3);
    }

    @Test
    void primeHasNoCombination() {
        assertExact(37);
    }

    @Test
    void anotherLargePrimeHasNoCombination() {
        assertExact(999983);
    }

    @Test
    void fourIsTheSmallestComposite() {
        assertExact(4, "2*2");
    }

    @Test
    void sixIsAUniqueSemiprime() {
        assertExact(6, "2*3");
    }

    @Test
    void squareOfPrime() {
        assertExact(9, "3*3");
    }

    @Test
    void tenIsAnotherSemiprime() {
        assertExact(10, "2*5");
    }

    @Test
    void eightIncludesRepeatedFactorsAndACompositeFactor() {
        assertExact(8, "2*2*2", "2*4");
    }

    @Test
    void twelveHasThreeDistinctDecompositions() {
        assertExact(12, "2*2*3", "2*6", "3*4");
    }

    @Test
    void sixteenCoversPowersOfTwo() {
        assertExact(16, "2*2*2*2", "2*2*4", "2*8", "4*4");
    }

    @Test
    void eighteenCombinesRepeatedAndDifferentPrimes() {
        assertExact(18, "2*3*3", "2*9", "3*6");
    }

    @Test
    void twentyFourHasLongAndShortCombinations() {
        assertExact(24, "2*2*2*3", "2*2*6", "2*3*4", "2*12", "3*8", "4*6");
    }

    @Test
    void twentySevenIsARepeatedPrimePower() {
        assertExact(27, "3*3*3", "3*9");
    }

    @Test
    void thirtyHasThreeDistinctPrimeFactors() {
        assertExact(30, "2*3*5", "2*15", "3*10", "5*6");
    }

    @Test
    void thirtyTwoHasManyRepeatedFactors() {
        assertExact(32, "2*2*2*2*2", "2*2*2*4", "2*2*8", "2*4*4", "2*16", "4*8");
    }

    @Test
    void thirtySixIncludesEqualAndUnequalPairs() {
        assertExact(36, "2*2*3*3", "2*2*9", "2*3*6", "2*18", "3*3*4", "3*12", "4*9", "6*6");
    }

    @Test
    void fortyEightExercisesDeeperRecursion() {
        assertExact(48, "2*2*2*2*3", "2*2*2*6", "2*2*3*4", "2*2*12", "2*3*8", "2*4*6", "2*24", "3*4*4", "3*16", "4*12", "6*8");
    }

    @Test
    void sixtyHasThreePrimeFactorsAndSeveralPairs() {
        assertExact(60, "2*2*3*5", "2*2*15", "2*3*10", "2*5*6", "2*30", "3*4*5", "3*20", "4*15", "5*12", "6*10");
    }

    @Test
    void sixtyFourIsAHighMultiplicityPower() {
        assertExact(64, "2*2*2*2*2*2", "2*2*2*2*4", "2*2*2*8", "2*2*4*4", "2*2*16", "2*4*8", "2*32", "4*4*4", "4*16", "8*8");
    }

    @Test
    void seventyTwoHasFifteenCombinations() {
        assertExact(72, "2*2*2*3*3", "2*2*2*9", "2*2*3*6", "2*2*18", "2*3*3*4", "2*3*12", "2*4*9", "2*6*6", "2*36", "3*3*8", "3*4*6", "3*24", "4*18", "6*12", "8*9");
    }

    @Test
    void oneHundredHasRepeatedFactorsAndSquares() {
        assertExact(100, "2*2*5*5", "2*2*25", "2*5*10", "2*50", "4*5*5", "4*25", "5*20", "10*10");
    }

    @Test
    void oneHundredTwentyHasTwentyCombinations() {
        assertExact(120, "2*2*2*3*5", "2*2*2*15", "2*2*3*10", "2*2*5*6", "2*2*30", "2*3*4*5", "2*3*20", "2*4*15", "2*5*12", "2*6*10", "2*60", "3*4*10", "3*5*8", "3*40", "4*5*6", "4*30", "5*24", "6*20", "8*15", "10*12");
    }

    @Test
    void millionIsLargeButWithinTheProblemBudget() {
        List<List<Integer>> result = solution.getFactors(1_000_000);
        assertEquals(1042, countByDivisorDynamicProgramming(1_000_000));
        assertEquals(1042, result.size());
        assertWellFormed(1_000_000, result);
    }

    @Test
    void everyValueThroughOneHundredTwentyMatchesIndependentProductDp() {
        for (int n = 1; n <= 120; n++) {
            assertEquals(independentCombinations(n), canonical(solution.getFactors(n)), "factor combinations for " + n);
        }
    }

    private void assertExact(int n, String... expected) {
        List<List<Integer>> first = solution.getFactors(n);
        Set<String> actual = canonical(first);
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertEquals(expectedSet.size(), expected.length, "expected data contains a duplicate");
        assertEquals(expectedSet, actual, "factor combinations for " + n);
        assertWellFormed(n, first);
        if (n >= 4) {
            first.clear();
            assertEquals(expectedSet, canonical(solution.getFactors(n)));
        }
    }

    private Set<String> canonical(List<List<Integer>> combinations) {
        Set<String> canonical = combinations.stream()
                .map(factors -> factors.stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining("*")))
                .collect(Collectors.toSet());
        assertEquals(combinations.size(), canonical.size(), "duplicate combination returned");
        return canonical;
    }

    private void assertWellFormed(int n, List<List<Integer>> combinations) {
        Set<String> seen = new HashSet<>();
        for (List<Integer> factors : combinations) {
            assertFalse(factors.isEmpty());
            assertTrue(factors.size() >= 2, "a combination must contain at least two factors: " + factors);
            long product = 1;
            for (int factor : factors) {
                assertTrue(factor >= 2);
                assertTrue(factor < n, "the input itself is not a factor: " + factors);
                product *= factor;
            }
            assertEquals(n, product, "invalid product: " + factors);
            assertTrue(seen.add(factors.stream().sorted().map(String::valueOf).collect(Collectors.joining("*"))),
                    "duplicate combination: " + factors);
        }
    }

    /**
     * Independent unbounded multiplicative coin-change DP. Processing factors in ascending order
     * makes every multiset appear once, without using the solution's target/square-root recursion.
     */
    private Set<String> independentCombinations(int n) {
        List<Set<List<Integer>>> dp = java.util.stream.IntStream.rangeClosed(0, n)
                .mapToObj(ignored -> new HashSet<List<Integer>>())
                .collect(Collectors.toList());
        dp.get(1).add(List.of());
        for (int factor = 2; factor < n; factor++) {
            for (int product = factor; product <= n; product++) {
                if (product % factor != 0) {
                    continue;
                }
                for (List<Integer> prior : dp.get(product / factor)) {
                    List<Integer> combination = new java.util.ArrayList<>(prior);
                    combination.add(factor);
                    dp.get(product).add(combination);
                }
            }
        }
        return dp.get(n).stream()
                .filter(combination -> combination.size() >= 2)
                .map(combination -> combination.stream().map(String::valueOf).collect(Collectors.joining("*")))
                .collect(Collectors.toSet());
    }

    /** Counts the same multiplicative partitions using only the divisors of n, not solution output. */
    private long countByDivisorDynamicProgramming(int n) {
        List<Integer> divisors = java.util.stream.IntStream.rangeClosed(1, n)
                .filter(candidate -> n % candidate == 0)
                .boxed()
                .toList();
        long[] counts = new long[divisors.size()];
        counts[0] = 1; // product 1: the empty factor sequence
        for (int factor : divisors) {
            if (factor < 2 || factor == n) {
                continue;
            }
            for (int productIndex = 1; productIndex < divisors.size(); productIndex++) {
                int product = divisors.get(productIndex);
                if (product % factor != 0) {
                    continue;
                }
                int prior = product / factor;
                counts[productIndex] += counts[divisors.indexOf(prior)];
            }
        }
        return counts[divisors.indexOf(n)];
    }
}
