package playground;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryIndexedTreeTest {
    @Test void emptyTreeHasZeroPrefix() { assertEquals(0, new BinaryIndexedTree(new int[0]).prefixSum(-1)); }
    @Test void singletonPrefixAndRange() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{5}); assertEquals(5, b.prefixSum(0)); assertEquals(5, b.rangeSum(0, 0)); }
    @Test void prefixes() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{1, 2, 3, 4}); assertEquals(1, b.prefixSum(0)); assertEquals(3, b.prefixSum(1)); assertEquals(6, b.prefixSum(2)); assertEquals(10, b.prefixSum(3)); }
    @Test void ranges() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{1, 2, 3, 4}); assertEquals(5, b.rangeSum(1, 2)); assertEquals(9, b.rangeSum(1, 3)); assertEquals(3, b.rangeSum(0, 1)); }
    @Test void updateAtBeginning() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{1, 2, 3}); b.update(0, 9); assertEquals(10, b.prefixSum(0)); assertEquals(15, b.prefixSum(2)); }
    @Test void updateAtEnd() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{1, 2, 3}); b.update(2, -2); assertEquals(4, b.rangeSum(0, 2)); }
    @Test void multipleUpdates() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{0, 0, 0, 0, 0}); b.update(1, 4); b.update(3, -2); b.update(1, 1); assertEquals(5, b.rangeSum(1, 1)); assertEquals(-2, b.rangeSum(3, 3)); }
    @Test void negativeValues() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{-3, 2, -5}); assertEquals(-6, b.prefixSum(2)); assertEquals(2, b.rangeSum(1, 1)); }
    @Test void sourceArrayIsCopied() { int[] a = {1, 2}; BinaryIndexedTree b = new BinaryIndexedTree(a); a[0] = 99; assertEquals(3, b.prefixSum(1)); }
    @Test void repeatedQueriesAreStable() { BinaryIndexedTree b = new BinaryIndexedTree(new int[]{2, 4, 6}); assertEquals(12, b.prefixSum(2)); assertEquals(12, b.prefixSum(2)); }
}
