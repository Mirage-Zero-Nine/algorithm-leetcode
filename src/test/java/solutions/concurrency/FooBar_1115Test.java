package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

/** Tests the semaphore protocol used by {@link FooBar_1115}. */
public class FooBar_1115Test {

    @ParameterizedTest(name = "n={0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 25, 64, 127, 250, 499, 999, 1000})
    public void producesExactlyAlternatingOutputForValidSizes(int n) throws Exception {
        assertEquals("foobar".repeat(n), execute(n, true));
    }

    @ParameterizedTest(name = "n={0}, fooFirst={1}")
    @MethodSource("startOrders")
    public void outputIsCorrectRegardlessOfWhichThreadStartsFirst(int n, boolean fooFirst)
            throws Exception {
        assertEquals("foobar".repeat(n), execute(n, fooFirst));
    }

    private static Stream<Arguments> startOrders() {
        return Stream.of(
                Arguments.of(1, false),
                Arguments.of(2, false),
                Arguments.of(17, true),
                Arguments.of(128, false),
                Arguments.of(1000, true));
    }

    @Test
    public void repeatedFreshInstancesDoNotShareSemaphoreState() throws Exception {
        for (int n : new int[] {1, 7, 64, 257}) {
            assertEquals("foobar".repeat(n), execute(n, n % 2 == 0));
        }
    }

    @Test
    public void zeroAndNegativeNProduceNoOutput() throws Exception {
        assertEquals("", execute(0, false));
        assertEquals("", execute(-3, true));
    }

    @Test
    public void nullFooRunnableFailsAtTheDocumentedCallback() {
        FooBar_1115 fooBar = new FooBar_1115(1);

        assertThrows(NullPointerException.class, () -> fooBar.foo(null));
    }

    @Test
    public void nullBarRunnableFailsAfterFooReleasesBar() throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(1);
        fooBar.foo(() -> { });

        assertThrows(NullPointerException.class, () -> fooBar.bar(null));
    }

    @Test
    public void interruptionIsPropagatedFromFooAndBarAcquire() throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(2);
        CountDownLatch firstFooPrinted = new CountDownLatch(1);
        AtomicReference<Throwable> fooFailure = new AtomicReference<>();
        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(firstFooPrinted::countDown);
            } catch (Throwable throwable) {
                fooFailure.set(throwable);
            }
        });

        foo.start();
        assertTrue(firstFooPrinted.await(1, TimeUnit.SECONDS));
        foo.interrupt();
        joinOrFail(foo);
        assertTrue(fooFailure.get() instanceof InterruptedException);

        FooBar_1115 barOnly = new FooBar_1115(1);
        AtomicBoolean barPrinted = new AtomicBoolean();
        AtomicReference<Throwable> barFailure = new AtomicReference<>();
        CountDownLatch barStarted = new CountDownLatch(1);
        Thread bar = new Thread(() -> {
            barStarted.countDown();
            try {
                barOnly.bar(() -> barPrinted.set(true));
            } catch (Throwable throwable) {
                barFailure.set(throwable);
            }
        });

        bar.start();
        assertTrue(barStarted.await(1, TimeUnit.SECONDS));
        bar.interrupt();
        joinOrFail(bar);
        assertTrue(barFailure.get() instanceof InterruptedException);
        assertFalse(barPrinted.get());
    }

    private static String execute(int n, boolean fooFirst) throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(n);
        StringBuffer output = new StringBuffer();
        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);
        AtomicReference<Throwable> failure = new AtomicReference<>();

        Thread foo = worker(
                () -> fooBar.foo(() -> output.append("foo")), ready, start, failure);
        Thread bar = worker(
                () -> fooBar.bar(() -> output.append("bar")), ready, start, failure);

        if (fooFirst) {
            foo.start();
            bar.start();
        } else {
            bar.start();
            foo.start();
        }
        assertTrue(ready.await(1, TimeUnit.SECONDS));
        start.countDown();
        joinOrFail(foo);
        joinOrFail(bar);

        assertTrue(failure.get() == null, () -> "worker failed: " + failure.get());
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
        thread.join(3000);
        if (thread.isAlive()) {
            thread.interrupt();
            thread.join(1000);
        }
        assertFalse(thread.isAlive(), () -> "thread did not terminate: " + thread.getName());
    }

    @FunctionalInterface
    private interface InterruptibleRunnable {
        void run() throws InterruptedException;
    }
}
