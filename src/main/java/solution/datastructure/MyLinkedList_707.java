package solution.datastructure;

/**
 * Design your implementation of the linked list.
 * You can choose to use the singly linked list or the doubly linked list.
 * A node in a singly linked list should have two attributes: val and next.val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list.
 * Assume all nodes in the linked list are 0-indexed.
 * Implement these functions in your linked list class:
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list.
 * After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
 * If index equals to the length of linked list, the node will be appended to the end of linked list.
 * If index is greater than the length, the node will not be inserted.
 * If index is negative, the node will be inserted at the head of the list.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 *
 * @author BorisMirage
 * Time: 2019/09/22 17:03
 * Created with IntelliJ IDEA
 */
public class MyLinkedList_707 {
    private final Node root = new Node();
    private int size;
    private final Node end = new Node();

    /**
     * Initialize of MyLinkedList.
     */
    public MyLinkedList_707() {
        size = 0;
        root.next = end;
        end.previous = root;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     *
     * @param index index to get
     * @return value in this node, or -1 if it is invalid
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            return -1;
        }
        Node tmp = root;
        for (int i = 0; i <= index; i++) {
            tmp = tmp.next;
        }
        return tmp.val;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     *
     * @param val add value
     */
    public void addAtHead(int val) {
        Node n = new Node();
        n.val = val;
        Node tmp = root.next;
        root.next = n;
        n.next = tmp;
        n.previous = root;
        tmp.previous = n;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     *
     * @param val add value
     */
    public void addAtTail(int val) {
        Node n = new Node();
        n.val = val;
        Node tmp = end.previous;
        n.next = end;
        end.previous = n;
        tmp.next = n;
        n.previous = tmp;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     * If index is negative, the node will be inserted at the head of the list.
     *
     * @param index add index
     * @param val   add value
     */
    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        Node n = new Node();
        n.val = val;
        Node dummy = root;
        for (int i = 0; i < index; i++) {
            dummy = dummy.next;
        }
        Node tmp = dummy.next;
        dummy.next = n;
        n.previous = dummy;
        n.next = tmp;
        tmp.previous = n;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     *
     * @param index node to be delete
     */
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) {
            return;
        }

        Node tmp = root;
        for (int i = 0; i <= index; i++) {
            tmp = tmp.next;
        }
        Node p = tmp.previous;
        Node n = tmp.next;
        p.next = n;
        n.previous = p;
        size--;
    }

    /**
     * Node in linked list with both pointer.
     */
    static class Node {
        int val;
        Node next;
        Node previous;
    }
}
