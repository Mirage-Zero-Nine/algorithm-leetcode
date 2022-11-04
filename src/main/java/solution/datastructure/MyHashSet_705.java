package solution.datastructure;

import java.util.LinkedList;
import java.util.List;

/**
 * Design a HashSet without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * 1. add(value): Insert a value into the HashSet.
 * 2. contains(value) : Return whether the value exists in the HashSet or not.
 * 3. remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 * Note:
 * 1. All values will be in the range of [0, 1000000].
 * 2. The number of operations will be in the range of [1, 10000].
 * 3. Please do not use the built-in HashSet library.
 *
 * @author BorisMirage
 * Time: 2020/01/13 13:31
 * Created with IntelliJ IDEA
 */

@SuppressWarnings("unchecked")
public class MyHashSet_705 {
    private List<Integer>[] arr;
    private int MAX_SIZE = 256; // set initial max capacity to 256
    private int size;

    /**
     * Initialize hash set.
     */
    public MyHashSet_705() {
        arr = new LinkedList[MAX_SIZE];
        size = 0;
    }

    /**
     * Add value to hash set.
     * Note that hash set will not contain duplicated value.
     *
     * @param key value to be added
     */
    public void add(int key) {
        int index = key % MAX_SIZE;

        if (arr[index] == null) {
            arr[index] = new LinkedList<>();
        }

        List<Integer> tmp = arr[index];

        for (int i : tmp) {
            if (i == key) {
                return;
            }
        }
        tmp.add(key);

        if (size >= MAX_SIZE * 0.75) {
            rehashing();
        }
    }

    /**
     * Remove value from hash set.
     *
     * @param key value to be removed from hash set.
     */
    public void remove(int key) {
        int index = key % MAX_SIZE;
        if (arr[index] == null) {
            return;
        }

        for (int i = 0; i < arr[index].size(); i++) {
            if (arr[index].get(i).equals(key)) {
                arr[index].remove(i);
                size--;
                return;
            }
        }
    }

    /**
     * Rehashing when total elements are more than 75% of total capacity to make hash set more balanced.
     */
    private void rehashing() {
        MAX_SIZE *= 2;
        List<Integer>[] newArr = new LinkedList[MAX_SIZE];

        for (List<Integer> l : arr) {
            if (l != null) {
                for (int i : l) {
                    int index = i % MAX_SIZE;
                    if (newArr[index] == null) {
                        newArr[index] = new LinkedList<>();
                    }
                    newArr[index].add(i);
                }
            }

        }
        arr = newArr;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = key % MAX_SIZE;
        if (arr[index] == null) {
            return false;
        }

        for (int i : arr[index]) {
            if (i == key) {
                return true;
            }
        }

        return false;
    }
}
