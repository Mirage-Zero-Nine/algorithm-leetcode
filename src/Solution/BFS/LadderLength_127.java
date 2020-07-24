package Solution.BFS;

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
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        Set<String> s = new HashSet<>(wordList);
        q.add(beginWord);
        int out = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String current = q.remove();

                if (current.equals(endWord)) {
                    return out;
                }

                for (int j = 0; j < current.length(); j++) {
                    char[] arr = current.toCharArray();

                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        arr[j] = letter;
                        String tmp = new String(arr);
                        if (s.contains(tmp)) {
                            q.offer(tmp);
                            s.remove(tmp);
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
