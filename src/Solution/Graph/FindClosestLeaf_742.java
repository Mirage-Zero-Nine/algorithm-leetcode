package Solution.Graph;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.*;

/**
 * Given a binary tree where every node has a unique value, and a target key k.
 * Find the value of the nearest leaf node to target k in the tree.
 * Nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree.
 * Also, a node is called a leaf if it has no children.
 * Note:
 * 1. root represents a binary tree with at least 1 node and at most 1000 nodes.
 * 2. Every node has a unique node.val in range [1, 1000].
 * 3. There exists some node in the given binary tree for which node.val == k.
 *
 * @author BorisMirage
 * Time: 2019/06/12 15:53
 * Created with IntelliJ IDEA
 */

public class FindClosestLeaf_742 {
    /**
     * This is actually not a tree problem, it is a graph problem.
     * First do the DFS from root node to find target node in tree.
     * Then do BFS from node k to obtain the nearest leave node.
     * The is actually based on undirected graph, where nodes has a double-way connection with other nodes.
     *
     * @param root root node
     * @param k    target node
     * @return nearest leaf node to target k in the tree
     */
    public int findClosestLeaf(TreeNode root, int k) {

        /* Corner case */
        if (root == null) {
            return -1;
        }
        if (root.right == null && root.left == null) {
            return root.val;
        }

        HashMap<TreeNode, TreeNode> m = new HashMap<>();
        HashSet<TreeNode> s = new HashSet<>();

        TreeNode tmp = dfs(root, k, m);

        return bfs(tmp, s, m);      // k is not found in tree (based on the problem description, it will not happen)
    }

    /**
     * DFS to find k in tree.
     *
     * @param r root
     * @param k target
     * @param m hash map works as double linked list
     * @return node k in tree
     */
    private TreeNode dfs(TreeNode r, int k, HashMap<TreeNode, TreeNode> m) {
        if (r.val == k) {
            return r;
        }

        if (r.left != null) {
            m.put(r.left, r);
            TreeNode left = dfs(r.left, k, m);
            if (left != null) {
                return left;
            }
        }
        if (r.right != null) {
            m.put(r.right, r);
            return dfs(r.right, k, m);
        }
        return null;
    }

    /**
     * Use BFS to find closest leaf in tree.
     *
     * @param n target node
     * @param s hash set stores previous visited node
     * @param m hash map stores child-parent pair
     * @return closest leaf
     */
    private int bfs(TreeNode n, HashSet<TreeNode> s, HashMap<TreeNode, TreeNode> m) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(n);
        int size;

        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();

                if (tmp.left == null && tmp.right == null) {
                    return tmp.val;
                }
                if (tmp.left != null & s.add(tmp.left)) {
                    q.add(tmp.left);
                }
                if (tmp.right != null & s.add(tmp.right)) {
                    q.add(tmp.right);
                }

                TreeNode next = m.get(tmp);
                if (next != null && s.add(next)) {
                    q.add(next);
                }
            }
        }

        return -1;
    }
}
