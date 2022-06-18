package solution.bfs;

import java.util.*;

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
     * @return length of shortest transformation sequence
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        /* Corner case */
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }

        queue.add(beginWord);
        int out = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.remove();

                if (current.equals(endWord)) {
                    return out;
                }

                for (int j = 0; j < current.length(); j++) {
                    char[] arr = current.toCharArray();

                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        arr[j] = letter;
                        String tmp = new String(arr);
                        if (set.contains(tmp)) {
                            queue.add(tmp);
                            set.remove(tmp);
                        }
                    }
                }
            }
            out++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String b = "hit";
        String e = "cog";
        String[] tmp = new String[]{"hot", "dot", "dog", "lot", "cog"};
        List<String> l = Arrays.asList(tmp);

        LadderLength_127 test = new LadderLength_127();
        System.out.println(test.ladderLength(b, e, l));

        b = "a";
        e = "c";
        tmp = new String[]{"a", "b", "c"};
        l = Arrays.asList(tmp);
        System.out.println(test.ladderLength(b, e, l));
    }
}
