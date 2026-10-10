package solutions.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * 1. void addNum(int num) - Add a integer number from the data stream to the data structure.
 * 2. double findMedian() - Return the median of all elements so far.
 * Inputs contain at most 50,000 values, and each value is in the range
 * {@code [-100000, 100000]}.
 *
 * @author BorisMirage
 * Time: 2020/02/20 15:22
 * Created with IntelliJ IDEA
 */

public class MedianFinder_295 {
    private final Queue<Integer> smallHeap;
    private final Queue<Integer> largeHeap;
    private boolean isEven;

    /**
     * Creates an empty median tracker backed by two heaps.  {@code smallHeap} is
     * a max heap containing the lower half of the values, while {@code largeHeap}
     * is a min heap containing the upper half.  After every insertion their
     * sizes differ by at most one, and every value in the lower half is less
     * than or equal to every value in the upper half.  The upper heap contains
     * the extra value when the total count is odd, so its head is the median;
     * when the count is even, the two heads are the middle values.  Both
     * insertion and lookup take {@code O(log n)} and {@code O(1)} time,
     * respectively, while the heaps use {@code O(n)} space.  The public
     * operations are synchronized so one instance can be used safely by
     * callers that serialize their observations through this object's monitor.
     */
    public MedianFinder_295() {
        // The max heap exposes the largest value in the lower half.
        this.smallHeap = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        // The min heap exposes the smallest value in the upper half.
        this.largeHeap = new PriorityQueue<>();
        isEven = true;
    }

    /**
     * Adds one stream value while restoring the two heap invariants.
     *
     * <p>The original size parity chooses the direction of the transfer.  If
     * the old size is even, the upper heap is about to need one extra value, so
     * the new number enters the lower heap first; its largest value is then
     * moved to the upper heap.  If the old size is odd, the reverse transfer
     * gives the lower heap the extra value.  This is written with shared
     * {@code source} and {@code destination} variables so both branches have
     * the same operation: inserting into the source and moving its boundary
     * value to the destination.  The moved boundary value is exactly the one
     * that could violate the ordering between the halves, so the transfer
     * restores ordering while also restoring the required heap sizes.
     *
     * @param num value to append to the stream
     */
    public synchronized void addNum(int num) {
        var source = isEven ? smallHeap : largeHeap;
        var destination = isEven ? largeHeap : smallHeap;

        // The old parity determines which heap should receive the extra item.
        // Polling immediately moves the source boundary across the partition,
        // preserving sorted lower-half/upper-half ordering in either branch.
        source.add(num);
        destination.add(source.poll());

        isEven = !isEven;
    }

    /**
     * Returns the median of all values added so far.
     *
     * <p>With an odd count, the upper heap has one extra value and its minimum
     * is the middle value.  With an even count, the two heap minima are the
     * adjacent middle values and their arithmetic mean is the median.  This
     * class intentionally returns {@code -1} for an empty stream, matching its
     * existing corner-case extension rather than throwing an exception.  The
     * published problem bounds keep the two middle values' sum within the
     * {@code int} range, but widening before addition also preserves the
     * mathematically correct result for the separately supported full-integer
     * extension.
     *
     * @return the current median, or {@code -1} when the stream is empty
     */
    public synchronized double findMedian() {
        // Both heaps are empty only before the first insertion.  A singleton
        // legitimately lives in largeHeap, so checking either heap would
        // incorrectly reject the first median.
        if (smallHeap.isEmpty() && largeHeap.isEmpty()) {
            return -1;
        }

        return isEven ?
                ((double) smallHeap.peek() + largeHeap.peek()) / 2.0
                : (double) largeHeap.peek();
    }
}
