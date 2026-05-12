package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KthLargestNumber_1985Test {
    private final KthLargestNumber_1985 solution = new KthLargestNumber_1985();

    @Test
    void testBasic() {
        assertEquals("10", solution.kthLargestNumber(new String[]{"3", "6", "7", "10"}, 1));
        assertEquals("7", solution.kthLargestNumber(new String[]{"3", "6", "7", "10"}, 2));
    }

    @Test
    void testDuplicates() {
        assertEquals("0", solution.kthLargestNumber(new String[]{"0", "0"}, 2));
        assertEquals("1", solution.kthLargestNumber(new String[]{"1", "1"}, 2));
    }

    @Test
    void testLargeNumbers() {
        assertEquals("87236929298425799136", solution.kthLargestNumber(new String[]{"683339452288515879", "87236929298425799136", "4805719838"}, 1));
    }

    @Test
    void testQuickSelect() {
        assertEquals("10", solution.kthLargestNumberQuickSelect(new String[]{"3", "6", "7", "10"}, 1));
        assertEquals("2", solution.kthLargestNumberQuickSelect(new String[]{"1", "1", "2"}, 1));
    }

    @Test
    void testSingleElement() {
        assertEquals("5", solution.kthLargestNumber(new String[]{"5"}, 1));
    }
}
