package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.ArrayReaderHelper;
import org.junit.jupiter.api.Test;

public class Search702Test {

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
}
