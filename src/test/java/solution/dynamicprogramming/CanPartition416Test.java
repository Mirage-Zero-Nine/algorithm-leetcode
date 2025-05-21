package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/20 23:52
 * Created with IntelliJ IDEA
 */

public class CanPartition416Test {
    private CanPartition_416 test = new CanPartition_416();

    @Test
    public void test() {
        assertTrue(test.canPartition(new int[]{1, 5, 11, 5}));
    }

    @Test
    public void test1() {
        assertTrue(test.canPartition(new int[]{6, 4, 4, 3, 1}));
        assertTrue(test.canPartitionDFS(new int[]{6, 4, 4, 3, 1}));
    }

    @Test
    public void test2() {
        assertTrue(test.canPartition(new int[]{1, 3, 4, 4, 6}));
        assertTrue(test.canPartitionDFS(new int[]{1, 3, 4, 4, 6}));
    }

    @Test
    public void test3() {
        assertTrue(test.canPartition(new int[]{0, 0, 0, 0}));
        assertTrue(test.canPartitionDFS(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void test4() {
        assertTrue(test.canPartition(new int[]{10, 5, 4, 1}));
        assertTrue(test.canPartitionDFS(new int[]{10, 5, 4, 1}));
    }

    @Test
    public void test5() {
        assertFalse(test.canPartition(new int[]{1, 2, 3, 5}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void testEmpty() {
        assertFalse(test.canPartition(new int[]{}));
        assertFalse(test.canPartitionDFS(new int[]{}));
        assertFalse(test.canPartition(null));
        assertFalse(test.canPartitionDFS(null));
    }
}