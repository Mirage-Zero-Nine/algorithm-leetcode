package solutions.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinCost_1578Test {
    private final MinCost_1578 solution = new MinCost_1578();

    @Test
    void testBasic() {
        assertEquals(3, solution.minCost("abaac", new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testNoDuplicates() {
        assertEquals(0, solution.minCost("abc", new int[]{1, 2, 3}));
    }

    @Test
    void testAllSame() {
        assertEquals(3, solution.minCost("aaa", new int[]{1, 2, 3}));
    }

    @Test
    void testTwoPairs() {
        assertEquals(9, solution.minCost("aabbcc", new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    void testSingleChar() {
        assertEquals(0, solution.minCost("a", new int[]{5}));
    }

    @Test
    void testTwoSameChars() {
        assertEquals(1, solution.minCost("aa", new int[]{1, 2}));
    }

    @Test
    void testLongGroupSameChar() {
        assertEquals(9, solution.minCost("aaaa", new int[]{3, 4, 5, 2}));
    }

    @Test
    void testAlternating() {
        assertEquals(0, solution.minCost("ababab", new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    void testAllCostOne() {
        assertEquals(2, solution.minCost("aaa", new int[]{1, 1, 1}));
    }

    @Test
    void testNegativeLikeCost() {
        // costs are always positive per problem, but test with zero cost
        assertEquals(0, solution.minCost("aa", new int[]{0, 0}));
    }

    @Test
    void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        int[] cost = new int[10000];
        for (int i = 0; i < 10000; i++) {
            sb.append('a');
            cost[i] = i + 1;
        }
        // keep the max cost (10000), delete all others: sum(1..9999) = 9999*10000/2 = 49995000
        assertEquals(49995000, solution.minCost(sb.toString(), cost));
    }
    @Test void testAdditionalPair() { assertEquals(4, solution.minCost("aa", new int[]{4, 5})); }
    @Test void testAdditionalTriple() { assertEquals(0, solution.minCost("aba", new int[]{1, 2, 3})); }
    @Test void testAdditionalRuns() { assertEquals(6, solution.minCost("aaaa", new int[]{1, 2, 3, 4})); }
    @Test void testAdditionalTwoRuns() { assertEquals(4, solution.minCost("aabb", new int[]{1, 2, 3, 4})); }
    @Test void testAdditionalAlternating() { assertEquals(0, solution.minCost("abab", new int[]{1, 2, 3, 4})); }
    @Test void testAdditionalHigh() { assertEquals(1, solution.minCost("aa", new int[]{100, 1})); }
    @Test void testAdditionalKeepFirst() { assertEquals(1, solution.minCost("aa", new int[]{1, 2})); }
    @Test void testAdditionalKeepLast() { assertEquals(1, solution.minCost("aa", new int[]{2, 1})); }
    @Test void testAdditionalLong() { assertEquals(10, solution.minCost("aaaaa", new int[]{1, 2, 3, 4, 5})); }
    @Test void testAdditionalMixed() { assertEquals(5, solution.minCost("aabcc", new int[]{1, 2, 3, 4, 5})); }
}
