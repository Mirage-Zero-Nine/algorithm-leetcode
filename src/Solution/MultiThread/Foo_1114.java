package Solution.MultiThread;

import java.util.concurrent.Semaphore;

/**
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism to ensure that second() is executed after first(), and third() is executed after second().
 *
 * @author BorisMirage
 * Time: 2020/02/03 18:17
 * Created with IntelliJ IDEA
 */

public class Foo_1114 {
    Semaphore run2, run3;

    /**
     * Two semaphores for task 2 and 3. Task 1 has no concurrency problem, therefore, no semaphore required for task 1.
     */
    public Foo_1114() {
        run2 = new Semaphore(0);        // semaphore for task 2
        run3 = new Semaphore(0);        // semaphore for task 3
    }

    /**
     * Run the first task.
     *
     * @param printFirst task 1
     * @throws InterruptedException thread interrupted
     */
    public void first(Runnable printFirst) throws InterruptedException {

        printFirst.run();       // printFirst.run() outputs "first"
        run2.release();
    }

    /**
     * Run the second task after first task.
     *
     * @param printSecond task 2
     * @throws InterruptedException thread interrupted
     */
    public void second(Runnable printSecond) throws InterruptedException {

        run2.acquire();         // check if first task is completed
        printSecond.run();      // printSecond.run() outputs "second"
        run3.release();         // task 2 is completed, release semaphore
    }

    /**
     * Run the third task after second task.
     *
     * @param printThird task 3
     * @throws InterruptedException thread interrupted
     */
    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();         // check if second task is completed
        printThird.run();       // printThird.run() outputs "third".
        run3.release();
    }
}
