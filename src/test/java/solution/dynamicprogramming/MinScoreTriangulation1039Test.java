package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinScoreTriangulation1039Test {

    private final MinScoreTriangulation_1039 test = new MinScoreTriangulation_1039();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minScoreTriangulation(new int[]{1, 2, 3}));
        assertEquals(144, test.minScoreTriangulation(new int[]{3, 7, 4, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.minScoreTriangulation(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5}));
    }
}
