package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * (The move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 *
 * @author BorisMirage
 * Time: 2019/07/26 14:51
 * Created with IntelliJ IDEA
 */

public class DistributeCoins_979 {
    private int move = 0;

    /**
     * DFS traverse. Count subtree nodes and coins. If they are not equal, then there are moves.
     *
     * @param root root node
     * @return number of moves required to make every node have exactly one coin
     */
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return move;
    }

    /**
     * DFS traversal. Find absolute difference between coins and nodes.
     *
     * @param r current node
     * @return absolute difference between coins and nodes
     */
    private int dfs(TreeNode r) {
        if (r == null) {
            return 0;
        }

        int left = dfs(r.left);
        int right = dfs(r.right);
        move += (Math.abs(left) + Math.abs(right));
        return r.val + left + right - 1;
    }
}
