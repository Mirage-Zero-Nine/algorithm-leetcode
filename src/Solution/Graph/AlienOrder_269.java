package Solution.Graph;

import java.util.Arrays;
import java.util.LinkedList;
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
     *
     * @param words given word list
     * @return order of letters
     */

    public String alienOrder(String[] words) {

        /* Corner case */
        if (words.length < 1) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }

        boolean[][] graph = new boolean[26][26];        // save each char in string
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);

        char[] previous = words[0].toCharArray(), current;
        for (char x : previous) {
            indegree[x - 'a'] = 0;      // init all existing chars in given list
        }

        for (int i = 1; i < words.length; i++) {        // iter words in list

            current = words[i].toCharArray();
            for (char x : current) {
                indegree[x - 'a'] = 0;
            }

            int index = 0;

            while (index < previous.length && index < current.length && previous[index] == current[index]) {
                index++;
            }

            if (index == current.length && previous.length != current.length) {
                return "";
            } else if (index != previous.length) {
                graph[previous[index] - 'a'][current[index] - 'a'] = true;
                previous = current;
            }
        }

        int count = 0;
        for (int a : indegree) {
            count = (a == 0) ? count + 1 : count;
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                indegree[j] = (graph[i][j]) ? indegree[j] + 1 : indegree[j];
            }
        }

        Queue<Integer> q = new LinkedList<>();

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

        return order.length() == count ? order.toString() : "";
    }

    public static void main(String[] args) {
        AlienOrder_269 test = new AlienOrder_269();
//        System.out.println(test.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
//        System.out.println(test.alienOrder(new String[]{"z", "x"}));
//        System.out.println(test.alienOrder(new String[]{"z", "x", "z"}));
//        System.out.println(test.alienOrder(new String[]{"z", "z"}));
        System.out.println(test.alienOrder(new String[]{"za", "zb", "ca", "cb"}));
    }
}
