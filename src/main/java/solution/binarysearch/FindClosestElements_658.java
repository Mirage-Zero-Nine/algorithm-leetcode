package solution.binarysearch;

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
        int left = 0, right = arr.length - k - 1;       // range of all possible start of subarray with size k + 1

        while (left <= right) {      // binary search for start index of k elements
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x) {      // finally, arr[i] should be closer to x compare to arr[i + k]
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

        int start = 0, end = arr.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] == x) {
                end = mid;
                break;
            } else if (arr[mid] > x) end = mid - 1;
            else start = mid + 1;
        }

        start = end;
        end++;
        while (k > 0) {
            if (end >= arr.length || (start >= 0 && x - arr[start] <= arr[end] - x)) {
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
            if (Math.abs(arr[start] - x) > Math.abs(arr[end] - x)) {
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
