package Solution.Trie;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * @author BorisMirage
 * Time: 2020/05/22 11:55
 * Created with IntelliJ IDEA
 */

public class NumMatchingSubseq_792 {
    /**
     * n pointers. Each pointer points at the last searched char in each word.
     * Create a iterator class that iterate all characters in the string. Then traverse the string.
     * Keep a list to group and store all the iterators. Each iterator is grouped as the first char in the iterator.
     * Under each char, obtain all iterators at this char.
     * If the iterator is at the last index of word, one subsequence is found.
     * Otherwise, iterate to the next char in current word and add back to the list.
     * Time analysis: O(n * m), where n is the length of the string, m is the length of the words list.
     *
     * @param S     given string
     * @param words given words list
     * @return the number of words[i] that is a subsequence of S
     */
    public int numMatchingSubseq(String S, String[] words) {

        List<List<CharacterIterator>> tmp = new ArrayList<>();      // store iterators based on first char of iterator

        for (int i = 0; i < 26; i++) {
            tmp.add(new ArrayList<>());
        }
        for (String w : words) {
            tmp.get(w.charAt(0) - 'a').add(new CharacterIterator(w));
        }

        int count = 0;
        for (Character c : S.toCharArray()) {
            int index = c - 'a';
            List<CharacterIterator> current = tmp.get(index);
            tmp.set(index, new ArrayList<>());      // remove all iterators in current char

            for (CharacterIterator i : current) {
                if (!i.isLast()) {                  // if current char in the iterator is not the last char in word
                    char next = i.getNext();        // move to next char
                    tmp.get(next - 'a').add(i);     // add iterator to next char
                } else {
                    count++;                        // otherwise, if the last char is reached, one word is found
                }
            }
        }

        return count;
    }

    /**
     * Iterator class that iterate all characters in the string.
     * One int represents current position of the iterator.
     */
    static class CharacterIterator {
        String s;           // string in this iterator
        int current;        // current index of char

        /**
         * Constructor of the iterator.
         *
         * @param s string of the iterator
         */
        CharacterIterator(String s) {
            this.s = s;
            current = 0;        // initially, the index points at the first char in string
        }

        /**
         * Check if current char is the last char in string
         *
         * @return if current char is the last char in string
         */
        boolean isLast() {
            return current == s.length() - 1;
        }

        /**
         * Return the next character in the string and move index forward by 1.
         *
         * @return next character in string, or null if current char is the last char in string
         */
        Character getNext() {
            if (isLast()) {     // if current position is the last char, return null
                return null;
            }

            return s.charAt(++current);
        }
    }

    /**
     * Idea is same as the previous approach.
     * The difference is that this solution imports the StringCharacterIterator instead of create one.
     *
     * @param S     given string
     * @param words given words list
     * @return the number of words[i] that is a subsequence of S
     */
    public int numMatchingSubseqIterator(String S, String[] words) {

        List<List<StringCharacterIterator>> waiting = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            waiting.add(new ArrayList<>());
        }

        for (String w : words) {
            waiting.get(w.charAt(0) - 'a').add(new StringCharacterIterator(w));
        }

        int count = 0;
        for (Character c : S.toCharArray()) {
            int index = c - 'a';
            List<StringCharacterIterator> current = waiting.get(index);
            waiting.set(index, new ArrayList<>());
            for (StringCharacterIterator i : current) {
                char next = i.next();
                if (next != StringCharacterIterator.DONE) {     // if not iterated to the end of word
                    waiting.get(next - 'a').add(i);
                } else {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumMatchingSubseq_792().numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));     // 2
        System.out.println(new NumMatchingSubseq_792().numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));      // 3
    }
}
