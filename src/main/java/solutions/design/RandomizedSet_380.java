package solutions.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * Note:
 * 1. insert(val): Inserts an item val to the set if not already present.
 * 2. remove(val): Removes an item val from the set if present.
 * 3. getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 *
 * @author BorisMirage
 * Time: 2019/07/18 14:15
 * Created with IntelliJ IDEA
 */

public class RandomizedSet_380 {
    // Each value has one list entry, and the map records that entry's current index.
    private final Map<Integer, Integer> map;
    private final List<Integer> list;

    /**
     * Creates an empty set using a list for direct index access and a map for value lookup.
     * Both start empty. Inserting a new value appends one entry and records its index;
     * removing a value fills its position with the last entry and updates that entry's index.
     * Keeping the list free of gaps lets a random index select a value directly.
     *
     * <p>Construction takes O(1) time and space. The two structures use O(n) space, where n
     * is the largest number of values stored at once; removal does not shrink their capacity.
     * Map lookups take O(1) average time; list appends take O(1) amortized time, meaning
     * occasional array growth is spread across many insertions.
     */
    public RandomizedSet_380() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Appends a value and records its index if it is absent. A duplicate leaves the set unchanged.
     * Takes O(1) average amortized time and O(1) extra space, apart from stored entries
     * and occasional backing-array growth.
     *
     * @param val value to add
     * @return true if the value was added; false if it was already present
     */
    public boolean insert(int val) {
        if (map.putIfAbsent(val, list.size()) != null) {
            return false;
        }
        list.add(val);
        return true;
    }

    /**
     * Removes a value by swapping it with the last list entry and trimming the end.
     * This avoids shifting later entries; the set does not require a particular order.
     * An absent value leaves the set unchanged. Takes O(1) average time and O(1) extra space.
     *
     * @param val value to remove
     * @return true if the value was removed; false if it was absent
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int indexToRemove = map.get(val), valueToSwap = list.getLast();
        Collections.swap(list, indexToRemove, list.size() - 1);
        // The moved value must point to its new slot before a later removal can find it.
        map.put(valueToSwap, indexToRemove);
        // When val was already last, this removes the same mapping just updated above.
        map.remove(val);
        // The removed value is now last, so trimming it leaves no gap or stale list entry.
        list.removeLast();
        return true;
    }

    /**
     * Returns a uniformly selected stored value without changing the set.
     * The caller must ensure the set is nonempty, as required by the problem.
     * Takes O(1) time and O(1) extra space.
     *
     * @return a current value, with equal probability for each stored value
     */
    public int getRandom() {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
