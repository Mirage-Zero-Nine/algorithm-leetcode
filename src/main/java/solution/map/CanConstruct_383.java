package solution.map;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * @author BorisMirage
 * Time: 2022/06/19 23:34
 * Created with IntelliJ IDEA
 */

public class CanConstruct_383 {
    /**
     * Map char appearance in magazine into a int array.
     * Then iterate ransomNote, if a char in array was all used, return false.
     *
     * @param ransomNote string to check
     * @param magazine   dictionary string
     * @return if ransomNote can be constructed by using the letters from magazine
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] array = new int[26];

        for (char c : magazine.toCharArray()) {
            array[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (--array[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
