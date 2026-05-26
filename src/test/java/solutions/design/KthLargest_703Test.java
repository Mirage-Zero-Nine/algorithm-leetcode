package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthLargest_703Test {

    @Test
    public void testHappyCases() {
        KthLargest_703 kl = new KthLargest_703(3, new int[]{4, 5, 8, 2});
        assertEquals(4, kl.add(3));
        assertEquals(5, kl.add(5));
        assertEquals(5, kl.add(10));
        assertEquals(8, kl.add(9));
        assertEquals(8, kl.add(4));
    }

    @Test
    public void testEdgeCases() {
        KthLargest_703 kl = new KthLargest_703(1, new int[]{});
        assertEquals(1, kl.add(1));
        assertEquals(2, kl.add(2));
    }

    @Test
    public void testLargeCase() {
        KthLargest_703 kl = new KthLargest_703(2, new int[]{1, 2, 3, 4, 5});
        assertEquals(5, kl.add(6));
        assertEquals(6, kl.add(7));
    }

    @Test
    public void testKEqualsArrayLength() {
        KthLargest_703 kl = new KthLargest_703(3, new int[]{1, 2, 3});
        assertEquals(1, kl.add(0));
        assertEquals(2, kl.add(4));
    }

    @Test
    public void testKLargerThanInitialArray() {
        KthLargest_703 kl = new KthLargest_703(4, new int[]{1, 2});
        // heap=[1,2,3], size<k, peek=1
        assertEquals(1, kl.add(3));
        // heap=[0,1,2,3], size=k, peek=0
        assertEquals(0, kl.add(0));
    }

    @Test
    public void testAllDuplicates() {
        KthLargest_703 kl = new KthLargest_703(2, new int[]{5, 5, 5});
        // heap=[5,5], add(5)->poll->[5,5]->peek=5
        assertEquals(5, kl.add(5));
        assertEquals(5, kl.add(4));
        // add(6)->heap=[5,5,6]->poll->[5,6]->peek=5
        assertEquals(5, kl.add(6));
    }

    @Test
    public void testNegativeNumbers() {
        KthLargest_703 kl = new KthLargest_703(2, new int[]{-3, -2, -1});
        // heap=[-2,-1], add(-4)->poll->[-2,-1]->peek=-2
        assertEquals(-2, kl.add(-4));
        // add(0)->heap=[-2,-1,0]->poll->[-1,0]->peek=-1
        assertEquals(-1, kl.add(0));
    }

    @Test
    public void testSingleElementK1() {
        KthLargest_703 kl = new KthLargest_703(1, new int[]{5});
        assertEquals(5, kl.add(3));
        assertEquals(6, kl.add(6));
        assertEquals(6, kl.add(1));
    }

    @Test
    public void testAddSmallerThanKth() {
        KthLargest_703 kl = new KthLargest_703(3, new int[]{10, 20, 30});
        assertEquals(10, kl.add(1));
        assertEquals(10, kl.add(2));
    }

    @Test
    public void testDescendingInput() {
        KthLargest_703 kl = new KthLargest_703(2, new int[]{});
        // heap=[MIN], size<k, peek=MIN
        assertEquals(Integer.MIN_VALUE, kl.add(Integer.MIN_VALUE));
        // heap=[MIN,MIN], size=k, peek=MIN
        assertEquals(Integer.MIN_VALUE, kl.add(Integer.MIN_VALUE));
        // add(0)->heap=[MIN,MIN,0]->poll->[MIN,0]->peek=MIN
        assertEquals(Integer.MIN_VALUE, kl.add(0));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) nums[i] = i;
        KthLargest_703 kl = new KthLargest_703(5, nums);
        // top 5 are 9995..9999, kth largest (5th) = 9995
        assertEquals(9996, kl.add(10000));
        assertEquals(9997, kl.add(10001));
    }
}
