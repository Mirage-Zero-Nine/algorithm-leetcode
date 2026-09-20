package solutions.design;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

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
     * Uses an explicit stack for preorder DFS, so a valid skewed tree at the
     * problem's 10,000-node limit cannot overflow the Java call stack.
     * Every node is emitted once and null children are omitted because the
     * BST ordering reconstructs their positions during decoding.
     *
     * @param root given root of BST
     * @return preorder values separated by commas, or an empty string for a
     *         null tree
     * @implNote The encoded representation has O(n) length and the traversal
     *           uses O(h) auxiliary stack space, where h is the tree height.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }

        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            sb.append(node.val).append(",");

            if (node.right != null) {
                nodes.push(node.right);
            }
            if (node.left != null) {
                nodes.push(node.left);
            }
        }
        return sb.toString();
    }

    /**
     * Decodes encoded data to tree.
     * The values are preorder values.  A stack records the next left/right
     * child slot and its legal BST range; when a value does not fit a slot,
     * that slot is empty and the value is considered for the next ancestor.
     * This is the iterative equivalent of the usual recursive bounds parser.
     *
     * @param data given serialized tree
     * @return origin BST
     * @implNote Runs in O(n) time and O(h) auxiliary space for a valid BST,
     *           excluding the token storage required by the input string.
     */
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Deque<DecodingFrame> frames = new ArrayDeque<>();
        frames.push(new DecodingFrame(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        for (int i = 1; i < values.length; i++) {
            int value = Integer.parseInt(values[i]);
            boolean attached = false;

            while (!frames.isEmpty() && !attached) {
                DecodingFrame frame = frames.peek();
                if (frame.nextChild == 0) {
                    frame.nextChild = 1;
                    if (value >= frame.min && value <= frame.node.val) {
                        frame.node.left = new TreeNode(value);
                        frames.push(new DecodingFrame(frame.node.left, frame.min, frame.node.val));
                        attached = true;
                    }
                } else if (frame.nextChild == 1) {
                    frame.nextChild = 2;
                    if (value >= frame.node.val && value <= frame.max) {
                        frame.node.right = new TreeNode(value);
                        frames.push(new DecodingFrame(frame.node.right, frame.node.val, frame.max));
                        attached = true;
                    }
                } else {
                    frames.pop();
                }
            }
        }
        return root;
    }

    /**
     * Stores one preorder node's legal value range and the next child slot
     * that the decoder should try (left, then right, then complete).
     */
    private static final class DecodingFrame {
        private final TreeNode node;
        private final int min;
        private final int max;
        private int nextChild;

        private DecodingFrame(TreeNode node, int min, int max) {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }
}
