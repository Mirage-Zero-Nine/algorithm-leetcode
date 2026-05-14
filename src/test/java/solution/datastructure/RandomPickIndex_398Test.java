package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RandomPickIndex_398Test {

    @Test
    public void testHappyCases() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 3, 3, 3});
        int idx = rp.pick(3);
        assertTrue(idx == 2 || idx == 3 || idx == 4);
    }

    @Test
    public void testEdgeCases() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1});
        assertTrue(rp.pick(1) == 0);
    }

    @Test
    public void testLargeCase() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 3, 4, 5, 1, 1, 1});
        int idx = rp.pick(1);
        assertTrue(idx == 0 || idx == 5 || idx == 6 || idx == 7);
    }

    @Test
    public void testUniqueElements() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{10, 20, 30, 40, 50});
        assertEquals(0, rp.pick(10));
        assertEquals(1, rp.pick(20));
        assertEquals(4, rp.pick(50));
    }

    @Test
    public void testAllSameElements() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{7, 7, 7, 7, 7});
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) seen.add(rp.pick(7));
        // should return indices 0-4
        for (int idx : seen) assertTrue(idx >= 0 && idx <= 4);
    }

    @Test
    public void testPickFirstElement() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{5, 1, 2, 3});
        assertEquals(0, rp.pick(5));
    }

    @Test
    public void testPickLastElement() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 3, 99});
        assertEquals(3, rp.pick(99));
    }

    @Test
    public void testNegativeValues() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{-1, -2, -1, -3, -1});
        int idx = rp.pick(-1);
        assertTrue(idx == 0 || idx == 2 || idx == 4);
    }

    @Test
    public void testMultiplePickCalls() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{1, 2, 1, 2, 1});
        for (int i = 0; i < 50; i++) {
            int idx = rp.pick(1);
            assertTrue(idx == 0 || idx == 2 || idx == 4);
        }
        for (int i = 0; i < 50; i++) {
            int idx = rp.pick(2);
            assertTrue(idx == 1 || idx == 3);
        }
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i % 10;
        RandomPickIndex_398 rp = new RandomPickIndex_398(arr);
        for (int i = 0; i < 100; i++) {
            int idx = rp.pick(0);
            assertTrue(idx >= 0 && idx < 10000);
            assertTrue(arr[idx] == 0);
        }
    }

    @Test
    public void testTwoOccurrences() {
        RandomPickIndex_398 rp = new RandomPickIndex_398(new int[]{3, 5, 3});
        int idx = rp.pick(3);
        assertTrue(idx == 0 || idx == 2);
    }
}
