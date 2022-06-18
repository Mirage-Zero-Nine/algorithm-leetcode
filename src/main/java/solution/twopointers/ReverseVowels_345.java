package solution.twopointers;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * @author BorisMirage
 * Time: 2019/06/19 17:34
 * Created with IntelliJ IDEA
 */

public class ReverseVowels_345 {
    /**
     * Two pointers. Swipe two chars in array if they were both vowels.
     *
     * @param s given string
     * @return string reversed vowels
     */
    public String reverseVowels(String s) {

        /* Corner case */
        if (s.length() < 2) {
            return s;
        }

        char[] v = "aeiouAEIOU".toCharArray();
        Set<Character> vowel = IntStream
                .range(0, v.length)
                .mapToObj(i -> v[i])
                .collect(Collectors.toSet());

        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;

        while (left < right) {

            while (left < right && !vowel.contains(arr[left])) {
                left++;
            }
            while (left < right && !vowel.contains(arr[right])) {
                right--;
            }

            char t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;

            left++;
            right--;
        }

        return new String(arr);
    }
}
