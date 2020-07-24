package Solution.Graph;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0, course 1 has to be taken.
 * Given the total number of courses and prerequisite pairs, return the ordering of courses taken to finish all courses.
 * There may be multiple correct orders, return one of them will suffice.
 * If it is impossible to finish all courses, return an empty array.
 * Note:
 * 1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * 2. There are no duplicate edges in the input prerequisites.
 *
 * @author BorisMirage
 * Time: 2019/06/16 18:45
 * Created with IntelliJ IDEA
 */

public class FindOrder_210 {
    /**
     * Add a list during the topological sorting. After sorting if it is available to finish, return the list.
     *
     * @param numCourses    # of total courses
     * @param prerequisites course - prerequisite pair
     * @return the ordering of courses have to take to finish all courses
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        /* Corner case */
        if (numCourses < 1) {
            return new int[]{};
        }

        HashMap<Integer, List<Integer>> m = new HashMap<>();        // prerequisite - course pair
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();

        for (int[] arr : prerequisites) {
            indegree[arr[0]]++;
            if (!m.containsKey(arr[1])) {
                m.put(arr[1], new LinkedList<>());
            }
            m.get(arr[1]).add(arr[0]);
        }

        LinkedList<Integer> out = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);       // find nodes with 0 indegree
                out.add(i);
            }
        }

        int count = 0;      // count total reachable nodes
        while (!q.isEmpty()) {

            int course = q.poll();
            count++;

            /* Remove the connections with nodes that indegree is 0 (indegree - 1) */
            for (int i = 0; m.get(course) != null && i < m.get(course).size(); i++) {     // avoid Null pointer when reaches end of graph

                if (--indegree[m.get(course).get(i)] == 0) {
                    q.add(m.get(course).get(i));        // add 0 indegree node after removing
                    out.add(m.get(course).get(i));
                }
            }
        }

        if (count == numCourses) {
            int[] res = new int[out.size()];

            for (int i = 0; i < out.size(); i++) {
                res[i] = out.get(i);
            }

            return res;
        }
        return new int[0];
    }
}
