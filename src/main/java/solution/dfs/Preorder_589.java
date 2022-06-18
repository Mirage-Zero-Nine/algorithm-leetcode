package solution.dfs;

import library.tree.narytree.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * Nary-Tree is represented in their level order traversal, each group of children is separated by the null value.
 *
 * @author BorisMirage
 * Time: 2020/03/28 11:14
 * Created with IntelliJ IDEA
 */

public class Preorder_589 {
    /**
     * Normal preorder traverse. Iterate all children in node to complete DFS.
     *
     * @param root root of tree
     * @return the preorder traversal of its nodes' values
     */
    public List<Integer> preorder(Node root) {
        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (root == null) {
            return out;
        }

        dfs(root, out);

        return out;
    }

    /**
     * Preorder traverse of n-ary tree.
     * root -> each child
     *
     * @param root root of tree
     * @param out  output list
     */
    private void dfs(Node root, List<Integer> out) {
        if (root == null) {
            return;
        }

        out.add(root.val);

        for (Node n : root.children) {
            dfs(n, out);
        }
    }
}
