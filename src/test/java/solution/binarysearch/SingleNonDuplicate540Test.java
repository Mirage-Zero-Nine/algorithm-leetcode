package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SingleNonDuplicate540Test {

    private final SingleNonDuplicate_540 test = new SingleNonDuplicate_540();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        assertEquals(10, test.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.singleNonDuplicate(new int[]{1}));
        assertEquals(1, test.singleNonDuplicate(new int[]{1, 2, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 6, 7, 7}));
    }
}
