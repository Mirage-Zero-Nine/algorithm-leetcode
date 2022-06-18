package solution.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * Return true if and only if she can.
 *
 * @author BorisMirage
 * Time: 2020/02/11 14:42
 * Created with IntelliJ IDEA
 */

public class IsNStraightHand_846 {
    /**
     * Sort the array, and count the number frequency.
     * Then traverse the array and find each consecutive group.
     * If the element is not sufficient for construct all groups, or the consecutive subarray ends, then return false.
     *
     * @param hand given array
     * @param W    group size
     * @return if the array can be rearranged into groups with size W, and consists of W consecutive cards
     */
    public boolean isNStraightHand(int[] hand, int W) {

        Map<Integer, Integer> m = new HashMap<>();      // count number frequency
        for (int n : hand) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        Arrays.sort(hand);
        for (int num : hand) {
            if (m.get(num) == 0) {      // current number is included in group
                continue;
            }

            for (int i = 0; i < W; i++) {                                   // find consecutive group with size of W
                if (!m.containsKey(num + i) || m.get(num + i) == 0) {       // if consecutive group is interrupted
                    return false;                                           // then return false
                }
                m.put(num + i, m.get(num + i) - 1);                         // update elements for consecutive group
            }
        }

        return true;
    }

    /**
     * Idea is same, the modification is to use tree map to sort the array.
     *
     * @param hand given array
     * @param W    group size
     * @return if the array can be rearranged into groups with size W, and consists of W consecutive cards
     */
    public boolean treeMap(int[] hand, int W) {

        Map<Integer, Integer> m = new TreeMap<>();

        for (int i : hand) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }

        for (int key : m.keySet()) {
            if (m.get(key) > 0) {
                for (int i = W - 1; i >= 0; i--) {
                    if (m.getOrDefault(key + i, 0) < m.get(key)) {
                        return false;
                    }
                    m.put(key + i, m.get(key + i) - m.get(key));
                }
            }
        }

        return true;
    }
}
