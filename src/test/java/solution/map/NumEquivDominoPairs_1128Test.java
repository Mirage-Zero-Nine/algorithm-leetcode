package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumEquivDominoPairs_1128Test {

    private final NumEquivDominoPairs_1128 test = new NumEquivDominoPairs_1128();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
        assertEquals(3, test.numEquivDominoPairs(new int[][]{{1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numEquivDominoPairs(new int[][]{{1, 2}}));
        assertEquals(0, test.numEquivDominoPairs(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.numEquivDominoPairs(new int[][]{{1, 1}, {1, 1}, {1, 1}, {1, 1}}));
    }

    @Test
    public void testAllSameDominoes() {
        // 5 same dominoes -> C(5,2) = 10
        assertEquals(10, test.numEquivDominoPairs(new int[][]{{2, 3}, {3, 2}, {2, 3}, {3, 2}, {2, 3}}));
    }

    @Test
    public void testNoPairs() {
        assertEquals(0, test.numEquivDominoPairs(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}));
    }

    @Test
    public void testSameValueDomino() {
        // [1,1] and [1,1] are equivalent
        assertEquals(1, test.numEquivDominoPairs(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void testTwoPairsOfDifferentTypes() {
        // [1,2],[2,1] is one pair; [3,4],[4,3] is another pair
        assertEquals(2, test.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {3, 4}, {4, 3}}));
    }

    @Test
    public void testSingleDomino() {
        assertEquals(0, test.numEquivDominoPairs(new int[][]{{9, 9}}));
    }

    @Test
    public void testMixedEquivalences() {
        // [1,2] appears 3 times -> C(3,2)=3; [3,3] appears 2 times -> C(2,2)=1; total=4
        assertEquals(4, test.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {1, 2}, {3, 3}, {3, 3}}));
    }

    @Test
    public void testGiantCase() {
        int[][] dominoes = new int[1000][2];
        for (int i = 0; i < 1000; i++) {
            dominoes[i] = new int[]{1, 2};
        }
        // C(1000, 2) = 499500
        assertEquals(499500, test.numEquivDominoPairs(dominoes));
    }
}
