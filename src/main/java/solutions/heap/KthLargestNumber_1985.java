package solutions.heap;

import java.util.PriorityQueue;

/**
 * You are given an array of strings nums and an integer k.
 * Each string in nums represents an integer without leading zeros.
 * Return the string that represents the kth largest integer in nums.
 * Note: Duplicate numbers should be counted distinctly.
 *
 * @author BorisMirage
 * Time: 2021/11/09 22:15
 * Created with IntelliJ IDEA
 */

public class KthLargestNumber_1985 {
    /**
     * Min heap.
     * The input number could be larger than max long value, directly modify the heap comparator to compare two strings.
     *
     * @param nums given array
     * @param k    kth largest
     * @return the string that represents the kth largest integer in nums
     */
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }

            return o1.compareTo(o2);
        });

        for (String s : nums) {
            pq.add(s);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.poll();
    }

    /**
     * Quick select solution.
     * Idea is the same compare to the find kth largest element in array.
     * A slight modification is to write an own compare method to compare to string number.
     *
     * @param nums given array
     * @param k    kth largest
     * @return the string that represents the kth largest integer in nums
     */
    public String kthLargestNumberQuickSelect(String[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int pivotPosition = partition(nums, left, right);

            if (pivotPosition - left + 1 > k) {
                right = pivotPosition - 1;
            } else if (pivotPosition - left + 1 < k) {
                k = k - (pivotPosition - left + 1);
                left = pivotPosition + 1;
            } else {
                return nums[pivotPosition];
            }
        }

        return null; // actually, it's always valid under given situation
    }

    /**
     * Partition in quick sort/selection.
     *
     * @param nums  given array
     * @param start start index
     * @param end   end index
     * @return index of partition pivot
     */
    private int partition(String[] nums, int start, int end) {
        int pivotIndex = start + (end - start) / 2;
        String pivot = nums[pivotIndex];
        swap(nums, pivotIndex, end); // swap pivot to the last element in given range

        int left = start, right = end - 1; // left and right boundary during the partition

        while (left <= right) {
            if (compare(nums[left], pivot) >= 0) {
                left++;
            } else if (compare(nums[right], pivot) < 0) {
                right--;
            } else {
                swap(nums, left++, right--);
            }
        }

        swap(nums, left, end);
        return left;
    }

    /**
     * Compare two string numbers.
     *
     * @param a first number
     * @param b second number
     * @return int larger than 0 when a > b, less than 0 when a < b, 0 when a == b
     */
    private int compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }

        return a.compareTo(b);
    }

    /**
     * Swap two elements in array by given index.
     *
     * @param nums given array
     * @param i    first index
     * @param j    second index
     */
    private void swap(String[] nums, int i, int j) {
        String tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
