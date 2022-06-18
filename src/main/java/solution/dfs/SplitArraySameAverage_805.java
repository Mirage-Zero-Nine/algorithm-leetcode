package solution.dfs;

import java.util.Arrays;

/**
 * In a given integer array A, we must move every element of A to either list B or list C.
 * Return true iff after such a move, there exist a non-empty list B and C where average of B and C is equal.
 *
 * @author BorisMirage
 * Time: 2019/10/21 18:09
 * Created with IntelliJ IDEA
 */

public class SplitArraySameAverage_805 {
    /**
     * This problem is actually k-sum problem.
     * Prerequisite is if (s1 / n1) == (s2 / n2), then (s1 / n1) = (s1 + s2) / (n1 + n2).
     * (s1 + s2) / (n1 + n2) is the average of whole array.
     * Therefore, if such a B and C exist, it will follow (s1 / n1) == average of array.
     * Hence, the problem is to find a subset that sum of the subset equals to ave of array multiple the size of subset.
     * Use DFS to find all possible subset. But there are some pruning method.
     * First, check if sum * i can be divided into int by n, where sum is array sum, i is subset size, n is array length.
     * Second, only select one element when there are multiple same elements in array.
     *
     * @param A given array
     * @return if there exist a non-empty list B and C where average of B and C is equal
     */
    public boolean splitArraySameAverage(int[] A) {

        if (A.length < 2) {
            return false;
        }

        Arrays.sort(A);
        int sum = 0, n = A.length;
        for (int i : A) {
            sum += i;
        }

        for (int i = 1; i <= n / 2; i++) {
            if (sum * i % n == 0 && dfs(A, sum * i / n, i, 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * DFS with pruning.
     * If total select element is more than array size, end searching.
     * Avoid duplicated element in array. If a duplicated element in the array, only select the first one.
     *
     * @param arr    given array
     * @param target target sum
     * @param count  how many elements can be selected
     * @param index  index of starting position
     * @return if there exist a non-empty list B and C where average of B and C is equal
     */
    private boolean dfs(int[] arr, int target, int count, int index) {
        if (count == 0) {
            return target == 0;     // find if it is a k sum
        }

        if (count + index > arr.length) {       // if total select element is more than array size, end searching
            return false;
        }

        /*
         * Avoid duplicated element in array.
         * If a duplicated element in the array, only select the first one.
         * Skip all later duplicated elements. */
        for (int j = index; j < arr.length; j++) {
            if ((j == index || arr[j] != arr[j - 1]) && dfs(arr, target - arr[j], count - 1, j + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SplitArraySameAverage_805().splitArraySameAverage(new int[]{4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5}));      // true
        System.out.println(new SplitArraySameAverage_805().splitArraySameAverage(new int[]{1, 7, 8, 10}));      // false
        System.out.println(new SplitArraySameAverage_805().splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));       // true
        System.out.println(new SplitArraySameAverage_805().splitArraySameAverage(new int[]{18, 10, 5, 3}));     // false
        System.out.println(new SplitArraySameAverage_805().splitArraySameAverage(new int[]{60, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}));       // false
    }
}
