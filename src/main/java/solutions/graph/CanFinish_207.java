package solutions.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * There are a total of n courses have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites.
 * For example, to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible to finish all courses?
 * The published constraints are 1 <= n <= 2000, 0 <= prerequisites.length <= 5000,
 * valid course indices, and no repeated prerequisite pair.
 *
 * @author BorisMirage
 * Time: 2019/06/16 13:58
 * Created with IntelliJ IDEA
 */

public class CanFinish_207 {
    /**
     * Uses Kahn's topological-sort algorithm with an adjacency list. For each
     * prerequisite, the list stores the courses that depend on it, so an edge
     * {@code [course, prerequisite]} is processed in the direction
     * {@code prerequisite -> course}. The indegree array records how many
     * prerequisites each course still has. Courses whose indegree is initially
     * zero are safe to take and seed the queue; removing one from the queue
     * releases its outgoing edges, and a dependent course becomes safe exactly
     * when its indegree reaches zero.
     * <p>
     * Every dequeued course is counted once. If all courses are dequeued, the
     * prerequisites form an acyclic ordering. A cycle keeps every course in that
     * cycle above indegree zero, so the count remains smaller than
     * {@code numCourses}. This implementation retains repeated pairs in the list
     * and increments indegree for each occurrence; the corresponding decrements
     * balance those occurrences. The published problem supplies unique pairs,
     * while this behavior also supports repeated pairs as an extension.
     *
     * <p>The algorithm takes O(V + E) time and O(V + E) auxiliary space. It
     * does not modify the caller's prerequisite array, but it does consume a
     * private indegree array while processing the graph.</p>
     *
     * @param numCourses    # of total courses
     * @param prerequisites course - prerequisite pair
     * @return if is it possible to finish all courses
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // corner cases
        if (numCourses < 1 || prerequisites == null) {
            return false;
        }
        if (prerequisites.length == 0) {
            return true;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        Arrays.stream(prerequisites).forEach(n -> {
            // Store prerequisite -> dependent so processing a finished course
            // can release exactly the courses waiting on it.
            indegree[n[0]]++;
            map.computeIfAbsent(n[1], _ -> new ArrayList<>());
            map.get(n[1]).add(n[0]);
        });

        // A zero-indegree course has no unmet prerequisite and can be taken now.
        Queue<Integer> q = new ArrayDeque<>(IntStream.range(0, numCourses).filter(n -> indegree[n] == 0).boxed().toList());
        int countCourses = 0;

        while (!q.isEmpty()) {
            countCourses++;
            // Removing one prerequisite permits each dependent course to lose
            // one remaining requirement. Only the transition to zero queues it;
            // this prevents a course from being processed before all of its
            // prerequisites have been removed.
            map.getOrDefault(q.poll(), Collections.emptyList()).forEach(course -> {
                if (--indegree[course] == 0) {
                    q.add(course);
                }
            });
        }

        return countCourses == numCourses;
    }

    /**
     * Uses a {@code numCourses x numCourses} adjacency matrix together with
     * indegrees. In {@code topo[course][prerequisite]}, a nonzero value records
     * the directed edge from the prerequisite to the course. Once a prerequisite
     * is removed from the queue, scanning its column finds every dependent course
     * and decreases that course's remaining-prerequisite count. A course enters
     * the queue only on the transition to indegree zero, so it is processed after
     * all of its prerequisites have been removed.
     * <p>
     * The matrix stores each pair only once: the loading check increments
     * indegree only for a new matrix entry. This is correct for the published
     * contract, which provides unique pairs, and gives repeated pairs the natural
     * extension behavior of one logical dependency. The list approach above has
     * different storage semantics because it retains repeated pairs and balances
     * them with repeated decrements.
     * <p>
     * As with the list approach, counting every dequeued course and comparing the
     * count with {@code numCourses} detects a cycle: courses in a cycle never
     * reach indegree zero. The matrix scan costs O(V) for every dequeued course,
     * so this approach takes O(V^2 + E) time and O(V^2 + V) auxiliary space, where
     * V is the number of courses and E is the number of input pairs. It does not
     * modify the caller's prerequisite array.
     *
     * @param numCourses    # of total courses
     * @param prerequisites course - prerequisite pair
     * @return if is it possible to finish all courses
     */
    public boolean canFinishWithIntArray(int numCourses, int[][] prerequisites) {

        // This method intentionally treats zero courses or no prerequisites as
        // immediately finishable; the list method preserves its own existing
        // zero-course behavior for compatibility.
        if (numCourses < 1 || prerequisites.length < 1) {
            return true;
        }

        int[][] topo = new int[numCourses][numCourses];     // row: course; column: prerequisite of current course
        int[] indegree = new int[numCourses];       // indegree of each course

        for (int[] arr : prerequisites) {
            // Count each directed edge once; repeated prerequisite pairs do not
            // add another logical dependency in the matrix representation.
            if (topo[arr[0]][arr[1]] == 0) {
                indegree[arr[0]]++;
                topo[arr[0]][arr[1]] = 1;
            }
        }

        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                // No unmet prerequisite means this course can begin the
                // topological ordering.
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int prerequisite = q.poll();
            count++;
            // A dependent is released only when this edge removes its final
            // remaining prerequisite. If no course can be released, any
            // uncounted courses belong to a cycle or depend on one.
            for (int i = 0; i < numCourses; i++) {
                if (topo[i][prerequisite] != 0 && --indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        return count == numCourses;
    }
}
