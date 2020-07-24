package Solution.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * @author BorisMirage
 * Time: 2019/07/26 10:11
 * Created with IntelliJ IDEA
 */

public class KClosest_973 {
    /**
     * Use a heap to store K points
     *
     * @param points given points list
     * @param K      K nearest points
     * @return K closest points to the origin (0, 0)
     */
    public int[][] kClosest(int[][] points, int K) {

        PriorityQueue<int[]> q = new PriorityQueue<>(K, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < points.length; i++) {
            q.add(new int[]{(int) Math.pow(points[i][0], 2) + (int) Math.pow(points[i][1], 2), i});
        }

        int[][] out = new int[K][2];

        for (int i = 0; i < K; i++) {
            out[i] = points[q.poll()[1]];
        }
        return out;
    }
}
