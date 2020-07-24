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
    private HashMap<Integer, HashSet<Integer>> m;
    private ArrayList<Integer> l;

    /**
     * Initialize data structure.
     * Use a hash map to save val and its different location.
     * And a array list to store value, the index of each value is store in hash map for O(1) access.
     */
    public RandomizedCollection_381() {
        m = new HashMap<>();
        l = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection.
     * Returns true if the collection did not already contain the specified element.
     *
     * @param val insert val
     * @return true if val is not in collection, false otherwise
     */
    public boolean insert(int val) {

        if (!m.containsKey(val)) {
            HashSet<Integer> s = new HashSet<>();
            m.put(val, s);
        }
        m.get(val).add(l.size());
        l.add(val);

        return m.get(val).size() == 1;
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
        if (!m.containsKey(val)) {
            return false;
        }

        int loc = m.get(val).iterator().next();     // obtain location to be removed
        m.get(val).remove(loc);                     // remove this location
        int end = l.size() - 1;

        if (loc < l.size() - 1) {       // if not removing last element in list
            int last = l.get(end);     // get last element
            l.set(loc, last);                   // replace removing position's element
            l.remove(end);
            m.get(last).add(loc);
            m.get(last).remove(end);
        } else {
            l.remove(end);         // remove last element in list
            m.get(val).remove(end);
        }

        if (m.get(val).isEmpty()) {
            m.remove(val);
        }

        return true;
    }


    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return l.get((int) (Math.random() * l.size()));
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
