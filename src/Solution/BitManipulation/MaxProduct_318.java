package Solution.BitManipulation;

/**
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * Each word will contain only lower case letters.
 * If no such two words exist, return 0.
 *
 * @author BorisMirage
 * Time: 2019/07/01 14:01
 * Created with IntelliJ IDEA
 */

public class MaxProduct_318 {
    /**
     * Use a bit counter to count char's appearance.
     *
     * @param words words list
     * @return maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters
     */
    public int maxProduct(String[] words) {

        int[] exist = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            String tmp = words[i];
            exist[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                exist[i] |= 1 << (tmp.charAt(j) - 'a');     // code word to int number
            }
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {

                /*
                 * (exist[i] & exist[j]) == 0 means word i and j has no same char.
                 * Each char is marked as 1 in exist[], and if they have same char, then & will not output 0. */
                if ((exist[i] & exist[j]) == 0 && (words[i].length() * words[j].length() > max)) {
                    max = words[i].length() * words[j].length();
                }
            }
        }
        return max;
    }
}
