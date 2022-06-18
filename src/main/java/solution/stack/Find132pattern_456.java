package solution.stack;

import java.util.Stack;

/**
 * Given a sequence of n integers, a 132 pattern is a subsequence that i < j < k and A[i] < A[k] < A[j].
 * Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 * Note: n will be less than 15,000.
 *
 * @author BorisMirage
 * Time: 2020/03/20 09:24
 * Created with IntelliJ IDEA
 */

public class Find132pattern_456 {
    /**
     * Monotone stack.
     * First, find the minimum value at left side of each value. This is the potential "1".
     * Then use a monotone stack to find the next great value of current element from right to left.
     * Each time, if the current element is larger than the top of stack, it could be "3".
     * If popped element is larger than min value at "3", then "2" is found.
     *
     * @param nums given array
     * @return whether there is a 132 pattern in the list
     */
    public boolean find132pattern(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length < 3) {
            return false;
        }

        int n = nums.length;
        int[] leftMin = new int[n];
        leftMin[0] = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);     // min value at left side of current value, "1"
        }

        Stack<Integer> s = new Stack<>();       // monotone stack
        int tmp;

        for (int i = n - 1; i >= 0; i--) {      // right from left
            while (!s.isEmpty() && nums[i] > s.peek()) {        // find next greater value in right side, which is "3"
                tmp = s.pop();              // pop out elements smaller than current element, tmp could be "2"
                if (tmp > leftMin[i]) {     // if popped element is larger than min value at "3", then "1" is found
                    return true;
                }
            }
            s.push(nums[i]);
        }

        return false;
    }

    /**
     * One-pass to find the pattern. The idea is same as monotone stack.
     * Each time, check if current element is smaller than previously found "2".
     * Otherwise, update "2" by ceiling the elements at right part of current array.
     * This approach is actually only to update "2".
     * Because if "2" is found, then "3" is exist. There is no need to confirm which is "2" and which is "3".
     * Only to find "1" is enough.
     *
     * @param nums given array
     * @return whether there is a 132 pattern in the list
     */
    public boolean find132patternOnePass(int[] nums) {
        int n = nums.length, top = n, second = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {      // from right to left
            if (nums[i] < second) {              // if current element is small than "2", then pattern found
                return true;
            }
            while (top < n && nums[i] > nums[top]) {        // if not, then current element could be "3"
                second = nums[top++];       // ceiling element at right side of current element
            }
            nums[--top] = nums[i];          // update ceiling result
        }

        return false;
    }
}
