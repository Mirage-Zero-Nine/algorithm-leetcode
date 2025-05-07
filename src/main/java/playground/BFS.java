package playground;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> output = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                    list.add(current.val);
                }
            }
            output.add(list);
        }

        return output;
    }
}
