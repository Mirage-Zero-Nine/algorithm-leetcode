package Solution.DataStructure;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 *
 * @author BorisMirage
 * Time: 2019/07/01 15:31
 * Created with IntelliJ IDEA
 */

public class ShuffleArray_384 {

    private int[] nums;
    private Random random;

    /**
     * Initialization.
     *
     * @param nums given array
     */
    public ShuffleArray_384(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     *
     * @return original configuration
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     *
     * @return random shuffling of the array
     */
    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] a = nums.clone();     // return shuffled new array
        for (int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1);
            swap(a, i, j);
        }
        return a;
    }

    /**
     * Swap two elements in array.
     *
     * @param a array
     * @param i first index
     * @param j second index
     */
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
