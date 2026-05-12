package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumEquivDominoPairs1128Test {

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
}
