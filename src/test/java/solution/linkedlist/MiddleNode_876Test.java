package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MiddleNode_876Test {

    private final MiddleNode_876 test = new MiddleNode_876();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(3, test.middleNode(build(1, 2, 3, 4, 5)).val);
        assertEquals(4, test.middleNode(build(1, 2, 3, 4, 5, 6)).val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.middleNode(build(1)).val);
        assertEquals(2, test.middleNode(build(1, 2)).val);
    }

    @Test
    public void testLargeCase() {
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i + 1;
        assertEquals(51, test.middleNode(build(vals)).val);
    }

    @Test
    public void testThreeElements() {
        assertEquals(2, test.middleNode(build(1, 2, 3)).val);
    }

    @Test
    public void testFourElements() {
        assertEquals(3, test.middleNode(build(1, 2, 3, 4)).val);
    }

    @Test
    public void testSevenElements() {
        assertEquals(4, test.middleNode(build(1, 2, 3, 4, 5, 6, 7)).val);
    }

    @Test
    public void testEightElements() {
        assertEquals(5, test.middleNode(build(1, 2, 3, 4, 5, 6, 7, 8)).val);
    }

    @Test
    public void testNegativeValues() {
        assertEquals(0, test.middleNode(build(-2, -1, 0, 1, 2)).val);
    }

    @Test
    public void testDuplicateValues() {
        // [5,5,5,5,5] -> middle is 3rd node (val 5)
        assertEquals(5, test.middleNode(build(5, 5, 5, 5, 5)).val);
    }

    @Test
    public void testGiantOddCase() {
        int n = 999;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        assertEquals(500, test.middleNode(build(vals)).val);
    }

    @Test
    public void testGiantEvenCase() {
        int n = 1000;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        assertEquals(501, test.middleNode(build(vals)).val);
    }
}
