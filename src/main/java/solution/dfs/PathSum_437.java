package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path must go downwards (traveling only from parent nodes to child nodes), no other specific requirement.
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * @author BorisMirage
 * Time: 2019/03/25 10:33
 * Created with IntelliJ IDEA
 */

public class PathSum_437 {
    /**
     * DFS post order traversal. Starts from leaf node.
     * For each node, heck if current node is the end of the path (node value equals to the sum remainder).
     * Also, for each node, search each child node with original target sum (since path starts at each node).
     *
     * @param root root node
     * @param sum  target sum
     * @return number of path that sum equals given int
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /**
     * DFS postorder traverse.
     * Path contains:
     * 1. path from previous node to current node
     * 2. path start from current node to left child node.
     * 3. path start from current node to right child node.
     *
     * @param n   current node
     * @param sum given sum (use <code>long</code> to avoid overflow)
     * @return number of path equal to given int until current node
     */
    private int dfs(TreeNode n, long sum) {
        if (n == null) {
            return 0;
        }

        return (n.val == sum ? 1 : 0) + dfs(n.left, sum - n.val) + dfs(n.right, sum - n.val);
    }

    private int count = 0;

    /**
     * DFS with hash map to avoid duplicated search in child node.
     *
     * @param root root node
     * @param sum  target sum
     * @return number of path that sum equals given int
     */
    public int pathSumWithHashMap(TreeNode root, int sum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>(); // map to store path count under each prefix sum
        prefixSumCount.put(0L, 1);
        dfs(root, 0, sum, prefixSumCount);
        return count;
    }

    /**
     * Postorder traversal with hash map.
     *
     * @param node    current tree node
     * @param pathSum current path sum
     * @param target  target sum
     * @param map     hash map with path count
     */
    public void dfs(TreeNode node, long pathSum, int target, Map<Long, Integer> map) {
        if (node == null) {
            return;
        }

        pathSum += node.val;
        if (map.containsKey(pathSum - target)) {
            count += map.get(pathSum - target);
        }
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);

        dfs(node.left, pathSum, target, map);
        dfs(node.right, pathSum, target, map);

        map.put(pathSum, map.get(pathSum) - 1);
    }
}
