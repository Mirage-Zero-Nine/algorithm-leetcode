package Solution.Heap;

import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k.
 * You should apply the following operation exactly k times:
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 * Return the minimum possible total number of stones remaining after applying the k operations.
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 * @author BorisMirage
 * Time: 2021/10/14 14:47
 * Created with IntelliJ IDEA
 */

public class MinStoneSum_1962 {
    /**
     * Use a max heap to store all elements from given array. Also calculate the total sum of the array.
     * Each time, poll out top of the heap and push back to the heap with the remaining value.
     * Meanwhile, subtract half of the heap top from array sum.
     * Repeat until k operations completed.
     *
     * @param piles given array
     * @param k     k times of operation
     * @return the minimum possible total number of stones remaining after applying the k operations
     */
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int total = 0;
        for (int i = 0; i < piles.length; i++) {
            total += piles[i];
            pq.add(piles[i]);
        }
        while (!pq.isEmpty() && k-- > 0) {
            int current = pq.poll();
            total -= current / 2;
            pq.add(current - current / 2);
        }

        return total;
    }
}
