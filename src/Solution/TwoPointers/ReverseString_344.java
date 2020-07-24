package Solution.TwoPointers;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 * Do not allocate extra space for another array, finish it in-place with O(1) extra memory.
 * You may assume all the characters consist of printable ascii characters.
 *
 * @author BorisMirage
 * Time: 2019/10/13 14:12
 * Created with IntelliJ IDEA
 */

public class ReverseString_344 {
    /**
     * Basic two pointers problems.
     * Each time, swap two chars that pointers point at and then shrink the window.
     *
     * @param s given string
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        char tmp;
        while (left < right) {
            tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}
