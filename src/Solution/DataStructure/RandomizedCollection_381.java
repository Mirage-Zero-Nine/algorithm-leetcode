package Solution.DataStructure;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 * 1. insert(val): Inserts an item val to the collection.
 * 2. remove(val): Removes an item val from the collection if present.
 * 3. getRandom: Returns a random element from current collection of elements.
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 *
 * @author BorisMirage
 * Time: 2019/07/18 14:32
 * Created with IntelliJ IDEA
 */

public class RandomizedCollection_381 {
    private final HashMap<Integer, HashSet<Integer>> map;
    private final List<Integer> list;

    /**
     * Initialize data structure.
     * Use a hash map to save val and its different location.
     * And an array list to store value, the index of each value is store in hash map for O(1) access.
     */
    public RandomizedCollection_381() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection.
     * Returns true if the collection did not already contain the specified element.
     *
     * @param val insert val
     * @return true if val is not in collection, false otherwise
     */
    public boolean insert(int val) {

        if (!map.containsKey(val)) {
            HashSet<Integer> s = new HashSet<>();
            map.put(val, s);
        }
        map.get(val).add(list.size());
        list.add(val);

        return map.get(val).size() == 1;
    }

    /**
     * Removes a value from the collection.
     * Returns true if the collection contained the specified element.
     * First, obtain the location of removing element in map.
     * If value is in the last of array, direct remove it.
     * Otherwise, replace the remove location with last element of list, and then remove the last element in array.
     * Update the new index of replaced element in hash map.
     *
     * @param val remove val
     * @return true if the collection contained the specified element, false otherwise
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int location = map.get(val).iterator().next();     // obtain location to be removed
        map.get(val).remove(location);                     // remove this location
        int end = list.size() - 1;

        if (location < list.size() - 1) {       // if not removing last element in list
            int last = list.get(end);     // get last element
            list.set(location, last);                   // replace removing position's element
            list.remove(end);
            map.get(last).add(location);
            map.get(last).remove(end);
        } else {
            list.remove(end);         // remove last element in list
            map.get(val).remove(end);
        }

        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }


    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection_381 test = new RandomizedCollection_381();
        System.out.println(test.insert(0));
        System.out.println(test.insert(1));
        System.out.println(test.remove(0));
        System.out.println(test.insert(2));
        System.out.println(test.remove(1));
        System.out.println(test.getRandom());
    }
}
