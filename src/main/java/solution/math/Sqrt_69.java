package solution.math;

/**
 * Implement int sqrt(int x) and return in int.
 *
 * @author BorisMirage
 * Time: 2019/01/21 23:36
 * Created with IntelliJ IDEA
 */

public class Sqrt_69 {
    /**
     * Bit manipulation.
     *
     * @param x given int
     * @return square root of x
     */
    public int sqrt(int x) {

        /* Corner case */
        if (x == 0) {
            return 0;
        }

        int h = 0;

        while ((1L << h) * (1L << h) <= x) { // find the highest bit
            h++;
        }
        h--;

        int b = h - 1;
        int root = (1 << h);

        while (b >= 0) {
            if ((root | (1L << b)) * (root | (1L << b)) <= x) {
                root |= (1 << b);
            }
            b--;
        }
        return root;
    }

    /**
     * Binary search to find square root of x.
     *
     * @param x given int
     * @return square root of x
     */
    public int binarySearch(int x) {

        /* Corner case */
        if (x < 2) {
            return x;
        }

        int left = 1, right = x / 2 + 1;        // narrow range

        while (left <= right) {

            int mid = (left + right) / 2;        // avoid overflow

            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;       // decimal part is truncated, therefore directly return closest one
    }
}
