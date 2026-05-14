package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class Solution_382Test {

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        Solution_382 sol = new Solution_382(build(1, 2, 3));
        int val = sol.getRandom();
        assertTrue(val == 1 || val == 2 || val == 3);
    }

    @Test
    public void testEdgeCases() {
        Solution_382 sol = new Solution_382(build(1));
        assertTrue(sol.getRandom() == 1);
    }

    @Test
    public void testLargeCase() {
        Solution_382 sol = new Solution_382(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int val = sol.getRandom();
        assertTrue(val >= 1 && val <= 10);
    }

    @Test
    public void testSingleNodeMultipleCalls() {
        Solution_382 sol = new Solution_382(build(42));
        for (int i = 0; i < 50; i++) {
            assertEquals(42, sol.getRandom());
        }
    }

    @Test
    public void testTwoNodes() {
        Solution_382 sol = new Solution_382(build(10, 20));
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) seen.add(sol.getRandom());
        assertTrue(seen.contains(10));
        assertTrue(seen.contains(20));
    }

    @Test
    public void testNegativeValues() {
        Solution_382 sol = new Solution_382(build(-5, -10, -15));
        int val = sol.getRandom();
        assertTrue(val == -5 || val == -10 || val == -15);
    }

    @Test
    public void testAllSameValues() {
        Solution_382 sol = new Solution_382(build(7, 7, 7, 7));
        for (int i = 0; i < 20; i++) {
            assertEquals(7, sol.getRandom());
        }
    }

    @Test
    public void testCoversAllValues() {
        Solution_382 sol = new Solution_382(build(1, 2, 3, 4, 5));
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 200; i++) seen.add(sol.getRandom());
        for (int v = 1; v <= 5; v++) assertTrue(seen.contains(v));
    }

    @Test
    public void testZeroValue() {
        Solution_382 sol = new Solution_382(build(0));
        assertEquals(0, sol.getRandom());
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = i;
        Solution_382 sol = new Solution_382(build(vals));
        for (int i = 0; i < 100; i++) {
            int val = sol.getRandom();
            assertTrue(val >= 0 && val < 1000);
        }
    }

    @Test
    public void testMixedValues() {
        Solution_382 sol = new Solution_382(build(-100, 0, 100));
        int val = sol.getRandom();
        assertTrue(val == -100 || val == 0 || val == 100);
    }
}
