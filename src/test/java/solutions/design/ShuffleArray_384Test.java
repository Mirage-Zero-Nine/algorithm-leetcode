package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShuffleArray_384Test {

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

    @Test
    public void testShufflePreservesElements() {
        int[] original = {10, 20, 30, 40, 50};
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        int[] shuffled = sa.shuffle();
        int[] sortedShuffled = shuffled.clone();
        Arrays.sort(sortedShuffled);
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, sortedShuffled);
    }

    @Test
    public void testResetAfterShuffle() {
        int[] original = {1, 2, 3, 4};
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        sa.shuffle();
        assertArrayEquals(new int[]{1, 2, 3, 4}, sa.reset());
    }

    @Test
    public void testMultipleShuffles() {
        int[] original = {1, 2, 3, 4, 5};
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        for (int i = 0; i < 10; i++) {
            int[] shuffled = sa.shuffle();
            assertEquals(5, shuffled.length);
            int[] sorted = shuffled.clone();
            Arrays.sort(sorted);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
        }
    }

    @Test
    public void testShuffleDoesNotModifyOriginal() {
        int[] original = {5, 4, 3, 2, 1};
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        sa.shuffle();
        sa.shuffle();
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, sa.reset());
    }

    @Test
    public void testTwoElements() {
        ShuffleArray_384 sa = new ShuffleArray_384(new int[]{1, 2});
        int[] shuffled = sa.shuffle();
        assertEquals(2, shuffled.length);
        Arrays.sort(shuffled);
        assertArrayEquals(new int[]{1, 2}, shuffled);
    }

    @Test
    public void testNegativeValues() {
        int[] original = {-3, -1, 0, 1, 3};
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        int[] shuffled = sa.shuffle();
        assertEquals(5, shuffled.length);
        int[] sorted = shuffled.clone();
        Arrays.sort(sorted);
        assertArrayEquals(new int[]{-3, -1, 0, 1, 3}, sorted);
    }

    @Test
    public void testGiantCase() {
        int[] original = new int[10000];
        for (int i = 0; i < 10000; i++) original[i] = i;
        ShuffleArray_384 sa = new ShuffleArray_384(original);
        int[] shuffled = sa.shuffle();
        assertEquals(10000, shuffled.length);
        int[] sorted = shuffled.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, sorted[i]);
        }
    }
}
