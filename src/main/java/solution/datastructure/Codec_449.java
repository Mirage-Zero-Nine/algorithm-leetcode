package solution.datastructure;

import library.tree.binarytree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * Only need to ensure a BST can be serialized to a string and it can be deserialized to the original tree.
 * The encoded string should be as compact as possible.
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 *
 * @author BorisMirage
 * Time: 2019/08/27 18:16
 * Created with IntelliJ IDEA
 */

public class Codec_449 {

    /**
     * Encodes a tree to a single string.
     * Use DFS during the encoding process.
     *
     * @param root given root of BST
     * @return serialize tree
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        encoding(root, sb);
        return sb.toString();
    }

    /**
     * DFS traverse the tree and convert data into string.
     *
     * @param root root node
     * @param sb   string builder
     */
    private void encoding(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");

        encoding(root.left, sb);
        encoding(root.right, sb);
    }

    /**
     * Decodes encoded data to tree.
     *
     * @param data given serialized tree
     * @return origin BST
     */
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));

        return decoding(nodes, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    /**
     * Decoding serialized string to tree.
     * Each time, if the peek value in tree is larger than max or smaller than min, then return null.
     * Otherwise, poll out the head of queue and create a new tree node with value.
     *
     * @param q   queue stores all nodes
     * @param min min value under current BST
     * @param max max value under current BST
     * @return tree
     */
    private TreeNode decoding(Queue<String> q, int min, int max) {
        if (q.isEmpty()) {
            return null;
        }

        int val = Integer.parseInt(q.peek());
        if (val < min || val > max) {
            return null;        // BST
        }
        q.poll();
        TreeNode r = new TreeNode(val);

        r.left = decoding(q, min, val);
        r.right = decoding(q, val, max);

        return r;
    }
}
