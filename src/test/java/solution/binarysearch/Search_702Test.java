package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.ArrayReaderHelper;
import org.junit.jupiter.api.Test;

public class Search_702Test {

    private final Search_702 test = new Search_702();

    @Test
    public void testHappyCases() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{-1, 0, 3, 5, 9, 12});
        assertEquals(4, test.search(reader, 9));
        assertEquals(5, test.search(reader, 12));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{-1, 0, 3, 5, 9, 12});
        assertEquals(-1, test.search(reader, 2));
    }

    @Test
    public void testLargeCase() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(9, test.search(reader, 10));
    }

    @Test
    public void testTargetAtStart() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{-5, -2, 0, 4, 9});
        assertEquals(0, test.search(reader, -5));
    }

    @Test
    public void testTargetLessThanMinimum() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{3, 6, 9});
        assertEquals(-1, test.search(reader, 1));
    }

    @Test
    public void testTargetGreaterThanMaximum() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{3, 6, 9});
        assertEquals(-1, test.search(reader, 100));
    }

    @Test
    public void testSingleElementFound() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{42});
        assertEquals(0, test.search(reader, 42));
    }

    @Test
    public void testSingleElementNotFound() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{42});
        assertEquals(-1, test.search(reader, 41));
    }

    @Test
    public void testNegativeNumbers() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{-20, -10, -5, -1});
        assertEquals(2, test.search(reader, -5));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[5000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i * 2;
        }
        ArrayReaderHelper reader = new ArrayReaderHelper(nums);
        assertEquals(4321, test.search(reader, 8642));
    }
}
