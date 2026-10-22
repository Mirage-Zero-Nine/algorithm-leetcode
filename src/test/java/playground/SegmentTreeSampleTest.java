package playground;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeSampleTest {
    private final SegmentTreeSample sample = new SegmentTreeSample();
    @Test void emptyArrayHasNullRoot() { assertNull(sample.buildSegmentTree(new int[0])); }
    @Test void singletonStoresBoundsAndValue() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{7}); assertEquals(0, n.min); assertEquals(0, n.max); assertEquals(7, n.value); assertNull(n.left); assertNull(n.right); }
    @Test void rootStoresMinimum() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{5, 2, 8, 1, 4}); assertEquals(0, n.min); assertEquals(4, n.max); assertEquals(1, n.value); }
    @Test void rootSplitsAtMidpoint() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{5, 2, 8, 1}); assertEquals(0, n.left.min); assertEquals(1, n.left.max); assertEquals(2, n.left.value); assertEquals(2, n.right.min); assertEquals(3, n.right.max); assertEquals(1, n.right.value); }
    @Test void leavesCoverEveryIndex() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{9, 8, 7, 6, 5, 4}); assertEquals(8, n.left.left.value); assertEquals(7, n.left.right.value); assertEquals(5, n.right.left.value); assertEquals(4, n.right.right.value); }
    @Test void negativesAndDuplicates() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{-1, -5, -5, 0}); assertEquals(-5, n.value); assertEquals(-5, n.left.value); assertEquals(-5, n.right.value); }
    @Test void extremeValues() { SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}); assertEquals(Integer.MIN_VALUE, n.value); }
    @Test void inputMutationDoesNotChangeBuiltTree() { int[] a = {3, 1}; SegmentTreeSample.SegmentTreeNode n = sample.buildSegmentTree(a); a[1] = -9; assertEquals(1, n.value); }
    @Test void printNullRootIsSafe() { assertDoesNotThrow(() -> sample.printAllNodes(null)); }
}
