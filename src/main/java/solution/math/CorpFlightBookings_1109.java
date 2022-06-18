package solution.math;

/**
 * There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.
 * The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 * @author BorisMirage
 * Time: 2019/07/27 14:45
 * Created with IntelliJ IDEA
 */

public class CorpFlightBookings_1109 {
    /**
     * To obtain the sum, no necessary to add each # of seats during iteration.
     * The sum is running sum, that is, add sum to its beginning and the total of each element is i + (i - 1).
     *
     * @param bookings given flight ticket list
     * @param n        # of total flights
     * @return array of length n representing the number of seats booked on each flight in order of label
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] out = new int[n];

        for (int[] arr : bookings) {
            out[arr[0] - 1] += arr[2];
            if (arr[1] < n) {
                out[arr[1]] -= arr[2];
            }
        }

        for (int i = 1; i < out.length; i++) {
            out[i] += out[i - 1];
        }

        return out;
    }
}
