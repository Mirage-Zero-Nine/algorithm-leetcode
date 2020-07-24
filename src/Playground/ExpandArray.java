package Playground;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * @author BorisMirage
 * Create Date: 2/26/18 15:16
 * Created with IntelliJ IDEA
 */

public class ExpandArray {

    private int[] expandArray;
    private HashMap<Integer, List<Integer>> arrayMap = new HashMap<>();
    private int end;

    /**
     * This class is based on array object, which contains some basic method of an array.
     * And it has some expanded function, such as quick sort, append a new element.
     *
     * @param array input array
     */
    public ExpandArray(int[] array) {
        expandArray = array;
        arrayMap();
        end = array.length;
    }

    /**
     * Store array index and value into map
     * key - array item
     * value - position (in ArrayList)
     */
    private void arrayMap() {
        for (int i = 0; i < expandArray.length; i++) {
            List<Integer> cur = arrayMap.getOrDefault(expandArray[i], new ArrayList<>());
            cur.add(i);
            arrayMap.put(expandArray[i], cur);
        }
    }

    /**
     * Check whether value is in array.
     *
     * @param value target value
     * @return true if in array
     */
    private boolean isExist(int value) {
        return arrayMap.containsKey(value);
    }

    /**
     * Obtain position based on given value.
     *
     * @param value given value
     * @return position list
     */
    private List<Integer> getPosition(int value) {
        return arrayMap.get(value);
    }

    /**
     * Obtain value based on index
     *
     * @param index given index
     * @return index matched value
     */
    private int getValue(int index) {
        return expandArray[index];
    }

    /**
     * Add value to last of array.
     *
     * @param value value to be added
     */
    private void addValueToLast(int value) {
        end = end + 1;
        List<Integer> cur = arrayMap.getOrDefault(value, new ArrayList<>());
        cur.add(end);
        arrayMap.put(value, cur);
    }

    /**
     * Return array.
     *
     * @return array
     */
    public int[] thisArray() {
        int[] res = new int[arrayMap.size()];
        Set<Integer> allKey = arrayMap.keySet();
        for (Integer key : allKey) {
            List<Integer> currentPosition = arrayMap.get(key);
            for (Integer positionInArray : currentPosition) {
                res[positionInArray] = key;
            }
        }
        return res;
    }

}
