package Solution.Greedy;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * Giving candies to these children subjected to the following requirements:
 * 1. Each child must have at least one candy.
 * 2. Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * @author BorisMirage
 * Time: 2019/07/15 18:09
 * Created with IntelliJ IDEA
 */

public class Candy_135 {
    /**
     * If sub array is increasing, then give candy based by previous rating.
     * If sub array is decreasing, then count the length of decreasing sub array.
     * If decreasing sub array is longer than largest candies give to previous child, update candies with length.
     * Otherwise, simply add the sum from 1 to length of decreasing sub array to total candies count.
     * Finally, if decreasing sub array reaches the end of array, count the decreasing sum again.
     *
     * @param ratings given rating array
     * @return minimum total candies to give
     */
    public int candy(int[] ratings) {

        /* Corner case */
        if (ratings.length < 2) {
            return ratings.length;
        }

        int decreasing = 0;         // length of decreasing sub array
        int previousCandy = 1;      // largest candies give to a child in previous array
        int totalCandy = 1;         // sum of candies

        for (int i = 1; i < ratings.length; i++) {

            if (ratings[i] < ratings[i - 1]) {      // calculate total length of decreasing array
                decreasing++;
            } else {

                /*
                 * If there is no decreasing array, then check current rating and previous rating.
                 * If two rating values are same, previous largest candies can be set to 1.
                 * If later part is decreasing array, sum will be recalculated.
                 * Otherwise, 1 candy will suffice, since only higher rating get more candies than their neighbors. */
                if (decreasing == 0) {
                    if (ratings[i] == ratings[i - 1]) {
                        previousCandy = 1;
                    } else {        // normal increasing array
                        previousCandy++;
                    }
                    totalCandy += previousCandy;
                } else {

                    /*
                     * If reaches the end of decreasing array, then there are two conditions:
                     * If decreasing part is shorter than most previous candies , simply add sum of length to total candies.
                     * Otherwise, the whole decreasing sub array will be updated.
                     * The candies give to child should starts at lowest rating, which is 1.
                     * Then add one during the traverse of decreasing sub array.
                     * Finally, add the difference at the start of decreasing sub array.
                     * The value is length of decreasing sub array minus previous candies give to this point. */
                    totalCandy += (1 + decreasing) * decreasing / 2;

                    if (decreasing >= previousCandy) {
                        totalCandy = totalCandy + (decreasing - previousCandy) + 1;
                    }

                    previousCandy = (ratings[i] == ratings[i - 1]) ? 1 : 2;     // current should be higher than lower rating
                    totalCandy += previousCandy;
                    decreasing = 0;
                }
            }
        }

        /*
         * If decreasing sub array reaches the end of array, then the sum of this sub array will not be added to total.
         * Therefore, complete the decreasing array calculation process again to finish it. */
        if (decreasing > 0) {
            totalCandy += (1 + decreasing) * decreasing / 2;
            if (decreasing >= previousCandy) {
                totalCandy += decreasing - previousCandy + 1;
            }
        }

        return totalCandy;
    }

    /**
     * Two pass to find each position's minimum candies.
     * First pass, from left to right, find the min candies that each increasing order subarray needed.
     * Second pass, from right to left, find the required candies for decreasing order subarray.
     * During second pass, candies depends on the larger one between previous candies plus one, or the origin candies.
     *
     * @param ratings given rating array
     * @return minimum total candies to give
     */
    public int twoPass(int[] ratings) {

        /* Corner case */
        if (ratings.length < 2) {
            return ratings.length;
        }

        int n = ratings.length;
        int[] tmp = new int[n];
        Arrays.fill(tmp, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {      // if current rating is larger than left one
                tmp[i] = tmp[i - 1] + 1;
            }
        }
        int out = 0;
        for (int i = n - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {      // if left one is larger than current rating
                tmp[i - 1] = Math.max(tmp[i] + 1, tmp[i - 1]);
            }
            out += tmp[i];
        }

        out += tmp[0];

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new Candy_135().twoPass(new int[]{1, 1, 2, 3, 7, 4, 6}));        // 14
        System.out.println(new Candy_135().twoPass(new int[]{1, 1, 2}));        // 4
        System.out.println(new Candy_135().twoPass(new int[]{1, 0, 2}));        // 5
        System.out.println(new Candy_135().twoPass(new int[]{1, 1, 2, 3, 7, 6, 5, 4, 3, 2, 1}));        // 35
    }
}
