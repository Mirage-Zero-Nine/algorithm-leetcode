package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinDistance72Test {

    private final MinDistance_72 test = new MinDistance_72();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.minDistance("horse", "ros"));
        assertEquals(5, test.minDistance("intention", "execution"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minDistance("abc", "abc"));
        assertEquals(3, test.minDistance("abc", ""));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.minDistance("zoologicoarchaeologist", "zoogeologist"));
    }
}
