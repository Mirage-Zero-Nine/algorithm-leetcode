package solutions.bfs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:24
 * Created with IntelliJ IDEA
 */

public class OpenLock_752Test {
    private final OpenLock_752 test = new OpenLock_752();

    @Test
    public void test() {
        assertEquals(6, test.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        assertEquals(-1, test.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
        assertEquals(-1, test.openLock(new String[]{"0000"}, "8888"));
    }

    @Test
    public void testHappyCases() {
        assertEquals(0, test.openLock(new String[]{}, "0000"));
        assertEquals(1, test.openLock(new String[]{}, "0009"));
        assertEquals(1, test.openLock(new String[]{}, "9000"));
        assertEquals(1, test.openLock(new String[]{}, "0900"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.openLock(new String[]{"0001", "0010", "0100", "1000", "0009", "0090", "0900", "9000"}, "9999"));
        assertEquals(-1, test.openLock(new String[]{"0000", "8888"}, "0000"));
        assertEquals(2, test.openLock(new String[]{"5555"}, "0011"));
    }

    @Test
    public void testGiantCase() {
        assertEquals(20, test.openLock(new String[]{}, "5555"));
    }

    @Test
    public void testSingleTurnTargets() {
        assertEquals(1, test.openLock(new String[]{}, "0001"));
        assertEquals(1, test.openLock(new String[]{}, "0010"));
        assertEquals(1, test.openLock(new String[]{}, "0100"));
        assertEquals(1, test.openLock(new String[]{}, "1000"));
    }

    @Test
    public void testWrapAroundBackward() {
        assertEquals(1, test.openLock(new String[]{}, "9000"));
        assertEquals(4, test.openLock(new String[]{}, "9999"));
    }

    @Test
    public void testTargetIsStart() {
        assertEquals(0, test.openLock(new String[]{}, "0000"));
    }

    @Test
    public void testAllDeadends() {
        // All neighbors of 0000 are dead
        assertEquals(-1, test.openLock(new String[]{"0001", "0010", "0100", "1000", "0009", "0090", "0900", "9000"}, "5555"));
    }

    @Test
    public void testDeadendIsTarget() {
        // Target itself is a deadend
        assertEquals(-1, test.openLock(new String[]{"0202"}, "0202"));
    }

    @Test
    public void testLargeDeadendSet() {
        // Many deadends but path still exists
        String[] deadends = new String[100];
        for (int i = 0; i < 100; i++) {
            deadends[i] = String.format("%04d", i + 1000);
        }
        // The deadends are disjoint from the direct path 0000 -> 0001 -> 0002.
        assertEquals(2, test.openLock(deadends, "0002"));
    }

    @Test
    public void testOfficialSecondExampleAndReverseWrap() {
        assertEquals(1, test.openLock(new String[]{"8888"}, "0009"));
        assertEquals(3, test.openLock(new String[]{}, "9099"));
    }

    @Test
    public void testShortestPathMustUseAnotherWheelWhenBothDirectNeighborsAreBlocked() {
        assertEquals(4, test.openLock(new String[]{"0001", "0010"}, "0011"));
        assertEquals(4, test.openLock(new String[]{"0001", "0009"}, "0002"));
    }

    @Test
    public void testTargetStartRemainsZeroWhenOtherDeadendExists() {
        assertEquals(0, test.openLock(new String[]{"1234"}, "0000"));
    }

    @Test
    public void testDuplicateAndUnorderedDeadendsDoNotChangeShortestPath() {
        assertEquals(4, test.openLock(new String[]{"1000", "0001", "0001"}, "0002"));
    }

    @Test
    public void testAdditionalUnobstructedDistances() {
        assertEquals(4, test.openLock(new String[]{}, "1098"));
        assertEquals(20, test.openLock(new String[]{}, "5555"));
    }

    @Test
    public void testMaximumDeadendInputStillFindsWraparoundShortestPath() {
        String[] deadends = new String[500];
        for (int i = 0; i < deadends.length; i++) {
            deadends[i] = String.format("%04d", i + 1000);
        }

        assertEquals(20, test.openLock(deadends, "5555"));
    }

    @Test
    public void testDeadendInputIsNotMutatedAndInstanceCanBeReused() {
        String[] deadends = {"0001", "0010"};
        String[] snapshot = Arrays.copyOf(deadends, deadends.length);

        assertEquals(4, test.openLock(deadends, "0011"));
        assertArrayEquals(snapshot, deadends);
        assertEquals(1, test.openLock(new String[]{}, "0001"));
    }

    @Test
    public void testIndependentSingleWheelDistances() {
        assertEquals(2, test.openLock(new String[]{}, "0002"));
        assertEquals(3, test.openLock(new String[]{}, "0007"));
        assertEquals(4, test.openLock(new String[]{}, "0006"));
        assertEquals(5, test.openLock(new String[]{}, "0005"));
    }

    @Test
    public void testDeadendsForceDetourButDoNotChangeInput() {
        String[] deadends = {"0001", "0009", "0010", "0090", "0100", "0900"};
        String[] before = Arrays.copyOf(deadends, deadends.length);
        // Both direct moves for the final wheel and the other start neighbors are blocked;
        // a shortest valid route detours through the first wheel and takes four turns.
        assertEquals(4, test.openLock(deadends, "0002"));
        assertArrayEquals(before, deadends);
    }

    @Test
    public void testSeveralWheelWrapAroundCombination() {
        assertEquals(2, test.openLock(new String[]{}, "9090"));
        assertEquals(4, test.openLock(new String[]{}, "1919"));
        assertEquals(12, test.openLock(new String[]{}, "3579"));
    }
}
