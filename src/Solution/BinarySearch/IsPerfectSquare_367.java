package Solution.BinarySearch;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 *
 * @author BorisMirage
 * Time: 2019/10/25 12:58
 * Created with IntelliJ IDEA
 */

public class IsPerfectSquare_367 {
    /**
     * Binary search to narrow the search range.
     *
     * @param num given int
     * @return returns True if num is a perfect square else False
     */
    public boolean isPerfectSquare(int num) {
        long left = 0, right = num;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long val = mid * mid;
            if (val > num) {
                right = mid - 1;
            } else if (val < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return (left * left) == num;
    }

    public static void main(String[] args) {
        IsPerfectSquare_367 test = new IsPerfectSquare_367();

        for (int i = 0; i < 10001; i++) {
            System.out.println("Number " + i + " is perfect square: " + test.isPerfectSquare(i));
        }
    }
}
