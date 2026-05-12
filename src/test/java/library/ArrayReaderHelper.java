package library;

import java.util.HashMap;
import java.util.Map;

public class ArrayReaderHelper extends ArrayReader {
    public ArrayReaderHelper(int[] arr) {
        // Override: map index -> value (get(k) returns value at index k)
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            m.put(i, arr[i]);
        }
        // Use reflection-free approach: override get() behavior via subclass
        this.indexToValue = m;
    }

    private Map<Integer, Integer> indexToValue;

    @Override
    public int get(int k) {
        return indexToValue.getOrDefault(k, 2147483647);
    }
}
