package Solution.Heap;

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
 *
 * @author BorisMirage
 * Time: 2020/02/20 15:22
 * Created with IntelliJ IDEA
 */

public class MedianFinder_295 {
    private Queue<Integer> small;
    private Queue<Integer> large;
    private boolean even = true;        // initially, the size of 2 heaps are both 0, which is even

    /**
     * Keeping two heaps. One min heap stores larger part of stream and one max heap stores smaller part.
     * The size of two heaps should be same, or the large heap can have one more elements when total size is odd.
     * There are two conditions:
     * 1. If two heaps have same size, then add one element to large heap.
     * 2. If total size is odd, then add one element to small heap.
     * The adding process is, for example, add one element to large heap:
     * Add given element to small heap first, then poll out the top of small heap, add the polled element to large heap.
     */
    public MedianFinder_295() {
        small = new PriorityQueue<>((o1, o2) -> o2 - o1);               // max heap stores smaller part of stream
        large = new PriorityQueue<>();                                  // min heap stores larger part of stream
    }

    /**
     * Add number to heap.
     * Basic rule: add value to a heap, then poll out top of this heap and add this value to the other heap.
     * Size of small heap should be larger or equal to large heap.
     * This rule make findMedian() easier.
     * If total size is odd, then small heap is larger. Add value to large heap to make two heaps size equal.
     * Otherwise, add value to small heap. If findMedian() is called, return top of the small heap.
     * To add value, if add to small heap, first add value to large heap, then move top of large heap to small heap.
     *
     * @param num given integer
     */
    public void addNum(int num) {
        if (even) {
            small.add(num);
            large.add(small.poll());
        } else {
            large.add(num);
            small.add(large.poll());
        }

        even = !even;
    }

    /**
     * If stream size is even, then return the average of two middle value in stream.
     * If stream size is odd, return value in the middle of stream, which is the top of small heap.
     * The reason is that, when adding value to heap, always keep small heap size larger or equal to large heap.
     *
     * @return median value in stream
     */
    public double findMedian() {
        if (even) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            return (double) large.peek();       // if total length is odd, then return top of small heap
        }
    }
}
