package library.tree;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur == null) {
                sb.append("#,");
            } else {
                sb.append(cur.val).append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        int i = sb.length();
        while (i >= 0) {        // compress string
            if (!sb.substring(i - 2, i).equals("#,")) {
                break;
            }

            i -= 2;
        }

        sb.setLength(i);

        return sb.toString();
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data serialized tree
     * @return tree
     */
    public static TreeNode deserialize(String data) {

        String[] arr = data.split(",");

        /* Corner case */
        if (arr.length == 0) {
            throw new IllegalArgumentException();
        }
        if (arr.length == 1 && (arr[0].equals("#") || arr[0].equals("null"))) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode current = q.poll();

            TreeNode left = (arr[i].equals("#") || arr[i].equals("null")) ? null : new TreeNode(Integer.parseInt(arr[i]));
            TreeNode right = (++i >= arr.length || (arr[i].equals("#") || arr[i].equals("null"))) ? null : new TreeNode(Integer.parseInt(arr[i]));

            current.left = left;
            current.right = right;

            if (left != null) {
                q.offer(left);
            }
            if (right != null) {
                q.offer(right);
            }

            i++;
        }

        return root;
    }

    /**
     * Print all nodes from tree in pre order.
     *
     * @param root root of tree
     */
    public static void printAll(TreeNode root) {
        List<Integer> tmp = new LinkedList<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()) {
            root = s.pop();
            while (root != null) {
                tmp.add(root.val);
                s.push(root.right);
                root = root.left;
            }
        }

        System.out.println(tmp);
    }

    public static void main(String[] args) {
        printAll(deserialize("1,2,3,4,5,6,7,8,9,10"));
    }
}
