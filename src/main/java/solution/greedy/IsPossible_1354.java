package solution.greedy;

import java.util.PriorityQueue;

/**
 * Given an array of integers target. From a starting array, A consisting of all 1's, perform the following procedures:
 * 1. let x be the sum of all elements currently in your array.
 * 2. choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
 * 3. You may repeat this procedure as many times as needed.
 * Return True if it is possible to construct the target array from A otherwise return False.
 *
 * @author BorisMirage
 * Time: 2020/03/17 10:47
 * Created with IntelliJ IDEA
 */

public class IsPossible_1354 {
    /**
     * Greedy with heap.
     * Trace back from largest element in array. This element is added by other elements and the replaced element.
     * Array with all 1s and [1, i] will always be true.
     * Hence, two conditions will be true.
     * 1. The total sum is always bigger than all other elements. If current max in heap is 1, then all elements are 1.
     * 2. If sum - current max is 1, then the array is [1, i], which is correct.
     *
     * @param target given array that should be formed up
     * @return true if it is possible to construct the target array from A, otherwise return false
     */
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));     // max heap
        long sum = 0;
        for (int n : target) {
            pq.add(n);
            sum += n;
        }

        while (true) {
            int currentMax = pq.poll();       // current max value in array
            sum -= currentMax;                // sum of remaining element in array

            /*
             * All 1s in array and [1, i] will always be true.
             * The total sum is always bigger than all other elements.
             * If current max in heap is 1, then all elements in array is 1.
             * If sum - current max is 1, then the array is [1, i], which is correct. */
            if (sum == 1 || currentMax == 1) {
                return true;
            }
            if (currentMax < sum || sum == 0 || currentMax % sum == 0) {
                return false;
            }
            currentMax %= sum;      // current = sum * k, reduce k extra operation to avoid TLE
            sum += currentMax;      // next round value will selected element i in array converted to i + (sum - i) * k
            pq.add(currentMax);     // add next round value back to heap
        }
    }
}
