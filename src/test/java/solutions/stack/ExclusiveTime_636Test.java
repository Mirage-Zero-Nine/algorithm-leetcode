package solutions.stack;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;;

public class ExclusiveTime_636Test {
    private final ExclusiveTime_636 e = new ExclusiveTime_636();

    @Test public void testSimple() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:7"));
        assertArrayEquals(new int[]{4, 4}, result);
    }
    @Test public void testSingleFunction() {
        int[] result = e.exclusiveTime(1, List.of("0:start:0", "0:end:3"));
        assertArrayEquals(new int[]{4}, result);
    }
    @Test public void testNested() {
        int[] result = e.exclusiveTime(3, List.of("0:start:0", "1:start:1", "1:end:2", "2:start:3", "2:end:4", "0:end:5"));
        assertArrayEquals(new int[]{2, 2, 2}, result);
    }
    @Test public void testAdjacent() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "0:end:1", "1:start:2", "1:end:3"));
        assertArrayEquals(new int[]{2, 2}, result);
    }

    @Test public void testDeepNesting() {
        int[] result = e.exclusiveTime(3, List.of("0:start:0", "1:start:1", "2:start:2", "2:end:3", "1:end:4", "0:end:5"));
        assertArrayEquals(new int[]{2, 2, 2}, result);
    }

    @Test public void testRecursiveCall() {
        int[] result = e.exclusiveTime(1, List.of("0:start:0", "0:start:1", "0:end:2", "0:end:3"));
        assertArrayEquals(new int[]{4}, result);
    }

    @Test public void testMultipleCallsSameFunction() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "0:end:1", "0:start:2", "0:end:3", "1:start:4", "1:end:5"));
        assertArrayEquals(new int[]{4, 2}, result);
    }

    @Test public void testLargeTimestamps() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "1:start:5", "1:end:10", "0:end:15"));
        assertArrayEquals(new int[]{10, 6}, result);
    }

    @Test public void testSingleUnitFunction() {
        int[] result = e.exclusiveTime(2, List.of("0:start:0", "1:start:1", "1:end:1", "0:end:2"));
        assertArrayEquals(new int[]{2, 1}, result);
    }

    @Test public void testGiantCase() {
        java.util.List<String> logs = new java.util.ArrayList<>();
        int n = 5000;
        for (int i = 0; i < n; i++) logs.add(i + ":start:" + (i * 2));
        for (int i = n - 1; i >= 0; i--) logs.add(i + ":end:" + (n * 2 + (n - 1 - i) * 2));
        int[] result = e.exclusiveTime(n, logs);
        assertEquals(n, result.length);
        for (int v : result) assertTrue(v > 0);
    }
    @Test public void testTwoNestedFunctions() { assertArrayEquals(new int[]{2,2}, e.exclusiveTime(2,List.of("0:start:0","1:start:1","1:end:2","0:end:3"))); }
    @Test public void testChildOneUnit() { assertArrayEquals(new int[]{1,1}, e.exclusiveTime(2,List.of("0:start:0","1:start:1","1:end:1","0:end:1"))); }
    @Test public void testRecursiveThreeLevels() { assertArrayEquals(new int[]{5},e.exclusiveTime(1,List.of("0:start:0","0:start:1","0:start:2","0:end:2","0:end:3","0:end:4"))); }
    @Test public void testIdleGapsInSeparateCalls() { assertArrayEquals(new int[]{3},e.exclusiveTime(1,List.of("0:start:5","0:end:6","0:start:10","0:end:10"))); }
    @Test public void testThreeSiblings() { assertArrayEquals(new int[]{4},e.exclusiveTime(1,List.of("0:start:0","0:end:0","0:start:2","0:end:3","0:start:5","0:end:5"))); }
    @Test public void testMultipleIdsWithChild() { assertArrayEquals(new int[]{1,1,2},e.exclusiveTime(3,List.of("0:start:0","1:start:0","1:end:0","2:start:1","2:end:2","0:end:3"))); }
    @Test public void testEndTimestampInclusive() { assertArrayEquals(new int[]{1},e.exclusiveTime(1,List.of("0:start:99","0:end:99"))); }
    @Test public void testIndependentInvocation() { assertArrayEquals(new int[]{1},e.exclusiveTime(1,List.of("0:start:0","0:end:0"))); assertArrayEquals(new int[]{2},e.exclusiveTime(1,List.of("0:start:4","0:end:5"))); }
    @Test public void testLargeSingleDuration() { assertArrayEquals(new int[]{1001},e.exclusiveTime(1,List.of("0:start:0","0:end:1000"))); }
    @Test public void testNestedDifferentOrder() { assertArrayEquals(new int[]{2,2},e.exclusiveTime(2,List.of("0:start:1","1:start:2","1:end:3","0:end:4"))); }
}
