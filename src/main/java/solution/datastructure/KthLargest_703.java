package solution.datastructure;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * KthLargest class will have a constructor which accepts an integer k and an integer array nums.
 * For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * @author BorisMirage
 * Time: 2020/03/05 14:25
 * Created with IntelliJ IDEA
 */

public class KthLargest_703 {
    private final PriorityQueue<Integer> pq;
    private final int k;

    /**
     * Use a min heap to store top k largest elements.
     * When adding new elements, if heap size is larger than k after adding the element, remove the top of heap.
     *
     * @param k    kth largest element
     * @param nums given array
     */
    public KthLargest_703(int k, int[] nums) {
        pq = new PriorityQueue<>(k);
        this.k = k;
        for (int n : nums) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    /**
     * Adding new value to heap and return the current kth largest elements in stream.
     *
     * @param val added value
     * @return the current kth largest elements in stream
     */
    public int add(int val) {
        pq.add(val);
        if (pq.size() > this.k) {
            pq.poll();
        }

        return pq.peek();
    }
}
