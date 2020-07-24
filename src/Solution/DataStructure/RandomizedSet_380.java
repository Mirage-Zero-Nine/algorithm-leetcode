package Solution.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    private HashMap<Integer, Integer> location;     // key: val, val: location
    private ArrayList<Integer> list;

    /**
     * Initialize data structure.
     * Use a hash map to save val and its different location.
     * And a array list to store value, the index of each value is store in hash map for O(1) access.
     */
    public RandomizedSet_380() {
        location = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set.
     * Returns true if the set did not already contain the specified element.
     *
     * @param val insert val
     * @return true if val is not in collection, false otherwise
     */
    public boolean insert(int val) {
        if (location.containsKey(val)) {
            return false;
        }
        location.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     * First, obtain the location of removing element in map.
     * If value is in the last of array, direct remove it.
     * Otherwise, replace the remove location with last element of list, and then remove the last element in array.
     * Update the new index of replaced element in hash map.
     *
     * @param val remove val
     * @return true if the collection contained the specified element, false otherwise
     */
    public boolean remove(int val) {

        if (!location.containsKey(val)) {
            return false;
        }
        int loc = location.get(val);
        if (loc < list.size()) {
            int last = list.get(list.size() - 1);
            Collections.swap(list, loc, list.size() - 1);
            location.put(last, loc);
            list.remove(list.size() - 1);
            location.remove(val);
        }
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }
}
