package solution.divideandconquer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author BorisMirage
 * Time: 2025/05/05 16:08
 * Created with IntelliJ IDEA
 */

public class Construct427Test {
    private final Construct_427 test = new Construct_427();

    @Test
    public void testConstruct_complexGrid() {
        int[][] grid = {
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
        };

        Construct_427.Node root = test.construct(grid);

        assertFalse(root.isLeaf);

        // Top-left quadrant
        assertTrue(root.topLeft.isLeaf);
        assertTrue(root.topLeft.val); // All 1s

        // Top-right quadrant (not a leaf)
        Construct_427.Node topRight = root.topRight;
        assertNotNull(topRight);
        assertFalse(topRight.isLeaf);
        assertTrue(topRight.topLeft.isLeaf);
        assertFalse(topRight.topLeft.val); // 0s
        assertTrue(topRight.topRight.isLeaf);
        assertFalse(topRight.topRight.val); // 0s
        assertTrue(topRight.bottomLeft.isLeaf);
        assertTrue(topRight.bottomLeft.val); // 1s
        assertTrue(topRight.bottomRight.isLeaf);
        assertTrue(topRight.bottomRight.val); // 1s

        // Bottom-left quadrant
        assertTrue(root.bottomLeft.isLeaf);
        assertTrue(root.bottomLeft.val); // All 1s

        // Bottom-right quadrant
        assertTrue(root.bottomRight.isLeaf);
        assertFalse(root.bottomRight.val); // All 0s
    }

    @Test
    public void testConstruct_allSame_shouldMergeToLeaf() {
        int[][] grid = {
                {1, 1},
                {1, 1}
        };

        Construct_427.Node root = test.construct(grid);

        assertTrue(root.isLeaf, "Root should be a leaf node");
        assertTrue(root.val, "Root value should be true");
        assertNull(root.topLeft);
        assertNull(root.topRight);
        assertNull(root.bottomLeft);
        assertNull(root.bottomRight);
    }

    @Test
    public void testConstruct_mixed_shouldNotMerge() {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };

        Construct_427.Node root = test.construct(grid);

        assertFalse(root.isLeaf, "Root should not be a leaf");
        assertNotNull(root.topLeft);
        assertTrue(root.topLeft.isLeaf);
        assertTrue(root.topLeft.val);

        assertTrue(root.topRight.isLeaf);
        assertFalse(root.topRight.val);

        assertTrue(root.bottomLeft.isLeaf);
        assertFalse(root.bottomLeft.val);

        assertTrue(root.bottomRight.isLeaf);
        assertTrue(root.bottomRight.val);
    }
}
