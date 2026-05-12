package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class Solution382Test {

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
}
