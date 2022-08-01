package solution.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 16:12
 * Created with IntelliJ IDEA
 */

public class SnapshotArray1146Test {

    private SnapshotArray_1146 test;

    @Test
    public void test() {
        test = new SnapshotArray_1146(2);
        assertEquals(0, test.snap());
        assertEquals(0, test.get(1, 0));
        assertEquals(0, test.get(0, 0));
        test.set(1, 8);
        assertEquals(0, test.get(1, 0));
        assertEquals(1, test.snap());
        assertEquals(8, test.get(1, 1));
    }

    @Test
    public void test1() {
        test = new SnapshotArray_1146(3);
        assertEquals(0, test.snap());
        assertEquals(0, test.get(0, 0));
        assertEquals(0, test.get(1, 0));
        assertEquals(0, test.get(2, 0));
        test.set(0, 5);
        assertEquals(1, test.snap());
        test.set(0, 6);
        assertEquals(2, test.snap());
        assertEquals(0, test.get(0, 0));
        assertEquals(5, test.get(0, 1));
        assertEquals(6, test.get(0, 2));
    }
}