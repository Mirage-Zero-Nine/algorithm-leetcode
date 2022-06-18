package solution.multithread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Implement a thread safe bounded blocking queue that has the following methods:
 * 1. BoundedBlockingQueue(int capacity): The constructor initializes the queue with a maximum capacity.
 * 2. void enqueue(int element): Adds an element to the front of the queue.
 * If the queue is full, the calling thread is blocked until the queue is no longer full.
 * 3. int dequeue(): Returns the element at the rear of the queue and removes it.
 * If the queue is empty, the calling thread is blocked until the queue is no longer empty.
 * 4. int size(): Returns the number of elements currently in the queue.
 * Your implementation will be tested using multiple threads at the same time.
 * Each thread will either be a producer only makes calls to the enqueue or a consumer only makes calls to the dequeue.
 * The size method will be called after every test case.
 * Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 *
 * @author BorisMirage
 * Time: 2020/03/20 12:51
 * Created with IntelliJ IDEA
 */

public class BoundedBlockingQueue_1188 {
    Queue<Integer> q;
    Semaphore enqueue;
    Semaphore dequeue;

    /**
     * Two semaphores to control the thread calling.
     * One semaphore controls the enqueue method, when adding an element to queue, the permit subtracted by 1.
     * One semaphore controls the dequeue method. Initially, it is 0.
     * Then if one element is added, then the permit is added by one.
     * If the dequeue is called, the permit subtracted by 1.
     *
     * @param capacity capacity of queue
     */
    public BoundedBlockingQueue_1188(int capacity) {
        q = new LinkedList<>();
        enqueue = new Semaphore(capacity);          // max allowance of threads to call enqueue
        dequeue = new Semaphore(0);         // initially, queue is empty and no call allowed
    }

    /**
     * Add element to queue.
     *
     * @param element element to be added
     * @throws InterruptedException if the queue is full
     */
    public void enqueue(int element) throws InterruptedException {
        enqueue.acquire();
        q.add(element);
        dequeue.release();
    }

    /**
     * Remove the rear element from queue and return this value.
     *
     * @return rear element from queue
     * @throws InterruptedException if the queue is empty
     */
    public int dequeue() throws InterruptedException {
        dequeue.acquire();
        int val = q.poll();
        enqueue.release();
        return val;
    }

    /**
     * Return the size of blocking queue.
     *
     * @return size of blocking queue
     */
    public int size() {
        return q.size();
    }
}
