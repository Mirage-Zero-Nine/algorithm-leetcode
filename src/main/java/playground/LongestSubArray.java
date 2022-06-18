package playground;

import java.util.Arrays;
import java.util.Random;

/**
 * @author BorisMirage
 * Time: 2018/02/02 10:20
 * Created with IntelliJ IDEA
 */

public class LongestSubArray {
    private int maxLength;
    private final int[] randomArray;
    private int currentArrayLength = 1;

    public LongestSubArray(int N) {
        randomArray = new int[N];
        Random randomInteger = new Random();
        /* Initialize a new random array. */
        for (int i = 0; i < randomArray.length; i++) {
            /* Generate numbers from min to max (including both):
             * random.nextInt(int bound)
             * Then it will generate random integer from 0 to bound. */
            randomArray[i] = randomInteger.nextInt(3);
        }
//        System.out.println(Arrays.toString(randomArray));
    }

    public int[] FindSubArray() {

        /* A cache for temporary store the start and the end of the continuous array. */
        int subStart = 0;
        int subEnd = 1;

        /* Store the real start and end of longest sub array after comparison. */
        int longestStart = 0;
        int longestEnd = 1;

        /* Counting. */
        int index;
        maxLength = 1;

        /* Loop. */
        for (index = 0; index < randomArray.length - 1; index++) {
            if (randomArray[index] == randomArray[index + 1]) {
                currentArrayLength += 1;

                /* Temporarily store the start index when new continuous element occurs. */
                if (currentArrayLength == 2) {
                    subStart = index;
                }
                if (currentArrayLength > maxLength) {
                    maxLength = currentArrayLength;

                    /* If the length is the longest then change the sub array end index. */
                    subEnd = index + 1;

                    /* Store the real longest index of sub array after comparison. */
                    longestStart = subStart;
                    longestEnd = subEnd;
                }
            } else {

                /* Reset sub array length when next element is not the same. */
                currentArrayLength = 1;
            }
        }

        /* Return the longest part of the array.
         * Arrays.copyOfRange(Array_Object, from_include, to_exclude)*/
        return Arrays.copyOfRange(randomArray, longestStart, longestEnd + 1);
    }

    public static void main(String[] args) {
        LongestSubArray unitTest = new LongestSubArray(100);
        System.out.println(Arrays.toString(unitTest.FindSubArray()));
    }
}
