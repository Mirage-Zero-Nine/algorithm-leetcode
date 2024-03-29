package solution.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list.
 * Find all shortest transformation sequence(s) from beginWord to endWord.
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 1. Return an empty list if there is no such transformation sequence.
 * 2. All words have the same length.
 * 3. All words contain only lowercase alphabetic characters.
 * 4. No duplicates in the word list. beginWord and endWord are non-empty and are different.
 *
 * @author BorisMirage
 * Time: 2019/05/29 10:59
 * Created with IntelliJ IDEA
 */

public class FindLadders_126 {
    /**
     * Use BFS to find if shortest path exists, and build graph mapping current word to previous word.
     * Then use DFS to search from end to beginning to construct each path.
     *
     * @param beginWord begin word
     * @param endWord   target word
     * @param wordList  middle words
     * @return all shortest transformation sequence(s) from beginWord to endWord
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        /* Corner case */
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return new LinkedList<>();
        }

        Set<String> set = new HashSet<>(wordList);
        Map<String, List<String>> m = new HashMap<>();
        List<List<String>> out = new LinkedList<>();

        if (bfs(beginWord, endWord, new HashSet<>(set), m)) {       // if shortest path exists
            List<String> tmp = new LinkedList<>();
            tmp.add(endWord);
            dfs(out, tmp, endWord, beginWord, m);       // search from end to begin
        }

        return out;
    }

    /**
     * BFS to build the graph. The graph is reversed, key is current word, and value is all words from previous level.
     *
     * @param begin      begin word
     * @param end        end word
     * @param dictionary all middle words
     * @param m          graph mapping current word to previous word
     * @return if the shortest path exists
     */
    private boolean bfs(String begin, String end, Set<String> dictionary, Map<String, List<String>> m) {
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        boolean success = false;
        while (!q.isEmpty()) {
            HashSet<String> level = new HashSet<>();
            int size = q.size();
            while (size-- > 0) {
                String current = q.poll();

                char[] tmp = current.toCharArray();
                for (int j = 0; j < tmp.length; j++) {
                    char original = tmp[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        tmp[j] = c;
                        String word = new String(tmp);      // next word
                        if (word.equals(end)) {
                            success = true;
                        }
                        if (c != original && dictionary.contains(word)) {
                            if (!level.contains(word)) {
                                List<String> next = new LinkedList<>();
                                next.add(current);
                                m.put(word, next);
                                q.offer(word);
                                level.add(word);
                            } else {
                                List<String> next = m.get(word);
                                next.add(current);
                                m.put(word, next);
                            }
                        }
                    }
                    tmp[j] = original;
                }
            }
            dictionary.removeAll(level);
        }

        return success;
    }

    /**
     * DFS to find all shortest paths based on pre-build graph.
     * Search starts from end word, and ends at begin word.
     *
     * @param out     output list
     * @param tmp     temporary list
     * @param current current word
     * @param end     end word
     * @param m       graph mapping current word to previous word
     */
    private void dfs(List<List<String>> out, List<String> tmp, String current, String end, Map<String, List<String>> m) {

        if (current.equals(end)) {
            out.add(new LinkedList<>(tmp));
            return;
        }

        List<String> next = m.get(current);
        for (String w : next) {
            tmp.add(0, w);
            dfs(out, tmp, w, end, m);
            tmp.remove(0);
        }
    }
}
