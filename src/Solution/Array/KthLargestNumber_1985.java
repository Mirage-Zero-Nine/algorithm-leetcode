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

    public static void main(String[] args) {
        System.out.println(new KthLargestNumber_1985().kthLargestNumber(new String[]{"3", "6", "7", "10"}, 4));
    }
}
