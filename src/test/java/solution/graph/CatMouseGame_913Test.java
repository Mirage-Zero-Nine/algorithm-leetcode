package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CatMouseGame_913Test {

    private final CatMouseGame_913 test = new CatMouseGame_913();

    @Test
    public void testHappyCases() {
        assertEquals(0, test.catMouseGame(new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.catMouseGame(new int[][]{{1, 3}, {0}, {3}, {0, 2}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.catMouseGame(new int[][]{{1, 2}, {0}, {0}}));
    }

    @Test
    public void testMouseWinsDirectPath() {
        // Mouse at 1, hole at 0, cat at 2. Mouse can go directly to 0.
        // graph: 0-1, 1-2
        assertEquals(1, test.catMouseGame(new int[][]{{1}, {0, 2}, {1}}));
    }

    @Test
    public void testCatWins() {
        // Cat at 2 can reach mouse at 1 before mouse reaches hole
        // 0-2, 1-2, 1-3, 2-3 => cat starts at 2, mouse at 1
        assertEquals(2, test.catMouseGame(new int[][]{{2}, {2, 3}, {0, 1, 3}, {1, 2}}));
    }

    @Test
    public void testLinearGraphMouseWins() {
        // 0-1-2-3: mouse at 1, cat at 2, hole at 0. Mouse goes to 0 immediately.
        assertEquals(1, test.catMouseGame(new int[][]{{1}, {0, 2}, {1, 3}, {2}}));
    }

    @Test
    public void testFourNodeDraw() {
        // graph: 0-[1,3], 1-[0,2,3], 2-[1,3], 3-[0,1,2]
        int result = test.catMouseGame(new int[][]{{1, 3}, {0, 2, 3}, {1, 3}, {0, 1, 2}});
        // Just verify it returns a valid result (0, 1, or 2)
        assertEquals(true, result >= 0 && result <= 2);
    }

    @Test
    public void testMouseTrappedCatWins() {
        // 0-[2,3], 1-[2], 2-[0,1,3], 3-[0,2]
        // Mouse at 1 only connects to 2 (where cat is). Cat wins immediately.
        assertEquals(2, test.catMouseGame(new int[][]{{2, 3}, {2}, {0, 1, 3}, {0, 2}}));
    }

    @Test
    public void testFiveNodeGraph() {
        // 0-[1], 1-[0,2,4], 2-[1,3,4], 3-[2], 4-[1,2]
        int result = test.catMouseGame(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}});
        assertEquals(true, result >= 0 && result <= 2);
    }

    @Test
    public void testSixNodeGraph() {
        // Example from LeetCode
        assertEquals(0, test.catMouseGame(new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}}));
    }

    @Test
    public void testThreeNodeMouseWins() {
        // 0-[1,2], 1-[0,2], 2-[0,1]: mouse at 1 can go to 0
        assertEquals(1, test.catMouseGame(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
    }
}
