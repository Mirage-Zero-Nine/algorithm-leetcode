package Solution.Array;

import java.util.Arrays;

/**
 * You have some apples, where arr[i] is the weight of the i-th apple.
 * You also have a basket that can carry up to 5000 units of weight.
 * Return the maximum number of apples you can put in the basket.
 *
 * @author BorisMirage
 * Time: 2019/09/21 09:39
 * Created with IntelliJ IDEA
 */

public class MaxNumberOfApples_1196 {
    /**
     * Sort array first, then starts from smallest element.
     *
     * @param arr given array
     * @return maximum number of apples you can put in the basket
     */
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int out = 0, total = 5000;

        for (int i = 0; i < arr.length && total - arr[i] >= 0; i++) {
            total -= arr[i];
            out++;
        }

        return out;
    }
}
