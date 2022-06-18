package playground;

import java.util.Arrays;

/**
 * Implementation of min heap.
 * The top of heap is the smallest element in array.
 * The logic structure of heap is a full binary tree, and each node's value is smaller than its children.
 *
 * @author BorisMirage
 * Time: 2019/11/22 13:09
 * Created with IntelliJ IDEA
 */

public class MinHeap {
    private int[] array;    // heap stores in array
    private int size;       // current heap size (not the size of array)

    /**
     * Constructor of min heap.
     *
     * @param capacity capacity of heap
     */
    public MinHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be larger than 0");
        }
        array = new int[capacity];
        size = 0;
    }

    /**
     * Constructor overloading.
     *
     * @param inputArray input array
     */
    public MinHeap(int[] inputArray) {
        this.array = Arrays.copyOf(inputArray, inputArray.length * 2);
        this.size = inputArray.length;
        heapify();
    }

    /**
     * Heapify.
     * Start at middle of array (the last level will be swapped if not in correct position), percolate up each element.
     */
    private void heapify() {
        for (int i = (size - 2) / 2; i >= 0; i--) {
            percolateUp(i);
        }
    }

    /**
     * Move element from certain position to its correct position by swap with element larger than it.
     *
     * @param index position of element
     */
    private void percolateUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (array[parent] > array[index]) {
                swap(parent, index);
            } else {
                break;
            }
            index = parent;
        }
    }

    /**
     * Move element from certain position to its correct position by swap with element smaller than it.
     * If two children are both smaller than current element, select smaller one between them, then swap it.
     *
     * @param index position of element
     */
    private void percolateDown(int index) {

        while (index <= (size - 2) / 2) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int swapIndex = left;
            if (right < size && array[right] < array[left]) {
                swapIndex = right;
            }
            if (array[index] > array[swapIndex]) {
                swap(index, swapIndex);
            } else {
                break;
            }
            index = swapIndex;
        }
    }

    /**
     * Offer a new element into heap.
     *
     * @param v value
     */
    public void offer(int v) {
        if (array.length == size) {     // if reaches the size of array, initialize a new array
            int[] newArray;
            newArray = Arrays.copyOf(array, size);
            array = newArray;
        }
        array[size] = v;
        percolateUp(size);      // initially, the element is at the end of array (leaf node in tree)
        size++;
    }

    /**
     * Remove and return first element in heap.
     * Swap last element in array to top of heap, then percolate down until reaches its correct position.
     *
     * @return smallest in heap
     */
    public Integer poll() {
        if (size == 0) {
            return null;
        }
        int smallest = array[0];
        array[0] = array[size - 1];
        percolateDown(0);
        size--;
        return smallest;
    }

    /**
     * Update certain element in array.
     * Percolate up first, then percolate down.
     *
     * @param index update position
     * @param v     value to be updated
     */
    public void update(int index, int v) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index!");
        }

        array[index] = v;
        percolateUp(index);
        percolateDown(index);
    }

    /**
     * Swap two elements in array.
     *
     * @param i first element
     * @param j second element
     */
    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

