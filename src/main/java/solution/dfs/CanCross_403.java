package solution.dfs;

import java.util.HashSet;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * Given a list of stones' positions (in units) in sorted ascending order.
 * Determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 * Note that the frog can only jump in the forward direction.
 * Note:
 * 1. The number of stones is ≥ 2 and is < 1,100.
 * 2. Each stone's position will be a non-negative integer < 231.
 * 3. The first stone's position is always 0.
 *
 * @author BorisMirage
 * Time: 2019/09/17 16:01
 * Created with IntelliJ IDEA
 */

public class CanCross_403 {
    /**
     * DFS search with hash set for pruning to find if end position can be reached from start.
     *
     * @param stones given stones
     * @return if current position can reach end point
     */
    public boolean canCross(int[] stones) {

        /* Corner case */
        if (stones.length == 0) {
            return true;
        }
        if (stones[1] > 1) {
            return false;
        }

        HashSet<Integer> s = new HashSet<>();

        for (int i = 1; i < stones.length; i++) {
            if (i > 3 && stones[i] > stones[i - 1] * 2) {
                return false;
            }
            s.add(stones[i]);
        }

        int target = stones[stones.length - 1];

        // HashSet<String> isVisited = new HashSet<>();
        HashSet<Long> isVisited = new HashSet<>();

        return dfs(target, stones[1], 1, s, isVisited);
    }

    /**
     * DFS search.
     * Each time try 3 jumps: k - 1, k, k + 1.
     *
     * @param target    target position
     * @param start     current start index
     * @param step      k steps reach to current position
     * @param s         set records all positions with stone
     * @param isVisited record all previous visited path by position and step
     * @return if current position can reach end point
     */
    private boolean dfs(int target, int start, int step, HashSet<Integer> s, HashSet<Long> isVisited) {
        long path = ((long) start) << 32 | step;
        // String path = start+","+step;

        if (isVisited.contains(path)) {
            return false;
        }
        if (start == target) {
            return true;
        }

        isVisited.add(path);

        if (s.contains(start + step + 1) && dfs(target, start + step + 1, step + 1, s, isVisited)) {
            return true;
        }

        if (s.contains(start + step) && dfs(target, start + step, step, s, isVisited)) {
            return true;
        }

        return step > 1 && s.contains(start + step - 1) && dfs(target, start + step - 1, step - 1, s, isVisited);
    }
}
