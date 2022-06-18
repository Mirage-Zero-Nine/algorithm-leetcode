package playground;

/**
 * @author BorisMirage
 * Time: 2019/10/18 20:29
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestIncreasingSubarray {
    public int longestSubarray(int[] arr) throws IllegalArgumentException {

        if (arr == null) {
            throw new IllegalArgumentException("Invalid array! ");
        }
        if (arr.length < 2) {
            return arr.length;
        }

        int max = 1, tmp = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                tmp++;
            } else {
                max = Math.max(tmp, max);
                tmp = 1;
            }
        }

        return max;
    }
}
