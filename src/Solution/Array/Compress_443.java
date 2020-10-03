package Solution.Array;

/**
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * - If the group's length is 1, append the character to s.
 * - Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 * @author BorisMirage
 * Time: 2020/10/03 09:50
 * Created with IntelliJ IDEA
 */

public class Compress_443 {
    /**
     * Simply traverse the array and count the appearance of chars. Note that the result should be added to input array.
     *
     * @param chars given array
     * @return length of compressed array
     */
    public int compress(char[] chars) {

        /* Corner case */
        if (chars.length < 2) {
            return chars.length;
        }

        int start = 0, count = 0;
        for (int i = 0; i < chars.length; i++) {
            count++;
            if (i == chars.length - 1 || chars[i] != chars[i + 1]) {
                chars[start] = chars[i];
                start++;
                if (count != 1) {
                    char[] arr = String.valueOf(count).toCharArray();
                    for (int j = 0; j < arr.length; j++, start++) {     // add each digit
                        chars[start] = arr[j];
                    }
                }
                count = 0;
            }
        }

        return start;
    }
}
