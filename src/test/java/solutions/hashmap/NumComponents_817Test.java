package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class NumComponents_817Test {

    private final NumComponents_817 test = new NumComponents_817();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numComponents(build(0, 1, 2, 3), new int[]{0, 1, 3}));
        assertEquals(2, test.numComponents(build(0, 1, 2, 3, 4), new int[]{0, 3, 1, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numComponents(build(0), new int[]{0}));
        assertEquals(0, test.numComponents(build(0, 1, 2), new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.numComponents(build(0, 1, 2, 3, 4, 5, 6), new int[]{0, 1, 3, 5, 6}));
    }

    @Test
    public void testAllInG() {
        assertEquals(1, test.numComponents(build(0, 1, 2, 3), new int[]{0, 1, 2, 3}));
    }

    @Test
    public void testNoneInG() {
        assertEquals(0, test.numComponents(build(0, 1, 2, 3), new int[]{5, 6, 7}));
    }

    @Test
    public void testAlternating() {
        // 0,1,2,3,4 with G={0,2,4} -> each is isolated -> 3 components
        assertEquals(3, test.numComponents(build(0, 1, 2, 3, 4), new int[]{0, 2, 4}));
    }

    @Test
    public void testSingleComponentAtEnd() {
        assertEquals(1, test.numComponents(build(0, 1, 2, 3, 4), new int[]{3, 4}));
    }

    @Test
    public void testSingleComponentAtStart() {
        assertEquals(1, test.numComponents(build(0, 1, 2, 3, 4), new int[]{0, 1}));
    }

    @Test
    public void testTwoSeparateComponents() {
        assertEquals(2, test.numComponents(build(0, 1, 2, 3, 4), new int[]{0, 1, 3, 4}));
    }

    @Test
    public void testGiantCase() {
        // Build list 0..99, G = all even numbers -> each even is isolated (odd gaps) -> 50 components
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i;
        int[] g = new int[50];
        for (int i = 0; i < 50; i++) g[i] = i * 2;
        assertEquals(50, test.numComponents(build(vals), g));
    }
}
