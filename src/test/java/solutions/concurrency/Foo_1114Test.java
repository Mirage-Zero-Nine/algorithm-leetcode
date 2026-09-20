package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/** Tests the semaphore ordering protocol used by {@link Foo_1114}. */
public class Foo_1114Test {

    private static final long TIMEOUT_SECONDS = 3;

    /** Every possible order of starting the three LeetCode worker threads is valid. */
    @ParameterizedTest(name = "thread start order {0}")
    @MethodSource("startOrders")
    public void allThreadStartOrdersProduceExactOutput(String label, int[] startOrder)
            throws Exception {
        assertEquals("firstsecondthird", executeConcurrent(startOrder));
    }

    private static Stream<Arguments> startOrders() {
        return Stream.of(
                Arguments.of("123", new int[] {1, 2, 3}),
                Arguments.of("132", new int[] {1, 3, 2}),
                Arguments.of("213", new int[] {2, 1, 3}),
                Arguments.of("231", new int[] {2, 3, 1}),
                Arguments.of("312", new int[] {3, 1, 2}),
                Arguments.of("321", new int[] {3, 2, 1}));
    }

    @Test
    public void sequentialCallsProduceExactOutput() throws Exception {
        Foo_1114 foo = new Foo_1114();
        StringBuilder output = new StringBuilder();

        foo.first(() -> output.append("first"));
        foo.second(() -> output.append("second"));
        foo.third(() -> output.append("third"));

        assertEquals("firstsecondthird", output.toString());
    }

    /** Different callback payloads must retain callback order, not just method names. */
    @ParameterizedTest(name = "callback payload set {0}")
    @ValueSource(strings = {"A,B,C", "1,2,3", "<one>,<two>,<three>", "x-y-z", ",,"})
    public void arbitraryCallbacksRunOnceInOrder(String payload) throws Exception {
        String[] parsedTokens = payload.indexOf(',') >= 0
                ? payload.split(",", -1)
                : new String[] {"x", "-y-", "z"};
        String[] callbackTokens = parsedTokens.length == 3
                ? parsedTokens
                : new String[] {"first-value", "second-value", "third-value"};

        Foo_1114 foo = new Foo_1114();
        StringBuilder output = new StringBuilder();
        int[] counts = new int[3];
        foo.first(() -> {
            counts[0]++;
            output.append(callbackTokens[0]);
        });
        foo.second(() -> {
            counts[1]++;
            output.append(callbackTokens[1]);
        });
        foo.third(() -> {
            counts[2]++;
            output.append(callbackTokens[2]);
        });

        assertEquals(callbackTokens[0] + callbackTokens[1] + callbackTokens[2], output.toString());
        assertEquals(1, counts[0]);
        assertEquals(1, counts[1]);
        assertEquals(1, counts[2]);
    }

    @Test
    public void freshInstancesDoNotShareSemaphoreState() throws Exception {
        for (int i = 0; i < 100; i++) {
            assertEquals("firstsecondthird", executeConcurrent(new int[] {3, 1, 2}));
        }
    }

