package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingElement1060Test {

    private final MissingElement_1060 test = new MissingElement_1060();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.missingElement(new int[]{4, 7, 9, 10}, 1));
        assertEquals(8, test.missingElement(new int[]{4, 7, 9, 10}, 3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(6, test.missingElement(new int[]{1, 2, 4}, 3));
        assertEquals(2, test.missingElement(new int[]{1, 3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(51, test.missingElement(new int[]{1, 100, 1000, 100000}, 50));
    }
}
