package solution.dfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author BorisMirage
 * Time: 2025/05/06 19:15
 * Created with IntelliJ IDEA
 */

public class Exist79Test {
    private final Exist_79 test = new Exist_79();

    @Test
    public void test() {
        assertFalse(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB")); // revisiting 'B' is not allowed
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ASFDE"));
        assertFalse(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "ABCDZ"));

        assertFalse(test.exist(new char[][]{
                {'A'}
        }, "AA"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        assertTrue(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "AB"));
        assertTrue(test.exist(new char[][]{
                {'A'}
        }, "A"));
        assertFalse(test.exist(new char[][]{
                {'A', 'B'},
                {'C', 'D'}
        }, "ABA"));
    }

    @Test
    public void testEmpty() {
        assertFalse(test.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, ""));
        assertFalse(test.exist(new char[][]{{}}, ""));
        assertFalse(test.exist(new char[][]{{}}, "ASCSADSA"));
        assertFalse(test.exist(new char[][]{}, "A"));
        assertFalse(test.exist(new char[0][0], "A"));
        assertFalse(test.exist(null, "ANY"));
        assertFalse(test.exist(new char[][]{{'A', 'B'}, {'C', 'D'}}, null));
        assertFalse(test.exist(new char[][]{
                {}, {}, {}
        }, "A"));
    }
}