package solution.array;

import org.junit.jupiter.api.Test;

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
}
