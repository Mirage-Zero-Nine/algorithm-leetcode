package Solution.Math;

/**
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e. 0 <= i < n).
 * In one operation, you can select two indices x, y where 0 <= x, y < n.
 * Subtract 1 from arr[x] and add 1 to arr[y] (i.e. perform arr[x] -=1 and arr[y] += 1).
 * The goal is to make all the elements of the array equal.
 * It is guaranteed that all the elements of the array can be made equal using some operations.
 * Given an integer n, the length of the array.
 * Return the minimum number of operations needed to make all the elements of arr equal.
 *
 * @author BorisMirage
 * Time: 2020/08/15 22:22
 * Created with IntelliJ IDEA
 */

public class MinOperations_1551 {
    /**
     * The input is actually a arithmetic sequence: 1, 3, 5, 7, 9, ..., (2n - 1).
     * Hence, no matter the n is even or odd, the final equal value will always be n.
     * The number of operation is the sum of equal value subtract to each value in first (or second) half of array.
     * This is the sum of arithmetic sequence as well, but with different value.
     * If n is even, then it is 1, 3, 5, 7, ..., n - 1, has n / 2 elements => (n / 2)^2
     * If n is odd, then it is 2, 4, 6, 8, ..., n - 1, has n/ 2 elements => (n / 2) * (n / 2 + 1)
     *
     * @param n givne n
     * @return the minimum number of operations needed to make all the elements of arr equal
     */
    public int minOperations(int n) {

        boolean isEven = n % 2 == 0;
        n /= 2;

        if (isEven) {
            return n * n;
        }

        return n * (n + 1);
    }
}
