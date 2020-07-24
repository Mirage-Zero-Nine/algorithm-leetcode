package Solution.TwoPointers;

/**
 * Given an array of integers A sorted in non-decreasing order.
 * Return an array of the squares of each number, also in sorted non-decreasing order.
 *
 * @author BorisMirage
 * Time: 2020/03/24 16:27
 * Created with IntelliJ IDEA
 */

public class SortedSquares_977 {
    /**
     * Two pointers. Starts from rightmost element and leftmost element. Fill the output array starts with largest elements.
     * Compare the absolute value and find the larger one.
     *
     * @param A given array
     * @return an array of the squares of each number, in sorted non-decreasing order
     */
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int left = 0, right = n - 1, index = n - 1;

        int[] out = new int[n];

        while (left <= right) {
            if (Math.abs(A[left]) < Math.abs(A[right])) {
                out[index--] = A[right] * A[right--];
            } else {
                out[index--] = A[left] * A[left++];
            }
        }

        return out;
    }

    /**
     * Find the first non-negative element in array.
     * Then use two pointers to sort the squares of elements in array.
     *
     * @param A given array
     * @return an array of the squares of each number, in sorted non-decreasing order
     */
    public int[] sortedSquaresBinarySearch(int[] A) {

        /* Corner case */
        if (A == null || A.length == 0) {
            return A;
        }

        int n = A.length, first = findPosition(A);        // index of first non-negative element;
        int negative = first - 1, positive = first, index = 0;
        int[] out = new int[n];

        while (negative >= 0 && positive < n) {
            int tmp1 = A[negative] * A[negative], tmp2 = A[positive] * A[positive];

            if (tmp1 > tmp2) {
                out[index++] = tmp2;
                positive++;
            } else {
                out[index++] = tmp1;
                negative--;
            }
        }

        while (negative >= 0) {     // remaining elements in negative part
            out[index++] = A[negative] * A[negative--];
        }

        while (positive < n) {      // remaining elements in positive part
            out[index++] = A[positive] * A[positive++];
        }

        return out;
    }

    /**
     * Find first non-negative element in array.
     *
     * @param array given array
     * @return index of first non-negative element in array
     */
    private int findPosition(int[] array) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == 0) {
                return mid;
            } else if (array[mid] > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
