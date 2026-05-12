package solution.findkth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindKthLargest_215Test {
    private final FindKthLargest_215 solver = new FindKthLargest_215();

    @Test public void testBasic() {
        assertEquals(5, solver.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test public void testDuplicates() {
        assertEquals(4, solver.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test public void testSingleElement() {
        assertEquals(7, solver.findKthLargest(new int[]{7}, 1));
    }

    @Test public void testKIsLength() {
        assertEquals(1, solver.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 6));
    }

    @Test public void testMinHeapApproach() {
        assertEquals(5, solver.findKthLargestMinHeap(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test public void testMinHeapWithDuplicates() {
        assertEquals(4, solver.findKthLargestMinHeap(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
