package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.
 * The answer can be returned in any order.
 *
 * @author BorisMirage
 * Time: 2019/08/13 15:42
 * Created with IntelliJ IDEA
 */

public class DistanceK_863 {
    private Map<TreeNode, HashSet<TreeNode>> m = new HashMap<>();       // save linked nodes

    /**
     * BFS + BFS.
     * First BFS is to find target, and add link between parent and child node.
     * Second BFS happens when target node is found.
     * When target node is found, do BFS starts at target node, utilize the link between nodes to find distance at K.
     * Note that during the second BFS, the new link between nodes should be added as well.
     *
     * @param root   root node
     * @param target target node
     * @param K      distance K from the target node
     * @return list of the values of all nodes that have a distance K from the target node
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> out = new ArrayList<>();

        /* Corner case */
        if (root == null || K < 1) {
            if (K == 0) {
                out.add(target.val);
                return out;
            } else {
                return out;
            }
        }

        Queue<TreeNode> q = new LinkedList<>();
        m.put(root, new HashSet<>());
        if (root.left != null) {
            q.add(root.left);
        }
        if (root.right != null) {
            q.add(root.right);
        }
        connect(root.left, root);
        connect(root.right, root);
        int size = q.size();
        boolean canContinue = !(root == target);

        while (!q.isEmpty() && canContinue) {       // BFS to find target and build map
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                if (tmp != null) {
                    connect(tmp.right, tmp);
                    connect(tmp.left, tmp);
                    if (tmp.left != null) {
                        q.add(tmp.left);
                    }
                    if (tmp.right != null) {
                        q.add(tmp.right);
                    }
                    canContinue = !(target.val == tmp.val);
                }
            }
        }

        if (!m.containsKey(target)) {
            return out;
        }

        Set<TreeNode> s = new HashSet<>();
        s.add(target);
        q = new LinkedList<>();     // BFS starts at target node
        q.add(target);

        while (!q.isEmpty()) {
            size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                if (tmp != null && m.get(tmp) != null) {
                    for (TreeNode node : m.get(tmp)) {
                        if (!s.contains(node)) {
                            s.add(node);
                            q.add(node);
                            if (!m.containsKey(node)) {
                                m.put(node, new HashSet<>());
                            }

                            if (node.left != null && !m.containsKey(node)) {
                                m.get(node).add(node.left);
                            }
                            if (node.right != null && !m.containsKey(node)) {
                                m.get(node).add(node.right);
                            }
                        }
                    }
                }
            }
            K--;

            if (K == 0) {
                for (TreeNode treeNode : q) {
                    out.add(treeNode.val);
                    return out;
                }
            }
        }
        return out;
    }

    /**
     * Add double-way connection between parent and child.
     *
     * @param node   current node
     * @param parent node of current parent
     */
    private void connect(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (!m.containsKey(node)) {
            m.put(node, new HashSet<>());
            m.get(node).add(parent);
            m.get(parent).add(node);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//        root.right.left.left = new TreeNode(14);
//        root.right.left.right = new TreeNode(15);
//
//        root.right.right.left = new TreeNode(12);
//        root.right.right.right = new TreeNode(13);
//
//        root.right.right.right.right = new TreeNode(16);
//        root.right.right.right.right.right = new TreeNode(17);

        System.out.println(new DistanceK_863().distanceK(root, new TreeNode(4), 2));
    }
}
