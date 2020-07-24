package Solution.SlidingWindow;

/**
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 *
 * @author BorisMirage
 * Time: 2019/08/10 14:32
 * Created with IntelliJ IDEA
 */

public class MinSwaps_1151 {
    /**
     * Sliding window.
     * First time count all 1s, this is the window size.
     * Move this window from begin to the end, find most 1 in one window, the rest 0 is the min swap.
     *
     * @param data given array
     * @return minimum number of swaps required to group all 1’s present in the array together
     */
    public int minSwaps(int[] data) {

        /* Corner case */
        if (data.length < 2) {
            return 0;
        }

        int count = 0, window = 0, max;
        for (int i : data) {
            count += i;
        }
        if (count == 1) {
            return 0;
        }

        for (int i = 0; i < count; i++) {
            window += data[i];
        }
        max = window;
        for (int i = 1; i + count <= data.length; i++) {
            window -= data[i - 1];
            window += data[i + count - 1];
            max = Math.max(window, max);
        }
        return count - max;
    }
}
