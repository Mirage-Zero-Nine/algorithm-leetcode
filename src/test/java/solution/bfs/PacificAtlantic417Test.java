package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PacificAtlantic417Test {

    private final PacificAtlantic_417 test = new PacificAtlantic_417();

    @Test
    public void testHappyCases() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
            {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        });
        assertEquals(7, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.pacificAtlantic(new int[][]{}).size());
        List<int[]> result = test.pacificAtlantic(new int[][]{{1}});
        assertEquals(1, result.size());
    }

    @Test
    public void testLargeCase() {
        List<int[]> result = test.pacificAtlantic(new int[][]{{1, 1}, {1, 1}});
        assertEquals(4, result.size());
    }
}
