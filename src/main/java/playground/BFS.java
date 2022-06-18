package playground;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2019/03/23 18:20
 * Created with IntelliJ IDEA
 */

public class BFS {
    /**
     * Breadth-first search implementation via tree node.
     *
     * @param root root node
     * @return all elements in binary tree in BFS order (left first)
     */
    public List<List<Integer>> breadthFirstSearch(TreeNode root) {

        List<List<Integer>> out = new LinkedList<>();

        if (root == null) {
            return out;
        }

        LinkedList<TreeNode> layer = new LinkedList<>();
        layer.add(root);
        while (!layer.isEmpty()) {

            int size = layer.size();
            List<Integer> temp = new LinkedList<>();

            /* Tree level iteration */
            for (int i = 0; i < size; i++) {
                TreeNode c = layer.getFirst();
                if (c.left != null) {
                    layer.addLast(c.left);
                }
                if (c.right != null) {
                    layer.addLast(c.right);
                }
                temp.add(c.val);
                layer.removeFirst();
            }

            /* Add layer */
            out.add(temp);
        }
        return out;
    }
}
