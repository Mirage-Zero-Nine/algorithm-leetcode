package solution.heap;

import java.util.PriorityQueue;

/**
 * You have some sticks with positive integer lengths.
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.
 * You perform this action until there is one stick remaining.
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 *
 * @author BorisMirage
 * Time: 2019/08/26 17:53
 * Created with IntelliJ IDEA
 */

public class ConnectSticks_1167 {
    /**
     * Each time, add two smallest sticks in array and add them back.
     * Therefore, use heap to store and poll two of them each time until heap size is smaller than 2.
     *
     * @param sticks given array
     * @return minimum cost of connecting all the given sticks into one stick in this way
     */
    public int connectSticks(int[] sticks) {

        if (sticks.length == 1) {
            return 0;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i : sticks) {
            q.add(i);
        }
        int total = 0;

        while (q.size() > 1) {
            int a = q.poll();
            int b = q.poll();

            total += a + b;

            q.add(a + b);
        }

        return total;
    }
}
