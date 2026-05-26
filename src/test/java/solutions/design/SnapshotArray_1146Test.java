package solutions.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 16:12
 * Created with IntelliJ IDEA
 */

public class SnapshotArray_1146Test {

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

    @Test
    public void testSetBeforeFirstSnap() {
        test = new SnapshotArray_1146(1);
        test.set(0, 9);
        int snap0 = test.snap();
        assertEquals(0, snap0);
        assertEquals(9, test.get(0, 0));
    }

    @Test
    public void testUnsetIndexDefaultsToZeroAcrossSnapshots() {
        test = new SnapshotArray_1146(4);
        test.set(1, 3);
        assertEquals(0, test.snap());
        assertEquals(1, test.snap());
        assertEquals(0, test.get(3, 0));
        assertEquals(0, test.get(3, 1));
    }

    @Test
    public void testValueCarriesForwardWhenNoNewSet() {
        test = new SnapshotArray_1146(2);
        test.set(0, 11);
        assertEquals(0, test.snap());
        assertEquals(1, test.snap());
        assertEquals(11, test.get(0, 0));
        assertEquals(11, test.get(0, 1));
    }

    @Test
    public void testOverwriteWithinSameSnapshotWindow() {
        test = new SnapshotArray_1146(2);
        test.set(1, 4);
        test.set(1, 7);
        assertEquals(0, test.snap());
        assertEquals(7, test.get(1, 0));
    }

    @Test
    public void testMultipleIndicesInSingleSnapshot() {
        test = new SnapshotArray_1146(5);
        test.set(0, 1);
        test.set(2, 3);
        test.set(4, 5);
        assertEquals(0, test.snap());
        assertEquals(1, test.get(0, 0));
        assertEquals(3, test.get(2, 0));
        assertEquals(5, test.get(4, 0));
    }

    @Test
    public void testBacktrackingToOlderSnapshot() {
        test = new SnapshotArray_1146(2);
        test.set(0, 2);
        assertEquals(0, test.snap());
        test.set(0, 8);
        assertEquals(1, test.snap());
        assertEquals(2, test.get(0, 0));
        assertEquals(8, test.get(0, 1));
    }

    @Test
    public void testNegativeValueSupport() {
        test = new SnapshotArray_1146(1);
        test.set(0, -5);
        assertEquals(0, test.snap());
        assertEquals(-5, test.get(0, 0));
    }

    @Test
    public void testGiantCaseManySnapshots() {
        test = new SnapshotArray_1146(50);
        for (int i = 0; i < 200; i++) {
            test.set(i % 50, i);
            test.snap();
        }
        assertEquals(150, test.get(0, 150));
        assertEquals(199, test.get(49, 199));
        assertEquals(0, test.get(1, 0));
    }
}
