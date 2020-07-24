package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

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
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, sum, res, new ArrayList<>());

        return res;
    }


    /**
     * Running DFS to find all possible path.
     *
     * @param r     current root node
     * @param sum   required sum
     * @param res   path list
     * @param cache temporary path list
     * @return all root-to-leaf paths sum equals the given sum
     */
    private List<List<Integer>> dfs(TreeNode r, int sum, List<List<Integer>> res, List<Integer> cache) {

        cache.add(r.val);

        if (r.left == null && r.right == null && r.val == sum) {
            res.add(new ArrayList<>(cache));

        } else {
            if (r.left != null) {
                dfs(r.left, sum - r.val, res, cache);
            }
            if (r.right != null) {
                dfs(r.right, sum - r.val, res, cache);
            }
        }

        cache.remove(cache.size() - 1);

        return res;
    }
}
