package solution.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a SnapshotArray that supports the following interface:
 * 1. SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * 2. void set(index, val) sets the element at the given index to be equal to val.
 * 3. int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * 4. int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * @author BorisMirage
 * Time: 2019/08/03 20:24
 * Created with IntelliJ IDEA
 */

public class SnapshotArray_1146 {

    private final Map<Integer, HashMap<Integer, Integer>> m = new HashMap<>();
    private final Map<Integer, Integer> t = new HashMap<>();      // temp map save states of each value
    private int id = 0;

    /**
     * Use two hash maps as snapshot.
     *
     * @param length given length
     */
    public SnapshotArray_1146(int length) {
    }

    /**
     * Set value into array.
     *
     * @param index given position to be updated
     * @param val   update value
     */
    public void set(int index, int val) {
        t.put(index, val);      // update value in current array
    }

    /**
     * Take snapshot of current array.
     *
     * @return id of snapshot
     */
    public int snap() {
        m.put(id, new HashMap<>(t));        // save current array state
        t.clear();                          // clear current state
        return id++;
    }

    /**
     * Get value based on snap_id and index.
     *
     * @param index   position in array
     * @param snap_id snapshot id
     * @return value of corresponding snapshot id and index
     */
    public int get(int index, int snap_id) {
        while (snap_id >= 0) {      // find value start in latest snapshot
            if (m.get(snap_id).containsKey(index)) {        // if the snapshot with largest id contains given index
                return m.get(snap_id).get(index);           // then the value is required
            } else {
                snap_id--;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SnapshotArray_1146 test = new SnapshotArray_1146(2);
        test.snap();
        test.get(1, 0);
        test.get(0, 0);
        test.set(1, 8);
        System.out.println(test.get(1, 0));
    }
}
