package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MajorityElement_229Test {

    private final MajorityElement_229 test = new MajorityElement_229();

    @Test
    public void testHappyCases() {
        List<Integer> result = test.majorityElement(new int[]{3, 2, 3});
        assertTrue(result.contains(3));
        List<Integer> result2 = test.majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        assertTrue(result2.contains(1) && result2.contains(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.majorityElement(new int[]{}));
        List<Integer> result = test.majorityElement(new int[]{1});
        assertTrue(result.contains(1));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.majorityElement(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3});
        assertTrue(result.contains(1) && result.contains(2));
    }

    @Test
    public void testSingleElementRepeated() {
        List<Integer> result = test.majorityElement(new int[]{5, 5, 5});
        assertTrue(result.contains(5));
        assertEquals(1, result.size());
    }

    @Test
    public void testTwoElements() {
        List<Integer> result = test.majorityElement(new int[]{1, 2});
        assertTrue(result.contains(1) && result.contains(2));
    }

    @Test
    public void testNoMajority() {
        // [1,2,3] each appears 1 time, n/3 = 1, need > 1 to qualify
        List<Integer> result = test.majorityElement(new int[]{1, 2, 3});
        assertEquals(0, result.size());
    }

    @Test
    public void testAllSameElements() {
        List<Integer> result = test.majorityElement(new int[]{7, 7, 7, 7, 7});
        assertTrue(result.contains(7));
    }

    @Test
    public void testNegativeNumbers() {
        List<Integer> result = test.majorityElement(new int[]{-1, -1, -1, 2, 3});
        assertTrue(result.contains(-1));
    }

    @Test
    public void testTwoMajorities() {
        // [1,1,2,2,3] n=5, n/3=1, need >1 => 1 appears 2 times, 2 appears 2 times
        List<Integer> result = test.majorityElement(new int[]{1, 1, 2, 2, 3});
        assertTrue(result.contains(1) && result.contains(2));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 5000; i++) arr[i] = 42;
        for (int i = 5000; i < 10000; i++) arr[i] = i;
        List<Integer> result = test.majorityElement(arr);
        assertTrue(result.contains(42));
    }

    @Test
    public void testAdditional1() {
        assertEquals(java.util.List.of(1), test.majorityElement(new int[]{1,1,1,2,2,3}));
    }

    @Test
    public void testAdditional2() {
        assertEquals(java.util.List.of(), test.majorityElement(new int[]{1,2,3,4}));
    }

    @Test
    public void testAdditional3() {
        assertEquals(java.util.List.of(2, 1), test.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }

    @Test
    public void testAdditional4() {
        assertEquals(java.util.List.of(0), test.majorityElement(new int[]{0,0,0,1,1,2,2}));
    }

    @Test
    public void testAdditional5() {
        assertEquals(java.util.List.of(-1), test.majorityElement(new int[]{-1,-1,-1,2,2,3}));
    }

    @Test
    public void testAdditional6() {
        assertEquals(java.util.List.of(), test.majorityElement(new int[]{1,1,2,2,3,3}));
    }

    @Test
    public void testAdditional7() {
        assertEquals(java.util.List.of(5), test.majorityElement(new int[]{5}));
    }

    @Test
    public void testAdditional8() {
        assertEquals(java.util.List.of(1, 2), test.majorityElement(new int[]{1,2,1,2,1}));
    }

    @Test
    public void testAdditional9() {
        assertEquals(java.util.List.of(4, 3), test.majorityElement(new int[]{4,4,4,4,3,3,3}));
    }

    @Test
    public void testAdditional10() {
        assertEquals(java.util.List.of(1000000000), test.majorityElement(new int[]{1000000000,1000000000,-1}));
    }
}
