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
     * Use a max heap to store K points
     *
     * @param points given points list
     * @param k      K nearest points
     * @return K closest points to the origin (0, 0)
     */
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>() { // {distance, index of point}
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];  // max heap
            }
        });

        for (int i = 0; i < points.length; i++) {
            pq.add(new int[]{(int) Math.pow(points[i][0], 2) + (int) Math.pow(points[i][1], 2), i});
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] out = new int[k][2];

        for (int i = 0; !pq.isEmpty() && i < k; i++) {
            out[i] = points[pq.poll()[1]];
        }

        return out;
    }
}
