package Solution.DFS;

import Lib.Tree.NaryTree.Node;

import java.util.PriorityQueue;

/**
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree.
 * This path may or may not pass through the root.
 *
 * @author BorisMirage
 * Time: 2020/07/24 18:47
 * Created with IntelliJ IDEA
 */

public class Diameter_1522 {
    /**
     * The diameter is to find the two longest child path in the tree.
     * DFS to find the two longest paths at each node, which the target length.
     * Return the longest path at current node as the return value.
     *
     * @param root root node of tree
     * @return the length of the diameter of the tree
     */
    public int diameter(Node root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int[] max = new int[1];
        dfs(root, max);

        return max[0];
    }

    /**
     * DFS to find the length of child path.
     * Note that this approach stops at the leave node of the tree.
     *
     * @param root root of current subtree
     * @param max  max sum of two paths in tree
     * @return max length of child path in current node
     */
    private int dfs(Node root, int[] max) {

        if (root.children == null || root.children.size() == 0) {       // leave node
            return 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Node child : root.children) {
            int depth = dfs(child, max);
            pq.add(depth);
            if (pq.size() > 2) {        // find the longest two path in tree
                pq.poll();
            }
        }

        int l1 = pq.poll(), l2 = pq.size() == 0 ? 0 : pq.poll();
        max[0] = Math.max(l1 + l2, max[0]);     // longest path at current subtree

        return Math.max(l1, l2) + 1;            // longest child path at current node
    }
}
