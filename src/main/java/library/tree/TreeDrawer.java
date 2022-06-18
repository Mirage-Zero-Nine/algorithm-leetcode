package library.tree;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Draw tree based on string.
 *
 * @author BorisMirage
 * Time: 2020/02/24 15:47
 * Created with IntelliJ IDEA
 */


public class TreeDrawer {

    public static void draw(TreeNode root) {
        int maxLevel = TreeDrawer.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || TreeDrawer.isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        TreeDrawer.printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            TreeDrawer.printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (TreeNode node : nodes) {
                TreeDrawer.printWhitespaces(firstSpaces - i);
                if (node == null) {
                    TreeDrawer.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (node.left != null) {
                    System.out.print("/");
                } else {
                    TreeDrawer.printWhitespaces(1);
                }

                TreeDrawer.printWhitespaces(i + i - 1);

                if (node.right != null) {
                    System.out.print("\\");
                } else {
                    TreeDrawer.printWhitespaces(1);
                }

                TreeDrawer.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(TreeDrawer.maxLevel(node.left), TreeDrawer.maxLevel(node.right)) + 1;
    }

    private static boolean isAllElementsNull(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("build a binary by String like leetcode.com, # as null pointer");
        String data = "1,2,3,4,5,6,7,8,9,10";
        TreeNode root = TreeParser.deserialize(data);
        TreeDrawer.draw(root);

        System.out.println("after invert");

        TreeNode newRoot = invertTree(root);
        TreeDrawer.draw(newRoot);
    }

    /**
     * Invert Binary Tree as example.
     *
     * @param root root of tree
     * @return inverted tree
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            root.left = invertTree(root.left);
        }

        if (root.right != null) {
            root.right = invertTree(root.right);
        }

        return root;
    }
}