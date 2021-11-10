package Solution.Array;

import java.util.Comparator;
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
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }

                return o1.compareTo(o2);
            }
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

    public static void main(String[] args) {
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"3", "6", "7", "10"}, 1)); // 10
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"3", "6", "7", "10"}, 2)); // 7
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"3", "6", "7", "10"}, 3)); // 6
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"3", "6", "7", "10"}, 4)); // 3
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"0", "0"}, 2)); // 0
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"683339452288515879", "7846081062003424420", "4805719838", "4840666580043", "83598933472122816064", "522940572025909479", "615832818268861533", "65439878015", "499305616484085", "97704358112880133", "23861207501102", "919346676", "60618091901581", "5914766072", "426842450882100996", "914353682223943129", "97", "241413975523149135", "8594929955620533", "55257775478129", "528", "5110809", "7930848872563942788", "758", "4", "38272299275037314530", "9567700", "28449892665", "2846386557790827231", "53222591365177739", "703029", "3280920242869904137", "87236929298425799136", "3103886291279"}, 3)); // "38272299275037314530"
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"1", "1"}, 2)); // 1
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"1", "1", "2"}, 1)); // 2
        System.out.println(new KthLargestNumber_1985().kthLargestNumberQuickSelect(new String[]{"1", "1", "2"}, 2)); // 1
    }
}
