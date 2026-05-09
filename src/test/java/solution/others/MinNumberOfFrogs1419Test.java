package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinNumberOfFrogs1419Test {

    private final MinNumberOfFrogs_1419 test = new MinNumberOfFrogs_1419();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minNumberOfFrogs("croak"));
        assertEquals(-1, test.minNumberOfFrogs("crcoakak"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.minNumberOfFrogs("croakcroak"));
        assertEquals(-1, test.minNumberOfFrogs("cr"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.minNumberOfFrogs("croakcroakcroak"));
    }
}
