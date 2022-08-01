package playground;

/**
 * Binary tree traverse sample.
 *
 * @author BorisMirage
 * Time: 2019/06/10 17:16
 * Created with IntelliJ IDEA
 */

public class BinaryTreeSample {

    /**
     * Given a binary tree, print its nodes according to the "bottom-up" postorder traversal.
     *
     * @param node root of binary tree
     */
    static void printPostorder(Node node) {
        if (node == null) {
            return;
        }

        // go to the left child of current node until it's null
        printPostorder(node.left);

        // then go to the right child of current node until it's null
        printPostorder(node.right);

        // now implementing method to handle node value
        System.out.print(node.key + " ");
    }


    /**
     * Given a binary tree, print its nodes in inorder.
     *
     * @param node root of binary tree
     */
    static void printInorder(Node node) {
        if (node == null) {
            return;
        }

        // go to the left child of current node until it's null
        printInorder(node.left);

        // handle node value
        System.out.print(node.key + " ");

        // go to the right child of current node until it's null
        printInorder(node.right);
    }

    /**
     * Given a binary tree, print its nodes in preorder.
     *
     * @param node root of binary tree
     */
    static void printPreorder(Node node) {
        if (node == null) {
            return;
        }

        // first handle node value
        System.out.print(node.key + " ");

        // go to the left child of current node until it's null
        printPreorder(node.left);

        // go to the right child of current node until it's null
        printPreorder(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(14);
        root.right.left.right = new Node(15);

        root.right.right.left = new Node(12);
        root.right.right.right = new Node(13);

        root.right.right.right.right = new Node(16);
        root.right.right.right.right.right = new Node(17);


        System.out.println("Preorder traversal of binary root is: ");
        printPreorder(root);

        System.out.println("\nInorder traversal of binary root is: ");
        printInorder(root);

        System.out.println("\nPostorder traversal of binary root is: ");
        printPostorder(root);
    }

    private static class Node {
        int key;
        Node left, right;

        Node(int item) {
            key = item;
            left = right = null;
        }
    }
}