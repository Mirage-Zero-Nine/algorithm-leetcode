package Solution.Array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array arr of integers (not necessarily distinct).
 * Split the array into some number of "chunks" (partitions), and individually sort each chunk.
 * After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 *
 * @author BorisMirage
 * Time: 2020/02/06 12:46
 * Created with IntelliJ IDEA
 */

public class MaxChunksToSorted_768 {
    /**
     * Monotone stack.
     * Traverse the array and keep a monotonous increasing stack. The top of stack is the max value during the traverse.
     * Each chunk should have a largest element.
     * Hence, if arr[i] is smaller than top of stack, store the top of the stack and pop elements smaller than arr[i].
     * In this way, the stacks will save each chunk's largest element.
     * Finally, return the size of stack, since it stores the largest element in chunk, which the size is # of chunks.
     * Time complexity: O(n).
     * Space complexity: O(n).
     *
     * @param arr given array
     * @return most number of chunks could have made
     */
    public int maxChunksToSorted(int[] arr) {
        Stack<Integer> s = new Stack<>();

        for (int i : arr) {

            if (!s.isEmpty() && i < s.peek()) {
                int currentMax = s.pop();
                while (!s.isEmpty() && i < s.peek()) {      // pop
                    s.pop();
                }
                s.push(currentMax);
            } else {
                s.push(i);      // keep increasing
            }
        }

        return s.size();
    }

    /**
     * Naive solution.
     * If the array is sorted, then each element in array is a chunk.
     * Compare it to unsorted original array, if part of both prefix sum is equal, then the sorted subarray is the same.
     * Sort the array. Then traverse the array. Count the prefix sum of sorted array and original array.
     * If the prefix sum of two arrays are equal, then one more chunk is found.
     * Time complexity: O(nlogn).
     * Space complexity: O(1).
     *
     * @param arr given array
     * @return most number of chunks could have made
     */
    public int sortArray(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        long sum1 = 0, sum2 = 0;        // int may cause overflow
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
            sum2 += sorted[i];
            if (sum1 == sum2) {
                count++;
            }
        }

        return count;
    }
}
