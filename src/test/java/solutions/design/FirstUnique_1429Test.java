package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstUnique_1429Test {

    @Test
    public void testHappyCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{2, 3, 5});
        assertEquals(2, fu.showFirstUnique());
        fu.add(5);
        assertEquals(2, fu.showFirstUnique());
        fu.add(2);
        assertEquals(3, fu.showFirstUnique());
        fu.add(3);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 1});
        assertEquals(-1, fu.showFirstUnique());
        FirstUnique_1429 fu2 = new FirstUnique_1429(new int[]{1});
        assertEquals(1, fu2.showFirstUnique());
    }

    @Test
    public void testLargeCase() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 3, 4, 5});
        assertEquals(1, fu.showFirstUnique());
        fu.add(1); fu.add(2); fu.add(3); fu.add(4);
        assertEquals(5, fu.showFirstUnique());
    }

    @Test
    public void testEmptyInitialization() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{});
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testUniqueAfterAddingNewValue() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{7, 7, 8, 8});
        assertEquals(-1, fu.showFirstUnique());
        fu.add(9);
        assertEquals(9, fu.showFirstUnique());
    }

    @Test
    public void testFrontUniqueBecomesDuplicateThenNextAppears() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{10, 11, 12});
        assertEquals(10, fu.showFirstUnique());
        fu.add(10);
        assertEquals(11, fu.showFirstUnique());
    }

    @Test
    public void testQueueOrderPreservedForUniques() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{4, 5, 6});
        fu.add(4);
        assertEquals(5, fu.showFirstUnique());
        fu.add(5);
        assertEquals(6, fu.showFirstUnique());
    }

    @Test
    public void testNegativeNumbersHandled() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{-1, -2, -1});
        assertEquals(-2, fu.showFirstUnique());
        fu.add(-2);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testRepeatedShowDoesNotChangeResultWhenStable() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 1, 3});
        assertEquals(2, fu.showFirstUnique());
        assertEquals(2, fu.showFirstUnique());
    }

    @Test
    public void testGiantCase() {
        int n = 5000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        FirstUnique_1429 fu = new FirstUnique_1429(nums);
        assertEquals(0, fu.showFirstUnique());
        for (int i = 0; i < n - 1; i++) {
            fu.add(i);
        }
        assertEquals(n - 1, fu.showFirstUnique());
    }
}
