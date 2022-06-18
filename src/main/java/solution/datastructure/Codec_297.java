package solution.datastructure;

import library.tree.binarytree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * Only to ensure a binary tree can be serialized to a string.
 * And this string can be deserialized to the original tree structure.
 *
 * @author BorisMirage
 * Time: 2019/09/01 20:42
 * Created with IntelliJ IDEA
 */

public class Codec_297 {
    /**
     * Encodes a tree to a single string by DFS.
     *
     * @param root root node
     * @return serialized tree
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /**
     * DFS to encode tree to string. Use "#" to mark null nodes.
     *
     * @param root root node
     * @param sb   string builder to save tree value.
     */
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('#').append(',');
            return;
        }

        sb.append(root.val).append(','); // avoid using '+', only append to StringBuilder to reduce time
        serialize(root.left, sb);
        serialize(root.right, sb);
    }


    /**
     * Decodes your encoded data to tree.
     * Split the data by ",", then build a queue based on split array.
     * Build the tree based on same pre-order traverse.
     *
     * @param data encoded string
     * @return deserialized root of tree
     */
    public TreeNode deserialize(String data) {
        return deserialize(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    /**
     * Build tree by queue. Right node and left node is recursively completed.
     * If current head of queue is "#", add null to current node.
     *
     * @param q queue stores serialized node value
     * @return deserialized root of tree
     */
    private TreeNode deserialize(Queue<String> q) {
        if (q.isEmpty()) {
            return null;
        }

        String data = q.poll();
        if (data.equals("#")) {
            return null;
        }

        TreeNode r = new TreeNode(Integer.parseInt(data));

        r.left = deserialize(q);
        r.right = deserialize(q);
        return r;
    }
}
