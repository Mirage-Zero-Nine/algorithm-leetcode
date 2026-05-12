package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindLatestStep_1562}.
 */
public class FindLatestStep_1562Test {

    private final FindLatestStep_1562 solver = new FindLatestStep_1562();

    @Test
    public void testLeetCodeExample1() {
        // arr = [1,5,4,2,3], m = 2
        // Step 1: [1,0,0,0,0] → groups: [1]
        // Step 2: [1,0,0,0,1] → groups: [1], [1]
        // Step 3: [1,0,0,1,1] → groups: [1], [2]
        // Step 4: [1,1,0,1,1] → groups: [2], [2]
        // Step 5: [1,1,1,1,1] → groups: [5]
        // Latest with group of 2: step 4
        int[] arr = {1, 5, 4, 2, 3};
        assertEquals(4, solver.findLatestStep(arr, 2));
    }

    @Test
    public void testLeetCodeExample2() {
        // arr = [3,1,5,4,2], m = 2
        int[] arr = {3, 1, 5, 4, 2};
        assertEquals(-1, solver.findLatestStep(arr, 2));
    }

    @Test
    public void testMEqualsN() {
        int[] arr = {1, 2, 3};
        assertEquals(3, solver.findLatestStep(arr, 3));
    }

    @Test
    public void testMEquals1() {
        // arr = [1,5,4,2,3], m = 1
        int[] arr = {1, 5, 4, 2, 3};
        assertEquals(3, solver.findLatestStep(arr, 1));
    }

    @Test
    public void testSingleElement() {
        int[] arr = {1};
        assertEquals(1, solver.findLatestStep(arr, 1));
    }

    @Test
    public void testTwoElements() {
        int[] arr = {1, 2};
        assertEquals(2, solver.findLatestStep(arr, 2));
    }

    @Test
    public void testTwoElementsM1() {
        int[] arr = {1, 2};
        assertEquals(1, solver.findLatestStep(arr, 1));
    }

    @Test
    public void testMTooLarge() {
        int[] arr = {1, 2, 3};
        assertEquals(-1, solver.findLatestStep(arr, 4));
    }

    @Test
    public void testGroupFormsThenDisappears() {
        // arr = [1,3,2], m = 1
        // Step 1: [1,0,0] → [1]
        // Step 2: [1,0,1] → [1], [1]
        // Step 3: [1,1,1] → [3]
        // Latest with group of 1: step 2
        int[] arr = {1, 3, 2};
        assertEquals(2, solver.findLatestStep(arr, 1));
    }

    @Test
    public void testLargeM() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(5, solver.findLatestStep(arr, 5));
    }

    @Test
    public void testReverseOrder() {
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(5, solver.findLatestStep(arr, 5));
    }

    @Test
    public void testMiddleGroup() {
        // arr = [3,2,1,4,5], m = 3
        // Step 1: [0,0,1,0,0] → [1]
        // Step 2: [0,1,1,0,0] → [2]
        // Step 3: [1,1,1,0,0] → [3]
        // Step 4: [1,1,1,1,0] → [4]
        // Step 5: [1,1,1,1,1] → [5]
        // Latest with group of 3: step 3
        int[] arr = {3, 2, 1, 4, 5};
        assertEquals(3, solver.findLatestStep(arr, 3));
    }
}
