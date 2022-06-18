package solution.bfs;

import library.tree.binarytree.TreeNode;

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
    private final Map<TreeNode, TreeNode> map = new HashMap<>();       // save linked nodes

    /**
     * Option 1: BFS + BFS.
     * First BFS is to find target, and build children-parent relationship.
     * Second BFS started from target TreeNode, for each node in each layer, add its non-null child to next level.
     * Finally, reaches nodes with distance K.
     * Note that during the second BFS, keep a set to record visited node.
     *
     * @param root   root node
     * @param target target node
     * @param K      distance K from the target node
     * @return list of the values of all nodes that have a distance K from the target node
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        /* Corner case */
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val != target.val) {
            bfs(root, target, map);
        }

        return bfs(target, map, K);
    }

    /**
     * First BFS: build children-parent relationship when finding the target.
     *
     * @param root   root node
     * @param target target node
     * @param map    hash map stores children-parent relationship
     */
    private void bfs(TreeNode root, TreeNode target, Map<TreeNode, TreeNode> map) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean found = false;
        while (!found) {
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    map.put(current.left, current);
                    if (current.left.val == target.val) {
                        found = true;
                        break;
                    } else {
                        queue.add(current.left);
                    }
                }
                if (current.right != null) {
                    map.put(current.right, current);
                    if (current.right.val == target.val) {
                        found = true;
                        break;
                    } else {
                        queue.add(current.right);
                    }
                }
            }
        }
    }

    /**
     * Second BFS to find the nodes with distance k.
     * Keep a hash set to store the nodes visited to avoid duplicate selection.
     *
     * @param target target node
     * @param map    map to record parent node
     * @param k      distance K from the target node
     * @return list of the values of all nodes that have a distance K from the target node
     */
    private List<Integer> bfs(TreeNode target, Map<TreeNode, TreeNode> map, int k) {
        Queue<TreeNode> level = new LinkedList<>();
        List<Integer> output = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        level.add(target);

        while (k > 0) {
            int length = level.size() - 1;

            if (length < 0) { // if k is larger than the furthest level in tree, no node could be returned
                return new ArrayList<>();
            }

            while (length >= 0) {
                TreeNode current = level.poll();
                visited.add(current.val);
                if (current.left != null && !visited.contains(current.left.val)) {
                    level.add(current.left);
                }
                if (current.right != null && !visited.contains(current.right.val)) {
                    level.add(current.right);
                }
                if (map.containsKey(current) && !visited.contains(map.get(current).val)) {
                    level.add(map.get(current));
                }
                length--;
            }
            k--;
        }
        int n = level.size();
        for (int i = 0; i < n; i++) {
            output.add(level.poll().val);
        }

        return output;
    }

    /**
     * Option 2, implement DFS to find target, then implement BFS to find the kth layer of target.
     * Note that during the BFS process, keep a set to record visited node.
     * Calling same BFS method with option 1.
     *
     * @param root   root node
     * @param target target node
     * @param k      distance K from the target node
     * @return list of the values of all nodes that have a distance K from the target node
     */
    public List<Integer> distanceKDFSBFS(TreeNode root, TreeNode target, int k) {

        /* Corner case */
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val != target.val) {
            dfs(root, target, map);
        }

        return bfs(target, map, k);
    }

    /**
     * DFS to find the target and build child-parent relationship, stores in map.
     *
     * @param root   root node
     * @param target target node
     * @param map    map to record parent node
     */
    private void dfs(TreeNode root, TreeNode target, Map<TreeNode, TreeNode> map) {
        if (root.left != null) {
            map.put(root.left, root);
            if (root.left.val == target.val) {
                return;
            } else {
                dfs(root.left, target, map);
            }
        }
        if (root.right != null) {
            map.put(root.right, root);
            if (root.right.val != target.val) {
                dfs(root.right, target, map);
            }
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
