package solution.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There is a collection of rocks, each rock has a positive integer weight.
 * Each turn, choose the two heaviest rocks and smash them together. Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 * 1. If x == y, both stones are totally destroyed;
 * 2. If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.
 * Return the weight of this stone (or 0 if there are no stones left.)
 *
 * @author BorisMirage
 * Time: 2019/07/25 10:49
 * Created with IntelliJ IDEA
 */

public class LastStoneWeight_1046 {
    /**
     * Use a heap to store each result.
     *
     * @param stones given stones
     * @return weight of this stone (or 0 if there are no stones left)
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> h = new PriorityQueue<>(Comparator.reverseOrder());

        for (int stone : stones) {
            h.add(stone);
        }

        while (h.size() >= 2) {
            int a = h.poll();
            int b = h.poll();
            if (a != b) {
                h.add(a - b);
            }
        }

        return (h.size() == 0) ? 0 : h.poll();
    }
}
