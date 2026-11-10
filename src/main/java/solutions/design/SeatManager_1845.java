package solutions.design;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * @author BorisMirage
 * Time: 2026/11/09 22:04
 * Created with IntelliJ IDEA
 */

public class SeatManager_1845 {
    private final Queue<Integer> pq;

    /**
     * Initializes a min-heap with every seat from {@code 1} through {@code n}.
     * The heap keeps the smallest currently unreserved seat at its root, so
     * reserving and returning a seat both remain efficient.
     *
     * <p>Time complexity: O(n) to build the heap.
     *
     * <p>Auxiliary space: O(n) for the available-seat heap.
     *
     * @param n number of seats, numbered from 1 through {@code n}
     */
    public SeatManager_1845(int n) {
        pq = new PriorityQueue<>(IntStream.rangeClosed(1, n).boxed().toList());
    }

    /**
     * Reserves and removes the lowest-numbered available seat from the heap.
     * The problem contract guarantees a seat is available when this is called;
     * this implementation returns {@code -1} when the heap is empty.
     *
     * <p>Time complexity: O(log n), or O(1) when no seats remain.
     *
     * <p>Auxiliary space: O(1) for the operation.
     *
     * @return the smallest available seat number, or {@code -1} if sold out
     */
    public int reserve() {
        return pq.isEmpty() ? -1 : pq.poll();
    }

    /**
     * Makes a previously reserved seat available again.
     * The problem contract guarantees {@code seatNumber} is currently reserved,
     * so adding it restores that seat to the pool without creating duplicates.
     *
     * <p>Time complexity: O(log n).
     *
     * <p>Auxiliary space: O(1) for the operation, excluding heap growth.
     *
     * @param seatNumber seat to return to the available pool
     */
    public void unreserve(int seatNumber) {
        // The min-heap restores the returned seat to the correct position,
        // keeping the next reservation equal to the smallest free seat.
        pq.add(seatNumber);
    }
}
