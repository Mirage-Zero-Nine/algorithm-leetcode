package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

public class FooBar_1115Test {

    @Test
    public void testSinglePair() throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(1);
        StringBuilder output = new StringBuilder();

        Thread foo = new Thread(() -> runUnchecked(() -> fooBar.foo(() -> output.append("foo"))));
        Thread bar = new Thread(() -> runUnchecked(() -> fooBar.bar(() -> output.append("bar"))));

        foo.start();
        bar.start();
        foo.join();
        bar.join();

        assertEquals("foobar", output.toString());
    }

    @Test
    public void testMultiplePairs() throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(4);
        StringBuilder output = new StringBuilder();

        Thread foo = new Thread(() -> runUnchecked(() -> fooBar.foo(() -> output.append("foo"))));
        Thread bar = new Thread(() -> runUnchecked(() -> fooBar.bar(() -> output.append("bar"))));

        foo.start();
        bar.start();
        foo.join();
        bar.join();

        assertEquals("foobarfoobarfoobarfoobar", output.toString());
    }

    @Test
    public void testBarBlocksUntilFooRuns() throws Exception {
        FooBar_1115 fooBar = new FooBar_1115(1);
        AtomicBoolean barRan = new AtomicBoolean(false);
        Thread bar = new Thread(() -> runUnchecked(() -> fooBar.bar(() -> barRan.set(true))));

        bar.start();
        Thread.sleep(100);
        assertTrue(bar.isAlive());
        assertTrue(!barRan.get());

        Thread foo = new Thread(() -> runUnchecked(() -> fooBar.foo(() -> { })));
        foo.start();
        foo.join();
        bar.join(1000);

        assertTrue(barRan.get());
    }

    @Test
    public void testNullRunnableThrows() throws Exception {
        FooBar_1115 fooOnly = new FooBar_1115(1);
        assertThrows(NullPointerException.class, () -> fooOnly.foo(null));

        FooBar_1115 barOnly = new FooBar_1115(1);
        Thread foo = new Thread(() -> runUnchecked(() -> barOnly.foo(() -> { })));
        foo.start();
        foo.join();
        assertThrows(NullPointerException.class, () -> barOnly.bar(null));
    }

    @Test
    public void testZeroAndNegativeNProduceNoOutput() throws Exception {
        assertEquals("", execute(new FooBar_1115(0)));
        assertEquals("", execute(new FooBar_1115(-3)));
    }

    @Test
    public void testLargeN() throws Exception {
        assertEquals("foobar".repeat(50), execute(new FooBar_1115(50)));
    }

    private static String execute(FooBar_1115 fooBar) throws Exception {
        StringBuilder output = new StringBuilder();
        CountDownLatch finished = new CountDownLatch(2);
        Thread foo = new Thread(() -> runAndCountDown(() -> fooBar.foo(() -> output.append("foo")), finished));
        Thread bar = new Thread(() -> runAndCountDown(() -> fooBar.bar(() -> output.append("bar")), finished));
        foo.start();
        bar.start();
        finished.await();
        return output.toString();
    }

    private static void runAndCountDown(InterruptibleRunnable runnable, CountDownLatch finished) {
        try {
            runnable.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            finished.countDown();
        }
    }

    private static void runUnchecked(InterruptibleRunnable runnable) {
        try {
            runnable.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    private interface InterruptibleRunnable {
        void run() throws InterruptedException;
    }
}
