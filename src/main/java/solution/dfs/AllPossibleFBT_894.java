package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 * Return a list of all possible full binary trees with N nodes.
 * Each element of the answer is the root node of one possible tree.
 * Each node of each tree in the answer must have node.val = 0.
 *
 * @author BorisMirage
 * Time: 2019/06/12 18:25
 * Created with IntelliJ IDEA
 */

public class AllPossibleFBT_894 {
    /**
     * Recursively generate subtree, then attach to the previous node.
     * The parameter passing during the recursion is the remaining number of nodes in tree.
     *
     * @param n number of nodes in tree
     * @return list of all possible full binary trees with N nodes
     */
    public List<TreeNode> allPossibleFBT(int n) {

        List<TreeNode> out = new LinkedList<>();

        /* Corner case */
        if (n % 2 == 0) {
            return out;
        }

        if (n == 1) {       // base case
            out.add(new TreeNode(0));
            return out;
        }

        for (int i = 1; i < n - 1; i += 2) {        // note that the # remaining node should reduce 1 of current root
            List<TreeNode> left = allPossibleFBT(i);                   // left subtree
            List<TreeNode> right = allPossibleFBT(n - i - 1);       // right subtree

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode tmp = new TreeNode(0);
                    tmp.left = l;
                    tmp.right = r;
                    out.add(tmp);
                }
            }
        }

        return out;
    }
}
