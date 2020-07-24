package Solution.DataStructure;

import Lib.Tree.NaryTree.Node;

import java.util.*;

/**
 * Design an algorithm to serialize and deserialize an N-ary tree.
 * An N-ary tree is a rooted tree in which each node has no more than N children.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * Only ensure that the tree can be serialized to a string and it can be deserialized to the original tree structure.
 *
 * @author BorisMirage
 * Time: 2020/02/03 16:37
 * Created with IntelliJ IDEA
 */

public class Codec_428 {
    /**
     * Encodes a tree to a string.
     *
     * @param root given tree node
     * @return encoded string
     */
    public String serialize(Node root) {
        List<String> list = new LinkedList<>();
        serializeHelper(root, list);
        return String.join(",", list);
    }

    /**
     * Pre-order traversal to encoded each node's value and its children list size.
     *
     * @param root root node of tree
     * @param list list of child
     */
    private void serializeHelper(Node root, List<String> list) {
        if (root == null) {
            return;
        }

        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node child : root.children) {
            serializeHelper(child, list);
        }
    }

    /**
     * Decodes encoded string to tree.
     *
     * @param data encoded string
     * @return decoded tree
     */
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] array = data.split(",");       // split to string array
        Queue<String> q = new LinkedList<>(Arrays.asList(array));

        return deserializeHelper(q);
    }

    /**
     * Build the tree based on node value and children list size. Same order as preorder traversal.
     *
     * @param q queue of all nodes and children size
     * @return deserialized n-ary tree
     */
    private Node deserializeHelper(Queue<String> q) {
        Node root = new Node();
        root.val = Integer.parseInt(q.poll());
        int size = Integer.parseInt(q.poll());
        root.children = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            root.children.add(deserializeHelper(q));
        }

        return root;
    }
}
