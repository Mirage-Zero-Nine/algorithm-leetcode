package solution.findkth;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * @author BorisMirage
 * Time: 2019/06/24 13:41
 * Created with IntelliJ IDEA
 */

public class FindKthLargest_215 {
    /**
     * Use partition to partition array until right side (larger side) has just k elements.
     *
     * @param nums given number
     * @param k    kth largest element
     * @return kth largest element in an unsorted array
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

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
                k = k - (pivotPosition - left + 1);         // find out how many larger element should be found
                left = pivotPosition + 1;                   // reset search range
            } else if (pivotPosition - left + 1 > k) {      // more than k larger elements are found
                right = pivotPosition - 1;                  // reset search range
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
     * @param start start index
     * @param end   end index
     * @return pivot position
     */
    private int partition(int[] array, int start, int end) {
        // Random r = new Random();
        // int pivotIndex = r.nextInt((right - left) + 1) + left;
        int pivotIndex = start + (end - start) / 2;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, end);     // swap elements to the end of range

        int left = start;
        int right = end - 1;         // set right bound to right - 1 to exclude pivot

        while (left <= right) {
            if (array[left] >= pivot) {
                left++;
            } else if (array[right] < pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }

        swap(array, left, end);
        return left;
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

    /**
     * Keep a min heap with size of k. The top of the heap is the min value of largest k elements in given array.
     *
     * @param nums given number
     * @param k    kth largest element
     * @return kth largest element in an unsorted array
     */
    public int findKthLargestMinHeap(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : nums) {
            pq.add(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.isEmpty() ? -1 : pq.poll();
    }

    public static void main(String[] args) {
        FindKthLargest_215 test = new FindKthLargest_215();
        System.out.println(test.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(test.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(test.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(test.findKthLargest(new int[]{5, 2, 4, 1, 3, 6, 0}, 4));
        System.out.println(test.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 9));
    }
}
