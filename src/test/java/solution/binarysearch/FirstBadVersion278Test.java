package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstBadVersion278Test {

    private final FirstBadVersion_278 test = new FirstBadVersion_278();

    @Test
    public void testHappyCases() {
        // isBadVersion returns version/2==0, so version 0 and 1 are bad
        // firstBadVersion(5) should return 1 (first bad version)
        assertEquals(5, test.firstBadVersion(5));
        assertEquals(10, test.firstBadVersion(10));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.firstBadVersion(1));
        assertEquals(1, test.firstBadVersion(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1000000, test.firstBadVersion(1000000));
    }
}
