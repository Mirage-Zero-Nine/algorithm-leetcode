package solutions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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


public class MyHashSet_705 {
    // Separate chaining buckets; their count is always maxSize.
    private List<ArrayList<Integer>> bucket;
    // Number of buckets used to calculate a key's hash index.
    private int maxSize = 256;
    // Counter used to determine when the bucket array should be resized.
    private int setSize = 0;

    /**
     * Creates an empty hash set with 256 buckets.
     */
    public MyHashSet_705() {
        bucket = IntStream.range(0, maxSize)
                .mapToObj(_ -> new ArrayList<Integer>())
                .toList();
    }

    /**
     * Adds {@code key} when it is not already present.
     *
     * @param key the non-negative key to add
     */
    public void add(int key) {
        setSize++;
        List<Integer> list = bucket.get(key % maxSize);
        if (!list.contains(key)) {
            list.add(key);
        }

        if (setSize > maxSize * 0.75) {
            rehash();
        }
    }

    /**
     * Removes {@code key} when it is present; otherwise this operation has no effect.
     *
     * @param key the non-negative key to remove
     */
    public void remove(int key) {
        if (bucket.get(key % maxSize).remove(Integer.valueOf(key))) {
            setSize--;
        }
    }

    /**
     * Returns whether {@code key} is currently stored in this set.
     *
     * @param key the non-negative key to look up
     * @return {@code true} if {@code key} is present, otherwise {@code false}
     */
    public boolean contains(int key) {
        return bucket.get(key % maxSize).stream().anyMatch(i -> i == key);
    }

    /**
     * Doubles the bucket count and redistributes each stored key using the new capacity.
     */
    private void rehash() {
        this.maxSize *= 2;
        List<ArrayList<Integer>> tmp = new ArrayList<>(this.bucket);
        this.bucket = IntStream.range(0, this.maxSize).mapToObj(_ -> new ArrayList<Integer>()).toList();
        tmp.stream()
                .filter(list -> !list.isEmpty())
                .forEach(list -> list.forEach(this::add));
    }
}
