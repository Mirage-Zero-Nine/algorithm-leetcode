package Solution.MultiThread;

import java.util.concurrent.Semaphore;

/**
 * The same instance of FooBar will be passed to two different threads.
 * Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.
 *
 * @author BorisMirage
 * Time: 2020/04/06 15:10
 * Created with IntelliJ IDEA
 */

public class FooBar_1115 {
    private int n;
    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    /**
     * Two semaphores. One controls foo and one controls bar.
     * foo printed before bar, so the semaphores related to foo should be initialized to 1.
     *
     * @param n print foobar n times
     */
    public FooBar_1115(int n) {
        this.n = n;
    }

    /**
     * Print foo.
     *
     * @param printFoo thread print foo
     * @throws InterruptedException if the printing is interrupted
     */
    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    /**
     * Print bar.
     *
     * @param printBar thread print bar
     * @throws InterruptedException if the printing is interrupted
     */
    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
