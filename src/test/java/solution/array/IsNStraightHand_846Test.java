package solution.array;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link IsNStraightHand_846}.
 */
public class IsNStraightHand_846Test {

    private final IsNStraightHand_846 solver = new IsNStraightHand_846();

    @Test
    public void testLeetCodeExample1() {
        // hand = [1,2,3,6,2,3,4,7,8], W = 3 → true
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
    }

    @Test
    public void testLeetCodeExample2() {
        // hand = [1,2,3,4,5], W = 4 → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true
        assertFalse(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
    }

    @Test
    public void testLeetCodeExample3() {
        // hand = [1,2,3,4,5], W = 1 → true
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testEmptyHand() {
        assertTrue(solver.isNStraightHand(new int[]{}, 1));
    }

    @Test
    public void testSingleCardW1() {
        assertTrue(solver.isNStraightHand(new int[]{1}, 1));
    }

    @Test
    public void testSingleCardW2() {
        assertFalse(solver.isNStraightHand(new int[]{1}, 2));
    }

    @Test
    public void testAllSameCards() {
        // hand = [1,1,1,1], W = 2 → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true
        assertFalse(solver.isNStraightHand(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testAllSameCardsW1() {
        // hand = [1,1,1,1], W = 1 → true
        assertTrue(solver.isNStraightHand(new int[]{1, 1, 1, 1}, 1));
    }

    @Test
    public void testPerfectStraight() {
        // hand = [1,2,3,4,5,6], W = 3 → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true (can't split evenly)
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3));
    }

    @Test
    public void testPerfectStraightW2() {
        // hand = [1,2,3,4,5,6], W = 2 → true
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 2));
    }

    @Test
    public void testPerfectStraightW3() {
        // hand = [1,2,3,4,5,6], W = 3 → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true (6 cards, W=3, need 2 groups)
        // Groups: [1,2,3] and [4,5,6] → true
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3));
    }

    @Test
    public void testGappedHand() {
        // hand = [1,3,5,7], W = 2 → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true
        assertFalse(solver.isNStraightHand(new int[]{1, 3, 5, 7}, 2));
    }

    @Test
    public void testWithDuplicates() {
        // hand = [5,5,4,3,2,1], W = 3 → true (groups: [3,4,5] and [1,2,5]... wait
        // Sorted: [1,2,3,4,5,5]
        // Group 1: [1,2,3], remaining: [4,5,5]
        // Group 2: [4,5,?] → need 6, not available → solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6}, 3) → true
        assertFalse(solver.isNStraightHand(new int[]{5, 5, 4, 3, 2, 1}, 3));
    }

    @Test
    public void testWithDuplicatesValid() {
        // hand = [1,2,3,3,4,5], W = 3 → true ([1,2,3] and [3,4,5])
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 3, 4, 5}, 3));
    }

    @Test
    public void testGroupSizeOne_AlwaysTrue() {
        // groupSize=1 means each card is its own group, always valid
        assertTrue(solver.isNStraightHand(new int[]{9, 3, 7, 1, 100}, 1));
    }

    @Test
    public void testHandSizeNotMultipleOfGroupSize() {
        // 7 cards cannot be evenly divided into groups of 3
        assertFalse(solver.isNStraightHand(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    @Test
    public void testGroupSizeLargerThanHand() {
        // groupSize > hand.length → impossible
        assertFalse(solver.isNStraightHand(new int[]{1, 2, 3}, 5));
    }

    @Test
    public void testDuplicatesFormingMultipleValidGroups() {
        // [1,2,3,1,2,3] gs=3 → two groups [1,2,3] and [1,2,3]
        assertTrue(solver.isNStraightHand(new int[]{1, 2, 3, 1, 2, 3}, 3));
    }

    @Test
    public void testGapInSequence() {
        // [1,2,4,5,6,7] gs=3 → gap between 2 and 4 prevents valid grouping
        assertFalse(solver.isNStraightHand(new int[]{1, 2, 4, 5, 6, 7}, 3));
    }

    @Test
    public void testAllSameValueGroupSizeGreaterThanOne() {
        // [5,5,5,5,5,5] gs=2 → no consecutive cards available
        assertFalse(solver.isNStraightHand(new int[]{5, 5, 5, 5, 5, 5}, 2));
    }

    @Test
    public void testNegativeCardValues() {
        // Negative values: [-3,-2,-1,0,1,2] gs=3 → [-3,-2,-1] and [0,1,2]
        assertTrue(solver.isNStraightHand(new int[]{-3, -2, -1, 0, 1, 2}, 3));
    }

    @Test
    public void testNegativeCardValuesInvalid() {
        // [-3,-2,0,1,2,3] gs=3 → gap between -2 and 0
        assertFalse(solver.isNStraightHand(new int[]{-3, -2, 0, 1, 2, 3}, 3));
    }

    @Test
    public void testLargeHandConstructedValid() {
        // Construct 1000 cards that form valid groups of size 5 (200 groups)
        // Each group is [i*5, i*5+1, i*5+2, i*5+3, i*5+4]
        int groupSize = 5;
        int numGroups = 200;
        int[] hand = new int[numGroups * groupSize];
        Random rng = new Random(42L);
        for (int g = 0; g < numGroups; g++) {
            int base = g * groupSize;
            for (int i = 0; i < groupSize; i++) {
                hand[g * groupSize + i] = base + i;
            }
        }
        // Shuffle to make it non-trivial
        for (int i = hand.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = hand[i];
            hand[i] = hand[j];
            hand[j] = tmp;
        }
        assertTrue(solver.isNStraightHand(hand, groupSize));
    }

    @Test
    public void testLargeHandInvalid() {
        // 999 cards cannot form groups of 5 (not divisible)
        int[] hand = new int[999];
        for (int i = 0; i < 999; i++) {
            hand[i] = i;
        }
        assertFalse(solver.isNStraightHand(hand, 5));
    }
}
