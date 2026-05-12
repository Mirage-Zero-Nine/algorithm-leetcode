package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JobScheduling1235Test {

    private final JobScheduling_1235 test = new JobScheduling_1235();

    @Test
    public void testHappyCases() {
        assertEquals(120, test.jobScheduling(new int[]{1, 2, 3, 3}, new int[]{3, 4, 5, 6}, new int[]{50, 10, 40, 70}));
        assertEquals(150, test.jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(50, test.jobScheduling(new int[]{1}, new int[]{2}, new int[]{50}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.jobScheduling(new int[]{1, 1, 1}, new int[]{2, 3, 4}, new int[]{5, 6, 4}));
    }
}
