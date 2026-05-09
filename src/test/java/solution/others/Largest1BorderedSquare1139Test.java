package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Largest1BorderedSquare1139Test {

    private final Largest1BorderedSquare_1139 test = new Largest1BorderedSquare_1139();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.largest1BorderedSquare(new int[][]{{0}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}}));
    }
}
