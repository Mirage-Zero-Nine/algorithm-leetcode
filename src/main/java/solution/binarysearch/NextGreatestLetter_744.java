package solution.binarysearch;

/**
 * Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.
 * Note that the letters wrap around.
 * For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 *
 * @author BorisMirage
 * Time: 2021/10/18 13:58
 * Created with IntelliJ IDEA
 */

public class NextGreatestLetter_744 {
    /**
     * The array is sorted. Implement binary search to find the target char.
     * If target char is larger than the largest char in the array, directly return the first char in array.
     * Otherwise, since the array is sorted, directly implement binary search to find the target char.
     *
     * @param letters given char array
     * @param target  target char
     * @return the smallest character in the array that is larger than target
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length;

        if (target >= letters[length - 1]) {
            return letters[0];
        }

        int left = 0, right = length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return letters[left];
    }
}
