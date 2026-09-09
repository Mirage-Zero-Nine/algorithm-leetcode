package solutions.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown.
 * Someone receive a list of non-empty words, where words are sorted lexicographically by this language.
 * Derive the order of letters in this language.
 * Note:
 * 1. All letters are in lowercase.
 * 2. If A is a prefix of B, then A must appear before B in the given dictionary.
 * 3. If the order is invalid, return an empty string.
 * 4. There may be multiple valid order of letters, return any one of them is fine.
 *
 * @author BorisMirage
 * Time: 2019/06/17 11:57
 * Created with IntelliJ IDEA
 */

public class AlienOrder_269 {
    /**
     * Topological sorting.
     * The first different char in word is the order of letters in given language.
     * Every character from the dictionary is returned once; invalid prefixes and cycles return "".
     *
     * @param words given word list
     * @return order of letters
     */

    public String alienOrder(String[] words) {

        /* Corner case */
        if (words == null || words.length < 1) {
            return "";
        }

        boolean[][] graph = new boolean[26][26];        // save each char in string
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);

        for (String word : words) {
            for (char x : word.toCharArray()) {
                indegree[x - 'a'] = 0;      // init all existing chars in given list
            }
        }

        for (int i = 1; i < words.length; i++) {        // iter words in list
            String previous = words[i - 1];
            String current = words[i];
            int index = 0;

            while (index < previous.length() && index < current.length()
                    && previous.charAt(index) == current.charAt(index)) {
                index++;
            }

            if (index == current.length() && previous.length() > current.length()) {
                return "";
            } else if (index < previous.length() && index < current.length()) {
                int from = previous.charAt(index) - 'a';
                int to = current.charAt(index) - 'a';
                if (!graph[from][to]) {
                    graph[from][to] = true;
                    indegree[to]++;
                }
            }
        }

        int uniqueChars = 0;
        for (int a : indegree) {
            if (a >= 0) {
                uniqueChars++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 26; i++) {
            if (indegree[i] == 0) {
                q.offer(i);     // indegree 0
            }
        }

        StringBuilder order = new StringBuilder();

        while (!q.isEmpty()) {
            int temp = q.poll();
            order.append((char) ('a' + temp));      // convert int to char

            for (int next = 0; next < 26; next++) {
                if (graph[temp][next]) {
                    if (--indegree[next] == 0) {
                        q.offer(next);
                    }
                    graph[temp][next] = false;
                }
            }
        }

        return order.length() == uniqueChars ? order.toString() : "";
    }

}
