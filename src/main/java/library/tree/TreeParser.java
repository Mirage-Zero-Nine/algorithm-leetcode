package library.tree;

import com.google.common.collect.Lists;
import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialize and deserialize tree.
 * Use null or # to represent null node.
 *
 * @author BorisMirage
 * Time: 2020/02/22 10:52
 * Created with IntelliJ IDEA
 */

public class TreeParser {
    /**
     * Encodes a tree to a single string.
     *
     * @param root root of tree
     * @return serialized tree
     */
    public static String serialize(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return "null";
        }

        Deque<Integer> inorderDeque = generateInorder(root);
        StringBuilder sb = new StringBuilder();
        inorderDeque.forEach(node -> sb.append(node).append(","));

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data serialized tree
     * @return tree
     */
    public static TreeNode deserialize(String data) {

        String[] array = data.split(",");

        /* Corner case */
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        if (array.length == 1 && (array[0].equals("#") || array[0].equals("null"))) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < array.length) {
            TreeNode current = queue.poll();

            TreeNode left = (array[i].equals("#") || array[i].equals("null")) ? null : new TreeNode(Integer.parseInt(array[i]));
            TreeNode right = (++i >= array.length || (array[i].equals("#") || array[i].equals("null"))) ? null : new TreeNode(Integer.parseInt(array[i]));

            current.left = left;
            current.right = right;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }

            i++;
        }

        return root;
    }

    /**
     * Print all nodes from tree in pre-order.
     *
     * @param root root of tree
     */
    public static List<Integer> convertToList(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return Lists.newArrayList((Integer) null);
        }

        Deque<Integer> inorderDeque = generateInorder(root);

        return new ArrayList<>(inorderDeque);
    }

    /**
     * Generate a deque contains all nodes in in-order.
     * Filter all null nodes append to the last of the deque.
     *
     * @param root root of the tree
     * @return deque contains all nodes in in-order
     */
    private static Deque<Integer> generateInorder(TreeNode root) {
        Deque<Integer> inorderDeque = new LinkedList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            TreeNode current = deque.poll();

            if (current == null) {
                inorderDeque.addLast(null);
            } else {
                inorderDeque.addLast(current.val);
                deque.addLast(current.left);
                deque.addLast(current.right);
            }
        }

        while (inorderDeque.peekLast() == null) {
            inorderDeque.pollLast();
        }

        return inorderDeque;
    }
}
