package solution.array;

/**
 * Given an array arr that represents a permutation of numbers from 1 to n.
 * You have a binary string of size n that initially has all its bits set to zero.
 * At each step i (1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
 * You are given an integer m and you need to find the latest step at which there exists a group of ones of length m.
 * A group of ones is a contiguous substring of 1s such that it cannot be extended in either direction.
 * Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.
 *
 * @author BorisMirage
 * Time: 2020/08/30 19:56
 * Created with IntelliJ IDEA
 */

public class FindLatestStep_1562 {
    /**
     * Each step, there will be 1 set into array.
     * After each step, it will be a new group, or combined with left 1s, or combined with right, or both with itself.
     * Hence, keep an int array, array[i] is the size of subarray with all 1s it currently have.
     * At each step, the new subarray size at ith element is the sum of left and right subarray size, and itself.
     * Then set the new subarray size (left + right + 1) at the start and the end of previous subarray for next step.
     *
     * @param arr given array
     * @param m   target length group size
     * @return the latest step exists a group of ones of length exactly m, or return -1 if no group exists
     */
    public int findLatestStep(int[] arr, int m) {
        int out = -1, n = arr.length;

        if (m == n) {       // all elements will be set to 1 eventually
            return m;
        }

        int[] length = new int[n + 2];      // left most and right most are always 0
        for (int i = 0; i < n; i++) {
            int index = arr[i], left = length[index - 1], right = length[index + 1], sum = left + right + 1;
            length[index - left] = sum;         // move to the start of subarray and set the new size
            length[index + right] = sum;        // move to the end of subarray and set the new size

            if (left == m || right == m) {
                out = i;
            }
        }

        return out;
    }
}
