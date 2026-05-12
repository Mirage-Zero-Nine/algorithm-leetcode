package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindClosestElements658Test {

    private final FindClosestElements_658 test = new FindClosestElements_658();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        assertEquals(List.of(1, 2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(1, 2), test.findClosestElements(new int[]{1, 2, 3}, 2, 1));
        assertEquals(List.of(2, 3, 4), test.findClosestElements(new int[]{1, 2, 3, 4, 5}, 3, 5));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(3, 4, 5, 6, 7), test.findClosestElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 5));
    }
}
