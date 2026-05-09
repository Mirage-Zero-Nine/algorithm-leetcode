package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindJudge997Test {

    private final FindJudge_997 test = new FindJudge_997();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findJudge(2, new int[][]{{1, 2}}));
        assertEquals(3, test.findJudge(3, new int[][]{{1, 3}, {2, 3}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
        assertEquals(-1, test.findJudge(3, new int[][]{{1, 2}, {2, 3}}));
        assertEquals(1, test.findJudge(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.findJudge(4, new int[][]{{1, 4}, {2, 4}, {3, 4}}));
    }
}
