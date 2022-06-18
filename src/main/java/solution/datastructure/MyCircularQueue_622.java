package solution.datastructure;

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
    private final int[] arr;
    private int size = 0;
    private final int limit;
    private int front = 0;
    private int rear = -1;

    /**
     * Initialization of circular queue. Set the size of the queue to be k.
     */
    public MyCircularQueue_622(int k) {
        arr = new int[k];
        limit = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     *
     * @param value insert value
     * @return true if the operation is successful
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % limit;
        arr[rear] = value;

        size++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     *
     * @return true if the operation is successful
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % limit;
        size--;
        return true;
    }

    /**
     * Get the front item from the queue.
     *
     * @return front item from the queue (head of queue)
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return arr[front];
    }

    /**
     * Get the last item from the queue.
     *
     * @return last item from the queue (rear of queue)
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        return arr[rear];
    }

    /**
     * Checks whether the circular queue is empty or not.
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     *
     * @return true if the queue is full
     */
    public boolean isFull() {
        return size == limit;
    }
}
