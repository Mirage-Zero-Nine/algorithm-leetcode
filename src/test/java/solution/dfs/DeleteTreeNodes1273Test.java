package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeleteTreeNodes1273Test {

    private final DeleteTreeNodes_1273 test = new DeleteTreeNodes_1273();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.deleteTreeNodes(7, new int[]{-1, 0, 0, 1, 2, 2, 2}, new int[]{1, -2, 4, 0, -2, -1, -1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.deleteTreeNodes(0, null, null));
        assertEquals(0, test.deleteTreeNodes(1, new int[]{-1}, new int[]{0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.deleteTreeNodes(5, new int[]{-1, 0, 0, 1, 1}, new int[]{-672, 441, 18, 0, 0}));
    }
}
