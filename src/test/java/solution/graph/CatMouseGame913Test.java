package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CatMouseGame913Test {

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
}
