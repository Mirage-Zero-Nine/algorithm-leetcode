package Solution.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array, print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array.
 * If it doesn't exist, output -1 for this number.
 *
 * @author BorisMirage
 * Time: 2020/02/24 13:34
 * Created with IntelliJ IDEA
 */

public class NextGreaterElements_503 {
    /**
     * Monotone stack.
     * The difference is that traverse the array twice.
     * After the first time of traverse, the stack will stores the decreasing subsequence without next greater elements.
     * The second time will find the next greater value for the decreasing elements.
     * These elements should be positioned ahead of elements in stack.
     * The circular array make them be the next greater value.
     *
     * @param nums given array
     * @return Next Greater Number for every element
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n * 2; i++) {
            int index = i % n, val = nums[index];
            while (!s.isEmpty() && val > nums[s.peek()]) {
                out[s.pop()] = val;
            }
            if (i < n) {        // avoid duplication
                s.push(i);
            }
        }

        return out;
    }
}
