package Solution.DataStructure;

import Lib.Tree.BinaryTree.TreeNode;

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
        encode(root, sb);
        return sb.toString();
    }

    /**
     * DFS to encode tree to string. Use "#" to mark null nodes.
     *
     * @param r  root node
     * @param sb string builder to save tree value.
     */
    private void encode(TreeNode r, StringBuilder sb) {
        if (r == null) {
            sb.append("#").append(',');
            return;
        }
        sb.append(r.val).append(',');
        encode(r.left, sb);
        encode(r.right, sb);
    }


    /**
     * Decodes your encoded data to tree.
     *
     * @param data encoded string
     * @return deserialized root of tree
     */
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return decode(nodes);
    }

    /**
     * Build tree by queue. Right node and left node is recursively completed.
     * If current head of queue is "#", add null to current node.
     *
     * @param q queue stores serialized node value
     * @return deserialized root of tree
     */
    private TreeNode decode(Queue<String> q) {
        if (q.isEmpty()) {
            return null;
        }

        String val = q.poll();

        if (val.equals("#")) {
            return null;
        }

        TreeNode r = new TreeNode(Integer.parseInt(val));

        r.left = decode(q);
        r.right = decode(q);

        return r;
    }
}
