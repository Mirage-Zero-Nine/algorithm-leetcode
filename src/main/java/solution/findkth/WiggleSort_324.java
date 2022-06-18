package solution.findkth;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * @author BorisMirage
 * Time: 2019/06/24 15:55
 * Created with IntelliJ IDEA
 */

public class WiggleSort_324 {

    /**
     * Find the median of array first, then fill the array with the elements that:
     * 1. Placing elements smaller than median to the last even slots.
     * 2. Placing elements larger than median to the first odd slots.
     * 3. Medians are put into the remaining slots.
     *
     * @param nums given array
     */
    public void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int n = nums.length;
        int left = (n + 1) / 2 - 1; // index of median in array
        int right = n - 1; // index of the largest element in array
        for (int i = 0; i < nums.length; i++) {   // copy large values on odd indexes
            if (i % 2 == 1) {
                nums[i] = copy[right];
                right--;
            } else { // copy values decrementing from median on even indexes
                nums[i] = copy[left];
                left--;
            }
        }
    }

    /**
     * Find the median of array first, then fill the array with the elements that:
     * 1. Placing elements smaller than median to the last even slots.
     * 2. Placing elements larger than median to the first odd slots.
     * 3. Medians are put into the remaining slots.
     * Implemented the quick selection and the index mapping method to reduce space complexity from O(n) to O(1).
     *
     * @param nums given array
     */
    public void wiggleSortIndexMapping(int[] nums) {

        int median = findMedian(nums); // find the median by implementing quick selection
        int n = nums.length;
        int left = 0, index = 0, right = n - 1;

        while (index <= right) {

            if (nums[newIndex(index, n)] > median) { // placing elements larger than median to the first odd slots.
                swap(nums, newIndex(left++, n), newIndex(index++, n));
            } else if (nums[newIndex(index, n)] < median) { // placing elements smaller than median to the last even slots.
                swap(nums, newIndex(right--, n), newIndex(index, n));
            } else {
                index++;
            }
        }
    }

    /**
     * Index mapping.
     *
     * @param index index in array
     * @param n     length of the array
     * @return mapped index in array
     */
    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    /**
     * Find median in array.
     *
     * @param nums given number
     * @return median in unsorted array
     */
    public int findMedian(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1, k = nums.length / 2;

        while (left <= right) {

            int pivotPosition = partition(nums, left, right);

            /*
             * Pivot position shows how many value in array is larger than pivot.
             * If the pivot divide to a right sub array that has a size of k, then kth is found.
             * Otherwise, if pivot divides a subarray smaller than k, then there are k - k' elements to be found.
             * If pivot divides a larger subarray, then directly narrow the search range to right subarray.
             * The reason is that under this condition, the right subarray contains more than k larger elements.
             * Therefore, directly partition in right subarray will suffice. */
            if (pivotPosition - left + 1 < k) {
                k = k - (pivotPosition - left + 1);     // find out how many larger element should be found
                left = pivotPosition + 1;       // reset search range
            } else if (pivotPosition - left + 1 > k) {      // more than k larger elements are found
                right = pivotPosition - 1;      // reset search range
            } else {
                return nums[pivotPosition];
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Quick selection, make elements value between [0, leftBound] are all >= pivot.
     *
     * @param array given array
     * @param left  left index
     * @param right right index
     * @return pivot position
     */
    private int partition(int[] array, int left, int right) {
        // Random r = new Random();
        // int pivotIndex = r.nextInt((right - left) + 1) + left;

        int pivotIndex = left + (right - left) / 2;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);     // swap elements to the end of range

        int leftBound = left;
        int rightBound = right - 1;     // set right bound to right - 1 to exclude pivot
        while (leftBound <= rightBound) {
            if (array[leftBound] >= pivot) {
                leftBound++;
            } else if (array[rightBound] < pivot) {
                rightBound--;
            } else {
                swap(array, leftBound++, rightBound--);
            }
        }
        swap(array, leftBound, right);
        return leftBound;
    }

    /**
     * Swap two elements in array by given index.
     *
     * @param array given array
     * @param left  left index
     * @param right right index
     */
    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
