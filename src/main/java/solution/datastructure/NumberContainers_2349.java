package solution.datastructure;

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
    private final Map<Integer, Integer> valueMap = new HashMap<>();
    private final Map<Integer, TreeSet<Integer>> sortMap = new HashMap<>();

    /**
     * Two hash maps. First hash map to store the given index and number.
     * Second hash map store the given number and its sorted index using a tree set.
     * Each time, insert or update value in both map.
     */
    public NumberContainers_2349() {
    }

    /**
     * Change the value in valueMap first, then update in sortMap as well.
     * Note if the index set is empty (means this value has no index in the container), remove the entry in map.
     *
     * @param index  given index
     * @param number new value
     */
    public void change(int index, int number) {
        if (valueMap.containsKey(index)) {
            int oldValue = valueMap.get(index);
            TreeSet<Integer> set = sortMap.get(oldValue);
            set.remove(index);
            if (set.size() == 0) {
                sortMap.remove(oldValue);
            }
        }

        valueMap.put(index, number);
        TreeSet<Integer> set = sortMap.getOrDefault(number, new TreeSet<>());
        set.add(index);
        sortMap.put(number, set);
    }

    /**
     * Find the minimum index or return -1 if number does not exist.
     *
     * @param number given number
     * @return minimum index or -1 if the number does not exist
     */
    public int find(int number) {
        return sortMap.containsKey(number) ? sortMap.get(number).first() : -1;
    }
}
