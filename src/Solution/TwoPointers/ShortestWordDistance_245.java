package Solution.TwoPointers;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between two words in the list.
 * word1 and word2 may be the same and they represent two individual words in the list.
 * Note:
 * 1. word1 and word2 are both in the list.
 *
 * @author BorisMirage
 * Time: 2019/06/19 16:53
 * Created with IntelliJ IDEA
 */

public class ShortestWordDistance_245 {
    /**
     * Two pointers problem.
     *
     * @param words contains all words
     * @param word1 first word
     * @param word2 second word
     * @return shortest distance between these two words in the list
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {

        int min = words.length - 1;     // max distance is the array length - 1

        for (int i = 0, j = -1; i < words.length; i++) {

            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (j != -1 && (!words[j].equals(words[i]) || word1.equals(word2))) {       // if word1.equals(word2)
                    min = Math.min(min, i - j);
                }
                j = i;
            }
        }
        return min;
    }
}
