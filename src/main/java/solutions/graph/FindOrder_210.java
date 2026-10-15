package solutions.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * Topological-sort + BFS: count each course's incoming
     * prerequisite edges, then repeatedly emit a course whose count is zero
     * and reduce the counts of its dependents. A cycle leaves courses
     * un-emitted, so it produces the required empty result.
     *
     * <p>Time complexity: O(V + E) expected, where V is {@code numCourses}, E
     * is the number of prerequisite pairs, and HashMap operations are expected
     * O(1).</p>
     * <p>Auxiliary space complexity: O(V + E) for indegrees, the queue, and
     * adjacency storage; the returned output array separately uses O(V), so
     * total allocated space is still O(V + E).</p>
     *
     * @param numCourses    the number of courses, labeled from {@code 0} through
     *                      {@code numCourses - 1}
     * @param prerequisites prerequisite pairs in {@code [course, prerequisite]}
     * @return a valid course ordering, or an empty array when all courses
     * cannot be completed
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // corner cases
        if (numCourses < 1 || prerequisites == null) {
            return new int[0];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegrees = new int[numCourses];

        // A pair [course, prerequisite] is stored in the prerequisite's outgoing
        // list. The indegree counts how many prerequisite edges still block each
        // course, so completing one course can reduce the counts of its neighbors.
        Arrays.stream(prerequisites).forEach(prerequisite -> {
            indegrees[prerequisite[0]]++;
            map.computeIfAbsent(prerequisite[1], _ -> new ArrayList<>()).add(prerequisite[0]);
        });

        // A zero-indegree course has no unfinished prerequisite and is therefore
        // schedulable now. The queue holds every currently schedulable course;
        // courses that become unblocked are added as their counts reach zero.
        Queue<Integer> q = IntStream.range(0, numCourses)
                .filter(n -> indegrees[n] == 0)
                .boxed().collect(Collectors.toCollection(ArrayDeque::new));
        int[] output = new int[numCourses];
        int currentOutputSize = 0;

        while (!q.isEmpty()) {
            // peek and poll deliberately refer to the same queue head: record the
            // course being taken, then remove that course before visiting its edges.
            output[currentOutputSize++] = q.peek();
            // Each outgoing edge represents one prerequisite now satisfied by the
            // removed course. The filter both decrements that dependent's remaining
            // count and queues it exactly once, at the moment its last blocker is gone.
            map.getOrDefault(q.poll(), Collections.emptyList()).stream()
                    .filter(next -> --indegrees[next] == 0)
                    .forEach(q::add);
        }

        // If the queue empties early, every remaining course still has a blocker.
        // Those blockers form a cycle, because every edge out of an emitted course
        // was already removed; otherwise all courses would have become available.
        return currentOutputSize == numCourses ? output : new int[0];
    }
}
