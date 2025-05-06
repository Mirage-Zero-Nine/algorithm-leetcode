package solution.divideandconquer;

import java.util.Arrays;

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * @author BorisMirage
 * Time: 2025/05/06 10:56
 * Created with IntelliJ IDEA
 */

public class SortArray_912 {

    /**
     * Implementation of quick sort.
     *
     * @param nums given array
     * @return sorted array
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        sort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * Quick sort: divide and conquer
     * Select the rightmost element in the array as pivot and loop the array.
     * When the element in array is larger than the pivot, move to the "right part".
     * When the element in the array is smaller than or equal to the pivot, move to the "left part".
     * The implementation detail is using a similar approach of sliding window, see code.
     *
     * @param nums  given array
     * @param left  left bound index
     * @param right right bound index
     */
    private void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        // divide the current array to 2 parts, small and large
        int index = partition(nums, left, right);

        // keep dividing
        sort(nums, left, index - 1);
        sort(nums, index + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = (left - 1);
        for (int j = left; j < right; j++) {

            // similar to sliding window where the window size starts at 1 (to keep pivot)
            // if the current element >= pivot, swap array[j] to array[i++] (since it's a "left part" element)
            // i++ shrinks window of "right part"
            // otherwise, move j to the next element since it's gonna be in right part
            if (pivot >= nums[j]) {
                swap(nums, ++i, j);
            }
        }

        // finally, put pivot to the largest index of the left part
        swap(nums, ++i, right);

        return i;
    }

    /**
     * Swap two elements in the array.
     *
     * @param nums given array
     * @param i    index of one element
     * @param j    index of one element
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Another approach implementing merge sort.
     *
     * @param nums given array
     * @return sorted array
     */
    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * Same divide and conquer idea. The implementation is different.
     * Divide the array into half, sort and merge the 2 subarrays.
     *
     * @param nums  given array
     * @param left  left bound
     * @param right right bound
     */
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(nums, left, middle);
        mergeSort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    /**
     * Sort and merge the given array.
     *
     * @param nums   array to be divided and sort
     * @param left   left bound
     * @param middle middle index
     * @param right  right bound
     */
    private void merge(int[] nums, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftSubarray = new int[leftSize], rightSubarray = new int[rightSize];

        int leftIndex = 0, rightIndex = 0, index = left;

        // copy elements from given array to 2 subarrays
        System.arraycopy(nums, left, leftSubarray, 0, leftSize);
        System.arraycopy(nums, middle + 1, rightSubarray, 0, rightSize);

        // comparing elements
        // left subarray is considered as "smaller part"
        // right subarray is considered as "larger part"
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftSubarray[leftIndex] <= rightSubarray[rightIndex]) {
                nums[index] = leftSubarray[leftIndex++];
            } else {
                nums[index] = rightSubarray[rightIndex++];
            }
            index++;
        }

        // put the remaining part of subarray into merged array
        while (leftIndex < leftSize) {
            nums[index++] = leftSubarray[leftIndex++];
        }
        while (rightIndex < rightSize) {
            nums[index++] = rightSubarray[rightIndex++];
        }
    }
}


