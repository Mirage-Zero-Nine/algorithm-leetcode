package library.tree;

import com.google.common.collect.Lists;
import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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

        List<Integer> inorder = generateInorder(root);
        StringBuilder sb = new StringBuilder();
        inorder.forEach(node -> sb.append(node).append(","));

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data serialized tree
     * @return tree
     */
    public static TreeNode deserialize(String data) {

        String[] array = Arrays.stream(data.split(","))
                .map(String::trim)
                .toArray(String[]::new);

        /* Corner case */
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        if (array.length == 1 && (array[0].equals("#") || array[0].equals("null"))) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
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

        List<Integer> inorder = generateInorder(root);

        return inorder;
    }

    /**
     * Generate a list containing all nodes in level-order.
     * Retain internal null children and remove null entries from the end.
     *
     * @param root root of the tree
     * @return list containing all nodes in level-order
     */
    private static List<Integer> generateInorder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        // Keep null children in the list so later nodes retain their level-order
        // positions. An index avoids repeatedly removing the front element.
        for (int index = 0; index < nodes.size(); index++) {
            TreeNode current = nodes.get(index);

            if (current == null) {
                values.add(null);
            } else {
                values.add(current.val);
                nodes.add(current.left);
                nodes.add(current.right);
            }
        }

        while (values.getLast() == null) {
            values.removeLast();
        }

        return values;
    }
}
