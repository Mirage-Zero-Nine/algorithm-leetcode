package solutions.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * @author BorisMirage
 * Time: 2020/02/19 13:47
 * Created with IntelliJ IDEA
 */

public class FindClosestElements_658 {
    /**
     * Binary search i in array that arr[i] to arr[i + k] is the result, since it will be a subarray of k size.
     * That is to say, arr[i] should be closer to x compare to arr[i + k].
     * Compare difference between x, arr[mid] and arr[mid + k].
     * If x - arr[mid] > arr[mid + k] - x, then the start position of subarray will be at right of middle.
     * Time complexity: O(nlogk + k).
     *
     * @param arr given array
     * @param k   k closest elements
     * @param x   target value
     * @return the k closest elements to x in the array
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;           // every start index of a k-element window is possible

        while (left < right) {      // binary search for start index of k elements
            int mid = left + (right - left) / 2;

            if ((long) x - arr[mid] > (long) arr[mid + k] - x) { // the right boundary is closer, so discard the left edge
                left = mid + 1;     // arr[mid + 1] ~ arr[mid + k] is closer than arr[mid] ~ arr[mid + k - 1]
            } else {
                right = mid;        // arr[mid] ~ arr[mid + k - 1] is closer than arr[mid + 1] ~ arr[mid + k]
            }
        }

        return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }

    /**
     * Basic binary search solution.
     * The idea is to first find the insert position, then select k closest elements.
     *
     * @param arr given array
     * @param k   k closest elements
     * @param x   target value
     * @return the k closest elements to x in the array
     */
    public List<Integer> findClosestElementsBasicBinarySearch(int[] arr, int k, int x) {

        int insertionPoint = 0;
        int searchEnd = arr.length;
        while (insertionPoint < searchEnd) {
            int mid = insertionPoint + (searchEnd - insertionPoint) / 2;
            if (arr[mid] < x) {
                insertionPoint = mid + 1;
            } else {
                searchEnd = mid;
            }
        }

        int start = insertionPoint - 1;
        int end = insertionPoint;
        while (k > 0) {
            if (end >= arr.length || (start >= 0 && (long) x - arr[start] <= (long) arr[end] - x)) {
                start--;
            } else {
                end++;
            }
            k--;
        }

        List<Integer> out = new ArrayList<>();
        for (int i = start + 1; i < end; i++) {
            out.add(arr[i]);
        }

        return out;
    }

    /**
     * Two pointers solution.
     * The result should be a consecutive subarray, therefore, move the pointer until end - start == k.
     *
     * @param arr given array
     * @param k   k closest elements
     * @param x   target value
     * @return the k closest elements to x in the array
     */
    public List<Integer> findClosestElementsTwoPointers(int[] arr, int k, int x) {
        int start = 0, end = arr.length - 1;

        while (end - start >= k) {
            if (Math.abs((long) arr[start] - x) > Math.abs((long) arr[end] - x)) {
                start++;
            } else {
                end--;
            }
        }

        List<Integer> out = new ArrayList<>(k);
        for (int i = start; i <= end; i++) {
            out.add(arr[i]);
        }

        return out;
    }
}
