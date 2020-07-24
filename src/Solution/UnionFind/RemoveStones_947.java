package Solution.UnionFind;

import java.util.HashMap;

/**
 * On a 2D plane, we place stones at some integer coordinate points.
 * Each coordinate point may have at most one stone.
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * What is the largest possible number of moves we can make?
 *
 * @author BorisMirage
 * Time: 2019/08/12 19:58
 * Created with IntelliJ IDEA
 */

public class RemoveStones_947 {
    private HashMap<Integer, Integer> m = new HashMap<>();
    private int count = 0;

    /**
     * Union find.
     * Count total connected island in whole map, the max move is the total # of 1 reduce # of islands.
     *
     * @param stones given array stores stone coord
     * @return largest possible number of moves
     */
    public int removeStones(int[][] stones) {

        for (int[] stone : stones) {
            union(stone[0], stone[1] + 10000);     // reverse to avoid duplicated coord x and y
        }

        return stones.length - count;
    }

    /**
     * Find father based on given x.
     *
     * @param x find father of this node
     * @return father of x
     */
    private int find(int x) {

        if (m.putIfAbsent(x, x) == null) {      // if current node is not root
            count++;                            // one less island can be found
        }

        if (x != m.get(x)) {
            m.put(x, find(m.get(x)));           // recursively find father, reset father to avoid counting duplication
        }

        return m.get(x);
    }

    /**
     * Union two nodes.
     *
     * @param id1 first node
     * @param id2 second node
     */
    private void union(int id1, int id2) {
        int f1 = find(id1);
        int f2 = find(id2);

        if (f1 != f2) {
            count--;
            m.put(f1, f2);
        }
    }

    public static void main(String[] args) {
        System.out.println(new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
    }
}
