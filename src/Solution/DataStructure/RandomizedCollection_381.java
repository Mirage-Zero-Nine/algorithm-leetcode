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
    private final HashMap<Integer, HashSet<Integer>> map; // store the value and its index in the list
    private final List<Integer> list; // store all the values for randomized get, should be an array list

    /**
     * Initialize data structure.
     * Use a hash map to save val and its different location.
     * And an array list to store value, the index of each value is store in hash map for O(1) randomized access.
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
     * Obtain one index of the value in hash map. Replace this element with the end of the array to remove this element.
     * Then update location of the element used in replacement in map.
     *
     * @param val remove val
     * @return true if the collection contained the specified element, false otherwise
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val).iterator().next();     // obtain location to be removed
        map.get(val).remove(index);                     // remove this location
        int end = list.size() - 1;

        if (index < list.size() - 1) {       // if not removing last element in list
            int last = list.get(end);     // get last element
            list.set(index, last);                   // replace removing position's element
            map.get(last).add(index);
            map.get(last).remove(end);
        } else {
            map.get(val).remove(end);
        }

        list.remove(end);         // remove last element from list

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
