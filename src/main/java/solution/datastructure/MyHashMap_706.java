package solution.datastructure;

import java.util.LinkedList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * - put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * - get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * - remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * @author BorisMirage
 * Time: 2018/10/07 17:49
 * Created with IntelliJ IDEA
 * No.100
 */

public class MyHashMap_706 {
    private LinkedList<Cell>[] map;
    int MAX_SIZE = 256;
    int size;

    /**
     * Initialize map.
     */
    @SuppressWarnings("unchecked")
    public MyHashMap_706() {
        this.map = new LinkedList[MAX_SIZE];
    }

    /**
     * Value will always be non-negative.
     *
     * @param key   given key
     * @param value given value
     */
    public void put(int key, int value) {
        int index = key % MAX_SIZE;
        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }

        Cell tmp = new Cell(key, value);
        LinkedList<Cell> l = map[index];

        for (Cell c : l) {
            if (c.key == key) {
                l.remove(c);
                break;
            }
        }

        l.add(tmp);
        size++;

        if (size >= MAX_SIZE * 0.75) {
            rehashing();
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     *
     * @param key given key
     * @return corresponding value
     */
    public int get(int key) {
        int index = key % MAX_SIZE;
        if (map[index] == null) {
            return -1;
        }

        LinkedList<Cell> l = map[index];

        for (Cell c : l) {
            if (c.key == key) {
                return c.val;
            }
        }

        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     *
     * @param key given key
     */
    public void remove(int key) {
        int index = key % MAX_SIZE;
        if (map[index] == null) {
            return;
        }

        LinkedList<Cell> l = map[index];

        l.removeIf(c -> c.key == key);

        size--;
    }

    /**
     * Rehashing to make hash set more balanced.
     */
    @SuppressWarnings("unchecked")
    private void rehashing() {
        MAX_SIZE *= 2;

        LinkedList<Cell>[] tmp = new LinkedList[MAX_SIZE];

        for (List<Cell> l : map) {
            if (l != null) {
                for (Cell c : l) {
                    int index = c.key % MAX_SIZE;
                    if (tmp[index] == null) {
                        tmp[index] = new LinkedList<>();
                    }
                    tmp[index].add(c);
                }
            }
        }

        map = tmp;
    }

    /**
     * Cell used in map.
     */
    static class Cell {
        int key;
        int val;

        public Cell(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MyHashMap_706 test = new MyHashMap_706();
        test.put(1, 1);
        System.out.println(test.get(1));
    }
}
