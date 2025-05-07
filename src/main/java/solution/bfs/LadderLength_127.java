package solution.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given beginWord and endWord and a dictionary's word list.
 * Find the length of shortest transformation sequence from beginWord to endWord.
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 1. Return 0 if there is no such transformation sequence.
 * 2. All words have the same length.
 * 3. All words contain only lowercase alphabetic characters.
 * 4. No duplicates in the word list. beginWord and endWord are non-empty and are different.
 *
 * @author BorisMirage
 * Time: 2019/05/28 16:24
 * Created with IntelliJ IDEA
 */

public class LadderLength_127 {
    /**
     * Use BFS to construct word. One char each time.
     *
     * @param beginWord begin word
     * @param endWord   target word
     * @param wordList  middle words
     * @return length of the shortest transformation sequence
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (beginWord == null || beginWord.isEmpty() || endWord == null || endWord.isEmpty() || wordList == null || wordList.isEmpty()) {
            return 0;
        }

        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }

        return bfs(beginWord, endWord, words);
    }

    /**
     * Implementation of BFS.
     *
     * @param beginWord begin word
     * @param endWord   target word
     * @param words     middle words
     * @return length of the shortest transformation sequence
     */
    private int bfs(String beginWord, String endWord, Set<String> words) {

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int length = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word != null) {
                    if (word.equals(endWord)) {
                        return length;
                    }
                    char[] wordArr = word.toCharArray();
                    for (int j = 0; j < word.length(); j++) {
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (wordArr[j] != c) {
                                char currentChar = wordArr[j];
                                wordArr[j] = c;
                                String tmp = new String(wordArr);
                                if (words.contains(tmp)) {
                                    queue.add(tmp);
                                    words.remove(tmp);
                                }
                                wordArr[j] = currentChar;
                            }
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
}
