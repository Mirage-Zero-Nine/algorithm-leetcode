package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer distance.
 * A pair of two leaf nodes in tree is good if the length of the shortest path between them <= distance.
 * Return the number of good leaf node pairs in the tree.
 *
 * @author BorisMirage
 * Time: 2020/07/25 22:44
 * Created with IntelliJ IDEA
 */

public class CountPairs_1530 {
    int count = 0;
    int distance;

    /**
     * Post order traversal.
     * DFS return a list that contains all length of path from current node to all the leave nodes under its subtree.
     * Iterate all the nodes in left and right subtree and find the leave node pair that distance is less than 10.
     *
     * @param root     root of tree
     * @param distance target distance
     * @return the number of good leaf node pairs in the tree
     */
    public int countPairs(TreeNode root, int distance) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        this.distance = distance;
        dfs(root);
        return count;
    }

    /**
     * Post order traversal.
     * Note that when confront a null node, return an empty list.
     * When meet a leave node, return a list contains 1.
     *
     * @param root root of current subtree
     * @return list contains all length of paths from current node to all the leave nodes under its subtree.
     */
    private List<Integer> dfs(TreeNode root) {
        List<Integer> out = new LinkedList<>();

        if (root == null) {
            return out;
        }

        if (root.left == null && root.right == null) {
            out.add(1);
            return out;
        }

        List<Integer> left = dfs(root.left), right = dfs(root.right);

        for (int l : left) {
            for (int r : right) {
                if (l + r <= distance) {        // iterate all possible path length
                    count++;
                }
            }
        }

        for (int l : left) {
            if (l < 9) {        // max distance is 10
                out.add(l + 1);
            }
        }

        for (int r : right) {
            if (r < 9) {        // max distance is 10
                out.add(r + 1);
            }
        }

        return out;
    }
}