package solutions.bfs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Returns the number of words in the shortest transformation sequence.
     *
     * <p>The sequence includes both {@code beginWord} and {@code endWord}.
     * Every intermediate word, including {@code endWord}, must be present in
     * {@code wordList}; {@code beginWord} does not need to be present.</p>
     *
     * <p>The dictionary is also used as the visited set. Removing a word when
     * it is enqueued prevents cycles and avoids maintaining a second set of
     * visited words.</p>
     *
     * @param beginWord the starting word
     * @param endWord   the target word
     * @param wordList  the dictionary of allowed transformed words
     * @return the shortest sequence length, or {@code 0} when no sequence exists
     * @implNote For {@code N} dictionary words of length {@code L}, the
     * worst-case time complexity is {@code O(N * L^2)}. The alphabet has a
     * fixed size of 26, and creating and hashing each candidate word costs
     * {@code O(L)}. Auxiliary space is {@code O(N * L)} for the dictionary and
     * BFS queue.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null ||
                endWord == null ||
                wordList == null ||
                beginWord.isEmpty() ||
                endWord.isEmpty() ||
                wordList.isEmpty() ||
                beginWord.length() != endWord.length()) {
            return 0;
        }

        Set<String> words = wordList.stream()
                .filter(Objects::nonNull)
                .filter(w -> !w.equals(beginWord))
                .collect(Collectors.toSet());

        if (!words.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>(List.of(beginWord));
        int length = 1;

        while (!queue.isEmpty()) {
            length++;

            // The queue may grow while this loop runs, so capture its size at
            // the start. Those captured entries form exactly one BFS level.
            for (int i = 0, levelSize = queue.size(); i < levelSize; i++) {
                if (enqueueNeighbors(queue.poll(), endWord, words, queue)) {
                    return length;
                }
            }
        }

        return 0;
    }

    /**
     * Generates and enqueues all unvisited one-letter mutations of a word.
     *
     * @param current the word being expanded
     * @param endWord the target word
     * @param words   the unvisited dictionary words
     * @param queue   the BFS queue
     * @return {@code true} when {@code endWord} is found
     */
    private boolean enqueueNeighbors(String current, String endWord, Set<String> words, Queue<String> queue) {
        char[] characters = current.toCharArray();

        for (int position = 0; position < characters.length; position++) {
            char original = characters[position];

            for (char replacement = 'a'; replacement <= 'z'; replacement++) {
                if (replacement != original) {
                    characters[position] = replacement;
                    String nextWord = new String(characters);

                    // remove() both checks dictionary membership and marks the word
                    // visited, so no separate visited set is needed.
                    if (words.remove(nextWord)) {
                        if (nextWord.equals(endWord)) {
                            return true;
                        }
                        queue.offer(nextWord);
                    }
                }
            }
            // Restore the buffer before changing the next position.
            characters[position] = original;
        }

        return false;
    }
}
