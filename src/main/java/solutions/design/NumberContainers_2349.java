package solutions.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Design a number container system that can do the following:
 * - Insert or Replace a number at the given index in the system.
 * - Return the smallest index for the given number in the system.
 * Implement the NumberContainers class:
 * - NumberContainers() Initializes the number container system.
 * - void change(int index, int number) Fills the container at index with the number.
 * If there is already a number at that index, replace it.
 * - int find(int number) Returns the smallest index for the given number.
 * Return -1 if there is no index that is filled by number in the system.
 *
 * @author BorisMirage
 * Time: 2022/08/03 15:44
 * Created with IntelliJ IDEA
 */
public class NumberContainers_2349 {
    private final Map<Integer, Integer> numbers;
    private final Map<Integer, TreeSet<Integer>> indexes;

    /**
     * Creates an empty container system. {@code numbers} records the current number at each
     * assigned index, while {@code indexes} stores every index for each number in sorted order.
     * Together they maintain the invariant that an index appears in exactly the set belonging to
     * its current number, and each set is removed when its last index moves away. Hash-map
     * operations are expected O(1), including occasional O(n) resizing; each retained index also
     * occupies one TreeSet node. Here n is the number of assigned indexes.
     *
     * <p>Time complexity: O(1) expected; initializing the two empty hash maps takes constant time.
     * Auxiliary space complexity: O(1); no entries are retained until {@link #change(int, int)}
     * is called.
     */
    public NumberContainers_2349() {
        numbers = new HashMap<>();
        indexes = new HashMap<>();
    }

    /**
     * Assigns {@code number} to {@code index}, removing the index from its previous number's
     * sorted set first. If the assignment is unchanged, the existing membership already satisfies
     * the invariant and the method returns immediately. Removing an emptied set makes a later
     * lookup correctly report that number as absent; otherwise its first TreeSet entry is the
     * smallest matching index. Indices and numbers are in [1, 1,000,000,000], with at most
     * 100,000 calls to {@code change} and {@code find} combined.
     *
     * <p>Time complexity: O(log n) worst case for TreeSet insertion/removal, plus expected O(1)
     * hash-map work (occasionally O(n) when a map resizes); n is the number of assigned indexes.
     * Auxiliary space complexity: O(1) for an ordinary call beyond retained data; a hash-map
     * resize may temporarily use O(n) additional bucket space. Each newly assigned index adds
     * O(1) retained mapping and TreeSet-node space, where n is the number of assigned indexes.
     *
     * @param index  the index to assign
     * @param number the number to store at the index
     */
    public void change(int index, int number) {
        if (numbers.containsKey(index)) {
            int oldNumber = numbers.get(index);
            if (oldNumber == number) {
                return;
            }
            // Remove the old membership before adding the new one so an index cannot belong to
            // two number sets; discard empty sets so find() can distinguish absent numbers.
            indexes.get(oldNumber).remove(index);
            if (indexes.get(oldNumber).isEmpty()) {
                indexes.remove(oldNumber);
            }
        }
        numbers.put(index, number);
        indexes.computeIfAbsent(number, _ -> new TreeSet<>()).add(index);
    }

    /**
     * Returns the smallest index currently assigned to {@code number}, or {@code -1} if none is
     * assigned. Because each number's indexes are kept in a TreeSet and empty sets are removed,
     * the first entry is the required minimum and a missing map entry means no match exists.
     *
     * <p>Time complexity: O(log n) worst case for retrieving the first TreeSet entry, plus
     * expected O(1) for the hash-map lookup (O(n) worst case under collisions), where n is the
     * number of assigned indexes. Auxiliary space complexity: O(1), with no per-call storage.
     *
     * @param number the number whose smallest assigned index is requested
     * @return the smallest matching index, or {@code -1} when no index contains the number
     */
    public int find(int number) {
        return indexes.containsKey(number) ? indexes.get(number).first() : -1;
    }
}
