package Solution.Heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M.
 * Each worker and bike is a 2D coordinate on this grid.
 * Assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 * Note:
 * 1. 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 * 2. All worker and bike locations are distinct.
 * 3. 1 <= workers.length <= bikes.length <= 10
 *
 * @author BorisMirage
 * Time: 2019/07/16 15:21
 * Created with IntelliJ IDEA
 */

public class AssignBikes_1057 {
    /**
     * Use a heap with modified comparator to store the distance-worker-bike pair.
     *
     * @param workers given workers array
     * @param bikes   given bikes
     * @return minimum possible sum of Manhattan distances between each worker and their assigned bike
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            int compare = Integer.compare(a[0], b[0]);      // compare distance

            if (compare == 0) {     // if distance is same
                if (a[1] == b[1]) {     // worker has smaller # has higher priority
                    return Integer.compare(a[2], b[2]);     // otherwise bike has smaller # has higher priority
                }
                return Integer.compare(a[1], b[1]);
            }
            return compare;
        });

        for (int i = 0; i < workers.length; i++) {
            int[] w = workers[i];

            for (int j = 0; j < bikes.length; j++) {
                int[] b = bikes[j];
                q.add(new int[]{Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]), i, j});      // add each worker to each bike's distance
            }
        }

        int[] out = new int[workers.length];
        Arrays.fill(out, -1);
        Set<Integer> s = new HashSet<>();

        while (s.size() < out.length && !q.isEmpty()) {

            int[] arr = q.poll();

            if (out[arr[1]] == -1 && !s.contains(arr[2])) {     // if the worker has not assigned a bike
                out[arr[1]] = arr[2];
                s.add(arr[2]);
            }
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AssignBikes_1057().assignBikes(new int[][]{{0, 0}, {2, 1}}, new int[][]{{1, 2}, {3, 3}})));
    }
}
