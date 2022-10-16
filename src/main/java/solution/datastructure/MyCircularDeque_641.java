package solution.datastructure;

/**
 * Design your implementation of the circular double-ended queue (deque).
 * Implement the MyCircularDeque class:
 * 1. MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * 2. boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful.
 * 3. boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful.
 * 4. boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful.
 * 5. boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful.
 * 6. int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * 7. int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * 8. boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * 9. boolean isFull() Returns true if the deque is full, or false otherwise.
 *
 * @author BorisMirage
 * Time: 2022/10/15 18:29
 * Created with IntelliJ IDEA
 */

public class MyCircularDeque_641 {
    private final int LIMIT;
    private final ListNode start;
    private final ListNode last;
    private int size;

    /**
     * Implement a double linked list as a deque.
     *
     * @param k max size of the deque
     */
    public MyCircularDeque_641(int k) {
        this.LIMIT = k;
        this.size = 0;
        start = new ListNode(-1);
        last = new ListNode(-1);
        start.next = last;
        last.previous = start;
    }

    /**
     * If the size does not reach the limit, create a new node and add to the front.
     *
     * @param value value to be added to the deque
     * @return true if value is added, false otherwise
     */
    public boolean insertFront(int value) {
        if (!isFull()) {
            ListNode newNode = new ListNode(value), temp = start.next;

            newNode.next = temp;
            newNode.previous = start;

            start.next = newNode;
            temp.previous = newNode;

            size++;
            return true;
        }
        return false;
    }

    /**
     * If the size does not reach the limit, create a new node and add to the end.
     *
     * @param value value to be added to the deque
     * @return true if value is added, false otherwise
     */
    public boolean insertLast(int value) {
        if (!isFull()) {
            ListNode newNode = new ListNode(value), temp = last.previous;
            newNode.previous = temp;
            newNode.next = last;

            temp.next = newNode;
            last.previous = newNode;

            size++;
            return true;
        }
        return false;
    }

    /**
     * If the deque is not empty, remove the first value in deque, the return true.
     * If the deque is empty, return false.
     *
     * @return true if the first element was removed, false if the list is empty
     */
    public boolean deleteFront() {
        if (!isEmpty()) {
            ListNode removeNode = start.next, temp = removeNode.next;

            start.next = temp;
            temp.previous = start;

            size--;
            return true;
        }
        return false;
    }

    /**
     * If the deque is not empty, remove the last value in deque, the return true.
     * If the deque is empty, return false.
     *
     * @return true if the first element was removed, false if the list is empty
     */
    public boolean deleteLast() {
        if (!isEmpty()) {
            ListNode removeNode = last.previous, temp = removeNode.previous;

            last.previous = temp;
            temp.next = last;

            size--;
            return true;
        }
        return false;
    }

    /**
     * Return the first value in the deque.
     *
     * @return return the first value from the deque, or -1 if the deque is empty
     */
    public int getFront() {
        return size > 0 ? start.next.value : -1;
    }

    /**
     * Return the last value in the deque.
     *
     * @return return the last value from the deque, or -1 if the deque is empty
     */
    public int getRear() {
        return size > 0 ? last.previous.value : -1;
    }

    /**
     * Check if the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if the deque is full.
     *
     * @return true if the deque is full, false otherwise
     */
    public boolean isFull() {
        return size == LIMIT;
    }

    /**
     * Double linked list node to create a deque.
     */
    private static class ListNode {
        int value;
        ListNode previous;
        ListNode next;

        /**
         * Constructor of the node.
         *
         * @param value value in current node
         */
        private ListNode(int value) {
            this.value = value;
        }
    }
}
