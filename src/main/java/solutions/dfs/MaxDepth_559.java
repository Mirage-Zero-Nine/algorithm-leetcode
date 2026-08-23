package solutions.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

import library.tree.narytree.Node;

/**
 * Given the root of an n-ary tree, returns its maximum depth.
 *
 * <p>The depth of an empty tree is {@code 0}; the depth of a tree containing
 * only its root is {@code 1}. Both recursive and iterative depth-first
 * implementations are provided.</p>
 *
 * @author BorisMirage
 * Time: 2026/08/22 23:06
 * Created with IntelliJ IDEA
 */
public class MaxDepth_559 {
    /**
     * Calculates the maximum depth with recursive DFS.
     *
     * <p>The depth is returned from each subtree instead of being stored in an
     * instance field, so repeated calls on the same solution object are
     * independent.</p>
     *
     * @param root root of the n-ary tree; may be {@code null}
     * @return maximum root-to-leaf depth, or {@code 0} for a {@code null} root
     */
    public int maxDepth(Node root) {
        return dfs(root, 0);
    }

    /**
     * Recursive DFS helper that carries the depth of the current node's parent.
     *
     * @param root        current node; may be {@code null}
     * @param parentDepth depth of the current node's parent
     * @return maximum depth found below the current node
     */
    private int dfs(Node root, int parentDepth) {
        if (root == null) {
            return parentDepth;
        }

        int currentDepth = parentDepth + 1;
        int maxDepth = currentDepth;

        if (root.children != null) {
            for (Node c : root.children) {
                if (c != null) {
                    maxDepth = Math.max(maxDepth, dfs(c, currentDepth));
                }
            }
        }

        return maxDepth;
    }

    /**
     * Calculates the maximum depth with iterative DFS.
     *
     * <p>Each stack entry stores the node's depth. This version uses an
     * explicit stack, so a highly unbalanced tree cannot exhaust the Java
     * call stack.</p>
     *
     * @param root root of the n-ary tree; may be {@code null}
     * @return maximum root-to-leaf depth, or {@code 0} for a {@code null} root
     */
    public int maxDepthIterative(Node root) {
        if (root == null) {
            return 0;
        }

        Deque<NodeDepth> stack = new ArrayDeque<>();
        stack.push(new NodeDepth(root, 1));
        int maxDepth = 0;

        while (!stack.isEmpty()) {
            NodeDepth current = stack.pop();
            maxDepth = Math.max(maxDepth, current.depth);

            // A node may have no children list, and malformed list entries are
            // ignored just as an absent child would be.
            if (current.node.children == null) {
                continue;
            }

            current.node.children.forEach(c -> {
                if (c != null) {
                    stack.push(new NodeDepth(c, current.depth + 1));
                }
            });
        }

        return maxDepth;
    }

    /**
     * A node paired with its depth in the explicit DFS stack.
     */
    private static final class NodeDepth {
        private final Node node;
        private final int depth;

        private NodeDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
