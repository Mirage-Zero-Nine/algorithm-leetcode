package solution.unionfind;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a string s, and an array of pairs of indices where pairs[i] = [a, b] indicates 2 indices of the string.
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * @author BorisMirage
 * Time: 2019/09/22 14:28
 * Created with IntelliJ IDEA
 */

public class SmallestStringWithSwaps_1202 {
    /**
     * If two chars in string can be swapped, then they can be connected.
     * If multiple chars in string can be swapped, then these chars are connected.
     * Therefore, the string and pairs are a graph, where the pairs constructed the union find structure.
     * Union the pairs of chars, and then put the connected chars into map with heap that can sort chars.
     * Finally, find the smallest char in each position, this order is the lexicographically smallest string.
     *
     * @param s     given string
     * @param pairs given swap pairs
     * @return the lexicographically smallest string that s can be changed to after using the swaps
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);

        for (List<Integer> p : pairs) {
            uf.union(p.get(0), p.get(1));
        }

        /*
         * Key: root of each char.
         * Value: chars under same root(connected). */
        Map<Integer, PriorityQueue<Character>> m = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int r = uf.find(i);
            if (!m.containsKey(r)) {
                PriorityQueue<Character> pq = new PriorityQueue<>(Comparator.naturalOrder());
                m.put(r, pq);
            }
            m.get(r).add(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        int[] father = uf.father;
        for (int i = 0; i < n; i++) {
            sb.append(m.get(father[i]).poll());
        }

        return sb.toString();
    }

    static class UnionFind {
        int[] father;
        int[] size;

        /**
         * Constructor of UnionFind.
         *
         * @param n length of string
         */
        UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Union two chars in string
         *
         * @param x first char's index
         * @param y second char's index
         */
        void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fx] = fy;
            }
        }

        /**
         * Find root of char.
         *
         * @param x index of searching char
         * @return root of current char
         */
        int find(int x) {
            if (father[x] == x) {
                return x;
            }
            father[x] = find(father[x]);

            return father[x];
        }
    }
}
