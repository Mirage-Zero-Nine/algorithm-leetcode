package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * @author BorisMirage
 * Time: 2019/03/23 11:16
 * Created with IntelliJ IDEA
 */

public class PathSum_113 {
    /**
     * DFS.
     *
     * @param root root tree node
     * @param sum  given sum
     * @return all root-to-leaf paths where each path's sum equals the given sum
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        dfs(root, sum, output, new ArrayList<>());

        return output;
    }


    /**
     * Running DFS to find all possible path.
     *
     * @param r      current root node
     * @param sum    required sum
     * @param output path list
     * @param cache  temporary path list
     */
    private void dfs(TreeNode r, int sum, List<List<Integer>> output, List<Integer> cache) {

        cache.add(r.val);

        if (r.left == null && r.right == null && r.val == sum) {
            output.add(new ArrayList<>(cache));

        } else {
            if (r.left != null) {
                dfs(r.left, sum - r.val, output, cache);
            }
            if (r.right != null) {
                dfs(r.right, sum - r.val, output, cache);
            }
        }

        cache.remove(cache.size() - 1);
    }
}
