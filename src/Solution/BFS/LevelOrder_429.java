package Solution.BFS;

import Lib.Tree.NaryTree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2021/10/31 11:23
 * Created with IntelliJ IDEA
 */

public class LevelOrder_429 {
    /**
     * Same as normal binary tree level order traversal, implement BFS.
     *
     * @param root root of the tree
     * @return the level order traversal of its nodes' values
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> output = new ArrayList<>();

        /* Corner case */
        if (root == null) {
            return output;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                list.add(current.val);
                for (Node n : current.children) {
                    if (n != null) {
                        queue.add(n);
                    }
                }
            }
            output.add(list);
        }

        return output;
    }
}
