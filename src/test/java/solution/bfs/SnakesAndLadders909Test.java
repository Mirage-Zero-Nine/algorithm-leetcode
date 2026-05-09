package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SnakesAndLadders909Test {

    private final SnakesAndLadders_909 test = new SnakesAndLadders_909();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.snakesAndLadders(new int[][]{
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        }));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.snakesAndLadders(new int[][]{{-1, -1}, {-1, -1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.snakesAndLadders(new int[][]{{-1, -1, -1}, {-1, 9, 8}, {-1, 8, 9}}));
    }
}
