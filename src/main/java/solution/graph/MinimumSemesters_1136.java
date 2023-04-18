package solution.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are N courses, labelled from 1 to N.
 * Given relations[i] = [X, Y], representing a prerequisite relationship between X and Y: X has to be studied before Y.
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 * Return the minimum number of semesters needed to study all courses.
 * If there is no way to study all the courses, return -1.
 * Note:
 * 1. 1 <= N <= 5000
 * 2. 1 <= relations.length <= 5000
 * 3. relations[i][0] != relations[i][1]
 * 4. There are no repeated relations in the input.
 *
 * @author BorisMirage
 * Time: 2019/08/04 11:15
 * Created with IntelliJ IDEA
 */

public class MinimumSemesters_1136 {
    /**
     * Topological sorting.
     * Count indegree of each node.
     * Then find all nodes with 0 indegree and remove them all. Minus all connected nodes by 1.
     * Each time, nodes with 0 indegree is a layer, and if all nodes are reachable, return # of layer.
     * If initially there is no 0 indegree node, or after the traverse there is any node has indegree, then return -1.
     * The reason is that there is cycle in graph, or contains node unreachable.
     *
     * @param N         N courses
     * @param relations relations between courses
     * @return the minimum number of semesters needed to study all courses, return -1 if no way to study all the courses
     */
    public int minimumSemesters(int N, int[][] relations) {
        int[] indegree = new int[N];
        int count = 0, sum = 0;
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, List<Integer>> m = new HashMap<>();

        for (int[] relation : relations) {      // calculate each node's indegree
            indegree[relation[1] - 1]++;
            if (!m.containsKey(relation[0] - 1)) {
                m.put(relation[0] - 1, new ArrayList<>());
            }
            m.get(relation[0] - 1).add(relation[1] - 1);
        }

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
            sum += indegree[i];
        }

        int size = q.size();

        while (!q.isEmpty()) {
            for (int i = 0; i < size; i++) {
                int key = 0;
                if (!q.isEmpty()) {
                    key = q.poll();
                }
                List<Integer> temp = m.get(key);
                for (int j = 0; temp != null && j < temp.size(); j++) {
                    int e = temp.get(j);
                    sum--;      // minus total indegree by 1
                    if (--indegree[e] == 0) {
                        q.add(e);
                    }
                }
            }
            size = q.size();
            count++;
        }

        return (sum > 0) ? -1 : count;      // if indegree has non-zero element, then there exist unreachable node
    }
}
