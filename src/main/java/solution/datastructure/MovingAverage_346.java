package solution.datastructure;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 *
 * @author BorisMirage
 * Time: 2018/10/07 17:20
 * Created with IntelliJ IDEA
 */

public class MovingAverage_346 {
    private final int size;
    private int head = 0;
    private int count = 0;
    private int sum = 0;
    private final int[] arr;

    /**
     * Data structure initialize.
     *
     * @param size windows size that control the queue size
     */
    public MovingAverage_346(int size) {
        this.size = size;
        arr = new int[size];
    }

    /**
     * Use circular queue with array to store and add/remove elements in array.
     * Tail is next to head.
     * Each time, fill tail cell with new incoming value and move head to the previous tail position.
     * In this way, the head will always stands at the newest elements and the replaced element is always the oldest.
     *
     * @param val given value
     * @return average of queue
     */
    public double next(int val) {
        if (count <= size) {
            count++;
        }

        /*
         * Tail is next to head.
         * Use mod to avoid overflow and convert out bound tail/head to the start of array. */
        int tail = (head + 1) % size;
        sum = sum - arr[tail] + val;

        head = (head + 1) % size;       // move head to next position
        arr[head] = val;

        return (double) sum / Math.min(size, count);
    }
}
