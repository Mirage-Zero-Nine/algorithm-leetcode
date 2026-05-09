package solution.multithread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

public class BoundedBlockingQueue_1188Test {

    @Test
    public void testBasicFifoBehavior() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(3);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueBlocksUntilElementAvailable() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        AtomicReference<Integer> value = new AtomicReference<>();
        Thread consumer = new Thread(() -> {
            try {
                value.set(queue.dequeue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        consumer.start();
        Thread.sleep(100);
        assertTrue(consumer.isAlive());

        queue.enqueue(42);
        consumer.join(1000);
        assertEquals(42, value.get());
    }

    @Test
    public void testEnqueueBlocksWhenQueueIsFull() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        queue.enqueue(1);

        AtomicBoolean secondEnqueueFinished = new AtomicBoolean(false);
        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(2);
                secondEnqueueFinished.set(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        Thread.sleep(100);
        assertTrue(producer.isAlive());
        assertTrue(!secondEnqueueFinished.get());

        assertEquals(1, queue.dequeue());
        producer.join(1000);
        assertTrue(secondEnqueueFinished.get());
        assertEquals(2, queue.dequeue());
    }

    @Test
    public void testInterruptedBlockedOperations() throws Exception {
        BoundedBlockingQueue_1188 emptyQueue = new BoundedBlockingQueue_1188(1);
        AtomicReference<Throwable> dequeueError = new AtomicReference<>();
        Thread blockedDequeue = new Thread(() -> {
            try {
                emptyQueue.dequeue();
            } catch (Throwable t) {
                dequeueError.set(t);
            }
        });
        blockedDequeue.start();
        Thread.sleep(100);
        blockedDequeue.interrupt();
        blockedDequeue.join(1000);
        assertTrue(dequeueError.get() instanceof InterruptedException);

        BoundedBlockingQueue_1188 fullQueue = new BoundedBlockingQueue_1188(1);
        fullQueue.enqueue(99);
        AtomicReference<Throwable> enqueueError = new AtomicReference<>();
        Thread blockedEnqueue = new Thread(() -> {
            try {
                fullQueue.enqueue(100);
            } catch (Throwable t) {
                enqueueError.set(t);
            }
        });
        blockedEnqueue.start();
        Thread.sleep(100);
        blockedEnqueue.interrupt();
        blockedEnqueue.join(1000);
        assertTrue(enqueueError.get() instanceof InterruptedException);
    }

    @Test
    public void testCapacityOneEdgeCase() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        queue.enqueue(-7);
        assertEquals(1, queue.size());
        assertEquals(-7, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void testLargeProducerConsumerFlow() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(8);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch finished = new CountDownLatch(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    queue.enqueue(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                finished.countDown();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    consumed.add(queue.dequeue());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                finished.countDown();
            }
        });

        producer.start();
        consumer.start();
        finished.await();

        assertEquals(100, consumed.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, consumed.get(i));
        }
        assertEquals(0, queue.size());
    }
}
