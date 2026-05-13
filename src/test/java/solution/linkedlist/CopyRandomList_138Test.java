package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.randomnode.Node;
import org.junit.jupiter.api.Test;

public class CopyRandomList_138Test {

    private final CopyRandomList_138 test = new CopyRandomList_138();

    @Test
    public void testHappyCases() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        n1.next = n2; n1.random = n2; n2.random = n2;
        Node copy = test.copyRandomList(n1);
        assertEquals(1, copy.val);
        assertEquals(2, copy.next.val);
        assertNotSame(n1, copy);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.copyRandomList(null));
        Node single = new Node(); single.val = 5;
        Node copy = test.copyRandomList(single);
        assertEquals(5, copy.val);
        assertNotSame(single, copy);
    }

    @Test
    public void testLargeCase() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        Node n3 = new Node(); n3.val = 3;
        n1.next = n2; n2.next = n3;
        n1.random = n3; n2.random = n1; n3.random = null;
        Node copy = test.copyRandomList(n1);
        assertEquals(1, copy.val);
        assertEquals(3, copy.random.val);
        assertNotSame(n1, copy);
    }

    @Test
    public void testSingleNodeSelfRandom() {
        Node n = new Node(); n.val = 7; n.random = n;
        Node copy = test.copyRandomList(n);
        assertEquals(7, copy.val);
        assertEquals(copy, copy.random);
        assertNotSame(n, copy);
    }

    @Test
    public void testAllRandomsNull() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        n1.next = n2;
        Node copy = test.copyRandomList(n1);
        assertEquals(1, copy.val);
        assertEquals(2, copy.next.val);
        assertNull(copy.random);
        assertNull(copy.next.random);
    }

    @Test
    public void testRandomPointsToHead() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        Node n3 = new Node(); n3.val = 3;
        n1.next = n2; n2.next = n3;
        n1.random = n1; n2.random = n1; n3.random = n1;
        Node copy = test.copyRandomList(n1);
        assertEquals(copy, copy.random);
        assertEquals(copy, copy.next.random);
        assertEquals(copy, copy.next.next.random);
    }

    @Test
    public void testRandomPointsToTail() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        Node n3 = new Node(); n3.val = 3;
        n1.next = n2; n2.next = n3;
        n1.random = n3; n2.random = n3; n3.random = n3;
        Node copy = test.copyRandomList(n1);
        assertEquals(copy.next.next, copy.random);
        assertEquals(copy.next.next, copy.next.random);
    }

    @Test
    public void testTwoNodesCrossRandom() {
        Node n1 = new Node(); n1.val = 10;
        Node n2 = new Node(); n2.val = 20;
        n1.next = n2; n1.random = n2; n2.random = n1;
        Node copy = test.copyRandomList(n1);
        assertEquals(20, copy.random.val);
        assertEquals(10, copy.next.random.val);
        assertNotSame(n1, copy.next.random);
    }

    @Test
    public void testDeepCopyIndependence() {
        Node n1 = new Node(); n1.val = 1;
        Node n2 = new Node(); n2.val = 2;
        n1.next = n2; n1.random = n2;
        Node copy = test.copyRandomList(n1);
        // Modify original
        n1.val = 99;
        assertEquals(1, copy.val); // copy unaffected
    }

    @Test
    public void testGiantCase() {
        int size = 100;
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) { nodes[i] = new Node(); nodes[i].val = i; }
        for (int i = 0; i < size - 1; i++) nodes[i].next = nodes[i + 1];
        // random points to mirror: i -> size-1-i
        for (int i = 0; i < size; i++) nodes[i].random = nodes[size - 1 - i];

        Node copy = test.copyRandomList(nodes[0]);
        Node cur = copy;
        for (int i = 0; i < size; i++) {
            assertEquals(i, cur.val);
            assertEquals(size - 1 - i, cur.random.val);
            assertNotSame(nodes[i], cur);
            cur = cur.next;
        }
        assertNull(cur);
    }
}
