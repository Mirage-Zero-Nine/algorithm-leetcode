package solutions.design;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1 and n and value is a string.
 * No two pairs have the same id.
 * Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion.
 * The concatenation of all the chunks should result in a list of the sorted values.
 * Implement the OrderedStream class:
 * OrderedStream(int n) Constructs the stream to take n values.
 * String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
 *
 * @author BorisMirage
 * Time: 2026/11/06 22:48
 * Created with IntelliJ IDEA
 */

public class OrderedStream_1656 {
    private final String[] arr;
    private int start;

    /**
     * Creates an empty stream whose IDs range from 1 through {@code n}.
     *
     * <p>The array is a set of numbered slots: slot {@code id - 1} stores the
     * value received for ID {@code id}. The stream also keeps a pointer to the
     * smallest ID that has not yet been returned. Keeping these two pieces of
     * state separate is important: receiving a value only fills its slot;
     * values are returned later when the pointer can walk through consecutive
     * filled slots. This lets one insertion return the largest currently
     * available ordered chunk, while a missing earlier ID makes the current
     * chunk empty and leaves the pointer unchanged.
     * <p>
     * Time complexity: O(n) to allocate the slots.
     * Auxiliary space: O(n) for the stored values, excluding returned chunks.
     *
     * @param n the number of IDs the stream accepts
     */
    public OrderedStream_1656(int n) {
        arr = new String[n];
        start = 0;
    }

    /**
     * Stores a value and returns every newly available value starting at the
     * next unreturned ID. A value can therefore be received without being
     * returned immediately: for example, receiving {@code (5, "E")} waits
     * while ID 4 is missing. Once {@code (4, "D")} arrives, this call returns
     * {@code ["D", "E"]} in ID order. The pointer advances past each value
     * returned, so later calls never return a value twice.
     * <p>
     * Time complexity: O(1 + k), where {@code k} is the number of values
     * returned by this insertion.
     * Auxiliary space: O(k) for the returned chunk, in addition to the O(n)
     * stream storage.
     *
     * @param idKey the unique ID of the arriving value, from 1 through n
     * @param value the value to store for that ID
     * @return the largest contiguous chunk beginning at the next unreturned ID
     */
    public List<String> insert(int idKey, String value) {
        arr[idKey - 1] = value;
        List<String> output = new ArrayList<>();
        // A filled later slot is only waiting data. Emit it after every earlier
        // slot has arrived, and move start so emitted values cannot recur.
        while (start < arr.length && arr[start] != null) {
            output.add(arr[start++]);
        }
        return output;
    }
}
