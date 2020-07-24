package Solution.Array;

/**
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1].
 * Split the array into some number of "chunks" (partitions), and individually sort each chunk.
 * After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 *
 * @author BorisMirage
 * Time: 2019/09/17 11:06
 * Created with IntelliJ IDEA
 */

public class MaxChunksToSorted_769 {
    /**
     * Note that in this problem:
     * 1. The concatenation of sorted subarray should be a sorted array.
     * 2. The array only contains a permutation of [0, 1, ..., arr.length - 1].
     * Hence, it is to find the # of subarray that max value in subarray is smaller than the largest index.
     * i.e., find all subarray that max[0, i] = i.
     * Each subarray is a chunk.
     *
     * @param arr given array
     * @return most number of chunks could have made
     */
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;

        /* Corner case */
        if (n < 2) {
            return n;
        }
        int max = 0, count = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);

            if (max == i) {     // array is the permutation of [0, 1, ..., arr.length - 1]
                count++;        // find a new chunk that max[0, i] = i
            }
        }

        return count;
    }
}
