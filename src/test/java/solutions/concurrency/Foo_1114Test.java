package solutions.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

public class Foo_1114Test {

    @Test
    public void testSequentialExecution() throws Exception {
        Foo_1114 foo = new Foo_1114();
        StringBuilder order = new StringBuilder();

        foo.first(() -> order.append("first"));
        foo.second(() -> order.append("second"));
        foo.third(() -> order.append("third"));

        assertEquals("firstsecondthird", order.toString());
    }

    @Test
    public void testConcurrentExecutionFromReverseStartOrder() throws Exception {
        Foo_1114 foo = new Foo_1114();
        StringBuilder order = new StringBuilder();
        CountDownLatch finished = new CountDownLatch(3);

        Thread third = new Thread(() -> runAndCountDown(() -> foo.third(() -> order.append("third")), finished));
        Thread second = new Thread(() -> runAndCountDown(() -> foo.second(() -> order.append("second")), finished));
        Thread first = new Thread(() -> runAndCountDown(() -> foo.first(() -> order.append("first")), finished));

        third.start();
        second.start();
        first.start();
        finished.await();

        assertEquals("firstsecondthird", order.toString());
    }

    @Test
    public void testSecondBlocksUntilFirstCompletes() throws Exception {
        Foo_1114 foo = new Foo_1114();
        AtomicBoolean secondRan = new AtomicBoolean(false);
        Thread second = new Thread(() -> {
            try {
                foo.second(() -> secondRan.set(true));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        second.start();
        Thread.sleep(100);
        assertTrue(second.isAlive());
        assertTrue(!secondRan.get());

        foo.first(() -> { });
        second.join(1000);
        assertTrue(secondRan.get());
    }

    @Test
    public void testNullRunnableThrows() throws Exception {
        Foo_1114 foo1 = new Foo_1114();
        assertThrows(NullPointerException.class, () -> foo1.first(null));

        Foo_1114 foo2 = new Foo_1114();
        foo2.first(() -> { });
        assertThrows(NullPointerException.class, () -> foo2.second(null));

        Foo_1114 foo3 = new Foo_1114();
        foo3.first(() -> { });
        foo3.second(() -> { });
        assertThrows(NullPointerException.class, () -> foo3.third(null));
    }

    @Test
    public void testRepeatedSequences() throws Exception {
        for (int i = 0; i < 100; i++) {
            Foo_1114 foo = new Foo_1114();
            StringBuilder order = new StringBuilder();
            CountDownLatch finished = new CountDownLatch(3);

            Thread first = new Thread(() -> runAndCountDown(() -> foo.first(() -> order.append("first")), finished));
            Thread second = new Thread(() -> runAndCountDown(() -> foo.second(() -> order.append("second")), finished));
            Thread third = new Thread(() -> runAndCountDown(() -> foo.third(() -> order.append("third")), finished));

            third.start();
            first.start();
            second.start();
            finished.await();

            assertEquals("firstsecondthird", order.toString());
        }
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

    @FunctionalInterface
    private interface InterruptibleRunnable {
        void run() throws InterruptedException;
    }
}
