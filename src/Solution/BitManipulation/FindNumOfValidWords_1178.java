package Solution.BitManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage";
 * Invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
 * Return an array answer, where answer[i] is the # of words in the given list words that are valid to the puzzles[i].
 *
 * @author BorisMirage
 * Time: 2019/08/31 20:12
 * Created with IntelliJ IDEA
 */

public class FindNumOfValidWords_1178 {
    /**
     * Use a bit map as hash map.
     * First convert all words into bit map.
     * Each time, check if all chars in words is in puzzle and if first char in puzzle in word.
     * Use "&" to check if all chars in word is in puzzle: word & puzzle == word
     * If word & puzzle != word, then there exists chars that in word but not in puzzle.
     *
     * @param words   given words
     * @param puzzles given puzzles
     * @return list contains # of words in the given list words that are valid to the puzzles[i]
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        int[] convertedWords = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            int chars = 0;
            for (int j = 0; j < words[i].length(); j++) {
                chars |= 1 << (words[i].charAt(j) - 'a');
            }
            convertedWords[i] = chars;
        }

        List<Integer> out = new ArrayList<>(puzzles.length);

        for (String puzzle : puzzles) {
            char first = puzzle.charAt(0);      // first char should be in word
            int firstMask = 1 << first - 'a';

            int chars = 0;
            for (int i = 0; i < puzzle.length(); i++) {
                chars |= 1 << (puzzle.charAt(i) - 'a');
            }

            int num = 0;
            for (int word : convertedWords) {
                /*
                 * 1. Check if first char in words: (word & chars) == word
                 * 2. Check if all chars in word are in puzzle: (firstMask & word) == firstMask */
                if ((word & chars) == word && (firstMask & word) == firstMask) {
                    num++;
                }
            }

            out.add(num);
        }
        return out;
    }

    public static void main(String[] args) {
        FindNumOfValidWords_1178 test = new FindNumOfValidWords_1178();
        System.out.println(test.findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"}, new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"}));
        // 1,1,3,2,4,0
    }
}
