package solution.stack;

import library.listnode.ListNode;

import java.util.ArrayList;

/**
 * Given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val.
 * j is the smallest possible choice.
 * If such a j does not exist, the next larger value is 0.
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * @author BorisMirage
 * Time: 2019/07/25 16:53
 * Created with IntelliJ IDEA
 */

public class NextLargerNodes_1019 {
    /**
     * Use a stack to store previous small value and its index.
     *
     * @param head head node
     * @return array of integers answer, where answer[i] = next_larger(node_{i+1}).
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        int size = 0;

        ListNode dummy = head;

        while (dummy != null) {
            size++;
            dummy = dummy.next;
        }

        int[] out = new int[size];
        ArrayList<int[]> arr = new ArrayList<>();       // use array list to replace stack
        int index = 0;

        while (head != null) {

            while (!arr.isEmpty() && head.val > arr.get(arr.size() - 1)[1]) {
                out[arr.get(arr.size() - 1)[0]] = head.val;
                arr.remove(arr.size() - 1);
            }

            arr.add(new int[]{index++, head.val});
            head = head.next;
        }

        return out;
    }
}
