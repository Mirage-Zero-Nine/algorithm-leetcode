package solution.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels.
 * Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends.
 * If the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock.
 * Return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * @author BorisMirage
 * Time: 2020/02/22 11:17
 * Created with IntelliJ IDEA
 */

public class OpenLock_752 {
    /**
     * Similar to word ladder. But the difference is that, each operation can change more than one char in string.
     * Use BFS to find the shortest path.
     *
     * @param deadends list of invalid combination
     * @param target   target combination
     * @return minimum total number of turns required to open the lock, or -1 if it is impossible
     */
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));

        /* Corner case */
        if (!set.add("0000")) {
            return -1;
        }

        Queue<String> q = new LinkedList<>();
        int min = 0;
        q.add("0000");      // initially state
        int size;

        while (!q.isEmpty()) {
            size = q.size();
            while (size-- > 0) {
                String current = q.poll();
                if (current.equals(target)) {
                    return min;
                }

                for (int i = 0; i < current.length(); i++) {
                    String forward = current.substring(0, i) + (char) ((Character.getNumericValue(current.charAt(i)) + 1) % 10 + '0') + current.substring(i + 1);
                    if (set.add(forward)) {
                        q.add(forward);
                    }
                    String backward = current.substring(0, i) + (char) (Math.floorMod(Character.getNumericValue(current.charAt(i)) - 1, 10) + '0') + current.substring(i + 1);
                    if (set.add(backward)) {
                        q.add(backward);
                    }
                }
            }
            min++;
        }

        return -1;
    }
}
