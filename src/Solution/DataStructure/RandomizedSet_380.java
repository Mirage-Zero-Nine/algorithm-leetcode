package Solution.DataStructure;

import java.util.*;

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
    private final Map<Integer, Integer> map;  // val-index pair
    private final List<Integer> list;         // stores all the values

    /**
     * Initialize data structure.
     * Use a hash map to save val and its different location.
     * And a array list to store value, the index of each value is store in hash map for O(1) access.
     */
    public RandomizedSet_380() {
        map = new HashMap<>();
        list = new ArrayList<>(); // ArrayList to guarantee O(1) get
    }

    /**
     * Inserts a value to the set.
     * Returns true if the set did not already contain the specified element.
     *
     * @param val insert val
     * @return true if val is not in collection, false otherwise
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size()); // put element to the last of the list
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     * First, obtain the location of removing element in map.
     * Replace the remove location with last element of list, and then remove the last element in array.
     * Update the new index of replaced element in hash map.
     *
     * @param val remove val
     * @return true if the collection contained the specified element, false otherwise
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val); // index of value need to be removed
        int lastElement = list.get(list.size() - 1); // last element in the list
        Collections.swap(list, index, list.size() - 1); // swap the item to be removed and the last element in list
        map.put(lastElement, index); // update the new index of previous last element in list
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }
}
