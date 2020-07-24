package Solution.Graph;

import java.util.*;

/**
 * There are a total of n courses have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites.
 * For example, to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible to finish all courses?
 *
 * @author BorisMirage
 * Time: 2019/06/16 13:58
 * Created with IntelliJ IDEA
 */

public class CanFinish_207 {
    /**
     * Topological sorting with a hash map to save connections.
     * Each loop remove the connections with nodes that indegree is 0 (indegree - 1).
     *
     * @param numCourses    # of total courses
     * @param prerequisites course - prerequisite pair
     * @return if is it possible to finish all courses
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        /* Corner case */
        if (numCourses < 1 || prerequisites.length < 1) {
            return true;
        }

        HashMap<Integer, List<Integer>> m = new HashMap<>();        // prerequisite -> course pair
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();

        for (int[] arr : prerequisites) {
            indegree[arr[0]]++;
            if (!m.containsKey(arr[1])) {
                m.put(arr[1], new LinkedList<>());
            }
            m.get(arr[1]).add(arr[0]);
        }

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);       // find nodes with 0 indegree
            }
        }

        int count = 0;      // count total reachable nodes
        while (!q.isEmpty()) {

            int course = q.poll();
            count++;

            for (int i = 0; m.get(course) != null && i < m.get(course).size(); i++) {     // avoid null when reaches end of graph
                if (--indegree[m.get(course).get(i)] == 0) {        // remove nodes with 0 indegree
                    q.add(m.get(course).get(i));        // add 0 indegree node after removing
                }
            }
        }

        return count == numCourses;
    }

    /**
     * Use 2D array to store connections. This method is slower and consumes more memory. But easy to understand.
     *
     * @param numCourses    # of total courses
     * @param prerequisites course - prerequisite pair
     * @return if is it possible to finish all courses
     */
    private boolean intArray(int numCourses, int[][] prerequisites) {

        /* Corner case */
        if (numCourses < 1 || prerequisites.length < 1) {
            return true;
        }

        int[][] topo = new int[numCourses][numCourses];     // row: course; column: prerequisite of current course
        int[] indegree = new int[numCourses];       // indegree of each course

        for (int[] arr : prerequisites) {
            indegree[arr[0]]++;       // avoid duplicate prerequisite
            topo[arr[0]][arr[1]] = 1;       // current course arr[0] has a prerequisite arr[1]
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);       // find nodes with 0 indegree
            }
        }

        while (!q.isEmpty()) {
            int prerequisite = q.poll();
            count++;        // one node can be reached
            for (int i = 0; i < numCourses; i++) {
                if (topo[i][prerequisite] != 0 && --indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        return count == numCourses;
    }
}
