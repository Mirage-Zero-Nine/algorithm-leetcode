package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SumOfBeauties_2012}.
 */
public class SumOfBeauties_2012Test {

    private final SumOfBeauties_2012 solver = new SumOfBeauties_2012();

    @Test
    public void testLeetCodeExample1() {
        // nums = [1,2,3]
        // i=1: maxLeft=1, minRight=3. 1<2<3 → beauty=2
        // total = 2
        int[] nums = {1, 2, 3};
        assertEquals(2, solver.sumOfBeauties(nums));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [2,4,6,4]
        // i=1: maxLeft=2, minRight=min(6,4)=4. 2<4 but 4<4 false → not 2. 2<4<6 → beauty=1
        // i=2: maxLeft=max(2,4)=4, minRight=4. 4<6 but 6<4 false → not 2. 4<6 false → not 1. beauty=0
        // total = 1
        int[] nums = {2, 4, 6, 4};
        assertEquals(1, solver.sumOfBeauties(nums));
    }

    @Test
    public void testStrictlyIncreasing() {
        // nums = [1,2,3,4,5]
        // i=1: maxLeft=1, minRight=3. 1<2<3 → beauty=2
        // i=2: maxLeft=2, minRight=4. 2<3<4 → beauty=2
        // i=3: maxLeft=3, minRight=5. 3<4<5 → beauty=2
        // total = 6
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(6, solver.sumOfBeauties(nums));
    }

    @Test
    public void testStrictlyIncreasing2() {
        // nums = [1,3,5,7,9]
        // i=1: maxLeft=1, minRight=min(5,7,9)=5. 1<3<5 → beauty=2
        // i=2: maxLeft=3, minRight=min(7,9)=7. 3<5<7 → beauty=2
        // i=3: maxLeft=5, minRight=9. 5<7<9 → beauty=2
        // total = 6
        int[] nums = {1, 3, 5, 7, 9};
        assertEquals(6, solver.sumOfBeauties(nums));
    }

    @Test
    public void testDecreasing() {
        // nums = [5,4,3,2,1]
        // i=1: maxLeft=5, 5<4? No. beauty=0
        // i=2: maxLeft=5, beauty=0
        // i=3: maxLeft=5, beauty=0
        // total = 0
        int[] nums = {5, 4, 3, 2, 1};
        assertEquals(0, solver.sumOfBeauties(nums));
    }

    @Test
    public void testVShaped() {
        // nums = [3,1,2]
        // i=1: maxLeft=3, minRight=2. 3<1? No. 3<1<2? No. beauty=0
        // total = 0
        int[] nums = {3, 1, 2};
        assertEquals(0, solver.sumOfBeauties(nums));
    }

    @Test
    public void testMountainShaped() {
        // nums = [1,5,3]
        // i=1: maxLeft=1, minRight=3. 1<5<3? No. 1<5<3? No. beauty=0
        // total = 0
        int[] nums = {1, 5, 3};
        assertEquals(0, solver.sumOfBeauties(nums));
    }

    @Test
    public void testWithDuplicates() {
        // nums = [2,2,2]
        // i=1: maxLeft=2, minRight=2. 2<2? No. 2<2<2? No. beauty=0
        // total = 0
        int[] nums = {2, 2, 2};
        assertEquals(0, solver.sumOfBeauties(nums));
    }

    @Test
    public void testMixed() {
        // nums = [1,4,3,2,5]
        // i=1: maxLeft=1, minRight=min(3,2,5)=2. 1<4<2? No. 1<4<3? No. beauty=0
        // i=2: maxLeft=max(1,4)=4, minRight=min(2,5)=2. 4<3? No. 4<3<2? No. beauty=0
        // i=3: maxLeft=max(4,3)=4, minRight=5. 4<2? No. 3<2<5? No. beauty=0
        // total = 0
        int[] nums = {1, 4, 3, 2, 5};
        assertEquals(0, solver.sumOfBeauties(nums));
    }

    @Test
    public void testPartialBeauty() {
        // nums = [1,2,3,4,3]
        // i=1: maxLeft=1, minRight=min(3,4,3)=3. 1<2<3 → beauty=2
        // i=2: maxLeft=2, minRight=min(4,3)=3. 2<3<3? No. 2<3<4 → beauty=1
        // i=3: maxLeft=3, minRight=3. 3<4<3? No. 3<4<3? No. beauty=0
        // total = 3
        int[] nums = {1, 2, 3, 4, 3};
        assertEquals(3, solver.sumOfBeauties(nums));
    }

    @Test
    public void testGiantStrictlyIncreasing() {
        // 1000 elements strictly increasing → all middle elements score 2
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        // beauty = 2 * (1000 - 2) = 1996
        assertEquals(1996, solver.sumOfBeauties(nums));
    }
}
