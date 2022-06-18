package solution.dfs;


import java.util.Stack;

/**
 * Given a string of comma separated values, verify if it is a correct preorder traversal serialization of binary tree.
 * Find an algorithm without reconstructing the tree.
 *
 * @author BorisMirage
 * Time: 2019/09/02 14:54
 * Created with IntelliJ IDEA
 */

public class IsValidSerialization_331 {
    /**
     * Follow pre order traverse rule to "build" the tree.
     * 1. Each non-empty node creates two slots for null node (left, right child).
     * 2. Each non-empty node requires one slot, and null node consume one slot of node.
     * Therefore, use "," as the mark that one slot is used.
     * If one non-empty node is found, slot + 2.
     * If one null node is found, slot remains unchanged.
     * If meet a ",", regraded as one node is used, slot - 1.
     *
     * @param preorder given preorder nodes
     * @return whether it is a correct preorder traversal serialization of a binary tree
     */
    public boolean isValidSerialization(String preorder) {
        int slot = 1;
        int n = preorder.length();

        for (int i = 0; i < n; ++i) {

            /*
             * ",": use one node
             * number: create two slot for nodes (null & non-empty)
             * "#": null node does not take node, combine with "," to use one slot. */
            if (preorder.charAt(i) == ',') {

                slot--;        // one node takes one slot
                if (slot < 0) {        // no more slots available
                    return false;
                }

                if (preorder.charAt(i - 1) != '#') {         // non-empty node creates two children slots
                    slot += 2;
                }
            }
        }

        /*
         * Check the last node.
         * Since last node does not have ",", it requires extra check. */
        slot = (preorder.charAt(n - 1) == '#') ? slot - 1 : slot + 1;

        return slot == 0;      // all slots should be used up
    }

    /**
     * Using stack to store nodes including "#".
     * Case 1: number -> push it to the stack.
     * Case 2: # -> check if the top of stack is also #.
     * If so, pop until top of stack is not #.
     * Finally, check if stack size is 1, and stack top is #.
     *
     * @param preorder given preorder nodes
     * @return whether it is a correct preorder traversal serialization of a binary tree
     */
    public boolean stack(String preorder) {

        /* Corner case */
        if (preorder.charAt(0) == '#' && preorder.length() > 1) {
            return false;
        }

        Stack<String> s = new Stack<>();
        String[] arr = preorder.split(",");

        for (String value : arr) {

            while (value.equals("#") && !s.isEmpty() && s.peek().equals("#")) {
                s.pop();
                if (s.isEmpty()) {
                    return false;       // pop out root -> invalid tree
                }
                s.pop();
            }
            s.push(value);
        }
        System.out.println(s);
        return s.size() == 1 && s.peek().equals("#");
    }

    /**
     * All non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root.
     * All null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
     * Difference between out degree and in degree: outdegree - indegree.
     * If the node is not null, increase diff by 2, because it provides two out degrees.
     * If a serialization is correct, outdegree - indegree should never be negative and diff will be zero when finished.
     *
     * @param preorder given preorder nodes
     * @return whether it is a correct preorder traversal serialization of a binary tree
     */
    public boolean degree(String preorder) {

        /* Corner case */
        if (preorder.charAt(0) == '#' && preorder.length() > 1) {
            return false;
        }

        String[] nodes = preorder.split(",");
        int diff = 1;       // root has
        for (String node : nodes) {
            if (--diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }

        return diff == 0;
    }
}
