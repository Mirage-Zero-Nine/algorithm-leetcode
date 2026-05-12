package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShuffleArray384Test {

    @Test
    public void testHappyCases() {
        ShuffleArray_384 sa = new ShuffleArray_384(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{1, 2, 3}, sa.reset());
        int[] shuffled = sa.shuffle();
        assertEquals(3, shuffled.length);
    }

    @Test
    public void testEdgeCases() {
        ShuffleArray_384 sa = new ShuffleArray_384(new int[]{1});
        assertArrayEquals(new int[]{1}, sa.shuffle());
        assertArrayEquals(new int[]{1}, sa.reset());
    }

    @Test
    public void testLargeCase() {
        ShuffleArray_384 sa = new ShuffleArray_384(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sa.reset());
        int[] shuffled = sa.shuffle();
        assertEquals(5, shuffled.length);
    }
}
