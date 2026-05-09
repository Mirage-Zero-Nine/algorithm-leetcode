package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinKnightMoves1197Test {

    private final MinKnightMoves_1197 test = new MinKnightMoves_1197();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minKnightMoves(2, 1));
        assertEquals(4, test.minKnightMoves(5, 5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.minKnightMoves(0, 0));
        assertEquals(4, test.minKnightMoves(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.minKnightMoves(3, 3));
    }
}
