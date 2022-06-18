package solution.unionfind;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, k is a real number.
 * Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * @author BorisMirage
 * Time: 2019/07/02 14:01
 * Created with IntelliJ IDEA
 */

public class CalcEquation_399 {

    private final Map<String, String> parent = new HashMap<>();       // parent of current node
    private final Map<String, Double> ratio = new HashMap<>();        // ratio of edge

    /**
     * Union find.
     * Given a query [a, b], if they share the same root, find out the value of common_root/a and common_root/b.
     * Then the result is (common_root/b) / (common_root/a).
     *
     * @param equations given equations list
     * @param values    given value list representing each equation's result
     * @param queries   given queries
     * @return answers of queries
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        if (equations == null || equations.size() == 0) {
            return new double[]{};
        }

        for (int i = 0; i < equations.size(); i++) {
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        double[] out = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0);
            String s2 = queries.get(i).get(1);
            if (!parent.containsKey(s1) || !parent.containsKey(s2) || !find(s1).equals(find(s2))) {
                out[i] = -1.0;
            } else {
                out[i] = ratio.get(s1) / ratio.get(s2);
            }
        }
//        System.out.println(parent);
        return out;
    }

    /**
     * Union.
     * If map does not contains the given node, then put it in map and consider it as root of itself.
     * This is the initially state of each given operand of equation.
     *
     * @param e1  first equation
     * @param e2  second equation
     * @param val value of equation
     */
    private void union(String e1, String e2, double val) {

        if (!parent.containsKey(e1)) {
            parent.put(e1, e1);
            ratio.put(e1, 1.0);     // a / a = 1
        }
        if (!parent.containsKey(e2)) {
            parent.put(e2, e2);
            ratio.put(e2, 1.0);
        }

        String p1 = find(e1);
        String p2 = find(e2);
        parent.put(p1, p2);
        ratio.put(p1, val * ratio.get(e2) / ratio.get(e1));
    }

    /**
     * Find root of given equation string.
     *
     * @param s given equation string
     * @return root of s
     */
    private String find(String s) {

        if (s.equals(parent.get(s))) {
            return s;       // s is root
        }

        String previous = parent.get(s);
        String root = find(previous);       // find root
        parent.put(s, root);        // update given operand's root
        ratio.put(s, ratio.get(s) * ratio.get(previous));
        return root;
    }
}