package solutions.bfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // target 0002 should still be reachable in 2 steps
        assertEquals(2, test.openLock(deadends, "0002"));
    }
}
