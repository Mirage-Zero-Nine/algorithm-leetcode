package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MergeStones_1000Test {

    private final MergeStones_1000 test = new MergeStones_1000();

    @Test
    public void testHappyCases() {
        assertEquals(20, test.mergeStones(new int[]{3, 2, 4, 1}, 2));
        assertEquals(25, test.mergeStones(new int[]{3, 5, 1, 2, 6}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.mergeStones(new int[]{3, 2, 4, 1}, 3));
        assertEquals(0, test.mergeStones(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(49, test.mergeStones(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, test.mergeStones(new int[]{5}, 3));
    }

    @Test
    public void testTwoElementsK2() {
        assertEquals(3, test.mergeStones(new int[]{1, 2}, 2));
    }

    @Test
    public void testThreeElementsK3() {
        assertEquals(6, test.mergeStones(new int[]{1, 2, 3}, 3));
    }

    @Test
    public void testImpossibleCase() {
        assertEquals(-1, test.mergeStones(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    public void testAllOnes() {
        assertEquals(8, test.mergeStones(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testK2FourElements() {
        assertEquals(24, test.mergeStones(new int[]{4, 3, 3, 2}, 2));
    }

    @Test
    public void testGiantCase() {
        assertEquals(90, test.mergeStones(new int[]{6, 4, 4, 6, 2, 4, 6}, 2));
    }

    @ParameterizedTest(name = "merge {0} with k={1}")
    @CsvSource({"'1,1,1',2,5", "'1,2,3,4',2,19", "'2,2,2,2',2,16", "'1,2,3,4,5',2,33", "'1,1,1,1,1',3,8", "'2,3,4',3,9", "'1,2,3,4,5,6',3,-1", "'5,1,1,5',2,21", "'1,3,2,4',2,20", "'2,1,4,3,5,2',2,44"})
    public void testAdditionalMergeOrders(String encoded, int k, int expected) {
        assertEquals(expected, test.mergeStones(parse(encoded), k));
    }

    private static int[] parse(String encoded) {
        String[] values = encoded.split(","); int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) result[i] = Integer.parseInt(values[i]);
        return result;
    }
}
