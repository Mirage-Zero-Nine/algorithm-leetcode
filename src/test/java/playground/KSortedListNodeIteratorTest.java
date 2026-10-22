package playground;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KSortedListNodeIteratorTest {
    private static ListNode list(int... values) { ListNode d = new ListNode(0), p = d; for (int v : values) { p.next = new ListNode(v); p = p.next; } return d.next; }
    private static List<Integer> drain(KSortedListNodeIterator it) { List<Integer> out = new ArrayList<>(); while (it.hasNext()) out.add(it.next().val); return out; }
    @Test void emptyInput() { KSortedListNodeIterator it = new KSortedListNodeIterator(new ListNode[0]); assertFalse(it.hasNext()); assertNull(it.next()); }
    @Test void oneList() { assertEquals(List.of(1, 2, 3), drain(new KSortedListNodeIterator(new ListNode[]{list(1, 2, 3)}))); }
    @Test void mergesLists() { assertEquals(List.of(1, 2, 3, 4, 5, 6), drain(new KSortedListNodeIterator(new ListNode[]{list(1, 4, 6), list(2, 3, 5)}))); }
    @Test void duplicates() { assertEquals(List.of(1, 1, 1, 2, 2), drain(new KSortedListNodeIterator(new ListNode[]{list(1, 1, 2), list(1, 2)}))); }
    @Test void negativeValues() { assertEquals(List.of(-5, -2, -1, 0), drain(new KSortedListNodeIterator(new ListNode[]{list(-5, -1), list(-2, 0)}))); }
    @Test void nullHeadIsReturnedAsNullNodeContract() { assertThrows(NullPointerException.class, () -> new KSortedListNodeIterator(new ListNode[]{null})); }
    @Test void hasNextIsIdempotent() { KSortedListNodeIterator it = new KSortedListNodeIterator(new ListNode[]{list(4)}); assertTrue(it.hasNext()); assertTrue(it.hasNext()); assertEquals(4, it.next().val); }
    @Test void nextAfterExhaustionRemainsNull() { KSortedListNodeIterator it = new KSortedListNodeIterator(new ListNode[]{list(1)}); it.next(); assertNull(it.next()); assertFalse(it.hasNext()); }
    @Test void nodesAreReturnedInOriginalChains() { ListNode a = list(1, 3); ListNode first = a; ListNode second = a.next; KSortedListNodeIterator it = new KSortedListNodeIterator(new ListNode[]{a}); assertSame(first, it.next()); assertSame(second, it.next()); }
}
