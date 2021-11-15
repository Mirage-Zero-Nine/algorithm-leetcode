package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a Binary Search Tree and a target number.
 * Return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * @author BorisMirage
 * Time: 2019/02/18 13:37
 * Created with IntelliJ IDEA
 */

public class FindTarget_653 {

    /**
     * DFS searching and use set to store previously visited value.
     * If a correct value was found, return true.
     * Note that this method is obviously faster than using stack.
     *
     * @param root root node
     * @param k    target
     * @return if the tree contains 2 elements that their sum is equal to k
     */
    public boolean findTarget_Set(TreeNode root, int k) {
        Set<Integer> temp = new HashSet<>();
        return dfs(root, k, temp);
    }

    /**
     * Recursively search the tree using DFS and find value.
     *
     * @param r currently root node
     * @param k target
     * @param s set that store previously visited value
     * @return true if find sum
     */
    private boolean dfs(TreeNode r, int k, Set<Integer> s) {
        if (r == null) {
            return false;
        }
        if (s.contains(k - r.val)) {
            return true;
        }
        s.add(r.val);
        return dfs(r.left, k, s) || dfs(r.right, k, s);
    }
}