    @Test
    public void firstCallbackMustReturnBeforeSecondCallbackCanRun() throws Exception {
        Foo_1114 foo = new Foo_1114();
        CountDownLatch secondStarted = new CountDownLatch(1);
        CountDownLatch firstEntered = new CountDownLatch(1);
        CountDownLatch releaseFirst = new CountDownLatch(1);
        AtomicBoolean firstReturned = new AtomicBoolean();
        AtomicBoolean secondRan = new AtomicBoolean();
        AtomicBoolean secondRanBeforeFirstReturned = new AtomicBoolean();
        AtomicReference<Throwable> failure = new AtomicReference<>();

        Thread second = new Thread(() -> {
            secondStarted.countDown();
            try {
                foo.second(() -> {
                    secondRan.set(true);
                    if (!firstReturned.get()) {
                        secondRanBeforeFirstReturned.set(true);
                    }
                });
            } catch (Throwable throwable) {
                failure.set(throwable);
            }
        });
        Thread first = new Thread(() -> {
            try {
                foo.first(() -> {
                    firstEntered.countDown();
                    awaitLatch(releaseFirst);
                    firstReturned.set(true);
                });
            } catch (Throwable throwable) {
                failure.set(throwable);
            }
        });

        second.start();
        assertTrue(secondStarted.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        first.start();
        assertTrue(firstEntered.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        releaseFirst.countDown();
        joinOrFail(first);
        joinOrFail(second);

        assertNull(failure.get(), () -> "worker failed: " + failure.get());
        assertTrue(secondRan.get());
        assertFalse(secondRanBeforeFirstReturned.get());
    }

    @Test
    public void secondCallbackMustReturnBeforeThirdCallbackCanRun() throws Exception {
        Foo_1114 foo = new Foo_1114();
        foo.first(() -> { });
        CountDownLatch thirdStarted = new CountDownLatch(1);
        CountDownLatch secondEntered = new CountDownLatch(1);
        CountDownLatch releaseSecond = new CountDownLatch(1);
        AtomicBoolean secondReturned = new AtomicBoolean();
        AtomicBoolean thirdRan = new AtomicBoolean();
        AtomicBoolean thirdRanBeforeSecondReturned = new AtomicBoolean();
        AtomicReference<Throwable> failure = new AtomicReference<>();

        Thread third = new Thread(() -> {
            thirdStarted.countDown();
            try {
                foo.third(() -> {
                    thirdRan.set(true);
                    if (!secondReturned.get()) {
                        thirdRanBeforeSecondReturned.set(true);
                    }
                });
            } catch (Throwable throwable) {
                failure.set(throwable);
            }
        });
        Thread second = new Thread(() -> {
            try {
                foo.second(() -> {
                    secondEntered.countDown();
                    awaitLatch(releaseSecond);
                    secondReturned.set(true);
                });
            } catch (Throwable throwable) {
                failure.set(throwable);
            }
        });

        third.start();
        assertTrue(thirdStarted.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        second.start();
        assertTrue(secondEntered.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        releaseSecond.countDown();
        joinOrFail(second);
        joinOrFail(third);

        assertNull(failure.get(), () -> "worker failed: " + failure.get());
        assertTrue(thirdRan.get());
        assertFalse(thirdRanBeforeSecondReturned.get());
    }

    @ParameterizedTest(name = "interrupted stage {0}")
    @ValueSource(strings = {"second", "third"})
    public void blockedStagePropagatesInterruptionWithoutRunningCallback(String stage)
            throws Exception {
        Foo_1114 foo = new Foo_1114();
        CountDownLatch enteredWorker = new CountDownLatch(1);
        AtomicBoolean callbackRan = new AtomicBoolean();
        AtomicReference<Throwable> failure = new AtomicReference<>();
        Thread worker = new Thread(() -> {
            enteredWorker.countDown();
            try {
                if (stage.equals("second")) {
                    foo.second(() -> callbackRan.set(true));
                } else {
                    foo.third(() -> callbackRan.set(true));
                }
            } catch (Throwable throwable) {
                failure.set(throwable);
            }
        });

        worker.start();
        assertTrue(enteredWorker.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        worker.interrupt();
        joinOrFail(worker);

        assertInstanceOf(InterruptedException.class, failure.get());
        assertFalse(callbackRan.get());
    }

    @Test
    public void nullFirstCallbackFailsImmediately() {
        assertThrows(NullPointerException.class, () -> new Foo_1114().first(null));
    }

    @Test
    public void nullSecondCallbackFailsAfterFirstPermit() throws Exception {
        Foo_1114 foo = new Foo_1114();
        foo.first(() -> { });

        assertThrows(NullPointerException.class, () -> foo.second(null));
    }

    @Test
    public void nullThirdCallbackFailsAfterSecondPermit() throws Exception {
        Foo_1114 foo = new Foo_1114();
        foo.first(() -> { });
        foo.second(() -> { });

        assertThrows(NullPointerException.class, () -> foo.third(null));
    }

    private static String executeConcurrent(int[] startOrder) throws Exception {
        Foo_1114 foo = new Foo_1114();
        StringBuffer output = new StringBuffer();
        CountDownLatch ready = new CountDownLatch(3);
        CountDownLatch start = new CountDownLatch(1);
        AtomicReference<Throwable> failure = new AtomicReference<>();
        Thread first = worker(
                () -> foo.first(() -> output.append("first")), ready, start, failure);
        Thread second = worker(
                () -> foo.second(() -> output.append("second")), ready, start, failure);
        Thread third = worker(
                () -> foo.third(() -> output.append("third")), ready, start, failure);
        Thread[] threads = {first, second, third};

        for (int method : startOrder) {
            threads[method - 1].start();
        }
        assertTrue(ready.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        start.countDown();
        for (Thread thread : threads) {
            joinOrFail(thread);
        }

        assertNull(failure.get(), () -> "worker failed: " + failure.get());
        return output.toString();
    }

    private static Thread worker(
            InterruptibleRunnable runnable,
            CountDownLatch ready,
            CountDownLatch start,
            AtomicReference<Throwable> failure) {
        return new Thread(() -> {
            ready.countDown();
            try {
                start.await();
                runnable.run();
            } catch (Throwable throwable) {
                failure.compareAndSet(null, throwable);
            }
        });
    }

    private static void joinOrFail(Thread thread) throws InterruptedException {
        thread.join(TimeUnit.SECONDS.toMillis(TIMEOUT_SECONDS));
        if (thread.isAlive()) {
            thread.interrupt();
            thread.join(TimeUnit.SECONDS.toMillis(1));
        }
        assertFalse(thread.isAlive(), () -> "thread did not terminate: " + thread.getName());
    }

    private static void awaitLatch(CountDownLatch latch) {
        try {
            if (!latch.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                throw new AssertionError("callback gate did not open");
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new AssertionError("callback was interrupted", exception);
        }
    }

    @FunctionalInterface
    private interface InterruptibleRunnable {
        void run() throws InterruptedException;
    }
}
