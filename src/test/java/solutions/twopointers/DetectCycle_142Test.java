package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DetectCycle_142Test {

    private final DetectCycle_142 test = new DetectCycle_142();

    @Test
    public void testHappyCases() {
        ListNode n1 = new ListNode(3), n2 = new ListNode(2), n3 = new ListNode(0), n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        assertEquals(2, test.detectCycle(n1).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.detectCycle(null));
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2;
        assertNull(test.detectCycle(n1));
    }

    @Test
    public void testLargeCase() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3);
        n1.next = n2; n2.next = n3; n3.next = n1;
        assertEquals(1, test.detectCycle(n1).val);
    }

    @Test
    public void testSingleNodeNoCycle() {
        assertNull(test.detectCycle(new ListNode(5)));
    }

    @Test
    public void testSingleNodeSelfCycle() {
        ListNode n1 = new ListNode(1);
        n1.next = n1;
        assertEquals(1, test.detectCycle(n1).val);
    }

    @Test
    public void testCycleAtTail() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3), n4 = new ListNode(4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n3;
        assertEquals(3, test.detectCycle(n1).val);
    }

    @Test
    public void testCycleAtHead() {
        ListNode n1 = new ListNode(10), n2 = new ListNode(20), n3 = new ListNode(30);
        n1.next = n2; n2.next = n3; n3.next = n1;
        assertEquals(10, test.detectCycle(n1).val);
    }

    @Test
    public void testLongListNoCycle() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 100; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        assertNull(test.detectCycle(head));
    }

    @Test
    public void testTwoNodesCycle() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2; n2.next = n1;
        assertEquals(1, test.detectCycle(n1).val);
    }

    @Test
    public void testGiantCaseWithCycle() {
        int size = 10000;
        ListNode[] nodes = new ListNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new ListNode(i);
        }
        for (int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[size - 1].next = nodes[5000];
        assertEquals(5000, test.detectCycle(nodes[0]).val);
    }

    @Test
    public void testNegativeValues() {
        ListNode n1 = new ListNode(-1), n2 = new ListNode(-2), n3 = new ListNode(-3);
        n1.next = n2; n2.next = n3; n3.next = n2;
        assertEquals(-2, test.detectCycle(n1).val);
    }

    @Test public void testTwoNodeNoCycle() { ListNode a=new ListNode(1), b=new ListNode(2); a.next=b; assertNull(test.detectCycle(a)); }
    @Test public void testCycleStartsAtSecondOfFour() { ListNode a=new ListNode(1),b=new ListNode(2),c=new ListNode(3),d=new ListNode(4); a.next=b;b.next=c;c.next=d;d.next=b;assertEquals(2,test.detectCycle(a).val); }
    @Test public void testCycleStartsAtTail() { ListNode a=new ListNode(1),b=new ListNode(2),c=new ListNode(3);a.next=b;b.next=c;c.next=c;assertEquals(3,test.detectCycle(a).val); }
    @Test public void testLongAcyclic() { ListNode h=new ListNode(0),p=h; for(int i=1;i<1000;i++){p.next=new ListNode(i);p=p.next;} assertNull(test.detectCycle(h)); }
    @Test public void testLongCycleNearHead() { ListNode h=new ListNode(0),p=h,entry=null; for(int i=1;i<100;i++){p.next=new ListNode(i);p=p.next;if(i==1)entry=p;} p.next=entry; assertEquals(1,test.detectCycle(h).val); }
    @Test public void testSelfCycleNegative() { ListNode n=new ListNode(-4);n.next=n;assertEquals(-4,test.detectCycle(n).val); }
    @Test public void testCycleAfterLongPrefix() { ListNode h=new ListNode(0),p=h; for(int i=1;i<5;i++){p.next=new ListNode(i);p=p.next;} ListNode e=new ListNode(99);p.next=e;e.next=e;assertEquals(99,test.detectCycle(h).val); }
    @Test public void testRepeatedCall() { assertNull(test.detectCycle(new ListNode(8))); ListNode n=new ListNode(9);n.next=n;assertEquals(9,test.detectCycle(n).val); }
    @Test public void testDistinctEqualValuesIdentity() { ListNode a=new ListNode(1),b=new ListNode(1);a.next=b;b.next=b;assertEquals(b,test.detectCycle(a)); }
}
