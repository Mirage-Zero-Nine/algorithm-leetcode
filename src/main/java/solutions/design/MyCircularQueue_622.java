package solutions.design;

/**
 * Design your implementation of the circular queue.
 * The circular queue is a linear data structure performing FIFO principle.
 * The last position is connected back to the first position to make a circle.
 * It is also called "Ring Buffer".
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, next element can not be inserted into queue.
 * But using the circular queue, we can use the space to store new values.
 * Your implementation should support following operations:
 * 1. MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * 2. Front: Get the front item from the queue. If the queue is empty, return -1.
 * 3. Rear: Get the last item from the queue. If the queue is empty, return -1.
 * 4. enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * 5. deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * 6. isEmpty(): Checks whether the circular queue is empty or not.
 * 7. isFull(): Checks whether the circular queue is full or not.
 *
 * @author BorisMirage
 * Time: 2019/11/04 16:22
 * Created with IntelliJ IDEA
 */
public class MyCircularQueue_622 {
    private final int[] array;
    private int size = 0;
    private int start;

    /**
     * Initializes an empty circular queue with a fixed capacity {@code k}.
     * <p>
     * This queue uses a fixed-size array with a wrap-around mechanism. When the end of
     * the array is reached, indexes loop back to zero, allowing removed slots to be
     * reused without shifting existing elements.
     * <p>
     * {@code start} tracks the oldest value, and {@code size} tracks the total elements.
     * New items are added at {@code (start + size) % k}.
     *
     * @param k the maximum capacity of the queue
     * @implNote Time complexity: O(k) for array allocation. Auxiliary space: O(k).
     */
    public MyCircularQueue_622(int k) {
        start = 0;
        array = new int[k];
    }

    /**
     * Adds a value at the logical tail when capacity remains.  The physical tail
     * is computed from the front offset and current size, so it wraps to index
     * zero after reaching the end of the array.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public boolean enQueue(int value) {
        if (!isFull()) {
            // The next free logical position may be before start after wrapping.
            int index = (start + size) % array.length;
            array[index] = value;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Removes the oldest value by advancing the front offset and reducing the
     * logical size.  The removed array cell need not be cleared because size
     * keeps stale storage outside the queue's logical contents.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public boolean deQueue() {
        if (!isEmpty()) {
            // Moving start, rather than shifting values, preserves FIFO order in O(1).
            start = (start + 1) % array.length;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Returns the oldest queued value, or {@code -1} when the queue is empty.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return array[start];
    }

    /**
     * Returns the newest queued value, or {@code -1} when the queue is empty.
     * The modulo expression locates the logical tail even after wraparound.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return array[(start + size - 1) % array.length];
    }

    /**
     * Reports whether no logical elements are stored.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Reports whether all allocated slots currently hold logical elements.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public boolean isFull() {
        return size == array.length;
    }
}
