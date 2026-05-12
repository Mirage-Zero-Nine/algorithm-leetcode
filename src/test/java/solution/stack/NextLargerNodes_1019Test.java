package solution.stack;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextLargerNodes_1019Test {
    private final NextLargerNodes_1019 solver = new NextLargerNodes_1019();

    private ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test public void testExample1() {
        assertArrayEquals(new int[]{5, 5, 0}, solver.nextLargerNodes(buildList(2, 1, 5)));
    }

    @Test public void testExample2() {
        assertArrayEquals(new int[]{7, 0, 5, 5, 0}, solver.nextLargerNodes(buildList(2, 7, 4, 3, 5)));
    }

    @Test public void testSingleNode() {
        assertArrayEquals(new int[]{0}, solver.nextLargerNodes(buildList(1)));
    }

    @Test public void testIncreasing() {
        assertArrayEquals(new int[]{2, 3, 4, 0}, solver.nextLargerNodes(buildList(1, 2, 3, 4)));
    }

    @Test public void testDecreasing() {
        assertArrayEquals(new int[]{0, 0, 0}, solver.nextLargerNodes(buildList(3, 2, 1)));
    }

    @Test public void testNull() {
        assertArrayEquals(new int[]{}, solver.nextLargerNodes(null));
    }
}
