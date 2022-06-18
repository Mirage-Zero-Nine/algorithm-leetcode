package solution.divideandconquer;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * @author BorisMirage
 * Time: 2018/10/13 10:25
 * Created with IntelliJ IDEA
 */

public class GenerateTrees_95 {
    /**
     * Select each point from 1 to n as root, recursive generate each root's left sub tree and right sub tree.
     *
     * @param n given n
     * @return all combination of valid BST
     */
    public List<TreeNode> generateTrees(int n) {

        /* Corner case */
        if (n == 0) {
            return new LinkedList<>();
        }
        return helper(1, n);
    }

    /**
     * Generating BST follows divide-and-conquer.
     * Each int from 1 to n will be the root of BST.
     * In each left-subtree and right subtree, from 1 to root - 1 and root + 1 to n will be the root of subtree.
     *
     * @param start start int
     * @param end   end int
     * @return generate all structurally unique BST
     */
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> out = new LinkedList<>();

        if (start > end) {      // empty tree
            out.add(null);
            return out;
        }

        if (start == end) {     // only this node when start == end
            out.add(new TreeNode(start));
            return out;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);     // left subtree
            List<TreeNode> right = helper(i + 1, end);      // right subtree

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode tmp = new TreeNode(i);         // current tree's root node
                    tmp.left = l;
                    tmp.right = r;
                    out.add(tmp);
                }
            }
        }

        return out;
    }
}
