package playground;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    private static List<Integer> drain(MinHeap h) { List<Integer> r = new ArrayList<>(); Integer x; while ((x = h.poll()) != null) r.add(x); return r; }
    @Test void invalidCapacity() { assertThrows(IllegalArgumentException.class, () -> new MinHeap(0)); assertThrows(IllegalArgumentException.class, () -> new MinHeap(-1)); }
    @Test void emptyPoll() { assertNull(new MinHeap(2).poll()); }
    @Test void singletonPoll() { MinHeap h = new MinHeap(1); h.offer(7); assertEquals(7, h.poll()); assertNull(h.poll()); }
    @Test void capacityConstructorOrdersValues() { assertEquals(List.of(1, 2, 3, 4), drain(new MinHeap( new int[]{4, 1, 3, 2}))); }
    @Test void duplicateAndNegativeValues() { assertEquals(List.of(-2, -2, 0, 3, 3), drain(new MinHeap(new int[]{3, -2, 0, 3, -2}))); }
    @Test void offerMaintainsOrder() { MinHeap h = new MinHeap(4); h.offer(5); h.offer(1); h.offer(4); h.offer(2); assertEquals(List.of(1, 2, 4, 5), drain(h)); }
    @Test void offerGrowsFullHeap() { MinHeap h = new MinHeap(1); for (int x : new int[]{5, 4, 3, 2, 1}) h.offer(x); assertEquals(List.of(1, 2, 3, 4, 5), drain(h)); }
    @Test void updatePercolatesUp() { MinHeap h = new MinHeap(new int[]{3, 5, 7}); h.update(2, 1); assertEquals(List.of(1, 3, 5), drain(h)); }
    @Test void updatePercolatesDown() { MinHeap h = new MinHeap(new int[]{1, 2, 3, 4}); h.update(0, 9); assertEquals(List.of(2, 3, 4, 9), drain(h)); }
    @Test void updateEveryValidIndex() { MinHeap h = new MinHeap(new int[]{4, 2, 6}); h.update(1, 8); h.update(2, 0); assertEquals(List.of(0, 2, 8), drain(h)); }
    @Test void updateRejectsSizeIndexAndNegatives() { MinHeap h = new MinHeap(new int[]{1}); assertThrows(IllegalArgumentException.class, () -> h.update(1, 2)); assertThrows(IllegalArgumentException.class, () -> h.update(-1, 2)); }
    @Test void emptyInputArrayCanGrow() { MinHeap h = new MinHeap(new int[0]); h.offer(2); h.offer(1); assertEquals(List.of(1, 2), drain(h)); }
    @Test void inputArrayIsCopied() { int[] a = {3, 1}; MinHeap h = new MinHeap(a); a[0] = -99; assertEquals(List.of(1, 3), List.of(h.poll(), h.poll())); }
}
