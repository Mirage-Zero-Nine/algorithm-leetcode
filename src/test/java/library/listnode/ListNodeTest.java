package library.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeTest {
    @Test void constructorStoresValueAndLeavesNextNull() {
        ListNode node = new ListNode(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, node.val);
        assertNull(node.next);
    }

    @Test void nextCanLinkNodesAndValuesRemainMutable() {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        first.next = second;
        second.val = 3;
        assertSame(second, first.next);
        assertEquals(3, first.next.val);
    }
}
