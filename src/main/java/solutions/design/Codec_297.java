package solutions.design;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.List;
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
     * Encodes a binary tree in preorder.  Each node contributes its integer
     * value, and each missing child contributes {@code #}; commas separate
     * tokens.  Recording both missing children preserves the shape, so trees
     * with the same values in different positions remain distinguishable.
     *
     * <p>The traversal visits every real node and every null child once, so
     * the time and output space are {@code O(n)} for a tree with {@code n}
     * nodes.  The recursive call stack uses {@code O(h)} auxiliary space,
     * where {@code h} is the tree height, in addition to the output.  The
     * input tree is read only and is not changed.</p>
     *
     * @param root root of the tree to encode, or {@code null}
     * @return the preorder representation
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(sb, root);
        return sb.toString();
    }

    private void dfs(StringBuilder sb, TreeNode root) {
        if (root == null) {
            // A marker is required for each absent child to retain structure.
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        dfs(sb, root.left);
        dfs(sb, root.right);
    }

    /**
     * Rebuilds the tree from the preorder representation produced by
     * {@link #serialize(TreeNode)}.  The queue exposes tokens in traversal
     * order; consuming one token for a node and then recursively consuming
     * its left and right subtrees mirrors the encoding exactly.
     *
     * <p>Each token is consumed once, giving {@code O(n)} time.  The token
     * queue uses {@code O(n)} auxiliary space, and recursive calls use another
     * {@code O(h)} stack frames for a tree of height {@code h}; the returned
     * tree itself is excluded from auxiliary-space accounting.  The method
     * expects a valid serialized representation containing integer values and
     * {@code #} null markers.</p>
     *
     * @param data serialized tree
     * @return the reconstructed root, or {@code null} for a null tree
     */
    public TreeNode deserialize(String data) {
        return deserialize(new ArrayDeque<>(List.of(data.split(","))));
    }

    /**
     * Consumes the next preorder token and recursively constructs its two
     * children.  Returning immediately for a null marker is what makes the
     * following token belong to the parent’s next child.
     *
     * @param q remaining preorder tokens
     * @return the subtree represented by the next token
     */
    private TreeNode deserialize(Queue<String> q) {
        String val = q.poll();
        if (val == null || val.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(q);
        root.right = deserialize(q);

        return root;
    }
}
