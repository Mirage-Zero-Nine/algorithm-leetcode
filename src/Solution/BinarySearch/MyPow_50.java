package Solution.BinarySearch;

/**
 * Implement pow(x, n), which calculates x raised to the power n (x^n).
 *
 * @author BorisMirage
 * Time: 2018/06/26 20:53
 * Created with IntelliJ IDEA
 */

public class MyPow_50 {
    /**
     * x^(2n) = x^n^2 = x^n * x^n
     * Binary search.
     *
     * @param x input base double
     * @param n exponent
     * @return Returns a double containing the results of the power operation.
     */

    public double myPow(double x, int n) {

        /* Corner case */
        if (n == 1) {
            return x;
        }

        long pow = n;

        if (pow < 0) {      // if the exponent is negative, then base should be converted
            x = 1 / x;
            pow = -pow;
        }

        double res = 1;
        double currentProduct = x;

        for (long i = pow; i > 0; i /= 2) {

            if (i % 2 == 1) {       // current pow is odd
                res = res * currentProduct;
            }

            currentProduct = currentProduct * currentProduct;       // current pow is even
        }

        return res;
    }
}
