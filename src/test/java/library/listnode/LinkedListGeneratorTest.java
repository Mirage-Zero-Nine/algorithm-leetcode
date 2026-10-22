package library.listnode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedListGeneratorTest {
    @Test void arrayCreatesEmptyList() { assertNull(LinkedListGenerator.generate(new int[]{})); }
    @Test void arrayCreatesSingleton() { assertEquals(4, LinkedListGenerator.generate(new int[]{4}).val); }
    @Test void arrayPreservesOrder() { assertArrayEquals(new int[]{1,2,3}, values(LinkedListGenerator.generate(new int[]{1,2,3}))); }
    @Test void arrayPreservesDuplicates() { assertArrayEquals(new int[]{2,2,2}, values(LinkedListGenerator.generate(new int[]{2,2,2}))); }
    @Test void arrayPreservesNegativeValues() { assertArrayEquals(new int[]{-2,0,3}, values(LinkedListGenerator.generate(new int[]{-2,0,3}))); }
    @Test void arrayPreservesBoundaries() { assertArrayEquals(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE}, values(LinkedListGenerator.generate(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE}))); }
    @Test void ascendingRange() { assertArrayEquals(new int[]{2,3,4}, values(LinkedListGenerator.generate(2,4))); }
    @Test void singletonRange() { assertArrayEquals(new int[]{5}, values(LinkedListGenerator.generate(5,5))); }
    @Test void reversedRangeIsEmpty() { assertNull(LinkedListGenerator.generate(5,4)); }
    @Test void negativeRange() { assertArrayEquals(new int[]{-2,-1,0}, values(LinkedListGenerator.generate(-2,0))); }
    @Test void generatedNodesAreDistinct() { ListNode h = LinkedListGenerator.generate(new int[]{1,2}); assertNotSame(h, h.next); }
    @Test void generatedTailIsNull() { assertNull(LinkedListGenerator.generate(new int[]{1}).next); }
    @Test void printNullDoesNotThrow() { assertDoesNotThrow(() -> LinkedListGenerator.printAll(null)); }
    @Test void printListDoesNotThrow() { assertDoesNotThrow(() -> LinkedListGenerator.printAll(LinkedListGenerator.generate(new int[]{1,2}))); }
    @Test void repeatedCallsAreIndependent() { ListNode first = LinkedListGenerator.generate(new int[]{1}); ListNode second = LinkedListGenerator.generate(new int[]{2}); first.val = 9; assertEquals(2, second.val); }
    private int[] values(ListNode head) { java.util.ArrayList<Integer> out = new java.util.ArrayList<>(); for (; head != null; head = head.next) out.add(head.val); return out.stream().mapToInt(Integer::intValue).toArray(); }
}
