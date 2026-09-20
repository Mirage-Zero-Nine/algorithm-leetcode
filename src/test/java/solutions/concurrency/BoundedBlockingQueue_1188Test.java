package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

/** Tests the FIFO, capacity, and blocking contracts of {@link BoundedBlockingQueue_1188}. */
public class BoundedBlockingQueue_1188Test {

    private static final long WAIT_SECONDS = 2;

    @Test
    public void testNewQueueIsEmpty() {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);

        assertEquals(0, queue.size());
    }

    @Test
    public void testIndependentQueueInstancesDoNotShareElements() throws Exception {
        BoundedBlockingQueue_1188 first = new BoundedBlockingQueue_1188(1);
        BoundedBlockingQueue_1188 second = new BoundedBlockingQueue_1188(1);

        first.enqueue(11);
        second.enqueue(22);

        assertEquals(1, first.size());
        assertEquals(1, second.size());
        assertEquals(11, first.dequeue());
        assertEquals(22, second.dequeue());
    }

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
    public void testCapacityOneEdgeCase() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);

        queue.enqueue(-7);
        assertEquals(1, queue.size());
        assertEquals(-7, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void testIntegerBoundaryAndDuplicateValues() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(5);
        int[] values = {Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE, -1};

        for (int value : values) {
            queue.enqueue(value);
        }
        for (int value : values) {
            assertEquals(value, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testSizeTracksMixedEnqueueAndDequeueOperations() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(4);

        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.size());
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(3, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(4, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void testAlternatingOperationsPreserveFifoOrder() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(2);

        queue.enqueue(1);
        assertEquals(1, queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());
        queue.enqueue(4);
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void testQueueCanBeFilledExactlyToCapacity() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(7);

        for (int i = 0; i < 7; i++) {
            queue.enqueue(i);
            assertEquals(i + 1, queue.size());
        }
        assertEquals(7, queue.size());
        for (int i = 0; i < 7; i++) {
            assertEquals(i, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueBlocksUntilElementAvailable() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch entered = new CountDownLatch(1);
        try {
            Future<Integer> consumer = executor.submit(() -> {
                entered.countDown();
                return queue.dequeue();
            });

            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertThrows(TimeoutException.class, () -> consumer.get(100, TimeUnit.MILLISECONDS));
            assertEquals(0, queue.size());

            queue.enqueue(42);
            assertEquals(42, consumer.get(WAIT_SECONDS, TimeUnit.SECONDS));
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testEnqueueBlocksWhenQueueIsFull() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        queue.enqueue(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch entered = new CountDownLatch(1);
        try {
            Future<?> producer = executor.submit(() -> {
                entered.countDown();
                queue.enqueue(2);
                return null;
            });

            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertThrows(TimeoutException.class, () -> producer.get(100, TimeUnit.MILLISECONDS));
            assertEquals(1, queue.size());

            assertEquals(1, queue.dequeue());
            producer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            assertEquals(2, queue.dequeue());
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testMultipleConsumersAreReleasedByIndividualEnqueues() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch entered = new CountDownLatch(3);
        List<Future<Integer>> consumers = new ArrayList<>();
        try {
            for (int i = 0; i < 3; i++) {
                consumers.add(executor.submit(() -> {
                    entered.countDown();
                    return queue.dequeue();
                }));
            }
            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            for (Future<Integer> consumer : consumers) {
                assertThrows(TimeoutException.class,
                    () -> consumer.get(100, TimeUnit.MILLISECONDS));
            }

            queue.enqueue(100);
            queue.enqueue(200);
            queue.enqueue(300);
            List<Integer> received = new ArrayList<>();
            for (Future<Integer> consumer : consumers) {
                received.add(consumer.get(WAIT_SECONDS, TimeUnit.SECONDS));
            }
            assertEquals(Set.of(100, 200, 300), Set.copyOf(received));
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testMultipleProducersAreReleasedAsSpaceBecomesAvailable() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        queue.enqueue(-1);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch entered = new CountDownLatch(3);
        List<Future<?>> producers = new ArrayList<>();
        int[] values = {10, 20, 30};
        List<Integer> dequeued = new ArrayList<>();
        try {
            for (int value : values) {
                producers.add(executor.submit(() -> {
                    entered.countDown();
                    queue.enqueue(value);
                    return null;
                }));
            }
            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            for (Future<?> producer : producers) {
                assertThrows(TimeoutException.class,
                    () -> producer.get(100, TimeUnit.MILLISECONDS));
            }

            assertEquals(-1, queue.dequeue());
            for (int i = 0; i < values.length; i++) {
                awaitAnyCompletion(producers);
                dequeued.add(queue.dequeue());
            }
            assertEquals(Set.of(10, 20, 30), Set.copyOf(dequeued));
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testInterruptedBlockedDequeueDoesNotChangeQueueState() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        AtomicReference<Throwable> error = new AtomicReference<>();
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch finished = new CountDownLatch(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> consumer = executor.submit(() -> {
                entered.countDown();
                try {
                    queue.dequeue();
                } catch (Throwable throwable) {
                    error.set(throwable);
                } finally {
                    finished.countDown();
                }
                return null;
            });
            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertThrows(TimeoutException.class, () -> consumer.get(100, TimeUnit.MILLISECONDS));
            consumer.cancel(true);
            assertTrue(finished.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertInstanceOf(InterruptedException.class, error.get());
            assertEquals(0, queue.size());

            queue.enqueue(7);
            assertEquals(7, queue.dequeue());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testInterruptedBlockedEnqueuePreservesExistingElementAndPermit() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1);
        queue.enqueue(99);
        AtomicReference<Throwable> error = new AtomicReference<>();
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch finished = new CountDownLatch(1);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> producer = executor.submit(() -> {
                entered.countDown();
                try {
                    queue.enqueue(100);
                } catch (Throwable throwable) {
                    error.set(throwable);
                } finally {
                    finished.countDown();
                }
                return null;
            });
            assertTrue(entered.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertThrows(TimeoutException.class, () -> producer.get(100, TimeUnit.MILLISECONDS));
            producer.cancel(true);
            assertTrue(finished.await(WAIT_SECONDS, TimeUnit.SECONDS));
            assertInstanceOf(InterruptedException.class, error.get());
            assertEquals(1, queue.size());
            assertEquals(99, queue.dequeue());

            queue.enqueue(101);
            assertEquals(101, queue.dequeue());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testRepeatedUseAfterDrainingQueue() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(2);

        for (int round = 0; round < 30; round++) {
            queue.enqueue(round);
            queue.enqueue(-round);
            assertEquals(round, queue.dequeue());
            assertEquals(-round, queue.dequeue());
            assertEquals(0, queue.size());
        }
    }

    @Test
    public void testSingleProducerConsumerPreservesFifoAcrossBlocking() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(2);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<?> producer = executor.submit(() -> {
                for (int i = 0; i < 250; i++) {
                    queue.enqueue(i);
                }
                return null;
            });
            Future<?> consumer = executor.submit(() -> {
                for (int i = 0; i < 250; i++) {
                    consumed.add(queue.dequeue());
                }
                return null;
            });
            producer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            consumer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            assertEquals(250, consumed.size());
            for (int i = 0; i < consumed.size(); i++) {
                assertEquals(i, consumed.get(i));
            }
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testMultipleProducersAndConsumersHaveNoLossOrDuplication() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(5);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(8);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> tasks = new ArrayList<>();
        int producerCount = 4;
        int consumerCount = 4;
        int valuesPerProducer = 150;
        int total = producerCount * valuesPerProducer;
        try {
            for (int producer = 0; producer < producerCount; producer++) {
                int producerId = producer;
                tasks.add(executor.submit(() -> {
                    start.await();
                    for (int i = 0; i < valuesPerProducer; i++) {
                        queue.enqueue(producerId * valuesPerProducer + i);
                    }
                    return null;
                }));
            }
            int valuesPerConsumer = total / consumerCount;
            for (int i = 0; i < consumerCount; i++) {
                tasks.add(executor.submit(() -> {
                    start.await();
                    for (int j = 0; j < valuesPerConsumer; j++) {
                        consumed.add(queue.dequeue());
                    }
                    return null;
                }));
            }
            start.countDown();
            for (Future<?> task : tasks) {
                task.get(WAIT_SECONDS, TimeUnit.SECONDS);
            }
            List<Integer> sorted = new ArrayList<>(consumed);
            Collections.sort(sorted);
            assertEquals(total, sorted.size());
            for (int i = 0; i < total; i++) {
                assertEquals(i, sorted.get(i));
            }
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testSeveralConsumersDrainACompleteBatchExactlyOnce() throws Exception {
        int total = 120;
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(total);
        for (int i = 0; i < total; i++) {
            queue.enqueue(i);
        }
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> tasks = new ArrayList<>();
        try {
            for (int i = 0; i < 3; i++) {
                tasks.add(executor.submit(() -> {
                    start.await();
                    for (int j = 0; j < total / 3; j++) {
                        consumed.add(queue.dequeue());
                    }
                    return null;
                }));
            }
            start.countDown();
            for (Future<?> task : tasks) {
                task.get(WAIT_SECONDS, TimeUnit.SECONDS);
            }
            List<Integer> sorted = new ArrayList<>(consumed);
            Collections.sort(sorted);
            assertEquals(total, sorted.size());
            for (int i = 0; i < total; i++) {
                assertEquals(i, sorted.get(i));
            }
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testProducerAndConsumerCanReuseQueueAfterConcurrentBatch() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(3);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<?> producer = executor.submit(() -> {
                for (int i = 0; i < 60; i++) {
                    queue.enqueue(i);
                }
                return null;
            });
            Future<?> consumer = executor.submit(() -> {
                for (int i = 0; i < 60; i++) {
                    assertEquals(i, queue.dequeue());
                }
                return null;
            });
            producer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            consumer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            assertEquals(0, queue.size());

            queue.enqueue(60);
            assertEquals(60, queue.dequeue());
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testConcurrentSizeNeverExceedsCapacity() throws Exception {
        int capacity = 6;
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(capacity);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicBoolean invalidSizeObserved = new AtomicBoolean();
        AtomicInteger maximumSize = new AtomicInteger();
        CountDownLatch start = new CountDownLatch(1);
        try {
            Future<?> producer = executor.submit(() -> {
                start.await();
                for (int i = 0; i < 1_000; i++) {
                    queue.enqueue(i);
                }
                return null;
            });
            Future<?> consumer = executor.submit(() -> {
                start.await();
                for (int i = 0; i < 1_000; i++) {
                    queue.dequeue();
                }
                return null;
            });
            Future<?> observer = executor.submit(() -> {
                start.await();
                while (!producer.isDone() || !consumer.isDone()) {
                    int size = queue.size();
                    maximumSize.accumulateAndGet(size, Math::max);
                    if (size < 0 || size > capacity) {
                        invalidSizeObserved.set(true);
                    }
                }
                return null;
            });
            start.countDown();
            producer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            consumer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            observer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            assertFalse(invalidSizeObserved.get());
            assertTrue(maximumSize.get() <= capacity);
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    @Test
    public void testMaximumDocumentedCapacityCanBeFilledAndDrained() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(1_000);

        for (int i = 0; i < 1_000; i++) {
            queue.enqueue(i);
        }
        assertEquals(1_000, queue.size());
        for (int i = 0; i < 1_000; i++) {
            assertEquals(i, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testLargeProducerConsumerFlow() throws Exception {
        BoundedBlockingQueue_1188 queue = new BoundedBlockingQueue_1188(8);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<?> producer = executor.submit(() -> {
                for (int i = 0; i < 1_000; i++) {
                    queue.enqueue(i);
                }
                return null;
            });
            Future<?> consumer = executor.submit(() -> {
                for (int i = 0; i < 1_000; i++) {
                    consumed.add(queue.dequeue());
                }
                return null;
            });
            producer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            consumer.get(WAIT_SECONDS, TimeUnit.SECONDS);
            assertEquals(1_000, consumed.size());
            assertEquals(new HashSet<>(consumed).size(), consumed.size());
            assertEquals(0, queue.size());
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(WAIT_SECONDS, TimeUnit.SECONDS));
        }
    }

    private static void awaitAnyCompletion(List<Future<?>> futures)
        throws InterruptedException, ExecutionException, TimeoutException {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(WAIT_SECONDS);
        while (System.nanoTime() < deadline) {
            for (int i = 0; i < futures.size(); i++) {
                Future<?> future = futures.get(i);
                if (!future.isDone()) {
                    continue;
                }
                future.get();
                futures.remove(i);
                return;
            }
            Thread.yield();
        }
        throw new TimeoutException("No producer completed before the deadline");
    }
}
