package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfSubarrays1248Test {

    private final NumberOfSubarrays_1248 test = new NumberOfSubarrays_1248();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        assertEquals(0, test.numberOfSubarrays(new int[]{2, 4, 6}, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numberOfSubarrays(new int[]{1}, 1));
        assertEquals(0, test.numberOfSubarrays(new int[]{2}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }
}
