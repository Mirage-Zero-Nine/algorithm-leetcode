package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class HasCycle_141Test {

    private final HasCycle_141 test = new HasCycle_141();

    @Test
    public void testHappyCases() {
        ListNode n1 = new ListNode(3), n2 = new ListNode(2), n3 = new ListNode(0), n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasCycle(null));
        assertFalse(test.hasCycle(new ListNode(1)));
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2;
        assertFalse(test.hasCycle(n1));
    }

    @Test
    public void testLargeCase() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3);
        n1.next = n2; n2.next = n3; n3.next = n1;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testSelfCycle() {
        ListNode n1 = new ListNode(1);
        n1.next = n1;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testTwoNodesCycle() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2; n2.next = n1;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testLongListNoCycle() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 50; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        assertFalse(test.hasCycle(head));
    }

    @Test
    public void testCycleAtTail() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3), n4 = new ListNode(4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n3;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testThreeNodesNoCycle() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3);
        n1.next = n2; n2.next = n3;
        assertFalse(test.hasCycle(n1));
    }

    @Test
    public void testGiantCaseWithCycle() {
        int size = 10000;
        ListNode[] nodes = new ListNode[size];
        for (int i = 0; i < size; i++) nodes[i] = new ListNode(i);
        for (int i = 0; i < size - 1; i++) nodes[i].next = nodes[i + 1];
        nodes[size - 1].next = nodes[0];
        assertTrue(test.hasCycle(nodes[0]));
    }

    @Test
    public void testGiantCaseNoCycle() {
        int size = 10000;
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < size; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        assertFalse(test.hasCycle(head));
    }

    @Test
    public void testCycleInMiddle() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3), n4 = new ListNode(4), n5 = new ListNode(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = n3;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testNullHead() {
        assertFalse(test.hasCycle(null));
    }

    @Test
    public void testSingleNodeNoLoop() {
        assertFalse(test.hasCycle(new ListNode(42)));
    }

    @Test
    public void testSingleNodeSelfLoop() {
        ListNode n = new ListNode(1);
        n.next = n;
        assertTrue(test.hasCycle(n));
    }

    @Test
    public void testNoCycleLength5() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 5; i++) { cur.next = new ListNode(i); cur = cur.next; }
        assertFalse(test.hasCycle(head));
    }

    @Test
    public void testNoCycleLength100() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 100; i++) { cur.next = new ListNode(i); cur = cur.next; }
        assertFalse(test.hasCycle(head));
    }

    @Test
    public void testCycleTailToHead() {
        ListNode[] nodes = new ListNode[10];
        for (int i = 0; i < 10; i++) nodes[i] = new ListNode(i);
        for (int i = 0; i < 9; i++) nodes[i].next = nodes[i + 1];
        nodes[9].next = nodes[0];
        assertTrue(test.hasCycle(nodes[0]));
    }

    @Test
    public void testCycleTailToMid() {
        ListNode[] nodes = new ListNode[10];
        for (int i = 0; i < 10; i++) nodes[i] = new ListNode(i);
        for (int i = 0; i < 9; i++) nodes[i].next = nodes[i + 1];
        nodes[9].next = nodes[5];
        assertTrue(test.hasCycle(nodes[0]));
    }

    @Test
    public void testCycleTailSelfLoop() {
        ListNode[] nodes = new ListNode[10];
        for (int i = 0; i < 10; i++) nodes[i] = new ListNode(i);
        for (int i = 0; i < 9; i++) nodes[i].next = nodes[i + 1];
        nodes[9].next = nodes[9];
        assertTrue(test.hasCycle(nodes[0]));
    }

    @Test
    public void testLong1000WithCycleNearEnd() {
        int size = 1000;
        ListNode[] nodes = new ListNode[size];
        for (int i = 0; i < size; i++) nodes[i] = new ListNode(i);
        for (int i = 0; i < size - 1; i++) nodes[i].next = nodes[i + 1];
        nodes[size - 1].next = nodes[990];
        assertTrue(test.hasCycle(nodes[0]));
    }

    @Test
    public void testLong1000NoCycle() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 1000; i++) { cur.next = new ListNode(i); cur = cur.next; }
        assertFalse(test.hasCycle(head));
    }
}
