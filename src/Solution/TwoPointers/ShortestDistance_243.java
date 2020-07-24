package Solution.TwoPointers;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * Note:
 * 1. word1 does not equal to word2.
 * 2. word1 and word2 are both in the list.
 *
 * @author BorisMirage
 * Time: 2019/06/19 16:29
 * Created with IntelliJ IDEA
 */

public class ShortestDistance_243 {
    /**
     * Two pointers point at each word that is same to word1 and word2.
     * Compare the min distance each time finds a new word.
     *
     * @param words contains all words
     * @param word1 first word
     * @param word2 second word
     * @return shortest distance between these two words in the list
     */
    public int shortestDistance(String[] words, String word1, String word2) {

        int min = words.length - 1;     // max distance is the array length - 1

        for (int i = 0, j = -1; i < words.length; i++) {

            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (j != -1 && (!words[j].equals(words[i]))) {       // avoid word1.equals(word2)
                    min = Math.min(min, i - j);
                }
                j = i;
            }
        }
        return min;
    }
}
