package solution.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
 * If it does not exist, output -1 for this number.
 *
 * @author BorisMirage
 * Time: 2020/02/24 13:16
 * Created with IntelliJ IDEA
 */

public class NextGreaterElement_496 {
    /**
     * Monotone stack.
     * First array is the subset of second array.
     * Find all next greater value in second array, then traverse the first array and find next greater value.
     *
     * @param nums1 subset of nums2
     * @param nums2 given array
     * @return all the next greater numbers for nums1's elements in the corresponding places of nums2
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> m = new HashMap<>();

        for (int n : nums2) {
            while (!s.isEmpty() && n > s.peek()) {
                m.put(s.pop(), n);
            }
            s.push(n);
        }

        int[] out = new int[nums1.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = m.getOrDefault(nums1[i], -1);
        }

        return out;
    }
}
