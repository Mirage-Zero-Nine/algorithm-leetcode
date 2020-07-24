package Solution.Map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 * You are also given some queries.
 * Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c.
 * If there is no solution return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/07 19:04
 * Created with IntelliJ IDEA
 */

public class ShortestDistanceColor_1182 {
    /**
     * Use a hash map to store the color position.
     * To find the min distance, ceil and floor the closest index in hash set by color, find the min one between them.
     *
     * @param colors  given colors
     * @param queries given queries
     * @return shortest distance between the given index i and the target color c
     */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> out = new LinkedList<>();
        HashMap<Integer, TreeSet<Integer>> m = new HashMap<>();     // key: colors value: position of color

        for (int i = 0; i < colors.length; i++) {
            if (!m.containsKey(colors[i])) {
                m.put(colors[i], new TreeSet<>());
            }
            m.get(colors[i]).add(i);
        }

        for (int[] q : queries) {
            int i = q[0], c = q[1];
            TreeSet<Integer> s = m.get(c);

            if (!m.containsKey(c)) {
                out.add(-1);
            } else {
                Integer a = s.floor(i), b = s.ceiling(i);
                if (a != null && b != null) {
                    out.add(Math.min(Math.abs(i - a), Math.abs(i - b)));
                } else if (a == null) {
                    out.add(Math.abs(i - b));
                } else {
                    out.add(Math.abs(i - a));
                }
            }
        }

        return out;
    }
}
