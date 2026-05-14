package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinBuildTime_1199Test {
    private final MinBuildTime_1199 solver = new MinBuildTime_1199();

    @Test public void testBasic() {
        assertEquals(1, solver.minBuildTime(new int[]{1}, 1));
    }

    @Test public void testTwoBlocks() {
        assertEquals(7, solver.minBuildTime(new int[]{1, 2}, 5));
    }

    @Test public void testMultipleBlocks() {
        assertEquals(4, solver.minBuildTime(new int[]{1, 2, 3}, 1));
    }

    @Test public void testSameValues() {
        assertEquals(17, solver.minBuildTime(new int[]{1, 2, 4, 7, 8, 11, 15}, 2));
    }

    @Test public void testSingleBlockLargeSplit() {
        assertEquals(100, solver.minBuildTime(new int[]{100}, 1000));
    }

    @Test public void testAllSameBlocks() {
        // [5,5,5,5], split=1: Huffman tree merging
        // poll 5,5 -> 5+1=6; poll 5,6 -> 6+1=7; poll 5,7 -> 7+1=8
        assertEquals(7, solver.minBuildTime(new int[]{5, 5, 5, 5}, 1));
    }

    @Test public void testZeroSplit() {
        // split=0 means splitting is free, so result is max block time
        assertEquals(10, solver.minBuildTime(new int[]{1, 5, 10, 3}, 0));
    }

    @Test public void testTwoBlocksSmallSplit() {
        // [1,2], split=1: poll 1, poll 2, add 2+1=3 -> result 3
        assertEquals(3, solver.minBuildTime(new int[]{1, 2}, 1));
    }

    @Test public void testThreeBlocksLargeSplit() {
        // [1,1,1], split=10: poll 1,1 -> 1+10=11; poll 1,11 -> 11+10=21
        assertEquals(21, solver.minBuildTime(new int[]{1, 1, 1}, 10));
    }

    @Test public void testGiantCase() {
        // 1000 blocks all value 1, split=1
        int[] blocks = new int[1000];
        java.util.Arrays.fill(blocks, 1);
        // Huffman tree: log2(1000) ~ 10 splits, result = 1 + 10 = 11
        int result = solver.minBuildTime(blocks, 1);
        assertEquals(11, result);
    }

    @Test public void testDescendingBlocks() {
        // [10, 8, 6, 4, 2], split=3
        // poll 2,4 -> 4+3=7; poll 6,7 -> 7+3=10; poll 8,10 -> 10+3=13; poll 10,13 -> 13+3=16
        assertEquals(16, solver.minBuildTime(new int[]{10, 8, 6, 4, 2}, 3));
    }
}
